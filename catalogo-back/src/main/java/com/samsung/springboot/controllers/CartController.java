package com.samsung.springboot.controllers;

import com.samsung.springboot.dtos.CartRecordDto;
import com.samsung.springboot.exceptions.ResourceNotFoundException;
import com.samsung.springboot.models.CartModel;
import com.samsung.springboot.models.ProductModel;
import com.samsung.springboot.services.CartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1_0")
public class CartController {
	
	@Autowired
	CartService cartService;
	
	@GetMapping("/carts")
	public ResponseEntity<List<CartModel>> getAllCarts(){
		List<CartModel> carts = cartService.getAll();
		return ResponseEntity.status(HttpStatus.OK).body(carts);
	}

	@GetMapping("/carts/search")
	public List<CartModel> getCartsByUserIdAndStatus(
			@RequestParam(required = false) Long id_user,
			@RequestParam(required = false) String status) {
		return cartService.findByUserIdAndStatus(id_user, status);
	}

	@GetMapping("/carts/{id}")
	public ResponseEntity<Object> getOneCart(@PathVariable(value="id") Long id){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(cartService.getOne(id));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@PostMapping("/carts")
	public ResponseEntity<CartModel> saveCart(@RequestBody @Valid CartRecordDto cartRecordDto) {
		CartModel savedCart = cartService.save(cartRecordDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedCart);
	}

	@DeleteMapping("/carts/{id}")
	public ResponseEntity<Object> deleteCart(@PathVariable(value="id") Long id) {
		try {
			cartService.delete(id);
			return ResponseEntity.status(HttpStatus.OK).body("Cart deleted successfully.");
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@PutMapping("/carts/{id}")
	public ResponseEntity<Object> updateCart(@PathVariable(value="id") Long id,
											 @RequestBody @Valid CartRecordDto cartRecordDto) {
		try {
			CartModel updatedCart = cartService.update(id, cartRecordDto);
			return ResponseEntity.status(HttpStatus.OK).body(updatedCart);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

}
