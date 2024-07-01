package com.samsung.springboot.repositories;

import com.samsung.springboot.models.CartModel;
import com.samsung.springboot.models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<CartModel, Long>{
    List<CartModel> findByUserIdAndStatus(Long userId, String status);
}
