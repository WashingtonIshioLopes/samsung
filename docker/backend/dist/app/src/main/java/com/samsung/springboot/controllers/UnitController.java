package com.samsung.springboot.controllers;

import com.samsung.springboot.dtos.UnitRecordDto;
import com.samsung.springboot.exceptions.ResourceNotFoundException;
import com.samsung.springboot.models.UnitModel;
import com.samsung.springboot.services.UnitService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1_0")
public class UnitController {
	
	@Autowired
	UnitService unitService;
	
	@GetMapping("/units")
	public ResponseEntity<List<UnitModel>> getAllUnits(){
		List<UnitModel> units = unitService.getAll();
		return ResponseEntity.status(HttpStatus.OK).body(units);
	}

	@GetMapping("/units/{id}")
	public ResponseEntity<Object> getOneUnit(@PathVariable(value="id") Long id){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(unitService.getOne(id));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@PostMapping("/units")
	public ResponseEntity<UnitModel> saveOrder(@RequestBody @Valid UnitRecordDto unitRecordDto) {
		UnitModel savedUnit = unitService.save(unitRecordDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedUnit);
	}

	@DeleteMapping("/units/{id}")
	public ResponseEntity<Object> deleteOrder(@PathVariable(value="id") Long id) {
		try {
			unitService.delete(id);
			return ResponseEntity.status(HttpStatus.OK).body("Unit deleted successfully.");
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@PutMapping("/units/{id}")
	public ResponseEntity<Object> updateOrder(@PathVariable(value="id") Long id,
											 @RequestBody @Valid UnitRecordDto unitRecordDto) {
		try {
			UnitModel updatedUnit = unitService.update(id, unitRecordDto);
			return ResponseEntity.status(HttpStatus.OK).body(updatedUnit);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

}
