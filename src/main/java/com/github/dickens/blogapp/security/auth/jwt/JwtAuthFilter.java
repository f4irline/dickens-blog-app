package com.github.dickens.blogapp.security.auth.jwt;

import com.github.dickens.blogapp.user.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JwtAuthFilter is used to get the JWT token from the API request, validate it using
 * the JwtTokenizer and then pass the user associated to the token to Spring Security.
 *
 * @author Tommi Lepola
 * @version 1.0
 * @since 2019.0330
 */
public class JwtAuthFilter extends OncePerRequestFilter {

    /**
     * The JwtTokenizer implementation that's used to validate the token.
     */
    @Autowired
    private JwtTokenizer tokenizer;

    /**
     * UserDetailsService implementation, which Spring uses to fetch
     * UserDetails and perform various checks related to roles and authentication.
     */
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    /**
     * Used to do the authentication using Spring Security's functionalities and our implementations.
     *
     * <p>
     * We first parse the JWT token from the HTTP request. Then we validate the token using the JwtTokenizer.
     * </p>
     *
     * <p>
     * After that we load the user's details using the id we got using the JwtTokenizer and set the currently
     * authenticated principal to be the authenticated user.
     * </p>
     *
     * @param request the HTTP request that invoked the filter.
     * @param response response that might have been passed to this filter instance.
     * @param filterChain the FilterChain that includes this filter instance.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = getJwtFromRequest(request);

            if (StringUtils.hasText(jwt) && tokenizer.validateToken(jwt)) {
                Long userId = tokenizer.getUserIdFromJWT(jwt);

                UserDetails userDetails = userDetailsService.loadUserById(userId);
                UsernamePasswordAuthenticationToken userAuthentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                userAuthentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(userAuthentication);
            }
        } catch (Exception ex) {
            logger.error("Could not set user authentication in security context", ex);
        }

        /**
         * Invoke the next filter.
         */
        filterChain.doFilter(request, response);
    }

    /**
     * Gets the JWT token from the HTTP request's "Authorization" header.
     *
     * @param request the HTTP request with the header.
     * @return only the token with no Bearer prefix or anything else.
     */
    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }
}
