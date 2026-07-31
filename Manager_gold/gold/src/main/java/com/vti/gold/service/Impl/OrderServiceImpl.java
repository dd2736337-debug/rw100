package com.vti.gold.service.Impl;

import com.vti.gold.Specification.OrderSpecification;
import com.vti.gold.dto.OrderDTO;
import com.vti.gold.dto.OrderDetailDTO;
import com.vti.gold.entity.*;
import com.vti.gold.form.OrderCreateForm;
import com.vti.gold.form.OrderDetailCreateForm;
import com.vti.gold.form.OrderUpdateForm;
import com.vti.gold.repository.GoldRepository;
import com.vti.gold.repository.OrderRepository;
import com.vti.gold.repository.UserRepository;
import com.vti.gold.service.IOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {


    @Autowired
    private OrderRepository orderRepository;


    @Autowired
    private UserRepository userRepository;


    @Autowired
    private GoldRepository goldRepository;


    @Autowired
    private ModelMapper modelMapper;


    @Transactional(readOnly = true)
    @Override
    public Page<OrderDTO> findAll(String username, OrderStatus status, Pageable pageable) {

        Specification<Order> specification = Specification.allOf(OrderSpecification.hasUsername(username), OrderSpecification.hasStatus(status));


        Page<Order> page = orderRepository.findAll(specification, pageable);


        return page.map(order -> {

            OrderDTO dto = modelMapper.map(order, OrderDTO.class);


            mapUser(order, dto);


            mapOrderDetails(order, dto);


            return dto;

        });
    }


    @Transactional(readOnly = true)
    @Override
    public Page<OrderDTO> findByUserId(Integer userId, Pageable pageable) {


        Page<Order> page = orderRepository.findByUser_Id(userId, pageable);


        return page.map(order -> {


            OrderDTO dto = modelMapper.map(order, OrderDTO.class);


            mapUser(order, dto);


            mapOrderDetails(order, dto);


            return dto;

        });
    }


    @Transactional(readOnly = true)
    @Override
    public OrderDTO findById(Integer id) {


        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found!"));


        OrderDTO dto = modelMapper.map(order, OrderDTO.class);


        mapUser(order, dto);


        mapOrderDetails(order, dto);


        return dto;
    }


    @Transactional
    @Override
    public OrderDTO create(OrderCreateForm form) {


        User user = userRepository.findById(form.getUserId()).orElseThrow(() -> new RuntimeException("User not found!"));


        Order order = new Order();


        order.setUser(user);
        order.setStatus(OrderStatus.PENDING);


        BigDecimal totalPrice = BigDecimal.ZERO;


        if (form.getOrderDetails() != null) {


            for (OrderDetailCreateForm detailForm : form.getOrderDetails()) {


                Gold gold = goldRepository.findById(detailForm.getGoldId()).orElseThrow(() -> new RuntimeException("Gold not found!"));


                if (gold.getQuantity() < detailForm.getQuantity()) {


                    throw new RuntimeException("Không đủ số lượng vàng!");

                }


                OrderDetail detail = new OrderDetail();


                detail.setGold(gold);


                detail.setQuantity(detailForm.getQuantity());


                detail.setPrice(gold.getPrice());


                detail.setOrder(order);


                order.getOrderDetails().add(detail);


                totalPrice = totalPrice.add(gold.getPrice().multiply(BigDecimal.valueOf(detailForm.getQuantity())));


                gold.setQuantity(gold.getQuantity() - detailForm.getQuantity());


                goldRepository.save(gold);

            }

        }


        order.setTotalPrice(totalPrice);


        Order savedOrder = orderRepository.save(order);


        OrderDTO dto = modelMapper.map(savedOrder, OrderDTO.class);


        mapUser(savedOrder, dto);


        mapOrderDetails(savedOrder, dto);


        return dto;

    }


    @Override
    @Transactional
    public OrderDTO update(Integer id, OrderUpdateForm form) {


        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found!"));


        order.setStatus(form.getStatus());

        Order saved=orderRepository.save(order);
        OrderDTO dto=modelMapper.map(saved,OrderDTO.class);
        mapUser(saved,dto);
        mapOrderDetails(saved,dto);
        return dto;

    }


    @Transactional
    @Override
    public void delete(Integer id) {


        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found!"));


        // hoàn lại số lượng vàng

        for (OrderDetail detail : order.getOrderDetails()) {


            Gold gold = detail.getGold();


            gold.setQuantity(gold.getQuantity() + detail.getQuantity());


            goldRepository.save(gold);

        }


        orderRepository.delete(order);

    }


    private void mapUser(Order order, OrderDTO dto) {

        if (order.getUser() != null) {


            dto.setUserId(order.getUser().getId());


            dto.setUsername(order.getUser().getUsername());


            dto.setFullName(order.getUser().getFullName());

        }

    }


    private void mapOrderDetails(Order order, OrderDTO dto) {


        if (order.getOrderDetails() == null) {

            return;

        }


        List<OrderDetailDTO> details =

                order.getOrderDetails().stream().map(detail -> {


                    OrderDetailDTO detailDTO = new OrderDetailDTO();


                    detailDTO.setId(detail.getId());


                    detailDTO.setQuantity(detail.getQuantity());


                    detailDTO.setPrice(detail.getPrice());


                    if (detail.getGold() != null) {


                        detailDTO.setGoldId(detail.getGold().getId());


                        detailDTO.setGoldName(detail.getGold().getName());


                        detailDTO.setGoldImage(detail.getGold().getImage());

                    }


                    return detailDTO;


                }).toList();


        dto.setOrderDetails(details);

    }

}