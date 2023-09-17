package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.models.User;
import com.example.demo.models.UserInput;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private UserRepository userRepository;
	
	public User register(UserInput userInput) {
		if ((userRepository.findByEmail(userInput.getEmail())).isPresent()) {
			throw new  ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already registered");
		}
		User user = new User();
		user.setName(userInput.getName());
		user.setEmail(userInput.getEmail());
		user.setRoles(userInput.getRoles());
		
		user.setPassword(encoder.encode(userInput.getPassword()));
		return userRepository.save(user);
	}
	
}
