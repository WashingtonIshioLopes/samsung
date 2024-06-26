package com.samsung.springboot.services;

import com.samsung.springboot.dtos.OrderItemRecordDto;
import com.samsung.springboot.exceptions.ResourceNotFoundException;
import com.samsung.springboot.models.OrderItemModel;
import com.samsung.springboot.repositories.OrderItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    public List<OrderItemModel> getAll() {
        return orderItemRepository.findAll();
    }

    public Optional<OrderItemModel> getOne(Long id) {
        Optional<OrderItemModel> ordemItemOptional = orderItemRepository.findById(id);
        if (ordemItemOptional.isEmpty()) {
            throw new ResourceNotFoundException("Order Item not found with id: " + id);
        }
        return ordemItemOptional;
    }

    public OrderItemModel save(OrderItemRecordDto orderItemRecordDto) {
        OrderItemModel orderItemModel = new OrderItemModel();
        BeanUtils.copyProperties(orderItemRecordDto, orderItemModel);
        return orderItemRepository.save(orderItemModel);
    }

    public void delete(Long id) {
        Optional<OrderItemModel> ordemItemOptional = orderItemRepository.findById(id);
        if (ordemItemOptional.isEmpty()) {
            throw new ResourceNotFoundException("Order Item not found with id: " + id);
        }
        ordemItemOptional.ifPresent(order_item -> orderItemRepository.delete(order_item));
    }

    public OrderItemModel update(Long id, OrderItemRecordDto orderItemRecordDto) {
        Optional<OrderItemModel> ordemItemOptional = orderItemRepository.findById(id);
        if (ordemItemOptional.isEmpty()) {
            throw new ResourceNotFoundException("Order Item not found with id: " + id);
        }
        OrderItemModel orderItemModel = ordemItemOptional.get();
        BeanUtils.copyProperties(orderItemRecordDto, orderItemModel);
        return orderItemRepository.save(orderItemModel);
    }
}
