package com.vti.testtingsystem.service;

import com.vti.testtingsystem.Enum.PositionName;
import com.vti.testtingsystem.dto.PositionDto;

import java.util.List;

public interface IPositionService {
    List<PositionDto> findAll();

    PositionDto findById(Integer id);

    PositionDto findByName(PositionName name);
//
//    void create (PositionDto dto);
//
//    void update(PositionDto dto,Integer id);
//
//    void delete(Integer id);


}
