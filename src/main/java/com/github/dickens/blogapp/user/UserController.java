package com.github.dickens.blogapp.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class UserController {

    @Autowired
    UserRepository userRepository;
    /*
    curl -H"Content-Type: application/json" -X POST -d {\"userName\":\"mkyong\",\"password\":\"abc\"} http://localhost:8080/api/user/add
     */
    @PostMapping(value = "/users/add")
    public void addUser(@RequestBody User user) {
        userRepository.save(user);
    }

    @DeleteMapping(value = "users/{userId}")
    public void getUser(@PathVariable int userId) {
        userRepository.deleteById(userId);
    }

}
