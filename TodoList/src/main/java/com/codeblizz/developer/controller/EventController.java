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

import com.codeblizz.developer.entity.Event;
import com.codeblizz.developer.repository.EventRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class EventController {
	
	Logger log = LoggerFactory.getLogger(EventController.class);
	
	@Autowired
	EventRepository eventRepository;
	
	@GetMapping("/event")
	Collection <Event> event(){
		return eventRepository.findAll();			
	}	
	
	@GetMapping("/event/{id}")
	ResponseEntity <Event> getEventById(@PathVariable Long id){
		log.info("Request to get aa collection of event: {}", id);
		Optional <Event> event = eventRepository.findById(id);
		return event.map(response->ResponseEntity.ok().body(response))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	@PostMapping("/event")
	ResponseEntity <Event> createEvent(@Valid @RequestBody Event evt) throws URISyntaxException{
		log.info("Request to create event: {}", evt);
		Event e = eventRepository.save(evt);
		return ResponseEntity.created(new URI("/api/event" + e.getId()))
				.body(e);
	}
	@PutMapping("/event")
	ResponseEntity <Event> updateEvent(@Valid @RequestBody Event evt){
		log.info("Request to update event: {}", evt);
		Event e = eventRepository.save(evt);
		return ResponseEntity.ok().body(e);
	}
	@DeleteMapping("/event")
	ResponseEntity <Event> deleteEventById(@Valid @RequestBody Event evt){
		log.info("Request to delete event: {}", evt);
		eventRepository.delete(evt);
		return ResponseEntity.ok().build();
	}
		
		

}
