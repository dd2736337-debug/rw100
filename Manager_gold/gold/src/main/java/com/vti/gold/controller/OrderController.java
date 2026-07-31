package com.vti.gold.controller;

import com.vti.gold.dto.OrderDTO;
import com.vti.gold.entity.OrderStatus;
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
    public Page<OrderDTO> findAll(
            @RequestParam(required = false) String username,

            @RequestParam(required = false) OrderStatus status,

            @PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

        return orderService.findAll(username,status,pageable);
    }

    @GetMapping("/user/{userId}")
    public Page<OrderDTO> findByUserId(

            @PathVariable Integer userId,

            @PageableDefault(
                    page = 0,
                    size = 5,
                    sort = "id",
                    direction = Sort.Direction.DESC)
            Pageable pageable) {

        return orderService.findByUserId(
                userId,
                pageable
        );
    }

    @GetMapping("/{id}")
    public OrderDTO findById(@PathVariable Integer id) {

        return orderService.findById(id);
    }

    @PostMapping
    public OrderDTO create(@Valid @RequestBody OrderCreateForm form) {

        return orderService.create(form);
    }

    @PutMapping("/{id}")
    public OrderDTO update(@PathVariable Integer id,

                       @Valid @RequestBody OrderUpdateForm form) {

      return  orderService.update(id, form);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {

        orderService.delete(id);
    }



}
