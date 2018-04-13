package com.example.kch.proj_manager_v8;

/**
 * Created by kch on 2018. 2. 17..
 */

public class User {
    String userID;
    String userPassword;
    String userAge;
    String userName;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public User(String userID, String userPassword, String userAge, String userName) {
        this.userID = userID;
        this.userPassword = userPassword;
        this.userAge = userAge;
        this.userName = userName;
    }
}
