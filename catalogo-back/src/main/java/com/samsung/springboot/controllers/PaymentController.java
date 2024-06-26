package com.samsung.springboot.controllers;

import com.samsung.springboot.dtos.PaymentRecordDto;
import com.samsung.springboot.exceptions.ResourceNotFoundException;
import com.samsung.springboot.models.PaymentModel;
import com.samsung.springboot.services.PaymentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1_0")
public class PaymentController {
	
	@Autowired
	PaymentService paymentService;
	
	@GetMapping("/payments")
	public ResponseEntity<List<PaymentModel>> getAllPayments(){
		List<PaymentModel> payments = paymentService.getAll();
		return ResponseEntity.status(HttpStatus.OK).body(payments);
	}

	@GetMapping("/payments/{id}")
	public ResponseEntity<Object> getOnePayment(@PathVariable(value="id") Long id){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(paymentService.getOne(id));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@PostMapping("/payments")
	public ResponseEntity<PaymentModel> savePayment(@RequestBody @Valid PaymentRecordDto paymentRecordDto) {
		PaymentModel savedPayment = paymentService.save(paymentRecordDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedPayment);
	}

	@DeleteMapping("/payments/{id}")
	public ResponseEntity<Object> deletePayment(@PathVariable(value="id") Long id) {
		try {
			paymentService.delete(id);
			return ResponseEntity.status(HttpStatus.OK).body("Payment deleted successfully.");
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@PutMapping("/payments/{id}")
	public ResponseEntity<Object> updatePayment(@PathVariable(value="id") Long id,
											 @RequestBody @Valid PaymentRecordDto paymentRecordDto) {
		try {
			PaymentModel updatedPayment = paymentService.update(id, paymentRecordDto);
			return ResponseEntity.status(HttpStatus.OK).body(updatedPayment);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

}
