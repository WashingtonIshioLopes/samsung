package com.samsung.springboot.services;

import com.samsung.springboot.dtos.PersonAddressRecordDto;
import com.samsung.springboot.exceptions.ResourceNotFoundException;
import com.samsung.springboot.models.PersonAddressModel;
import com.samsung.springboot.models.PersonModel;
import com.samsung.springboot.repositories.PersonAddressRepository;
import com.samsung.springboot.repositories.PersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PersonAddressService {

    @Autowired
    private PersonAddressRepository personAddressRepository;
    @Autowired
    private PersonRepository userRepository;

    public List<PersonAddressModel> getAll() {
        return personAddressRepository.findAll();
    }

    public Optional<PersonAddressModel> getOne(Long id) {
        Optional<PersonAddressModel> personAddressOptional = personAddressRepository.findById(id);
        if (personAddressOptional.isEmpty()) {
            throw new ResourceNotFoundException("Person Address not found with id: " + id);
        }
        return personAddressOptional;
    }

    public PersonAddressModel save(PersonAddressRecordDto personAddressRecordDto) {

        Optional<PersonModel> userOptional = userRepository.findById(personAddressRecordDto.id_user());
        if (userOptional.isEmpty()) {
            throw new ResourceNotFoundException("User not found with id: " + personAddressRecordDto.id_user());
        }

        PersonAddressModel orderModel = new PersonAddressModel();
        BeanUtils.copyProperties(personAddressRecordDto, orderModel);
        orderModel.setUser(userOptional.get());
        orderModel.setCreatedAt(LocalDateTime.now());

        return personAddressRepository.save(orderModel);
    }

    public void delete(Long id) {
        Optional<PersonAddressModel> personAddressOptional = personAddressRepository.findById(id);
        if (personAddressOptional.isEmpty()) {
            throw new ResourceNotFoundException("Person Address not found with id: " + id);
        }
        personAddressOptional.ifPresent(order -> personAddressRepository.delete(order));
    }

    public PersonAddressModel update(Long id, PersonAddressRecordDto personAddressRecordDto) {
        Optional<PersonAddressModel> personAddressOptional = personAddressRepository.findById(id);
        if (personAddressOptional.isEmpty()) {
            throw new ResourceNotFoundException("Order not found with id: " + id);
        }

        Optional<PersonModel> userOptional = userRepository.findById(personAddressRecordDto.id_user());
        if (userOptional.isEmpty()) {
            throw new ResourceNotFoundException("User not found with id: " + personAddressRecordDto.id_user());
        }

        PersonAddressModel orderModel = personAddressOptional.get();
        BeanUtils.copyProperties(personAddressRecordDto, orderModel);
        orderModel.setUser(userOptional.get());
        orderModel.setUpdatedAt(LocalDateTime.now());

        return personAddressRepository.save(orderModel);
    }
}
