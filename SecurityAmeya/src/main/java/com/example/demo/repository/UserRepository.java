package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import com.example.demo.models.User;


public interface UserRepository extends CrudRepository<User, Integer>{
	
	@RestResource(exported = false)
	public User save(User user);
	
	@RestResource(exported = false)
	public Optional<User> findByEmail(String email);
}
