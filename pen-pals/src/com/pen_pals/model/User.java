package com.pen_pals.model;

public class User {
    String username;
    String password;
    String email;
    String firstName;

    public User(String username, String password, String email, String firstName) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
    }

    public String getUserName() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return this.email;
    }

    public String getFirstName() {
        return this.firstName;
    }

}
