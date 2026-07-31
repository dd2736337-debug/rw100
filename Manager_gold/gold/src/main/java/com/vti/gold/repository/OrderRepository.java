package com.vti.gold.repository;

import com.vti.gold.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderRepository extends JpaRepository<Order,Integer>, JpaSpecificationExecutor<Order> {
    @EntityGraph(attributePaths = {
            "user",
            "orderDetails",
            "orderDetails.gold"
    })
    Page<Order> findByUser_Id(
            Integer userId,
            Pageable pageable
    );


    @Override
    @EntityGraph(attributePaths = {
            "user",
            "orderDetails",
            "orderDetails.gold"
    })
    Page<Order> findAll(
            org.springframework.data.jpa.domain.Specification<Order> spec,
            Pageable pageable
    );


    @EntityGraph(attributePaths = {
            "user",
            "orderDetails",
            "orderDetails.gold"
    })
    java.util.Optional<Order> findById(Integer id);


    boolean existsByUser_Id(Integer userId);
}
