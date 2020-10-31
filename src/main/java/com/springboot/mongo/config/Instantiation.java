package com.springboot.mongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.springboot.mongo.DTO.AuthorDTO;
import com.springboot.mongo.DTO.CommentDTO;
import com.springboot.mongo.domains.Post;
import com.springboot.mongo.domains.User;
import com.springboot.mongo.repositories.IPostRepository;
import com.springboot.mongo.repositories.IUserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private IPostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

		User guilherme = new User(null, "Guilherme", "vilatorog@gmail.com");
		User thais = new User(null, "Thais", "thais@gmail.com");
		User vinicius = new User(null, "Vinicius", "vinicius@gmail.com");
		User gabriel = new User(null, "Gabriel", "gabriel@gmail.com");

		userRepository.saveAll(Arrays.asList(guilherme, thais, vinicius, gabriel));
		
		Post post1 = new Post(null, sdf.parse("30/10/2020"), "Estudando Java", "Aumentando os conhecimentos em Java", new AuthorDTO(guilherme));
		Post post2 = new Post(null, sdf.parse("29/10/2020"), "Buscando novos cursos", "Realizando uma pesquisa para iniciar novos cursos em TI", new AuthorDTO(guilherme));
		
		postRepository.saveAll(Arrays.asList(post1, post2));		
		
		guilherme.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(guilherme);
		
		CommentDTO comment1 = new CommentDTO(null, "Estude!", sdf.parse("30/11/2020"));
		CommentDTO comment2 = new CommentDTO(null, "Pare!", sdf.parse("30/11/2020"));
		post1.getComments().addAll(Arrays.asList(comment1, comment2));
		postRepository.save(post1);
	}
}