package com.samsung.springboot.repositories;

import com.samsung.springboot.models.ProductCategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategoryModel, Long>{

}
