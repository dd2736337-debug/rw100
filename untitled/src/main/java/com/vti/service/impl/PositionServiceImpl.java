package com.vti.service.impl;

import com.vti.entity.Position;
import com.vti.repository.IPosition;
import com.vti.repository.impl.PositionRepositoryImpl;
import com.vti.service.IPositionService;

import java.util.List;

public class PositionServiceImpl implements IPositionService {
    private final IPosition repository = new PositionRepositoryImpl();

    @Override
    public List<Position> findAll() {
        return repository.findAll();
    }

    @Override
    public Position findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public void create(Position position) {
        repository.create(position);

    }

    @Override
    public void update(Position position) {
        repository.update(position);
    }

    @Override
    public void delete(Integer id) {
        repository.delete(id);
    }
}
