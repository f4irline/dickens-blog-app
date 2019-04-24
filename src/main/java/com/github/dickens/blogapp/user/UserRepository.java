package com.github.dickens.blogapp.user;

import org.springframework.data.repository.CrudRepository;

/**
 * User repository implementation through CrudRepository.
 *
 * <p>
 * UserRepository is implemented using CrudRepository, which grants us basic Spring Data JPA
 * functionalities for fetching data.
 * </p>
 *
 * <p>
 * Some custom functionalities are also implemented.
 * </p>
 *
 * @author Tommi Lepola
 * @author Ville-Veikko Nieminen
 * @version 3.0
 * @since 2019.0312
 */
public interface UserRepository extends CrudRepository<User, Long> {
    /**
     * Find user by user's userName.
     *
     * @param userName user's userName.
     * @return User object.
     */
    User findByUserName(String userName);

    /**
     * Check if user exists with this userName.
     *
     * <p>
     * Used for example in user registration to check that no two users with same name can register.
     * </p>
     *
     * @param userName name to be checked.
     * @return true if user already exists, false if not.
     */
    Boolean existsByUserName(String userName);
}
