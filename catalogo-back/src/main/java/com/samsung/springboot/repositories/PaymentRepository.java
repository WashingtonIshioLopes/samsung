package com.samsung.springboot.repositories;

import com.samsung.springboot.models.OrderModel;
import com.samsung.springboot.models.PaymentTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentTypeModel, Long>{

}
