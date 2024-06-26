package com.samsung.springboot.services;

import com.samsung.springboot.dtos.UserRecordDto;
import com.samsung.springboot.exceptions.ResourceNotFoundException;
import com.samsung.springboot.models.UserModel;
import com.samsung.springboot.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserModel> getAll() {
        return userRepository.findAll();
    }

    public Optional<UserModel> getOne(Long id) {
        Optional<UserModel> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        return userOptional;
    }

    public UserModel save(UserRecordDto userRecordDto) {
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userRecordDto, userModel);
        return userRepository.save(userModel);
    }

    public void delete(Long id) {
        Optional<UserModel> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new ResourceNotFoundException("Product not found with id: " + id);
        }
        userOptional.ifPresent(user -> userRepository.delete(user));
    }

    public UserModel update(Long id, UserRecordDto userRecordDto) {
        Optional<UserModel> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new ResourceNotFoundException("Product not found with id: " + id);
        }
        UserModel userModel = userOptional.get();
        BeanUtils.copyProperties(userRecordDto, userModel);
        return userRepository.save(userModel);
    }
}
