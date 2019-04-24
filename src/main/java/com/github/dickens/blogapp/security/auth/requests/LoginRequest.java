package com.github.dickens.blogapp.security.auth.requests;

import javax.validation.constraints.NotBlank;

/**
 * POJO that defines what kind of login requests we expect.
 *
 * @author Tommi Lepola
 * @version 1.0
 * @since 2019.0330
 */
public class LoginRequest {

    /**
     * User's userName.
     */
    @NotBlank
    private String userName;

    /**
     * User's password.
     */
    @NotBlank
    private String password;

    /**
     * Returns the login request user's username.
     *
     * @return user's username.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the login request user's username.
     *
     * @param userName user's username.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Returns the login request user's password.
     *
     * @return user's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the login request user's password.
     *
     * @param password user's password.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
