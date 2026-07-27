package com.vti.gold.service.Impl;

import com.vti.gold.dto.OrderDTO;
import com.vti.gold.entity.Order;
import com.vti.gold.entity.User;
import com.vti.gold.form.OrderCreateForm;
import com.vti.gold.form.OrderUpdateForm;
import com.vti.gold.repository.OrderRepository;
import com.vti.gold.repository.UserRepository;
import com.vti.gold.service.IOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private OrderRepository orderRepository;


    @Autowired
    private UserRepository userRepository;


    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<OrderDTO> findAll(Pageable pageable) {
        Page<Order> page = orderRepository.findAll(pageable);


        return page.map(order -> {

            OrderDTO dto = modelMapper.map(order, OrderDTO.class);
            if (order.getUser() != null) {

                dto.setUserId(order.getUser().getId());

                dto.setUsername(order.getUser().getUsername());
            }
            return dto;
        });
    }

    @Override
    public OrderDTO findById(Integer id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found!"));
        OrderDTO dto = modelMapper.map(order, OrderDTO.class);


        if (order.getUser() != null) {

            dto.setUserId(order.getUser().getId());

            dto.setUsername(order.getUser().getUsername());
        }
        return dto;
    }

    @Override
    public void create(OrderCreateForm form) {
        User user = userRepository.findById(form.getUserId()).orElseThrow(() -> new RuntimeException("User not found!"));

        Order order = new Order();
        order.setUser(user);
        order.setTotalPrice(form.getTotalPrice());
        order.setStatus(form.getStatus());
        order.setCreatedAt(LocalDateTime.now());

        orderRepository.save(order);
    }

    @Override
    public void update(Integer id, OrderUpdateForm form) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found!"));
        order.setTotalPrice(form.getTotalPrice());
        order.setStatus(form.getStatus());
        orderRepository.save(order);

    }

    @Override
    public void delete(Integer id) {

        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found!"));


        orderRepository.delete(order);

    }
}
