package com.github.dickens.blogapp.user.role;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Optional<Role> findByDefinition(RoleDefinition definition);
}
