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

import com.codeblizz.developer.entity.ToDo;
import com.codeblizz.developer.repository.TodoRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class TodoController {
	
	private final Logger log = LoggerFactory.getLogger(TodoRepository.class);
	
	@Autowired
	private TodoRepository todoRepository;

	
	@GetMapping("/todos")
	Collection<ToDo> activity(){
		return todoRepository.findAll();	
	}
	
	@GetMapping("/todo/{id}/")
	ResponseEntity <?> getActivityById(@PathVariable Long id){
		log.info("Request to find id: {}", id);
		Optional<ToDo> todo = todoRepository.findById(id);
			return todo.map(response->ResponseEntity.ok().body(response))
					.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	@PostMapping("/todo")
	ResponseEntity <ToDo> createActivity(@Valid @RequestBody ToDo t) throws URISyntaxException {
		log.info("Request to create activity: {}", t);
		ToDo todo = todoRepository.save(t);
		return ResponseEntity.created(new URI("/api/todo" + todo.getId()))
				.body(todo);
	}
	@PutMapping("/todo/{id}")
	ResponseEntity <ToDo> updateActivity(@Valid @RequestBody ToDo t){
		log.info("Request to update activity: {}", t);
		ToDo todo = todoRepository.save(t);
		return ResponseEntity.ok().body(todo);
	}
	@DeleteMapping("/todo/{id}")
	ResponseEntity <ToDo> deleteActivityById(@PathVariable Long id){
		log.info("Request to delete an id: {}", id);
		todoRepository.deleteById(id);;
		return ResponseEntity.ok().build();		
	}	
}
