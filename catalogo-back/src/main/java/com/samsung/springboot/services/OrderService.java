package com.samsung.springboot.services;

import com.samsung.springboot.dtos.OrderRecordDto;
import com.samsung.springboot.exceptions.ResourceNotFoundException;
import com.samsung.springboot.models.CheckoutModel;
import com.samsung.springboot.models.OrderModel;
import com.samsung.springboot.models.PersonModel;
import com.samsung.springboot.repositories.CheckoutRepository;
import com.samsung.springboot.repositories.OrderRepository;
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
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private PersonRepository userRepository;
    @Autowired
    private CheckoutRepository checkoutRepository;

    public List<OrderModel> getAll() {
        return orderRepository.findAll();
    }

    public Optional<OrderModel> getOne(Long id) {
        Optional<OrderModel> orderOptional = orderRepository.findById(id);
        if (orderOptional.isEmpty()) {
            throw new ResourceNotFoundException("Order not found with id: " + id);
        }
        return orderOptional;
    }

    public OrderModel save(OrderRecordDto orderRecordDto) {

        Optional<CheckoutModel> checkoutOptional = checkoutRepository.findById(orderRecordDto.id_ckeckout());
        if (checkoutOptional.isEmpty()) {
            throw new ResourceNotFoundException("Checkout not found with id: " + orderRecordDto.id_ckeckout());
        }

        OrderModel orderModel = new OrderModel();
        BeanUtils.copyProperties(orderRecordDto, orderModel);
        orderModel.setId_checkout(orderRecordDto.id_ckeckout());
        orderModel.setCreatedAt(LocalDateTime.now());

        return orderRepository.save(orderModel);
    }

    public void delete(Long id) {
        Optional<OrderModel> orderOptional = orderRepository.findById(id);
        if (orderOptional.isEmpty()) {
            throw new ResourceNotFoundException("Order not found with id: " + id);
        }
        orderOptional.ifPresent(order -> orderRepository.delete(order));
    }

    public OrderModel update(Long id, OrderRecordDto orderRecordDto) {
        Optional<OrderModel> orderOptional = orderRepository.findById(id);
        if (orderOptional.isEmpty()) {
            throw new ResourceNotFoundException("Order not found with id: " + id);
        }

        Optional<CheckoutModel> checkoutOptional = checkoutRepository.findById(orderRecordDto.id_ckeckout());
        if (checkoutOptional.isEmpty()) {
            throw new ResourceNotFoundException("Checkout not found with id: " + orderRecordDto.id_ckeckout());
        }

        OrderModel orderModel = orderOptional.get();
        BeanUtils.copyProperties(orderRecordDto, orderModel);
        orderModel.setId_checkout(orderRecordDto.id_ckeckout());
        orderModel.setUpdatedAt(LocalDateTime.now());

        return orderRepository.save(orderModel);
    }
}
