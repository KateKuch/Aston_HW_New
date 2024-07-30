package org.example.contacts.dao;

import org.example.contacts.model.Contact;
import  org.example.contacts.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDao {

    public List<Contact> getAllContacts() {
        List<Contact> contacts = new ArrayList<>();
        String sql = "SELECT * FROM contacts";

        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)){

            while (rs.next()) {
                Contact contact = new Contact();
                contact.setId(rs.getInt("id"));
                contact.setName(rs.getString("name"));
                contact.setEmail(rs.getString("email"));
                contacts.add(contact);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    public void addContact(Contact contact) {
        String sql = "INSERT INTO contacts (name, email) VALUES (?, ?)";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, contact.getName());
            stmt.setString(2, contact.getEmail());
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updateContact (Contact contact) {
        String sql = "UPDATE contacts SET name = ?, email = ? WHERE id = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, contact.getName());
            stmt.setString(2, contact.getEmail());
            stmt.setInt(3, contact.getId());
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteContact (int id) {
        String sql = "DELETE FROM contacts WHERE id = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Contact getContactById (int id) {
        String sql = "SELECT * FROM contacts WHERE id = ?";
        Contact contact = null;

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                contact = new Contact();
                contact.setId(resultSet.getInt("id"));
                contact.setName(resultSet.getString("name"));
                contact.setEmail(resultSet.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  contact;
    }
}
