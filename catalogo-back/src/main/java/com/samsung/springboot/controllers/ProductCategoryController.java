package com.samsung.springboot.controllers;

import com.samsung.springboot.dtos.ProductCategoryRecordDto;
import com.samsung.springboot.exceptions.ResourceNotFoundException;
import com.samsung.springboot.models.ProductCategoryModel;
import com.samsung.springboot.services.ProductCategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1_0")
public class ProductCategoryController {
	
	@Autowired
	ProductCategoryService productCategoryService;
	
	@GetMapping("/units")
	public ResponseEntity<List<ProductCategoryModel>> getAllUnits(){
		List<ProductCategoryModel> productCategorys = productCategoryService.getAll();
		return ResponseEntity.status(HttpStatus.OK).body(productCategorys);
	}

	@GetMapping("/units/{id}")
	public ResponseEntity<Object> getOneUnit(@PathVariable(value="id") Long id){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(productCategoryService.getOne(id));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@PostMapping("/units")
	public ResponseEntity<ProductCategoryModel> saveOrder(@RequestBody @Valid ProductCategoryRecordDto productCategoryRecordDto) {
		ProductCategoryModel savedProductCategory = productCategoryService.save(productCategoryRecordDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedProductCategory);
	}

	@DeleteMapping("/units/{id}")
	public ResponseEntity<Object> deleteOrder(@PathVariable(value="id") Long id) {
		try {
			productCategoryService.delete(id);
			return ResponseEntity.status(HttpStatus.OK).body("Product Category deleted successfully.");
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@PutMapping("/units/{id}")
	public ResponseEntity<Object> updateOrder(@PathVariable(value="id") Long id,
											 @RequestBody @Valid ProductCategoryRecordDto productCategoryRecordDto) {
		try {
			ProductCategoryModel updatedProductCategory = productCategoryService.update(id, productCategoryRecordDto);
			return ResponseEntity.status(HttpStatus.OK).body(updatedProductCategory);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

}
