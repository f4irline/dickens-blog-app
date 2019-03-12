package com.github.dickens.blogapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
public class MyRestController {

    @Autowired
    UserRepository userRepository;

    /*
    curl -H"Content-Type: application/json" -X POST -d {\"userName\":\"mkyong\",\"password\":\"abc\"} http://localhost:8080/user/add
     */
    @PostMapping(value = "/user/add")
    public void addUser(@RequestBody User user) {
        userRepository.save(user);
    }

}
