package com.vti.testtingsystem.service;

import com.vti.testtingsystem.Enum.PositionName;
import com.vti.testtingsystem.dto.PositionDto;
import com.vti.testtingsystem.form.PositionCreateAndUpdateForm;

import java.util.List;

public interface IPositionService {
    List<PositionDto> findAll();

    PositionDto findById(Integer id);

    PositionDto findByName(PositionName name);

    void create(PositionCreateAndUpdateForm form);

    void update(PositionCreateAndUpdateForm form, Integer id);

    void delete(Integer id);


}
