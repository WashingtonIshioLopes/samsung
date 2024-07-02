package com.samsung.springboot.services;

import com.samsung.springboot.dtos.CheckoutRecordDto;
import com.samsung.springboot.exceptions.ResourceNotFoundException;
import com.samsung.springboot.models.CartModel;
import com.samsung.springboot.models.CheckoutModel;
import com.samsung.springboot.models.PaymentTypeModel;
import com.samsung.springboot.models.PersonModel;
import com.samsung.springboot.repositories.CartRepository;
import com.samsung.springboot.repositories.CheckoutRepository;
import com.samsung.springboot.repositories.PaymentTypeRepository;
import com.samsung.springboot.repositories.PersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CheckoutService {

    @Autowired
    private CheckoutRepository checkoutRepository;
    @Autowired
    private PersonRepository userRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private PaymentTypeRepository paymentTypeRepository;

    public List<CheckoutModel> getAll() {
        return checkoutRepository.findAll();
    }

    public Optional<CheckoutModel> getOne(Long id) {
        Optional<CheckoutModel> checkoutOptional = checkoutRepository.findById(id);
        if (checkoutOptional.isEmpty()) {
            throw new ResourceNotFoundException("Checkout not found with id: " + id);
        }
        return checkoutOptional;
    }

    public CheckoutModel save(CheckoutRecordDto checkoutRecordDto) {

        Optional<CartModel> cartOptional = cartRepository.findById(checkoutRecordDto.id_cart());
        if (cartOptional.isEmpty()) {
            throw new ResourceNotFoundException("Cart not found with id: " + checkoutRecordDto.id_cart());
        }

        Optional<PaymentTypeModel> paymentOptional = paymentTypeRepository.findById(checkoutRecordDto.id_payment());
        if (paymentOptional.isEmpty()) {
            throw new ResourceNotFoundException("Payment not found with id: " + checkoutRecordDto.id_payment());
        }

        CheckoutModel checkoutModel = new CheckoutModel();
        BeanUtils.copyProperties(checkoutRecordDto, checkoutModel);
        checkoutModel.setId_cart(checkoutRecordDto.id_cart());
        checkoutModel.setId_payment(checkoutRecordDto.id_payment());
        checkoutModel.setCreatedAt(LocalDateTime.now());

        return checkoutRepository.save(checkoutModel);
    }

    public void delete(Long id) {
        Optional<CheckoutModel> checkoutOptional = checkoutRepository.findById(id);
        if (checkoutOptional.isEmpty()) {
            throw new ResourceNotFoundException("Checkout not found with id: " + id);
        }
        checkoutOptional.ifPresent(order -> checkoutRepository.delete(order));
    }

    public CheckoutModel update(Long id, CheckoutRecordDto checkoutRecordDto) {
        Optional<CheckoutModel> checkoutOptional = checkoutRepository.findById(id);
        if (checkoutOptional.isEmpty()) {
            throw new ResourceNotFoundException("Order not found with id: " + id);
        }

        Optional<CartModel> cartOptional = cartRepository.findById(checkoutRecordDto.id_cart());
        if (cartOptional.isEmpty()) {
            throw new ResourceNotFoundException("Cart not found with id: " + checkoutRecordDto.id_cart());
        }

        Optional<PaymentTypeModel> paymentOptional = paymentTypeRepository.findById(checkoutRecordDto.id_payment());
        if (paymentOptional.isEmpty()) {
            throw new ResourceNotFoundException("Payment not found with id: " + checkoutRecordDto.id_payment());
        }

        CheckoutModel checkoutModel = checkoutOptional.get();
        BeanUtils.copyProperties(checkoutRecordDto, checkoutModel);
        checkoutModel.setId_cart(checkoutRecordDto.id_cart());
        checkoutModel.setId_payment(checkoutRecordDto.id_payment());
        checkoutModel.setUpdatedAt(LocalDateTime.now());

        return checkoutRepository.save(checkoutModel);
    }
}
