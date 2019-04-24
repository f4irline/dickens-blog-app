package com.github.dickens.blogapp.security.auth.jwt;

import com.github.dickens.blogapp.user.UserPrincipalImpl;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * JwtTokenizer is a utility class implementation which is used to generate JWT tokens
 * and verify them when user's send them back.
 *
 * @author Tommi Lepola
 * @version 1.0
 * @since 2019.0330
 */
@Component
public class JwtTokenizer {
    /**
     * Logger which is used for debugging purposes.
     */
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenizer.class);

    /**
     * Used to sign the returned JWT token with this secret key.
     */
    @Value("${app.jwtSecret}")
    private String jwtSecret;

    /**
     * Used to set an expiration time to the JWT token which is sent to user.
     */
    @Value("${app.jwtExpirationInMs}")
    private int jwtExpirationInMs;

    /**
     * Generates the token that's distributed to user.
     *
     * <p>
     * We build the JWT token using SHA-512 algorithm and the jwtSecret key. We set the
     * JWT token to point to one user and set it's creation date and expiration date.
     * </p>
     *
     * @param authentication the token for the authentication request.
     * @return JWT token as a string.
     */
    public String generateToken(Authentication authentication) {

        UserPrincipalImpl userPrincipal = (UserPrincipalImpl) authentication.getPrincipal();

        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .setSubject(Long.toString(userPrincipal.getId()))
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    /**
     * We parse the JWT token using the user's provided token and our defined secret key together.
     * Then we get the user related to the JWT token that user provided.
     *
     * @param token the token provided by user.
     * @return the user's ID.
     */
    public Long getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }

    /**
     * Used to validate user's sent token.
     *
     * @param authToken the token user provided.
     * @return true if validated, false if not.
     */
    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            logger.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty.");
        }
        return false;
    }
}
