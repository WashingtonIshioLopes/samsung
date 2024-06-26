package com.samsung.springboot.controllers;

import com.samsung.springboot.dtos.OrderRecordDto;
import com.samsung.springboot.exceptions.ResourceNotFoundException;
import com.samsung.springboot.models.OrderModel;
import com.samsung.springboot.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1_0")
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@GetMapping("/orders")
	public ResponseEntity<List<OrderModel>> getAllOrders(){
		List<OrderModel> orders = orderService.getAll();
		return ResponseEntity.status(HttpStatus.OK).body(orders);
	}

	@GetMapping("/orders/{id}")
	public ResponseEntity<Object> getOneOrder(@PathVariable(value="id") Long id){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(orderService.getOne(id));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@PostMapping("/orders")
	public ResponseEntity<OrderModel> saveOrder(@RequestBody @Valid OrderRecordDto orderRecordDto) {
		OrderModel savedOrder = orderService.save(orderRecordDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder);
	}

	@DeleteMapping("/orders/{id}")
	public ResponseEntity<Object> deleteOrder(@PathVariable(value="id") Long id) {
		try {
			orderService.delete(id);
			return ResponseEntity.status(HttpStatus.OK).body("Order deleted successfully.");
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@PutMapping("/orders/{id}")
	public ResponseEntity<Object> updateOrder(@PathVariable(value="id") Long id,
											 @RequestBody @Valid OrderRecordDto orderRecordDto) {
		try {
			OrderModel updatedOrder = orderService.update(id, orderRecordDto);
			return ResponseEntity.status(HttpStatus.OK).body(updatedOrder);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

}
