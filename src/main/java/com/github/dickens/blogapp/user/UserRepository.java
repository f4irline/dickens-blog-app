package com.github.dickens.blogapp.user;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUserName(String userName);

    Boolean existsByUserName(String userName);
}
