package com.samsung.springboot.services;

import com.samsung.springboot.dtos.CartItemRecordDto;
import com.samsung.springboot.exceptions.ResourceNotFoundException;
import com.samsung.springboot.models.CartItemModel;
import com.samsung.springboot.models.CartModel;
import com.samsung.springboot.models.ProductModel;
import com.samsung.springboot.repositories.CartItemRepository;
import com.samsung.springboot.repositories.CartRepository;
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
public class CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;

    public List<CartItemModel> getAll() {
        return cartItemRepository.findAll();
    }

    public Optional<CartItemModel> getOne(Long id) {
        Optional<CartItemModel> cartItemOptional = cartItemRepository.findById(id);
        if (cartItemOptional.isEmpty()) {
            throw new ResourceNotFoundException("Cart Item not found with id: " + id);
        }
        return cartItemOptional;
    }

    public CartItemModel save(CartItemRecordDto cartItemRecordDto) {

        Optional<ProductModel> productOptional = productRepository.findById(cartItemRecordDto.id_product());
        if (productOptional.isEmpty()) {
            throw new ResourceNotFoundException("Product not found with id: " + cartItemRecordDto.id_product());
        }

        Optional<CartModel> cartOptional = cartRepository.findById(cartItemRecordDto.id_cart());
        if (cartOptional.isEmpty()) {
            throw new ResourceNotFoundException("Cart not found with id: " + cartItemRecordDto.id_cart());
        }

        CartItemModel cartItemModel = new CartItemModel();
        BeanUtils.copyProperties(cartItemRecordDto, cartItemModel);
        cartItemModel.setCart(cartOptional.get());
        cartItemModel.setProduct(productOptional.get());
        cartItemModel.setCreatedAt(LocalDateTime.now());

        return cartItemRepository.save(cartItemModel);
    }

    public void delete(Long id) {
        Optional<CartItemModel> cartItemOptional = cartItemRepository.findById(id);
        if (cartItemOptional.isEmpty()) {
            throw new ResourceNotFoundException("Cart Item not found with id: " + id);
        }
        cartItemOptional.ifPresent(cart_item -> cartItemRepository.delete(cart_item));
    }

    public CartItemModel update(Long id, CartItemRecordDto cartItemRecordDto) {
        Optional<CartItemModel> cartItemOptional = cartItemRepository.findById(id);
        if (cartItemOptional.isEmpty()) {
            throw new ResourceNotFoundException("Cart Item not found with id: " + id);
        }
        Optional<ProductModel> productOptional = productRepository.findById(cartItemRecordDto.id_product());
        if (productOptional.isEmpty()) {
            throw new ResourceNotFoundException("Product not found with id: " + cartItemRecordDto.id_product());
        }

        Optional<CartModel> cartOptional = cartRepository.findById(cartItemRecordDto.id_cart());
        if (cartOptional.isEmpty()) {
            throw new ResourceNotFoundException("Cart not found with id: " + cartItemRecordDto.id_cart());
        }

        CartItemModel cartItemModel = new CartItemModel();
        BeanUtils.copyProperties(cartItemRecordDto, cartItemModel);
        cartItemModel.setCart(cartOptional.get());
        cartItemModel.setProduct(productOptional.get());
        cartItemModel.setUpdatedAt(LocalDateTime.now());

        return cartItemRepository.save(cartItemModel);
    }

    public List<CartItemModel> findByCartId(Long cartId) {
        return cartItemRepository.findByCartId(cartId);
    }
}
