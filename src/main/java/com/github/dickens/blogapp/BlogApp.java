package com.github.dickens.blogapp;

import com.github.dickens.blogapp.user.User;
import com.github.dickens.blogapp.user.UserRepository;
import com.github.dickens.blogapp.comment.Comment;
import com.github.dickens.blogapp.comment.CommentRepository;
import com.github.dickens.blogapp.post.Post;
import com.github.dickens.blogapp.post.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class BlogApp implements CommandLineRunner {

	@Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    CommentRepository commentRepository;

	public static void main(String[] args) {
		SpringApplication.run(BlogApp.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
        User heikki = new User("Hexa","Koirannimi",User.USER);
        User tiina = new User("Tintti","Tiina96",User.USER);
        User tommi = new User("Tombha","GhostRider98",User.ADMIN);
        List<User> lista = new ArrayList<>();
        lista.add(heikki);
        lista.add(tiina);
        lista.add(tommi);

		userRepository.saveAll(lista);
		//user author, String title, String body, int likes
		postRepository.save(new Post(heikki, "Hauskaa hommaa tämä blogaaminen","No ei oikeesti"));
        postRepository.save(new Post(tiina, "Tampere","On helmi mesta"));
        postRepository.save(new Post(tommi, "Sköördiföö","Matafakafoufou"));

        commentRepository.save(new Comment(postRepository.findById(4).get(),userRepository.findById(1).get(),"This is shittu comment"));
	}
}
