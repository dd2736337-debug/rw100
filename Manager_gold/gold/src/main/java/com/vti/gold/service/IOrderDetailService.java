package com.vti.gold.service;

import com.vti.gold.dto.OrderDetailDTO;
import com.vti.gold.form.OrderDetailCreateForm;
import com.vti.gold.form.OrderDetailUpdateForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IOrderDetailService {
    Page<OrderDetailDTO> findAll(Pageable pageable);


    OrderDetailDTO findById(Integer id);


    void create(OrderDetailCreateForm form);


    void update(Integer id, OrderDetailUpdateForm form);


    void delete(Integer id);
}
