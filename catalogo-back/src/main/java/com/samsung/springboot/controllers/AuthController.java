package com.samsung.springboot.controllers;

import com.samsung.springboot.dtos.LoginDocumentRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samsung.springboot.dtos.RegisterRequestDTO;
import com.samsung.springboot.dtos.LoginRequestDTO;
import com.samsung.springboot.dtos.AuthResponseDTO;
import com.samsung.springboot.services.AuthService;

@RestController
@RequestMapping("/api/v1_0/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService; 

	@PostMapping("/register")
	public ResponseEntity<AuthResponseDTO> register(@RequestBody RegisterRequestDTO dto) {
		return ResponseEntity.ok(authService.register(dto));
	}
	
	@PostMapping("/login")
	public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginRequestDTO dto) {
		return ResponseEntity.ok(authService.authenticate(dto));
	}

	@PostMapping("/login_document")
	public ResponseEntity<AuthResponseDTO> login_document(@RequestBody LoginDocumentRequestDTO dto) {
		return ResponseEntity.ok(authService.authenticateDocument(dto));
	}
}
