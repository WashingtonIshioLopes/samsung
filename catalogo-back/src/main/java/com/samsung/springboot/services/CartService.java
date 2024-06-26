package com.samsung.springboot.services;

import com.samsung.springboot.dtos.CartRecordDto;
import com.samsung.springboot.exceptions.ResourceNotFoundException;
import com.samsung.springboot.models.CartModel;
import com.samsung.springboot.repositories.CartRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public List<CartModel> getAll() {
        return cartRepository.findAll();
    }

    public Optional<CartModel> getOne(Long id) {
        Optional<CartModel> cartOptional = cartRepository.findById(id);
        if (cartOptional.isEmpty()) {
            throw new ResourceNotFoundException("Cart not found with id: " + id);
        }
        return cartOptional;
    }

    public CartModel save(CartRecordDto cartRecordDto) {
        CartModel cartModel = new CartModel();
        BeanUtils.copyProperties(cartRecordDto, cartModel);
        return cartRepository.save(cartModel);
    }

    public void delete(Long id) {
        Optional<CartModel> cartOptional = cartRepository.findById(id);
        if (cartOptional.isEmpty()) {
            throw new ResourceNotFoundException("Cart not found with id: " + id);
        }
        cartOptional.ifPresent(order -> cartRepository.delete(order));
    }

    public CartModel update(Long id, CartRecordDto cartRecordDto) {
        Optional<CartModel> cartOptional = cartRepository.findById(id);
        if (cartOptional.isEmpty()) {
            throw new ResourceNotFoundException("Cart not found with id: " + id);
        }
        CartModel cartModel = cartOptional.get();
        BeanUtils.copyProperties(cartRecordDto, cartModel);
        return cartRepository.save(cartModel);
    }
}
