package com.vti.gold.service;

import com.vti.gold.dto.OrderDTO;
import com.vti.gold.entity.OrderStatus;
import com.vti.gold.form.OrderCreateForm;
import com.vti.gold.form.OrderUpdateForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IOrderService {

    Page<OrderDTO> findAll(String username, OrderStatus status, Pageable pageable);


    Page<OrderDTO> findByUserId(Integer userId, Pageable pageable);


    OrderDTO findById(Integer id);


    OrderDTO create(OrderCreateForm form);


    OrderDTO update(Integer id, OrderUpdateForm form);


    void delete(Integer id);

}