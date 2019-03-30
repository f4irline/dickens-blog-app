package com.github.dickens.blogapp;

import com.github.dickens.blogapp.user.User;
import com.github.dickens.blogapp.user.UserRepository;
import com.github.dickens.blogapp.comment.Comment;
import com.github.dickens.blogapp.comment.CommentRepository;
import com.github.dickens.blogapp.post.Post;
import com.github.dickens.blogapp.post.PostRepository;
import com.github.dickens.blogapp.utils.DataInit;
import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
