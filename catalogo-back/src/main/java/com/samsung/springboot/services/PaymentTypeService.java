package com.samsung.springboot.services;

import com.samsung.springboot.dtos.PaymentTypeRecordDto;
import com.samsung.springboot.exceptions.ResourceNotFoundException;
import com.samsung.springboot.models.PaymentTypeModel;
import com.samsung.springboot.repositories.PaymentTypeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PaymentTypeService {

    @Autowired
    private PaymentTypeRepository paymentRepository;

    public List<PaymentTypeModel> getAll() {
        return paymentRepository.findAll();
    }

    public Optional<PaymentTypeModel> getOne(Long id) {
        Optional<PaymentTypeModel> paymentModel = paymentRepository.findById(id);
        if (paymentModel.isEmpty()) {
            throw new ResourceNotFoundException("Payment not found with id: " + id);
        }
        return paymentModel;
    }

    public PaymentTypeModel save(PaymentTypeRecordDto paymentRecordDto) {
        PaymentTypeModel paymentModel = new PaymentTypeModel();
        BeanUtils.copyProperties(paymentRecordDto, paymentModel);
        paymentModel.setCreatedAt(LocalDateTime.now());
        return paymentRepository.save(paymentModel);
    }

    public void delete(Long id) {
        Optional<PaymentTypeModel> paymentModel = paymentRepository.findById(id);
        if (paymentModel.isEmpty()) {
            throw new ResourceNotFoundException("Payment not found with id: " + id);
        }
        paymentModel.ifPresent(payment -> paymentRepository.delete(payment));
    }

    public PaymentTypeModel update(Long id, PaymentTypeRecordDto paymentRecordDto) {
        Optional<PaymentTypeModel> paymentOptional = paymentRepository.findById(id);
        if (paymentOptional.isEmpty()) {
            throw new ResourceNotFoundException("Payment not found with id: " + id);
        }
        PaymentTypeModel paymentModel = paymentOptional.get();
        BeanUtils.copyProperties(paymentRecordDto, paymentModel);
        paymentModel.setUpdatedAt(LocalDateTime.now());
        return paymentRepository.save(paymentModel);
    }
}
