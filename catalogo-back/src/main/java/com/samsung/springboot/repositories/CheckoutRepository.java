package com.samsung.springboot.repositories;

import com.samsung.springboot.models.CheckoutModel;
import com.samsung.springboot.models.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckoutRepository extends JpaRepository<CheckoutModel, Long>{

}
