package com.example.wisherman.model;

import java.util.HashMap;

public class User {
    private int userID;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private HashMap<String,WishList> wishLists;

    public User(){}

    public User(String username, String password, String firstName, String lastName, String email) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }



    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public HashMap<String, WishList> getWishLists() {
        return wishLists;
    }

    public void setWishLists(HashMap<String, WishList> wishLists) {
        this.wishLists = wishLists;
    }
}
