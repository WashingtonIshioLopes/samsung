package com.samsung.springboot.controllers;

import com.samsung.springboot.dtos.CategoryRecordDto;
import com.samsung.springboot.exceptions.ResourceNotFoundException;
import com.samsung.springboot.models.CategoryModel;
import com.samsung.springboot.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1_0")
public class CategoryController {
	
	@Autowired
	CategoryService productCategoryService;
	
	@GetMapping("/categories")
	public ResponseEntity<List<CategoryModel>> getAllUnits(){
		List<CategoryModel> productCategorys = productCategoryService.getAll();
		return ResponseEntity.status(HttpStatus.OK).body(productCategorys);
	}

	@GetMapping("/categories/{id}")
	public ResponseEntity<Object> getOneUnit(@PathVariable(value="id") Long id){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(productCategoryService.getOne(id));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@PostMapping("/categories")
	public ResponseEntity<CategoryModel> saveOrder(@RequestBody @Valid CategoryRecordDto productCategoryRecordDto) {
		CategoryModel savedProductCategory = productCategoryService.save(productCategoryRecordDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedProductCategory);
	}

	@DeleteMapping("/categories/{id}")
	public ResponseEntity<Object> deleteOrder(@PathVariable(value="id") Long id) {
		try {
			productCategoryService.delete(id);
			return ResponseEntity.status(HttpStatus.OK).body("Product Category deleted successfully.");
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@PutMapping("/categories/{id}")
	public ResponseEntity<Object> updateOrder(@PathVariable(value="id") Long id,
											 @RequestBody @Valid CategoryRecordDto productCategoryRecordDto) {
		try {
			CategoryModel updatedProductCategory = productCategoryService.update(id, productCategoryRecordDto);
			return ResponseEntity.status(HttpStatus.OK).body(updatedProductCategory);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

}
