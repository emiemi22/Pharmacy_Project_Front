package com.example.pharmacy.network;

public class User {
    private long id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String password;
    private String role ;

    @Override
    public String toString() {
        return
                "firstName:'" + firstName + '\'' +
                ", lastName:'" + lastName + '\'' +
                ", emailAddress:'" + emailAddress + '\'' +
                ", password:'" + password + '\'' +
                ", role:'" + role ;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
