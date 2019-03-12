package com.github.dickens.blogapp;

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
		//User author, String title, String body, int likes
		postRepository.save(new Post(heikki, "Hauskaa hommaa tämä blogaaminen","No ei oikeesti"));
        postRepository.save(new Post(tiina, "Tampere","On helmi mesta"));
        postRepository.save(new Post(tommi, "Sköördiföö","Matafakafoufou"));
	}
}
