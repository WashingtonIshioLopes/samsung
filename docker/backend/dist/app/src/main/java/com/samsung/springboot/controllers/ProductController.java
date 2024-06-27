package com.samsung.springboot.controllers;

import com.samsung.springboot.dtos.ProductRecordDto;
import com.samsung.springboot.exceptions.ResourceNotFoundException;
import com.samsung.springboot.models.ProductModel;
import com.samsung.springboot.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1_0")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/products")
	public ResponseEntity<List<ProductModel>> getAllProducts(){
		List<ProductModel> products = productService.getAll();
		return ResponseEntity.status(HttpStatus.OK).body(products);
	}

	@GetMapping("/products/search")
	public List<ProductModel> getProductsByCategoryIdAndDescription(
			@RequestParam(required = false) Long id_category,
			@RequestParam(required = false) String description) {
		return productService.getByCategoryIdAndDescription(id_category, description);
	}

	@GetMapping("/products/{id}")
	public ResponseEntity<Object> getOneProduct(@PathVariable(value="id") Long id){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(productService.getOne(id));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@PostMapping("/products")
	public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductRecordDto productRecordDto) {
		ProductModel savedProduct = productService.save(productRecordDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
	}

	@DeleteMapping("/products/{id}")
	public ResponseEntity<Object> deleteProduct(@PathVariable(value="id") Long id) {
		try {
			productService.delete(id);
			return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully.");
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@PutMapping("/products/{id}")
	public ResponseEntity<Object> updateProduct(@PathVariable(value="id") Long id,
												@RequestBody @Valid ProductRecordDto productRecordDto) {
		try {
			ProductModel updatedProduct = productService.update(id, productRecordDto);
			return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

}
