package com.springboot.mongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.springboot.mongo.domains.User;

public interface IUserRepository extends MongoRepository<User, String> {
}