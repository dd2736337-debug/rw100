package com.vti.service;

import com.vti.entity.Position;

import java.util.List;

public interface IPositionService {
    List<Position> findAll();

    Position findById(Integer id);

    void create(Position position);

    void update(Position position);

    void delete(Integer id);
}
