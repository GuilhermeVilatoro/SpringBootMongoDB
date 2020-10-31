package com.springboot.mongo.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.springboot.mongo.domains.Post;

public interface IPostRepository extends MongoRepository<Post, String> {

	@Query("{ 'title': { $regex: ?0, $options: 'i'} }")
	List<Post> searchTitle(String title);

	List<Post> findByTitleContainingIgnoreCase(String title);
}