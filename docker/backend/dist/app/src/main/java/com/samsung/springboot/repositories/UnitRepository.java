package com.samsung.springboot.repositories;

import com.samsung.springboot.models.OrderModel;
import com.samsung.springboot.models.UnitModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitRepository extends JpaRepository<UnitModel, Long>{

}
