package com.samsung.springboot.controllers;

import com.samsung.springboot.dtos.OrderItemRecordDto;
import com.samsung.springboot.exceptions.ResourceNotFoundException;
import com.samsung.springboot.models.OrderItemModel;
import com.samsung.springboot.services.OrderItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1_0")
public class OrderItemController {
	
	@Autowired
	OrderItemService orderItemService;
	
	@GetMapping("/orderitens")
	public ResponseEntity<List<OrderItemModel>> getAllUsers(){
		List<OrderItemModel> users = orderItemService.getAll();
		return ResponseEntity.status(HttpStatus.OK).body(users);
	}

	@GetMapping("/orderitens/{id}")
	public ResponseEntity<Object> getOneUser(@PathVariable(value="id") Long id){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(orderItemService.getOne(id));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@PostMapping("/orderitens")
	public ResponseEntity<OrderItemModel> saveUser(@RequestBody @Valid OrderItemRecordDto orderItemRecordDto) {
		OrderItemModel savedOrderItem = orderItemService.save(orderItemRecordDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedOrderItem);
	}

	@DeleteMapping("/orderitens/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable(value="id") Long id) {
		try {
			orderItemService.delete(id);
			return ResponseEntity.status(HttpStatus.OK).body("Order Item deleted successfully.");
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@PutMapping("/orderitens/{id}")
	public ResponseEntity<Object> updateUser(@PathVariable(value="id") Long id,
												@RequestBody @Valid OrderItemRecordDto orderItemRecordDto) {
		try {
			OrderItemModel updatedOrderItem = orderItemService.update(id, orderItemRecordDto);
			return ResponseEntity.status(HttpStatus.OK).body(updatedOrderItem);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

}
