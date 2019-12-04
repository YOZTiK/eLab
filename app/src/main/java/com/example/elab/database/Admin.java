package com.example.elab.database;

public class Admin {

    private String email;
    private String password;
    private String username;

    public Admin(String email, String password, String username){
        this.email = email;
        this.password = password;
        this.username = username;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}