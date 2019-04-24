package com.github.dickens.blogapp.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * UserPrincipalImpl instances are returned to Spring Security from UserDetailsServiceImpl.
 *
 * <p>
 * This class's instances are used by Spring Security to perform actions regarding authentication
 * and user's roles.
 * </p>
 *
 * @author Tommi Lepola
 * @version 1.0
 * @since 2019.0327
 */
public class UserPrincipalImpl implements UserDetails {

    /**
     * The instance of the user that's being checked.
     */
    private User user;

    /**
     * Constructor.
     *
     * @param user the instance of the user that's being checked.
     */
    public UserPrincipalImpl(User user) {
        this.user = user;
    }

    /**
     * Returns user's roles in a list for authorization checking.
     *
     * @return user's role in a list.
     */
    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getDefinition().name())
        ).collect(Collectors.toList());
    }

    /**
     * Returns user's ID.
     *
     * @return user's ID.
     */
    @JsonIgnore
    public Long getId() {
        return user.getUserId();
    }

    /**
     * Returns user's password for authentication checking.
     *
     * @return user's password.
     */
    @JsonIgnore
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * Returns user's userName.
     *
     * @return user's userName.
     */
    @JsonIgnore
    @Override
    public String getUsername() {
        return user.getUserName();
    }

    /**
     * Returns if account is expired (false) or not (true).
     *
     * @return always true in this app.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Returns if account is locked (false) or not (true).
     *
     * @return always true in this app.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Returns if account's credentials have expired (false) or not (true).
     *
     * @return always true in this app.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Returns if account is enabled or not.
     *
     * @return always true in this app.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * Returns user instance.
     *
     * @return user instance.
     */
    public User getUser() {
        return user;
    }
}
