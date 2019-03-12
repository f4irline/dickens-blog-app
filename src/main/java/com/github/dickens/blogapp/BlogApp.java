package com.github.dickens.blogapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlogApp implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(BlogApp.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		userRepository.save(new User("HerskaHeikki","Passu"));
	}
}
