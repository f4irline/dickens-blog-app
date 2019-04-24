package com.github.dickens.blogapp.user.role;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Role's repository implementation through CrudRepository.
 *
 * <p>
 * RoleRepository is implemented using CrudRepository, which grants us basic Spring Data JPA
 * functionalities for fetching data.
 * </p>
 *
 * <p>
 * One custom functionality has been implemented to fetch role's by it's Enum definition.
 * </p>
 *
 * @author Tommi Lepola
 * @version 1.0
 * @since 2019.0330
 */
public interface RoleRepository extends CrudRepository<Role, Long> {
    /**
     * Returns role by it's Enum definition.
     *
     * @param definition role as Enum.
     * @return the Role that was asked.
     */
    Optional<Role> findByDefinition(RoleDefinition definition);
}
