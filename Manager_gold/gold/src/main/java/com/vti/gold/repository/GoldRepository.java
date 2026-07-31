package com.vti.gold.repository;

import com.vti.gold.entity.Gold;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface GoldRepository extends JpaRepository<Gold, Integer>, JpaSpecificationExecutor<Gold> {

}
