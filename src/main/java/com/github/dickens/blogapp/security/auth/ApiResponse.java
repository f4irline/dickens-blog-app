package com.github.dickens.blogapp.security.auth;

/**
 * POJO which is returned to user on API requests.
 *
 * <p>
 * Answers to user with success: false or success:true and a message.
 * </p>
 *
 * @author Tommi Lepola
 * @version 1.0
 * @since 2019.0330
 */
public class ApiResponse {
    /**
     * Boolean is true if api request was succesful, false if unsuccesful.
     */
    private Boolean success;

    /**
     * Message in the response.
     */
    private String message;

    /**
     * Constructor.
     *
     * @param success true if succesful request, false if not.
     * @param message message that's included in the response.
     */
    public ApiResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    /**
     * Returns the success boolean of the response.
     *
     * @return true if request related to this object was succesful, false if not.
     */
    public Boolean getSuccess() {
        return success;
    }

    /**
     * Set's the success boolean of this response.
     *
     * @param success success boolean.
     */
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    /**
     * Returns the message of the response.
     *
     * @return the message of the response.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message of the response.
     *
     * @param message the message of the response.
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
