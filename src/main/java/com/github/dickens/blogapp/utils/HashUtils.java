package com.github.dickens.blogapp.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Utils for hashing password.
 *
 * @author Ville-Veikko Nieminen
 * @author Tommi Lepola
 * @since 2019.0312
 * @version 1.0
 */
@Service
public class HashUtils {
    /**
     * Returns hashed version from given password.
     *
     * @param password containing the password
     * @return String representing hashed version from password
     */
    public String hashMyPassword(String password) {
        BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
        return bCryptEncoder.encode(password);
    }
}
