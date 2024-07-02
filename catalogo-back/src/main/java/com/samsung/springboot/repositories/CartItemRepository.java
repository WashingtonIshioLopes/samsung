package com.samsung.springboot.repositories;

import com.samsung.springboot.models.CartItemModel;
import com.samsung.springboot.models.CartModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItemModel, Long>{
    List<CartItemModel> findByCartId(Long cartId);
}
