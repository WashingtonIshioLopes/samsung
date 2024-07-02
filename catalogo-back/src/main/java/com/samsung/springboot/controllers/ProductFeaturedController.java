package com.samsung.springboot.controllers;

import com.samsung.springboot.dtos.ProductFeaturedRecordDto;
import com.samsung.springboot.exceptions.ResourceNotFoundException;
import com.samsung.springboot.models.ProductFeaturedModel;
import com.samsung.springboot.services.ProductFeaturedService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1_0")
public class ProductFeaturedController {
	
	@Autowired
	ProductFeaturedService productFeaturedService;
	
	@GetMapping("/productfeatured")
	public ResponseEntity<List<ProductFeaturedModel>> getAllProducts(){
		List<ProductFeaturedModel> productFeatureds = productFeaturedService.getAll();
		return ResponseEntity.status(HttpStatus.OK).body(productFeatureds);
	}

	@GetMapping("/productfeatured/{id}")
	public ResponseEntity<Object> getOneProduct(@PathVariable(value="id") Long id){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(productFeaturedService.getOne(id));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@PostMapping("/productfeatured")
	public ResponseEntity<ProductFeaturedModel> saveProduct(@RequestBody @Valid ProductFeaturedRecordDto productFeaturedRecordDto) {
		ProductFeaturedModel savedProductFeatured = productFeaturedService.save(productFeaturedRecordDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedProductFeatured);
	}

	@DeleteMapping("/productfeatured/{id}")
	public ResponseEntity<Object> deleteProduct(@PathVariable(value="id") Long id) {
		try {
			productFeaturedService.delete(id);
			return ResponseEntity.status(HttpStatus.OK).body("Product Featured deleted successfully.");
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@PutMapping("/productfeatured/{id}")
	public ResponseEntity<Object> updateProduct(@PathVariable(value="id") Long id,
												@RequestBody @Valid ProductFeaturedRecordDto productFeaturedRecordDto) {
		try {
			ProductFeaturedModel updatedProductFeatured = productFeaturedService.update(id, productFeaturedRecordDto);
			return ResponseEntity.status(HttpStatus.OK).body(updatedProductFeatured);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

}
