package com.samsung.springboot.services;

import com.samsung.springboot.dtos.ProductRecordDto;
import com.samsung.springboot.exceptions.ResourceNotFoundException;
import com.samsung.springboot.models.CategoryModel;
import com.samsung.springboot.models.ProductImageModel;
import com.samsung.springboot.models.ProductModel;
import com.samsung.springboot.models.UnitModel;
import com.samsung.springboot.repositories.ProductImageRepository;
import com.samsung.springboot.repositories.ProductRepository;
import com.samsung.springboot.repositories.UnitRepository;
import com.samsung.springboot.repositories.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UnitRepository unitRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductImageRepository productImageRepository;

    /*
    public List<ProductRecordDto> getAll() {
        List<ProductModel> listProductModel = productRepository.findAll();
        List<ProductRecordDto> listProductRecordDto = listProductModel.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return listProductRecordDto;
    }

    public Optional<ProductRecordDto> getOne(Long id) {
        Optional<ProductModel> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            throw new ResourceNotFoundException("Product not found with id: " + id);
        }
        ProductModel productModel = productOptional.get();
        ProductRecordDto productRecordDto = convertToDto(productModel);
        return Optional.of(productRecordDto);
    }

    public ProductRecordDto save(ProductRecordDto productRecordDto) {

        Optional<UnitModel> unitOptional = unitRepository.findById(productRecordDto.id_unit());
        if (unitOptional.isEmpty()) {
            throw new ResourceNotFoundException("Unit not found with id: " + productRecordDto.id_unit());
        }

        Optional<CategoryModel> categoryOptional = categoryRepository.findById(productRecordDto.id_category());
        if (categoryOptional.isEmpty()) {
            throw new ResourceNotFoundException("Category not found with id: " + productRecordDto.id_category());
        }

        ProductModel productModel = new ProductModel();
        BeanUtils.copyProperties(productRecordDto, productModel);
        productModel.setUnit(unitOptional.get());
        productModel.setCategory(categoryOptional.get());

        // Map images from DTO to ProductModel
        if (productRecordDto.images() != null) {
            List<ProductImageModel> productImages = productRecordDto.images().stream()
                    .map(image -> {
                        ProductImageModel productImage = new ProductImageModel();
                        productImage.setImage(image);
                        productImage.setProduct(productModel);
                        return productImage;
                    })
                    .collect(Collectors.toList());
            productModel.setImages(productImages);
        }

        ProductModel savedProduct = productRepository.save(productModel);
        return convertToDto(savedProduct);
    }

    public void delete(Long id) {
        Optional<ProductModel> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            throw new ResourceNotFoundException("Product not found with id: " + id);
        }
        productOptional.ifPresent(product -> productRepository.delete(product));
    }

    public ProductRecordDto update(Long id, ProductRecordDto productRecordDto) {

        Optional<ProductModel> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            throw new ResourceNotFoundException("Product not found with id: " + id);
        }

        Optional<UnitModel> unitOptional = unitRepository.findById(productRecordDto.id_unit());
        if (unitOptional.isEmpty()) {
            throw new ResourceNotFoundException("Unit not found with id: " + productRecordDto.id_unit());
        }

        Optional<CategoryModel> categoryOptional = categoryRepository.findById(productRecordDto.id_category());
        if (categoryOptional.isEmpty()) {
            throw new ResourceNotFoundException("Category not found with id: " + productRecordDto.id_category());
        }

        ProductModel productModel = productOptional.get();
        BeanUtils.copyProperties(productRecordDto, productModel);
        productModel.setUnit(unitOptional.get());
        productModel.setCategory(categoryOptional.get());

        // Map images from DTO to ProductModel
        if (productRecordDto.images() != null) {
            List<ProductImageModel> productImages = productRecordDto.images().stream()
                    .map(image -> {
                        ProductImageModel productImage = new ProductImageModel();
                        productImage.setImage(image);
                        productImage.setProduct(productModel);
                        return productImage;
                    })
                    .collect(Collectors.toList());
            productModel.setImages(productImages);
        }

        ProductModel savedProduct = productRepository.save(productModel);
        return convertToDto(savedProduct);
    }

    private ProductRecordDto convertToDto(ProductModel productModel) {
        List<String> imageUrls = productModel.getImages().stream()
                .map(ProductImageModel::getImage)
                .collect(Collectors.toList());

        return new ProductRecordDto(
                productModel.getDescription(),
                productModel.getPrice(),
                productModel.getUnit().getId(),
                productModel.getCategory().getId(),
                productModel.getWeight(),
                imageUrls
        );
    }
    */

    public List<ProductModel> getAll() {
        return productRepository.findAll();
    }

    public Optional<ProductModel> getOne(Long id) {

        Optional<ProductModel> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            throw new ResourceNotFoundException("Product not found with id: " + id);
        }

        return productOptional;
    }

    public ProductModel save(ProductRecordDto productRecordDto) {

        Optional<UnitModel> unitOptional = unitRepository.findById(productRecordDto.id_unit());
        if (unitOptional.isEmpty()) {
            throw new ResourceNotFoundException("Unit not found with id: " + productRecordDto.id_unit());
        }

        Optional<CategoryModel> categoryOptional = categoryRepository.findById(productRecordDto.id_category());
        if (categoryOptional.isEmpty()) {
            throw new ResourceNotFoundException("Category not found with id: " + productRecordDto.id_category());
        }

        ProductModel productModel = new ProductModel();
        BeanUtils.copyProperties(productRecordDto, productModel);
        productModel.setUnit(unitOptional.get());
        productModel.setCategory(categoryOptional.get());

        return productRepository.save(productModel);
    }

    public void delete(Long id) {
        Optional<ProductModel> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            throw new ResourceNotFoundException("Product not found with id: " + id);
        }
        productOptional.ifPresent(product -> productRepository.delete(product));
    }

    public ProductModel update(Long id, ProductRecordDto productRecordDto) {

        Optional<ProductModel> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            throw new ResourceNotFoundException("Product not found with id: " + id);
        }

        Optional<UnitModel> unitOptional = unitRepository.findById(productRecordDto.id_unit());
        if (unitOptional.isEmpty()) {
            throw new ResourceNotFoundException("Unit not found with id: " + productRecordDto.id_unit());
        }

        Optional<CategoryModel> categoryOptional = categoryRepository.findById(productRecordDto.id_category());
        if (categoryOptional.isEmpty()) {
            throw new ResourceNotFoundException("Category not found with id: " + productRecordDto.id_category());
        }

        ProductModel productModel = productOptional.get();
        BeanUtils.copyProperties(productRecordDto, productModel);
        productModel.setUnit(unitOptional.get());
        productModel.setCategory(categoryOptional.get());

        return productRepository.save(productModel);
    }


}
