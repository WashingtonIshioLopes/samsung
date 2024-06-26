package com.samsung.springboot.services;

import com.samsung.springboot.dtos.OrderRecordDto;
import com.samsung.springboot.exceptions.ResourceNotFoundException;
import com.samsung.springboot.models.OrderModel;
import com.samsung.springboot.repositories.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

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
        OrderModel orderModel = new OrderModel();
        BeanUtils.copyProperties(orderRecordDto, orderModel);
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
        OrderModel orderModel = orderOptional.get();
        BeanUtils.copyProperties(orderRecordDto, orderModel);
        return orderRepository.save(orderModel);
    }
}
