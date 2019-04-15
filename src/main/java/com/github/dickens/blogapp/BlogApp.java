package com.github.dickens.blogapp;

import com.github.dickens.blogapp.utils.DataInit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlogApp implements CommandLineRunner {

	@Autowired
    DataInit dataInit;

	public static void main(String[] args) {
		SpringApplication.run(BlogApp.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
        dataInit.initData();
    }
}
