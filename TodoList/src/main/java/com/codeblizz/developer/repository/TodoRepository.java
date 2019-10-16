package com.codeblizz.developer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codeblizz.developer.entity.ToDo;

@Repository
public interface TodoRepository extends JpaRepository<ToDo, Long> {
	ToDo findByName(String name);

}
