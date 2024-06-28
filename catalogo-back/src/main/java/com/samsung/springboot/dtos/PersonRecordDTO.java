package com.samsung.springboot.dtos;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.samsung.springboot.models.PersonModel;
import com.samsung.springboot.enums.Role;

public class PersonRecordDTO {
	
	private Long id;
	private String name;
	private String document;
	private String email;
	private String password;
	private Set<String> roles = new HashSet<>();
	
	public PersonRecordDTO() {
		super();
	}

	public PersonRecordDTO(Long id, String name, String document, String email, Set<String> roles) {
		super();
		this.id = id;
		this.name = name;
		this.document = document;
		this.email = email;
		this.roles = roles;
	}
	
	public PersonRecordDTO(PersonModel person) {
		super();
		this.id = person.getId();
		this.name = person.getName();
		this.document = person.getDocument();
		this.email = person.getEmail();
		this.setRoles(person.getRoles());
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}
	
	public Set<String> getRoles() {
		return roles;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles.stream().map(r -> r.getDescription()).collect(Collectors.toSet());
	}
	
}
