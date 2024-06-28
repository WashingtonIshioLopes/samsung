package com.samsung.springboot.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.samsung.springboot.models.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

	Optional<Person> findByEmail(String email);
}
