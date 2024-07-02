package com.samsung.springboot.controllers;

import com.samsung.springboot.dtos.ProductImageRecordDto;
import com.samsung.springboot.exceptions.ResourceNotFoundException;
import com.samsung.springboot.models.ProductImageModel;
import com.samsung.springboot.services.ProductImageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1_0")
public class ProductImageController {
	
	@Autowired
	ProductImageService productImageService;
	
	@GetMapping("/productimages")
	public ResponseEntity<List<ProductImageModel>> getAllProducts(){
		List<ProductImageModel> productImages = productImageService.getAll();
		return ResponseEntity.status(HttpStatus.OK).body(productImages);
	}

	@GetMapping("/productimages/{id}")
	public ResponseEntity<Object> getOneProduct(@PathVariable(value="id") Long id){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(productImageService.getOne(id));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@PostMapping("/productimages")
	public ResponseEntity<ProductImageModel> saveProduct(@RequestBody @Valid ProductImageRecordDto productImageRecordDto) {
		ProductImageModel savedProductImage = productImageService.save(productImageRecordDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedProductImage);
	}

	@DeleteMapping("/productimages/{id}")
	public ResponseEntity<Object> deleteProduct(@PathVariable(value="id") Long id) {
		try {
			productImageService.delete(id);
			return ResponseEntity.status(HttpStatus.OK).body("Product Image deleted successfully.");
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@PutMapping("/productimages/{id}")
	public ResponseEntity<Object> updateProduct(@PathVariable(value="id") Long id,
												@RequestBody @Valid ProductImageRecordDto productImageRecordDto) {
		try {
			ProductImageModel updatedProductImage = productImageService.update(id, productImageRecordDto);
			return ResponseEntity.status(HttpStatus.OK).body(updatedProductImage);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

}
