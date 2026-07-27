package com.vti.gold.service;

import com.vti.gold.dto.OrderDTO;
import com.vti.gold.form.OrderCreateForm;
import com.vti.gold.form.OrderUpdateForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IOrderService {
    Page<OrderDTO> findAll(Pageable pageable);


    OrderDTO findById(Integer id);


    void create(OrderCreateForm form);


    void update(Integer id, OrderUpdateForm form);


    void delete(Integer id);
}
