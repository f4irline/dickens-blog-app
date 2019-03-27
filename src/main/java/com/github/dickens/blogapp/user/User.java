package com.github.dickens.blogapp.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.dickens.blogapp.utils.HashUtils;

import javax.persistence.*;

@Entity
public class User {

    @Transient
    HashUtils utils = new HashUtils();

    @TableGenerator(name = "User_Gen",
            table = "USER_ID_GEN",
            pkColumnName = "USER_ID",
            valueColumnName = "GEN_VAL",
            pkColumnValue = "User_Gen",
            initialValue = 1000,
            allocationSize = 100)

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "User_Gen")
    private int userId;
    private String userName;
    private String userFirst;
    private String userLast;
    private String password;

    public User() {

    }

    public User(String userName, String userFirst, String userLast, String password) {
        this.userName = userName;
        this.userFirst = userFirst;
        this.userLast = userLast;
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

    public String getUserFirst() {
        return userFirst;
    }

    public void setUserFirst(String userFirst) {
        this.userFirst = userFirst;
    }

    public String getUserLast() {
        return userLast;
    }

    public void setUserLast(String userLast) {
        this.userLast = userLast;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = utils.hashMyPassword(password);
    }
}
