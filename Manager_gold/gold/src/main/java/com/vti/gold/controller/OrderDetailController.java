package com.vti.gold.controller;

import com.vti.gold.dto.OrderDetailDTO;
import com.vti.gold.form.OrderDetailCreateForm;
import com.vti.gold.form.OrderDetailUpdateForm;
import com.vti.gold.service.IOrderDetailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-details")
@CrossOrigin("*")
public class OrderDetailController {
    @Autowired
    private IOrderDetailService orderDetailService;

    @GetMapping
    public Page<OrderDetailDTO> findAll(@PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

        return orderDetailService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public OrderDetailDTO findById(@PathVariable Integer id) {

        return orderDetailService.findById(id);
    }



    @PutMapping("/{id}")
    public void update(@PathVariable Integer id,

                       @Valid @RequestBody OrderDetailUpdateForm form) {

        orderDetailService.update(id, form);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {

        orderDetailService.delete(id);
    }

    @GetMapping("/order/{orderId}")
    public List<OrderDetailDTO> findByOrderId(
            @PathVariable Integer orderId) {

        return orderDetailService.findByOrderId(orderId);
    }


}
