package com.qacart.todo.models;

import java.util.Date;

public class Root {
    public boolean isCompleted;
    public String _id;
    public String item;
    public String message;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String userID;
    public Date createdAt;
    public int __v;
}