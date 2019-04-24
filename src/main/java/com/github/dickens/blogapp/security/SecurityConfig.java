package com.github.dickens.blogapp.security;

import com.github.dickens.blogapp.security.auth.jwt.JwtAuthFilter;
import com.github.dickens.blogapp.security.auth.jwt.JwtAuthenticationEntryPoint;
import com.github.dickens.blogapp.user.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

/**
 * SecurityConfig class is used to perform various Spring Security actions.
 *
 * <p>
 * This config file is used to secure the app and implement various authorization and authentication
 * related tasks.
 * </p>
 *
 * <p>
 * Security is basically implemented using JWT tokens, which are send to user when user logs in succesfully
 * and user returns the token in every request.
 * </p>
 *
 * @author Tommi Lepola
 * @version 2.0
 * @since 2019.0330
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * UserDetailsService implementation, which Spring uses to fetch
     * UserDetails and perform various checks related to roles and authentication.
     */
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    /**
     * Handler which is used to send HTTP Unauthorized error to clients that try to access a protected
     * resource without authentication.
     */
    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    /**
     * Returns JwtAuthFilter, which is used to filter all API requests that require authentication
     * and validate the token in them.
     *
     * @return the filter.
     */
    @Bean
    public JwtAuthFilter jwtAuthenticationFilter() {
        return new JwtAuthFilter();
    }


    /**
     * Returns the main interface that's used to authenticate the user.
     *
     * @return the interface implementation that's used in authentication.
     * @throws Exception
     */
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * Used to create the AuthenticationManager instance above. Defines how we're authenticating the user: in memory
     * authentication? JDBC authentication? In this case we're authenticating the user based on our custom
     * UserDetailsService implementation.
     *
     * @param authenticationManagerBuilder the authentication manager builder instance.
     * @throws Exception
     */
    @Override
    public void configure(final AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    /**
     * Used to configure various different security related parameters.
     *
     * <p>
     * We configure here routes that require and don't require authentication, how we're managing our sessions,
     * how we're handling unauthorized use of our app, require HTTPS and how we're managing our CSRF tokens.
     * </p>
     *
     * <p>
     * We're also telling spring that we're using JWT as our authentication filter. Also every request
     * going to HTTP are redirected to HTTPS if X-Forwarded-Proto header is present.
     * </p>
     * @param http security configuration object.
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .headers().frameOptions().disable() //Comment this for production
                .and() // Comment this for production
            .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).disable() // Comment the disable here for production
                //.and() // Uncomment this for production
            .requiresChannel()
                .requestMatchers(r -> r.getHeader("X-Forwarder-Proto") != null)
                .requiresSecure()
                .and()
            .exceptionHandling()
                .authenticationEntryPoint(unauthorizedHandler)
                .and()
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
            .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/api/**").permitAll()
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers("/static/**").permitAll()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated();

        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}