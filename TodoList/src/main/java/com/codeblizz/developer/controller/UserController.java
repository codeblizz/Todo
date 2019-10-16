package com.codeblizz.developer.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codeblizz.developer.entity.User;
import com.codeblizz.developer.repository.UserRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	Logger log = LoggerFactory.getLogger(UserController.class);
	
	@GetMapping("/user")
	Collection <User> getUser(){
		return userRepository.findAll();
	}
	
	@GetMapping("/user/{id}")
	ResponseEntity <User> getUserById(@PathVariable Long id) {
		Optional<User> u = userRepository.findById(id);
		return u.map(response->ResponseEntity.ok().body(response))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@PostMapping("/user")
	ResponseEntity <User> createUser(@Valid @RequestBody User user) throws URISyntaxException{
		log.info("Request to create user: {}", user);
		User u = userRepository.save(user);
		return ResponseEntity.created(new URI("/api/user" + u.getId()))
				.body(u);		
	}
	
	@PutMapping("/user")
	ResponseEntity <User> updateUser(@Valid @RequestBody User user){
		log.info("Request to update user: {}", user);
		User u = userRepository.save(user);
		return ResponseEntity.ok().body(u);
		
	}
	
	@DeleteMapping("/user/{id}")
	ResponseEntity <User> deleteUser(@PathVariable Long id){
		log.info("Request to delete user: {}", id);
		userRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}

}
