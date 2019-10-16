package com.codeblizz.developer.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name="todo")
public class ToDo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@NonNull
	private String name;

	private String amatuer;

	private String intermediary;

	private String professional;

	private String expert;
	
	private String master;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	private User user;
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Set<Event> events;

}
