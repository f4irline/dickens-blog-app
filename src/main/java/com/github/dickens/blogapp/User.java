package com.github.dickens.blogapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class User {

    @Transient
    Utils utils = new Utils();

    @Id
    @GeneratedValue
    private int userId;
    private String userName;
    private String password;

    public User() {

    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = utils.hashMyPassword(password);
      //  System.out.println(utils.hashMyPassWord(password));
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = utils.hashMyPassword(password);
        //this.password = passwordEncoder.encode(password);
    }
/*
    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }
    */
}
