package com.github.dickens.blogapp.security.auth.jwt;

/**
 * POJO that defines the response we pass to the user when user has authenticated.
 *
 * @author Tommi Lepola
 * @version 1.0
 * @since 2019.0330
 */
public class JwtAuthResponse {
    /**
     * The JWT token.
     */
    private String accessToken;

    /**
     * Token's type is Bearer, therefore it should be used in Authorization tag with "Bearer" prefix.
     */
    private String tokenType = "Bearer";

    /**
     * Constructor for the response.
     *
     * @param accessToken the token that user will use.
     */
    public JwtAuthResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * Returns the access token.
     *
     * @return the access token.
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * Sets the access token.
     *
     * @param accessToken the access token.
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * Returns the token's type.
     *
     * @return the token's type.
     */
    public String getTokenType() {
        return tokenType;
    }

    /**
     * Set's the token's type.
     *
     * @param tokenType the token's type.
     */
    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}
