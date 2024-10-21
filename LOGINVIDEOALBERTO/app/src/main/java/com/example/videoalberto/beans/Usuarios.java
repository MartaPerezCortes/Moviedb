package com.example.videoalberto.beans;

import java.io.Serializable;

public class Usuarios implements Serializable {
    private String id;
    private String email;
    private String password;

    public Usuarios(String id, String password, String email) {
        this.id = id;
        this.password = password;
        this.email = email;
    }

    public Usuarios() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
