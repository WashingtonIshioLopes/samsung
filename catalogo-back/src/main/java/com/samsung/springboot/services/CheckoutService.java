package com.samsung.springboot.services;

import com.samsung.springboot.dtos.CheckoutRecordDto;
import com.samsung.springboot.exceptions.ResourceNotFoundException;
import com.samsung.springboot.models.CheckoutModel;
import com.samsung.springboot.models.PersonModel;
import com.samsung.springboot.repositories.CheckoutRepository;
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

        Optional<PersonModel> userOptional = userRepository.findById(checkoutRecordDto.id_user());
        if (userOptional.isEmpty()) {
            throw new ResourceNotFoundException("User not found with id: " + checkoutRecordDto.id_user());
        }

        CheckoutModel checkoutModel = new CheckoutModel();
        BeanUtils.copyProperties(checkoutRecordDto, checkoutModel);
        checkoutModel.setUser(userOptional.get());
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

        Optional<PersonModel> userOptional = userRepository.findById(checkoutRecordDto.id_user());
        if (userOptional.isEmpty()) {
            throw new ResourceNotFoundException("User not found with id: " + checkoutRecordDto.id_user());
        }

        CheckoutModel checkoutModel = checkoutOptional.get();
        BeanUtils.copyProperties(checkoutRecordDto, checkoutModel);
        checkoutModel.setUser(userOptional.get());
        checkoutModel.setUpdatedAt(LocalDateTime.now());

        return checkoutRepository.save(checkoutModel);
    }
}
