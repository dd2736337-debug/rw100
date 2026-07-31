package com.vti.gold.repository;

import com.vti.gold.entity.Gold;
import com.vti.gold.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,Integer> {
    List<OrderDetail> findByOrder_Id(Integer orderId);

    List<OrderDetail> findByGold_Id(Integer goldId);

    boolean existsByGold_Id(Integer goldId);
}
