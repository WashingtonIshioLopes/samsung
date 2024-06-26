package com.samsung.springboot.services;

import com.samsung.springboot.dtos.UnitRecordDto;
import com.samsung.springboot.exceptions.ResourceNotFoundException;
import com.samsung.springboot.models.UnitModel;
import com.samsung.springboot.repositories.UnitRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UnitService {

    @Autowired
    private UnitRepository unitRepository;

    public List<UnitModel> getAll() {
        return unitRepository.findAll();
    }

    public Optional<UnitModel> getOne(Long id) {
        Optional<UnitModel> unitOptional = unitRepository.findById(id);
        if (unitOptional.isEmpty()) {
            throw new ResourceNotFoundException("Unit not found with id: " + id);
        }
        return unitOptional;
    }

    public UnitModel save(UnitRecordDto unitRecordDto) {
        UnitModel unitModel = new UnitModel();
        BeanUtils.copyProperties(unitRecordDto, unitModel);
        return unitRepository.save(unitModel);
    }

    public void delete(Long id) {
        Optional<UnitModel> unitOptional = unitRepository.findById(id);
        if (unitOptional.isEmpty()) {
            throw new ResourceNotFoundException("Order not found with id: " + id);
        }
        unitOptional.ifPresent(unit -> unitRepository.delete(unit));
    }

    public UnitModel update(Long id, UnitRecordDto unitRecordDto) {
        Optional<UnitModel> unitOptional = unitRepository.findById(id);
        if (unitOptional.isEmpty()) {
            throw new ResourceNotFoundException("Unit not found with id: " + id);
        }
        UnitModel unitModel = unitOptional.get();
        BeanUtils.copyProperties(unitRecordDto, unitModel);
        return unitRepository.save(unitModel);
    }
}
