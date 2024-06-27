package com.samsung.springboot.repositories;

import com.samsung.springboot.models.CartItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItemModel, Long>{
}
