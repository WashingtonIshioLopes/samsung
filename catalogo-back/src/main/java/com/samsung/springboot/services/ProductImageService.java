package com.samsung.springboot.services;

import com.samsung.springboot.dtos.ProductImageRecordDto;
import com.samsung.springboot.exceptions.ResourceNotFoundException;
import com.samsung.springboot.models.ProductImageModel;
import com.samsung.springboot.repositories.ProductImageRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductImageService {

    @Autowired
    private ProductImageRepository productImageRepository;

    public List<ProductImageModel> getAll() {
        return productImageRepository.findAll();
    }

    public Optional<ProductImageModel> getOne(Long id) {
        Optional<ProductImageModel> productImageOptional = productImageRepository.findById(id);
        if (productImageOptional.isEmpty()) {
            throw new ResourceNotFoundException("Product Image not found with id: " + id);
        }
        return productImageOptional;
    }

    public ProductImageModel save(ProductImageRecordDto productImageRecordDto) {
        ProductImageModel productImageModel = new ProductImageModel();
        BeanUtils.copyProperties(productImageRecordDto, productImageModel);
        return productImageRepository.save(productImageModel);
    }

    public void delete(Long id) {
        Optional<ProductImageModel> productImageOptional = productImageRepository.findById(id);
        if (productImageOptional.isEmpty()) {
            throw new ResourceNotFoundException("Product Image not found with id: " + id);
        }
        productImageOptional.ifPresent(productimage -> productImageRepository.delete(productimage));
    }

    public ProductImageModel update(Long id, ProductImageRecordDto productImageRecordDto) {
        Optional<ProductImageModel> productImageOptional = productImageRepository.findById(id);
        if (productImageOptional.isEmpty()) {
            throw new ResourceNotFoundException("Product not found with id: " + id);
        }
        ProductImageModel productImageModel = productImageOptional.get();
        BeanUtils.copyProperties(productImageRecordDto, productImageModel);
        return productImageRepository.save(productImageModel);
    }
}
