package com.samsung.springboot.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.samsung.springboot.models.PersonModel;

public interface PersonRepository extends JpaRepository<PersonModel, Long> {

	Optional<PersonModel> findByEmail(String email);
	Optional<PersonModel> findByDocument(String document);
}
