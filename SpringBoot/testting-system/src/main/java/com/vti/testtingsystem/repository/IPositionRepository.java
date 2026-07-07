package com.vti.testtingsystem.repository;

import com.vti.testtingsystem.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPositionRepository extends JpaRepository<Position,Integer> {
}
