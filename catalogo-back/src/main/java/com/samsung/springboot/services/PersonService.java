package com.samsung.springboot.services;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samsung.springboot.dtos.PersonDTO;
import com.samsung.springboot.models.Person;
import com.samsung.springboot.enums.Role;
import com.samsung.springboot.exceptions.DuplicationException;
import com.samsung.springboot.exceptions.NotFoundException;
import com.samsung.springboot.repositories.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	private PersonRepository repository;
	
	public Person findById(Long id) {
		return repository.findById(id).orElseThrow(
				() -> new NotFoundException("Person not found: " + id));
	}
	
	public Person findByEmail(String email) {
		return repository.findByEmail(email).orElseThrow(
				() -> new NotFoundException("Person not found: " + email));
	}
	
	public List<Person> findAll() {
		return repository.findAll();
	}
	
	public Person create(Person person) {
		person.setId(null);
		person.addRole(Role.USER);
		checkEmailDuplication(person);
		return repository.save(person);
	}
	
	public PersonDTO create(PersonDTO dto) {
		return new PersonDTO(create(new Person(dto)));
	}
	
	public Person update(Person person) {
		checkEmailDuplication(person);
		Person p = findById(person.getId());
		p.setName(person.getName());
		p.setEmail(person.getEmail());
		p.setRoles(person.getRoles());
		return repository.save(p);
	}
	
	public void delete(Long id) {
		final Person p = findById(id);
		repository.delete(p);
	}
	
	private void checkEmailDuplication(Person person) {
		final String email = person.getEmail();
		if (email != null && email.length() > 0) {
			final Long id = person.getId(); 
			final Person p = repository.findByEmail(email).orElse(null);
			if (p != null && Objects.equals(p.getEmail(), email) && !Objects.equals(p.getId(), id)) {
				throw new DuplicationException("Email duplication: " + email);
			}
		}
	}

}
