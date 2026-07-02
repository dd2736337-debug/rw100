package com.vti.controller;

import com.vti.entity.Position;
import com.vti.service.IPositionService;
import com.vti.service.impl.PositionServiceImpl;

import java.util.List;

public class PositionController {
    private final IPositionService service=new PositionServiceImpl();

    public List<Position> findAll() {
        return service.findAll();
    }

    public Position findById(Integer id) {
        return service.findById(id);
    }

    public void create(Position position) {
        service.create(position);
    }

    public void update(Position position) {
        service.update(position);
    }

    public void delete(Integer id) {
        service.delete(id);
    }

}
