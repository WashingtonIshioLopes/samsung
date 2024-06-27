package com.samsung.springboot.services;

import com.samsung.springboot.dtos.OrderItemRecordDto;
import com.samsung.springboot.exceptions.ResourceNotFoundException;
import com.samsung.springboot.models.OrderItemModel;
import com.samsung.springboot.models.OrderModel;
import com.samsung.springboot.models.ProductModel;
import com.samsung.springboot.repositories.OrderItemRepository;
import com.samsung.springboot.repositories.OrderRepository;
import com.samsung.springboot.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;

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

        Optional<ProductModel> productOptional = productRepository.findById(orderItemRecordDto.id_product());
        if (productOptional.isEmpty()) {
            throw new ResourceNotFoundException("Product not found with id: " + orderItemRecordDto.id_product());
        }

        Optional<OrderModel> orderOptional = orderRepository.findById(orderItemRecordDto.id_order());
        if (orderOptional.isEmpty()) {
            throw new ResourceNotFoundException("Order not found with id: " + orderItemRecordDto.id_order());
        }

        OrderItemModel orderItemModel = new OrderItemModel();
        BeanUtils.copyProperties(orderItemRecordDto, orderItemModel);
        orderItemModel.setOrder(orderOptional.get());
        orderItemModel.setProduct(productOptional.get());
        orderItemModel.setCreatedAt(LocalDateTime.now());

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
        Optional<ProductModel> productOptional = productRepository.findById(orderItemRecordDto.id_product());
        if (productOptional.isEmpty()) {
            throw new ResourceNotFoundException("Product not found with id: " + orderItemRecordDto.id_product());
        }

        Optional<OrderModel> orderOptional = orderRepository.findById(orderItemRecordDto.id_order());
        if (orderOptional.isEmpty()) {
            throw new ResourceNotFoundException("Order not found with id: " + orderItemRecordDto.id_order());
        }

        OrderItemModel orderItemModel = new OrderItemModel();
        BeanUtils.copyProperties(orderItemRecordDto, orderItemModel);
        orderItemModel.setOrder(orderOptional.get());
        orderItemModel.setProduct(productOptional.get());
        orderItemModel.setUpdatedAt(LocalDateTime.now());

        return orderItemRepository.save(orderItemModel);
    }
}
