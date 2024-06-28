package com.samsung.springboot.services;

import com.samsung.springboot.dtos.LoginDocumentRequestDTO;
import com.samsung.springboot.exceptions.ResourceNotFoundException;
import com.samsung.springboot.models.CartItemModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.samsung.springboot.dtos.LoginRequestDTO;
import com.samsung.springboot.dtos.AuthResponseDTO;
import com.samsung.springboot.dtos.RegisterRequestDTO;
import com.samsung.springboot.models.PersonModel;

import java.util.Optional;

@Service
public class AuthService {

	@Autowired
	private PersonService personService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	public AuthResponseDTO register(RegisterRequestDTO dto) {
		
		PersonModel person = new PersonModel();
		person.setName(dto.getName());
		person.setDocument(dto.getDocument());
		person.setEmail(dto.getEmail());
		person.setPassword(passwordEncoder.encode(dto.getPassword()));
		
		person = personService.create(person);
		
		return new AuthResponseDTO(jwtService.generateToken(person.getEmail()));
	}
	
	public AuthResponseDTO authenticate(LoginRequestDTO dto) {
		
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						dto.getEmail(), 
						dto.getPassword()));
		
		final PersonModel person = personService.findByEmail(dto.getEmail());
		return new AuthResponseDTO(jwtService.generateToken(person.getEmail()));
	}

	public AuthResponseDTO authenticateDocument(LoginDocumentRequestDTO dto) {

		PersonModel person = personService.findByDocument(dto.getDocument());
		return new AuthResponseDTO(jwtService.generateToken(person.getEmail()));

	}

}
