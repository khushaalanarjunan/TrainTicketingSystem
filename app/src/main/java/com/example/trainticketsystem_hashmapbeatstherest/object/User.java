package com.example.trainticketsystem_hashmapbeatstherest.object;

public class User {

    private String userId;
    private String userFullName;
    private String userMYKad;
    private Boolean userGender;
    private String userEmail;
    private String userContact;
    private String userPassword;
    private String userBalance;


    public User() {
    }


    public User(String userId, String userFullName, String userMYKad, Boolean userGender, String userEmail, String userContact, String userPassword, String userBalance) {
        this.userId = userId;
        this.userFullName = userFullName;
        this.userMYKad = userMYKad;
        this.userGender = userGender;
        this.userEmail = userEmail;
        this.userContact = userContact;
        this.userPassword = userPassword;
        this.userBalance = userBalance;
    }

    public String getBalance() {
        return userBalance;
    }

    public void setBalance(String userBalance) {
        this.userBalance = userBalance;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getUserMYKad() {
        return userMYKad;
    }

    public void setUserMYKad(String userMYKad) {
        this.userMYKad = userMYKad;
    }

    public Boolean getUserGender() {
        return userGender;
    }

    public void setUserGender(Boolean userGender) {
        this.userGender = userGender;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserContact() {
        return userContact;
    }

    public void setUserContact(String userContact) {
        this.userContact = userContact;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
