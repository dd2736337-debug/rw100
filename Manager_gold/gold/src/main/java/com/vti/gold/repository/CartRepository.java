package com.vti.gold.repository;

import com.vti.gold.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    List<Cart> findByUser_Id(Integer userId);

    Optional<Cart> findByUser_IdAndGold_Id(Integer userId, Integer goldId);

    void deleteByUser_IdAndGold_Id(Integer userId, Integer goldId);

    void deleteAllByUser_Id(Integer userId);
}