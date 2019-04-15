package com.github.dickens.blogapp.security.auth.requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class RegisterRequest {
    @NotBlank
    @Size(min = 4, max = 15)
    private String userName;

    @NotBlank
    @Size(min = 4, max = 40)
    private String userFirst;

    @NotBlank
    @Size(min = 4, max = 40)
    private String userLast;

    @NotBlank
    @Size(min = 4, max = 20)
    private String password;

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
        this.password = password;
    }
}
