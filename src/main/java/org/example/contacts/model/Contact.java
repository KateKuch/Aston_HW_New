package org.example.contacts.model;

import java.util.List;

public class Contact {

    private int id;
    private String name;
    private String email;
    private List<Address> addresses;
    private List<Group> groups;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public int getId() {
        return id;
    }

    public String getName(){
        return name;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public String getEmail() {
        return email;
    }
}
