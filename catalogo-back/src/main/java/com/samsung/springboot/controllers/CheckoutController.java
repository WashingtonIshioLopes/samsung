package com.samsung.springboot.controllers;

import com.samsung.springboot.dtos.CheckoutRecordDto;
import com.samsung.springboot.exceptions.ResourceNotFoundException;
import com.samsung.springboot.models.CheckoutModel;
import com.samsung.springboot.services.CheckoutService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1_0")
public class CheckoutController {
	
	@Autowired
	CheckoutService checkoutService;
	
	@GetMapping("/checkout")
	public ResponseEntity<List<CheckoutModel>> getAllOrders(){
		List<CheckoutModel> checkouts = checkoutService.getAll();
		return ResponseEntity.status(HttpStatus.OK).body(checkouts);
	}

	@GetMapping("/checkout/{id}")
	public ResponseEntity<Object> getOneOrder(@PathVariable(value="id") Long id){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(checkoutService.getOne(id));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@PostMapping("/checkout")
	public ResponseEntity<CheckoutModel> saveOrder(@RequestBody @Valid CheckoutRecordDto checkoutRecordDto) {
		CheckoutModel savedCheckout = checkoutService.save(checkoutRecordDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedCheckout);
	}

	@DeleteMapping("/checkout/{id}")
	public ResponseEntity<Object> deleteOrder(@PathVariable(value="id") Long id) {
		try {
			checkoutService.delete(id);
			return ResponseEntity.status(HttpStatus.OK).body("Checkout deleted successfully.");
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@PutMapping("/checkout/{id}")
	public ResponseEntity<Object> updateOrder(@PathVariable(value="id") Long id,
											 @RequestBody @Valid CheckoutRecordDto checkoutRecordDto) {
		try {
			CheckoutModel updatedCheckout = checkoutService.update(id, checkoutRecordDto);
			return ResponseEntity.status(HttpStatus.OK).body(updatedCheckout);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

}
