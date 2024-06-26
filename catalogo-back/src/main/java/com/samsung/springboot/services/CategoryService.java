package com.samsung.springboot.services;

import com.samsung.springboot.dtos.CategoryRecordDto;
import com.samsung.springboot.exceptions.ResourceNotFoundException;
import com.samsung.springboot.models.CategoryModel;
import com.samsung.springboot.repositories.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryService {

    @Autowired
    private CategoryRepository productCategoryRepository;

    public List<CategoryModel> getAll() {
        return productCategoryRepository.findAll();
    }

    public Optional<CategoryModel> getOne(Long id) {
        Optional<CategoryModel> productCategoryOptional = productCategoryRepository.findById(id);
        if (productCategoryOptional.isEmpty()) {
            throw new ResourceNotFoundException("Product Category not found with id: " + id);
        }
        return productCategoryOptional;
    }

    public CategoryModel save(CategoryRecordDto productCategoryRecordDto) {
        CategoryModel productCategoryModel = new CategoryModel();
        BeanUtils.copyProperties(productCategoryRecordDto, productCategoryModel);
        return productCategoryRepository.save(productCategoryModel);
    }

    public void delete(Long id) {
        Optional<CategoryModel> productCategoryOptional = productCategoryRepository.findById(id);
        if (productCategoryOptional.isEmpty()) {
            throw new ResourceNotFoundException("Product Category not found with id: " + id);
        }
        productCategoryOptional.ifPresent(productcategory -> productCategoryRepository.delete(productcategory));
    }

    public CategoryModel update(Long id, CategoryRecordDto productCategoryRecordDto) {
        Optional<CategoryModel> productCategoryOptional = productCategoryRepository.findById(id);
        if (productCategoryOptional.isEmpty()) {
            throw new ResourceNotFoundException("Product Category not found with id: " + id);
        }
        CategoryModel productCategoryModel = productCategoryOptional.get();
        BeanUtils.copyProperties(productCategoryRecordDto, productCategoryModel);
        return productCategoryRepository.save(productCategoryModel);
    }
}
