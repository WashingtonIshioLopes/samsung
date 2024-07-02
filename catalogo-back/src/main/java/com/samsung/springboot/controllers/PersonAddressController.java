package com.samsung.springboot.controllers;

import com.samsung.springboot.dtos.PersonAddressRecordDto;
import com.samsung.springboot.exceptions.ResourceNotFoundException;
import com.samsung.springboot.models.PersonAddressModel;
import com.samsung.springboot.services.PersonAddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1_0")
public class PersonAddressController {
	
	@Autowired
	PersonAddressService personAddressService;
	
	@GetMapping("/personaddress")
	public ResponseEntity<List<PersonAddressModel>> getAllOrders(){
		List<PersonAddressModel> orders = personAddressService.getAll();
		return ResponseEntity.status(HttpStatus.OK).body(orders);
	}

	@GetMapping("/personaddress/{id}")
	public ResponseEntity<Object> getOneOrder(@PathVariable(value="id") Long id){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(personAddressService.getOne(id));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@PostMapping("/personaddress")
	public ResponseEntity<PersonAddressModel> saveOrder(@RequestBody @Valid PersonAddressRecordDto personAddressRecordDto) {
		PersonAddressModel savedOrder = personAddressService.save(personAddressRecordDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder);
	}

	@DeleteMapping("/personaddress/{id}")
	public ResponseEntity<Object> deleteOrder(@PathVariable(value="id") Long id) {
		try {
			personAddressService.delete(id);
			return ResponseEntity.status(HttpStatus.OK).body("Person Address deleted successfully.");
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@PutMapping("/personaddress/{id}")
	public ResponseEntity<Object> updateOrder(@PathVariable(value="id") Long id,
											 @RequestBody @Valid PersonAddressRecordDto personAddressRecordDto) {
		try {
			PersonAddressModel updatedOrder = personAddressService.update(id, personAddressRecordDto);
			return ResponseEntity.status(HttpStatus.OK).body(updatedOrder);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

}
