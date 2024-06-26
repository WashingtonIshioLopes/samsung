package com.samsung.springboot.services;

import com.samsung.springboot.dtos.PaymentRecordDto;
import com.samsung.springboot.exceptions.ResourceNotFoundException;
import com.samsung.springboot.models.PaymentModel;
import com.samsung.springboot.repositories.PaymentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public List<PaymentModel> getAll() {
        return paymentRepository.findAll();
    }

    public Optional<PaymentModel> getOne(Long id) {
        Optional<PaymentModel> paymentModel = paymentRepository.findById(id);
        if (paymentModel.isEmpty()) {
            throw new ResourceNotFoundException("Payment not found with id: " + id);
        }
        return paymentModel;
    }

    public PaymentModel save(PaymentRecordDto paymentRecordDto) {
        PaymentModel paymentModel = new PaymentModel();
        BeanUtils.copyProperties(paymentRecordDto, paymentModel);
        return paymentRepository.save(paymentModel);
    }

    public void delete(Long id) {
        Optional<PaymentModel> paymentModel = paymentRepository.findById(id);
        if (paymentModel.isEmpty()) {
            throw new ResourceNotFoundException("Payment not found with id: " + id);
        }
        paymentModel.ifPresent(payment -> paymentRepository.delete(payment));
    }

    public PaymentModel update(Long id, PaymentRecordDto paymentRecordDto) {
        Optional<PaymentModel> paymentOptional = paymentRepository.findById(id);
        if (paymentOptional.isEmpty()) {
            throw new ResourceNotFoundException("Payment not found with id: " + id);
        }
        PaymentModel paymentModel = paymentOptional.get();
        BeanUtils.copyProperties(paymentRecordDto, paymentModel);
        return paymentRepository.save(paymentModel);
    }
}
