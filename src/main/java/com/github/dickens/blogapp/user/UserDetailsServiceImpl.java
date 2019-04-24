package com.github.dickens.blogapp.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * UserDetailsService implementation class, which Spring Security uses to perform actions
 * related to roles and authentication.
 *
 * @author Tommi Lepola
 * @version 1.0
 * @since 2019.0327
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    /**
     * CrudRepository for users.
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Loads UserDetails object by user's userName.
     *
     * @param userName user's userName.
     * @return UserDetails object with user's details.
     * @throws UsernameNotFoundException if user was not found.
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("No user found with name "+userName);
        }
        return new UserPrincipalImpl(user);
    }

    /**
     * Loads UserDetails object by user's ID.
     *
     * @param userId user's id.
     * @return UserDetails object with user's details.
     * @throws UsernameNotFoundException if user was not found.
     */
    @Transactional
    public UserDetails loadUserById(Long userId) throws UsernameNotFoundException {
        User user = userRepository.findById(userId).get();
        if (user == null) {
            throw new UsernameNotFoundException("No user found with id "+userId);
        }

        return new UserPrincipalImpl(user);
    }
}
