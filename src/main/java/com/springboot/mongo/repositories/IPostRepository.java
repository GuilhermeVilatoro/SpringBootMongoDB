package com.springboot.mongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.springboot.mongo.domains.Post;

public interface IPostRepository extends MongoRepository<Post, String> {
}