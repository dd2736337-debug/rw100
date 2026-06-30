package com.vti.repository;

import com.vti.entity.Position;


import java.util.List;

public interface IPosition {
    List<Position> findAll();

    Position findById(Integer id);

    void create(String name);

    void update(String updateName, Integer id);


}
