package com.github.dickens.blogapp.security.auth.requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * POJO that defines what kind of login requests we expect.
 *
 * @author Tommi Lepola
 * @version 1.0
 * @since 2019.0330
 */
public class RegisterRequest {
    /**
     * New user's userName.
     */
    @NotBlank
    @Size(min = 4, max = 15)
    private String userName;

    /**
     * New user's first name.
     */
    @NotBlank
    @Size(min = 4, max = 40)
    private String userFirst;

    /**
     * New user's last name.
     */
    @NotBlank
    @Size(min = 4, max = 40)
    private String userLast;

    /**
     * New user's password.
     */
    @NotBlank
    @Size(min = 4, max = 20)
    private String password;

    /**
     * Returns new user's username.
     *
     * @return new user's username.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Set's the new user's username.
     *
     * @param userName user's username.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Returns new user's first name.
     *
     * @return new user's first name.
     */
    public String getUserFirst() {
        return userFirst;
    }

    /**
     * Set's the new user's last name.
     *
     * @param userFirst user's first name.
     */
    public void setUserFirst(String userFirst) {
        this.userFirst = userFirst;
    }

    /**
     * Returns new user's last name.
     *
     * @return new user's last name.
     */
    public String getUserLast() {
        return userLast;
    }

    /**
     * Set's the new user's last name.
     *
     * @param userLast user's last name.
     */
    public void setUserLast(String userLast) {
        this.userLast = userLast;
    }

    /**
     * Returns new user's password.
     *
     * @return new user's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set's the new user's password.
     *
     * @param password user's password.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
