package com.example.jwt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwt.response.Response;

@RestController
public class HelloWorldController {

	@RequestMapping({ "/hello" })
	public ResponseEntity<Response> firstPage() {
		return ResponseEntity.ok(new Response("Hello Test Pass"));
	}

}
