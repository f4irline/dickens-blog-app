package com.github.dickens.blogapp.security.auth.jwt;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Used by Spring Security to respond to user with
 * HTTP Unauthorized if user is trying to access a protected resource
 * with no authentication.
 *
 * @author Tommi Lepola
 * @version 1.0
 * @since 2019.0330
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    /**
     * Build the error response.
     *
     * @param httpServletRequest the request servlet instance.
     * @param httpServletResponse the response servlet instance.
     * @param e the exception that invoked this method.
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void commence(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
    }
}
