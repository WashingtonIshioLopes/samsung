package com.samsung.springboot.services;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samsung.springboot.dtos.PersonRecordDTO;
import com.samsung.springboot.models.PersonModel;
import com.samsung.springboot.enums.Role;
import com.samsung.springboot.exceptions.DuplicationException;
import com.samsung.springboot.exceptions.NotFoundException;
import com.samsung.springboot.repositories.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	private PersonRepository repository;
	
	public PersonModel findById(Long id) {
		return repository.findById(id).orElseThrow(
				() -> new NotFoundException("Person not found: " + id));
	}
	
	public PersonModel findByEmail(String email) {
		return repository.findByEmail(email).orElseThrow(
				() -> new NotFoundException("Person not found: " + email));
	}

	public PersonModel findByDocument(String document) {
		return repository.findByDocument(document).orElseThrow(
				() -> new NotFoundException("Person not found: " + document));
	}
	
	public List<PersonModel> findAll() {
		return repository.findAll();
	}
	
	public PersonModel create(PersonModel person) {
		person.setId(null);
		person.addRole(Role.USER);
		checkEmailDuplication(person);
		return repository.save(person);
	}
	
	public PersonRecordDTO create(PersonRecordDTO dto) {
		return new PersonRecordDTO(create(new PersonModel(dto)));
	}
	
	public PersonModel update(PersonModel person) {
		checkEmailDuplication(person);
		PersonModel p = findById(person.getId());
		p.setName(person.getName());
		p.setEmail(person.getEmail());
		p.setRoles(person.getRoles());
		return repository.save(p);
	}
	
	public void delete(Long id) {
		final PersonModel p = findById(id);
		repository.delete(p);
	}
	
	private void checkEmailDuplication(PersonModel person) {
		final String email = person.getEmail();
		if (email != null && email.length() > 0) {
			final Long id = person.getId(); 
			final PersonModel p = repository.findByEmail(email).orElse(null);
			if (p != null && Objects.equals(p.getEmail(), email) && !Objects.equals(p.getId(), id)) {
				throw new DuplicationException("Email duplication: " + email);
			}
		}
	}

}
