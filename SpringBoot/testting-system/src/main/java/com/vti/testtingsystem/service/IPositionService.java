package com.vti.testtingsystem.service;

import com.vti.testtingsystem.dto.PositionDto;
import com.vti.testtingsystem.entity.Position;

import java.util.List;

public interface IPositionService {
    List<Position> findAll();

    Position findById(Integer id);

    void create (PositionDto dto);

    void update(PositionDto dto,Integer id);

    void delete(Integer id);


}
