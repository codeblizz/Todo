package com.codeblizz.developer;

import java.time.Instant;
import java.util.Collections;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import com.codeblizz.developer.entity.Event;
import com.codeblizz.developer.entity.ToDo;
import com.codeblizz.developer.repository.TodoRepository;

import lombok.AllArgsConstructor;

@EnableAutoConfiguration
@AllArgsConstructor
public class Initializer implements CommandLineRunner {
	
	public final TodoRepository todoRepository;

	@Override
	public void run(String... args) throws Exception {

		Stream.of("Swimming Activity", "Dancing Activity", "Football Activity", "Gulf Activity")
			.forEach(name-> todoRepository.save(new ToDo(name)));
		
		ToDo t = todoRepository.findByName("Swimming Activity");
		Event e = Event.builder()
				.title("Free Style Swimming Technique")
				.date(Instant.parse("2019-12-15T06:00:00.0034"))
				.description("Olympic Style Lesson")
				.build();
		t.setEvents(Collections.singleton(e));
		todoRepository.save(t);
		todoRepository.findAll().forEach(System.out::println);
					
	}
	
	

}
