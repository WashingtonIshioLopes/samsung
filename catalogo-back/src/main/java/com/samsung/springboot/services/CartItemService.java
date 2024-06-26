package com.samsung.springboot.services;

import com.samsung.springboot.dtos.CartItemRecordDto;
import com.samsung.springboot.exceptions.ResourceNotFoundException;
import com.samsung.springboot.models.CartItemModel;
import com.samsung.springboot.repositories.CartItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    public List<CartItemModel> getAll() {
        return cartItemRepository.findAll();
    }

    public Optional<CartItemModel> getOne(Long id) {
        Optional<CartItemModel> cartItemOptional = cartItemRepository.findById(id);
        if (cartItemOptional.isEmpty()) {
            throw new ResourceNotFoundException("Order Item not found with id: " + id);
        }
        return cartItemOptional;
    }

    public CartItemModel save(CartItemRecordDto cartItemRecordDto) {
        CartItemModel cartItemModel = new CartItemModel();
        BeanUtils.copyProperties(cartItemRecordDto, cartItemModel);
        return cartItemRepository.save(cartItemModel);
    }

    public void delete(Long id) {
        Optional<CartItemModel> cartItemOptional = cartItemRepository.findById(id);
        if (cartItemOptional.isEmpty()) {
            throw new ResourceNotFoundException("Order Item not found with id: " + id);
        }
        cartItemOptional.ifPresent(cart_item -> cartItemRepository.delete(cart_item));
    }

    public CartItemModel update(Long id, CartItemRecordDto cartItemRecordDto) {
        Optional<CartItemModel> cartItemOptional = cartItemRepository.findById(id);
        if (cartItemOptional.isEmpty()) {
            throw new ResourceNotFoundException("Order Item not found with id: " + id);
        }
        CartItemModel cartItemModel = cartItemOptional.get();
        BeanUtils.copyProperties(cartItemRecordDto, cartItemModel);
        return cartItemRepository.save(cartItemModel);
    }
}
