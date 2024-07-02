package com.samsung.springboot.services;

import com.samsung.springboot.dtos.ProductFavoriteRecordDto;
import com.samsung.springboot.exceptions.ResourceNotFoundException;
import com.samsung.springboot.models.ProductFavoriteModel;
import com.samsung.springboot.models.ProductFavoriteModel;
import com.samsung.springboot.models.ProductModel;
import com.samsung.springboot.repositories.ProductFavoriteRepository;
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
public class ProductFavoriteService {

    @Autowired
    private ProductFavoriteRepository productFavoriteRepository;
    @Autowired
    private ProductRepository productRepository;

    public List<ProductFavoriteModel> getAll() {
        return productFavoriteRepository.findAll();
    }

    public Optional<ProductFavoriteModel> getOne(Long id) {
        Optional<ProductFavoriteModel> productFavoriteOptional = productFavoriteRepository.findById(id);
        if (productFavoriteOptional.isEmpty()) {
            throw new ResourceNotFoundException("Product Favorite not found with id: " + id);
        }
        return productFavoriteOptional;
    }

    public ProductFavoriteModel save(ProductFavoriteRecordDto productFavoriteRecordDto) {

        Optional<ProductModel> productOptional = productRepository.findById(productFavoriteRecordDto.id_product());
        if (productOptional.isEmpty()) {
            throw new ResourceNotFoundException("Product not found with id: " + productFavoriteRecordDto.id_product());
        }

        ProductFavoriteModel productFavoriteModel = new ProductFavoriteModel();
        BeanUtils.copyProperties(productFavoriteRecordDto, productFavoriteModel);
        productFavoriteModel.setProduct(productOptional.get());
        productFavoriteModel.setCreatedAt(LocalDateTime.now());

        return productFavoriteRepository.save(productFavoriteModel);
    }

    public void delete(Long id) {
        Optional<ProductFavoriteModel> productFavoriteOptional = productFavoriteRepository.findById(id);
        if (productFavoriteOptional.isEmpty()) {
            throw new ResourceNotFoundException("Product Featured not found with id: " + id);
        }
        productFavoriteOptional.ifPresent(productFavorite -> productFavoriteRepository.delete(productFavorite));
    }

    public ProductFavoriteModel update(Long id, ProductFavoriteRecordDto productFavoriteRecordDto) {
        Optional<ProductFavoriteModel> productFavoriteOptional = productFavoriteRepository.findById(id);
        if (productFavoriteOptional.isEmpty()) {
            throw new ResourceNotFoundException("Product Favorite not found with id: " + id);
        }

        Optional<ProductModel> productOptional = productRepository.findById(productFavoriteRecordDto.id_product());
        if (productOptional.isEmpty()) {
            throw new ResourceNotFoundException("Product not found with id: " + productFavoriteRecordDto.id_product());
        }

        ProductFavoriteModel productFavoriteModel = productFavoriteOptional.get();
        BeanUtils.copyProperties(productFavoriteRecordDto, productFavoriteModel);
        productFavoriteModel.setProduct(productOptional.get());
        productFavoriteModel.setUpdatedAt(LocalDateTime.now());

        return productFavoriteRepository.save(productFavoriteModel);
    }
}
