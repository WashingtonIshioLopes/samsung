package com.samsung.springboot.repositories;

import com.samsung.springboot.models.PaymentTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentTypeRepository extends JpaRepository<PaymentTypeModel, Long>{

}
