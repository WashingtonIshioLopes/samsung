package com.samsung.springboot.services;

import com.samsung.springboot.dtos.ProductCategoryRecordDto;
import com.samsung.springboot.exceptions.ResourceNotFoundException;
import com.samsung.springboot.models.ProductCategoryModel;
import com.samsung.springboot.repositories.ProductCategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    public List<ProductCategoryModel> getAll() {
        return productCategoryRepository.findAll();
    }

    public Optional<ProductCategoryModel> getOne(Long id) {
        Optional<ProductCategoryModel> productCategoryOptional = productCategoryRepository.findById(id);
        if (productCategoryOptional.isEmpty()) {
            throw new ResourceNotFoundException("Product Category not found with id: " + id);
        }
        return productCategoryOptional;
    }

    public ProductCategoryModel save(ProductCategoryRecordDto productCategoryRecordDto) {
        ProductCategoryModel productCategoryModel = new ProductCategoryModel();
        BeanUtils.copyProperties(productCategoryRecordDto, productCategoryModel);
        return productCategoryRepository.save(productCategoryModel);
    }

    public void delete(Long id) {
        Optional<ProductCategoryModel> productCategoryOptional = productCategoryRepository.findById(id);
        if (productCategoryOptional.isEmpty()) {
            throw new ResourceNotFoundException("Product Category not found with id: " + id);
        }
        productCategoryOptional.ifPresent(productcategory -> productCategoryRepository.delete(productcategory));
    }

    public ProductCategoryModel update(Long id, ProductCategoryRecordDto productCategoryRecordDto) {
        Optional<ProductCategoryModel> productCategoryOptional = productCategoryRepository.findById(id);
        if (productCategoryOptional.isEmpty()) {
            throw new ResourceNotFoundException("Product Category not found with id: " + id);
        }
        ProductCategoryModel productCategoryModel = productCategoryOptional.get();
        BeanUtils.copyProperties(productCategoryRecordDto, productCategoryModel);
        return productCategoryRepository.save(productCategoryModel);
    }
}
