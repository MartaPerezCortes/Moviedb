package com.example.loginandroid_29_09_2023.login_user.data;

import com.example.loginandroid_29_09_2023.beans.User;

import java.util.ArrayList;

public class MyData {
    private String message;
    private User user;


    public String getMessage() {
        return message;
    }
    public User getLstUsers() {
        return user;
    }
    public void setLstUsers(User user) {
        this.user = user;
    }
}

