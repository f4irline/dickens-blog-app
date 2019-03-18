package com.github.dickens.blogapp.user;

import com.github.dickens.blogapp.Utils;

import javax.persistence.*;

@Entity
public class User {

    @Transient
    Utils utils = new Utils();

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
    private String password;
    private int role;

    public static int USER = 0;
    public static int ADMIN = 1;

    public User() {

    }

    public User(String userName, String password, int role) {
        this.userName = userName;
        this.password = utils.hashMyPassword(password);
        this.role = role;

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

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
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
