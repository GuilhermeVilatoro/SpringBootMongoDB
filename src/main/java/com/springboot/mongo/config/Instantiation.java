package com.springboot.mongo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.springboot.mongo.domains.User;
import com.springboot.mongo.repositories.IUserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private IUserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		userRepository.deleteAll();

		User guilherme = new User(null, "Guilherme", "vilatorog@gmail.com");
		User thais = new User(null, "Thais", "thais@gmail.com");
		User vinicius = new User(null, "Vinicius", "vinicius@gmail.com");
		User gabriel = new User(null, "Gabriel", "gabriel@gmail.com");

		userRepository.saveAll(Arrays.asList(guilherme, thais, vinicius, gabriel));
	}
}