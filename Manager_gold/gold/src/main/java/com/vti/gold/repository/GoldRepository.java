package com.vti.gold.repository;

import com.vti.gold.entity.Gold;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GoldRepository extends JpaRepository<Gold,Integer> {
}
