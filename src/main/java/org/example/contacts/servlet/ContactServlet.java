package org.example.contacts.servlet;

import org.example.contacts.dao.ContactDao;
import org.example.contacts.model.Contact;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/contacts")
public class ContactServlet extends HttpServlet {
    private ContactDao contactDao = new ContactDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "edit":
                showEditForm(req, resp);
                break;
            case "delete":
                deleteContact(req, resp);
                break;
            case "list":
            default:
                listContacts(req, resp);
                break;
        }
    }

    private void listContacts(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Contact> contacts = contactDao.getAllContacts();
        req.setAttribute("contacts", contacts);
        req.getRequestDispatcher("/contacts.jsp").forward(req, resp);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Contact contact = contactDao.getContactById(id);
        req.setAttribute("contact", contact);
        req.getRequestDispatcher("/edit_contact.jsp").forward(req, resp);
    }

    private void deleteContact(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        contactDao.deleteContact(id);
        resp.sendRedirect("/contacts");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "add";
        }

        switch (action) {
            case "update":
                updateContact(req, resp);
                break;
            case "add":
            default:
                addContact(req, resp);
                break;
        }
    }

    private void addContact(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");

        Contact contact = new Contact();
        contact.setName(name);
        contact.setEmail(email);

        contactDao.addContact(contact);
        resp.sendRedirect("/contacts");
    }

    private void updateContact(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String email = req.getParameter("email");

        Contact contact = new Contact();
        contact.setId(id);
        contact.setName(name);
        contact.setEmail(email);

        contactDao.updateContact(contact);
        resp.sendRedirect("/contacts");
    }
}
