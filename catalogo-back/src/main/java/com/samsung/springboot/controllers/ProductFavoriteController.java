package com.samsung.springboot.controllers;

import com.samsung.springboot.dtos.ProductFavoriteRecordDto;
import com.samsung.springboot.exceptions.ResourceNotFoundException;
import com.samsung.springboot.models.CartModel;
import com.samsung.springboot.models.ProductFavoriteModel;
import com.samsung.springboot.services.ProductFavoriteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1_0")
public class ProductFavoriteController {
	
	@Autowired
	ProductFavoriteService productFavoriteService;
	
	@GetMapping("/productfavorite")
	public ResponseEntity<List<ProductFavoriteModel>> getAllProducts(){
		List<ProductFavoriteModel> productFavorites = productFavoriteService.getAll();
		return ResponseEntity.status(HttpStatus.OK).body(productFavorites);
	}

	@GetMapping("/productfavorite/search")
	public List<ProductFavoriteModel> getProductsByUserId(
			@RequestParam(required = false) Long id_user) {
		return productFavoriteService.findByUserId(id_user);
	}

	@GetMapping("/productfavorite/{id}")
	public ResponseEntity<Object> getOneProduct(@PathVariable(value="id") Long id){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(productFavoriteService.getOne(id));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@PostMapping("/productfavorite")
	public ResponseEntity<ProductFavoriteModel> saveProduct(@RequestBody @Valid ProductFavoriteRecordDto productFavoriteRecordDto) {
		ProductFavoriteModel savedProductFavorite = productFavoriteService.save(productFavoriteRecordDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedProductFavorite);
	}

	@DeleteMapping("/productfavorite/{id}")
	public ResponseEntity<Object> deleteProduct(@PathVariable(value="id") Long id) {
		try {
			productFavoriteService.delete(id);
			return ResponseEntity.status(HttpStatus.OK).body("Product Favorite deleted successfully.");
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@PutMapping("/productfavorite/{id}")
	public ResponseEntity<Object> updateProduct(@PathVariable(value="id") Long id,
												@RequestBody @Valid ProductFavoriteRecordDto productFavoriteRecordDto) {
		try {
			ProductFavoriteModel updatedProductFavorite = productFavoriteService.update(id, productFavoriteRecordDto);
			return ResponseEntity.status(HttpStatus.OK).body(updatedProductFavorite);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

}
