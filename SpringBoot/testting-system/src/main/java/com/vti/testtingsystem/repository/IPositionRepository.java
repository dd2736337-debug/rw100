package com.vti.testtingsystem.repository;

import com.vti.testtingsystem.Enum.PositionName;
import com.vti.testtingsystem.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IPositionRepository extends JpaRepository<Position, Integer> {
    Optional<Position> findByName(PositionName name);

}
