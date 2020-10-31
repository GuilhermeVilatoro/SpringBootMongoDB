package com.springboot.mongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.mongo.domains.Post;
import com.springboot.mongo.repositories.IPostRepository;
import com.springboot.mongo.services.exceptions.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private IPostRepository postRepository;

	public List<Post> findByTitle(String title) {
		return postRepository.findByTitleContainingIgnoreCase(title);
	}

	public List<Post> searchTitle(String title) {
		return postRepository.searchTitle(title);
	}

	public Post findById(String id) {
		Optional<Post> post = postRepository.findById(id);
		return post.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
}