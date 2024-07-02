package com.samsung.springboot.repositories;

import com.samsung.springboot.models.ProductFavoriteModel;
import com.samsung.springboot.models.ProductFeaturedModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductFavoriteRepository extends JpaRepository<ProductFavoriteModel, Long>{

}
