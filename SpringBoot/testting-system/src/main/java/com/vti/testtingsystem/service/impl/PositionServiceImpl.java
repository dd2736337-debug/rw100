package com.vti.testtingsystem.service.impl;

import com.vti.testtingsystem.entity.Position;
import com.vti.testtingsystem.repository.IPositionRepository;
import com.vti.testtingsystem.service.IPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionServiceImpl implements IPositionService {
    @Autowired
    private IPositionRepository repository;

    @Override
    public List<Position> findAll() {
        return repository.findAll();
    }

    @Override
    public Position findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Position không tồn tại"));

    }


}
