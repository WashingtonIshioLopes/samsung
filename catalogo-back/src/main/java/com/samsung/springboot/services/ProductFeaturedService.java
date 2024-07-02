package com.samsung.springboot.services;

import com.samsung.springboot.dtos.ProductFeaturedRecordDto;
import com.samsung.springboot.exceptions.ResourceNotFoundException;
import com.samsung.springboot.models.ProductFeaturedModel;
import com.samsung.springboot.models.ProductModel;
import com.samsung.springboot.repositories.ProductFeaturedRepository;
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
public class ProductFeaturedService {

    @Autowired
    private ProductFeaturedRepository productFeaturedRepository;
    @Autowired
    private ProductRepository productRepository;

    public List<ProductFeaturedModel> getAll() {
        return productFeaturedRepository.findAll();
    }

    public Optional<ProductFeaturedModel> getOne(Long id) {
        Optional<ProductFeaturedModel> productFeaturedOptional = productFeaturedRepository.findById(id);
        if (productFeaturedOptional.isEmpty()) {
            throw new ResourceNotFoundException("Product Featured not found with id: " + id);
        }
        return productFeaturedOptional;
    }

    public ProductFeaturedModel save(ProductFeaturedRecordDto productFeaturedRecordDto) {

        Optional<ProductModel> productOptional = productRepository.findById(productFeaturedRecordDto.id_product());
        if (productOptional.isEmpty()) {
            throw new ResourceNotFoundException("Product not found with id: " + productFeaturedRecordDto.id_product());
        }

        ProductFeaturedModel productFeaturedModel = new ProductFeaturedModel();
        BeanUtils.copyProperties(productFeaturedRecordDto, productFeaturedModel);
        productFeaturedModel.setProduct(productOptional.get());
        productFeaturedModel.setCreatedAt(LocalDateTime.now());

        return productFeaturedRepository.save(productFeaturedModel);
    }

    public void delete(Long id) {
        Optional<ProductFeaturedModel> productFeaturedOptional = productFeaturedRepository.findById(id);
        if (productFeaturedOptional.isEmpty()) {
            throw new ResourceNotFoundException("Product Featured not found with id: " + id);
        }
        productFeaturedOptional.ifPresent(productFeatured -> productFeaturedRepository.delete(productFeatured));
    }

    public ProductFeaturedModel update(Long id, ProductFeaturedRecordDto productFeaturedRecordDto) {
        Optional<ProductFeaturedModel> productFeaturedOptional = productFeaturedRepository.findById(id);
        if (productFeaturedOptional.isEmpty()) {
            throw new ResourceNotFoundException("Product Featured not found with id: " + id);
        }

        Optional<ProductModel> productOptional = productRepository.findById(productFeaturedRecordDto.id_product());
        if (productOptional.isEmpty()) {
            throw new ResourceNotFoundException("Product not found with id: " + productFeaturedRecordDto.id_product());
        }

        ProductFeaturedModel productFeaturedModel = productFeaturedOptional.get();
        BeanUtils.copyProperties(productFeaturedRecordDto, productFeaturedModel);
        productFeaturedModel.setProduct(productOptional.get());
        productFeaturedModel.setUpdatedAt(LocalDateTime.now());

        return productFeaturedRepository.save(productFeaturedModel);
    }
}
