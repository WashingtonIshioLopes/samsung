package com.samsung.springboot.repositories;

import com.samsung.springboot.models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Long>{
    @Query("SELECT p FROM ProductModel p WHERE (:id_category IS NULL OR p.category.id = :id_category) AND (:description IS NULL OR p.description LIKE %:description%)")
    List<ProductModel> findByCategoryIdAndDescription(@Param("id_category") Long categoryId, @Param("description") String description);
}
