package com.vti.gold.controller;

import com.vti.gold.dto.OrderDTO;
import com.vti.gold.form.OrderCreateForm;
import com.vti.gold.form.OrderUpdateForm;
import com.vti.gold.service.IOrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin("*")
public class OrderController {
    @Autowired
    private IOrderService orderService;

    @GetMapping
    public Page<OrderDTO> findAll(@PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

        return orderService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public OrderDTO findById(@PathVariable Integer id) {

        return orderService.findById(id);
    }

    @PostMapping
    public void create(@Valid @RequestBody OrderCreateForm form) {

        orderService.create(form);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Integer id,

                       @Valid @RequestBody OrderUpdateForm form) {

        orderService.update(id, form);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {

        orderService.delete(id);
    }

}
