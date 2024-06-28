package com.samsung.springboot.repositories;

import com.samsung.springboot.models.ProductModel;
import com.samsung.springboot.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long>{
    UserModel findByDocument(String document);
    UserModel findByAuthorization(String findByAuthorization);
}
