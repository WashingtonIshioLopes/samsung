package com.samsung.springboot.controllers;

import com.samsung.springboot.dtos.CartItemRecordDto;
import com.samsung.springboot.exceptions.ResourceNotFoundException;
import com.samsung.springboot.models.CartItemModel;
import com.samsung.springboot.services.CartItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1_0")
public class CartItemController {
	
	@Autowired
	CartItemService cartItemService;
	
	@GetMapping("/cartitens")
	public ResponseEntity<List<CartItemModel>> getAllUsers(){
		List<CartItemModel> users = cartItemService.getAll();
		return ResponseEntity.status(HttpStatus.OK).body(users);
	}

	@GetMapping("/cartitens/{id}")
	public ResponseEntity<Object> getOneUser(@PathVariable(value="id") Long id){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(cartItemService.getOne(id));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@PostMapping("/cartitens")
	public ResponseEntity<CartItemModel> saveUser(@RequestBody @Valid CartItemRecordDto cartItemRecordDto) {
		CartItemModel savedCartItem = cartItemService.save(cartItemRecordDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedCartItem);
	}

	@DeleteMapping("/cartitens/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable(value="id") Long id) {
		try {
			cartItemService.delete(id);
			return ResponseEntity.status(HttpStatus.OK).body("Cart Item deleted successfully.");
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@PutMapping("/cartitens/{id}")
	public ResponseEntity<Object> updateUser(@PathVariable(value="id") Long id,
												@RequestBody @Valid CartItemRecordDto cartItemRecordDto) {
		try {
			CartItemModel updatedCartItem = cartItemService.update(id, cartItemRecordDto);
			return ResponseEntity.status(HttpStatus.OK).body(updatedCartItem);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

}
