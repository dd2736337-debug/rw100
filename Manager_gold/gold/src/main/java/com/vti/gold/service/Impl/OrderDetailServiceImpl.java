package com.vti.gold.service.Impl;

import com.vti.gold.dto.OrderDetailDTO;
import com.vti.gold.entity.Gold;
import com.vti.gold.entity.Order;
import com.vti.gold.entity.OrderDetail;
import com.vti.gold.form.OrderDetailCreateForm;
import com.vti.gold.form.OrderDetailUpdateForm;
import com.vti.gold.repository.GoldRepository;
import com.vti.gold.repository.OrderDetailRepository;
import com.vti.gold.repository.OrderRepository;
import com.vti.gold.service.IOrderDetailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl implements IOrderDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;


    @Autowired
    private OrderRepository orderRepository;


    @Autowired
    private GoldRepository goldRepository;


    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<OrderDetailDTO> findAll(Pageable pageable) {
        Page<OrderDetail> page = orderDetailRepository.findAll(pageable);


        return page.map(detail -> {


            OrderDetailDTO dto = modelMapper.map(detail, OrderDetailDTO.class);


            if (detail.getOrder() != null) {

                dto.setOrderId(detail.getOrder().getId());
            }


            if (detail.getGold() != null) {

                dto.setGoldId(detail.getGold().getId());


                dto.setGoldName(detail.getGold().getName());
            }


            return dto;

        });
    }

    @Override
    public OrderDetailDTO findById(Integer id) {
        OrderDetail detail = orderDetailRepository.findById(id).orElseThrow(() -> new RuntimeException("OrderDetail not found!"));


        OrderDetailDTO dto = modelMapper.map(detail, OrderDetailDTO.class);


        if (detail.getOrder() != null) {

            dto.setOrderId(detail.getOrder().getId());
        }


        if (detail.getGold() != null) {

            dto.setGoldId(detail.getGold().getId());


            dto.setGoldName(detail.getGold().getName());
        }


        return dto;
    }

    @Override
    public void create(OrderDetailCreateForm form) {
        Order order = orderRepository.findById(form.getOrderId()).orElseThrow(() -> new RuntimeException("Order not found!"));


        Gold gold = goldRepository.findById(form.getGoldId()).orElseThrow(() -> new RuntimeException("Gold not found!"));


        OrderDetail detail = new OrderDetail();


        detail.setOrder(order);


        detail.setGold(gold);


        detail.setQuantity(form.getQuantity());


        detail.setPrice(form.getPrice());


        orderDetailRepository.save(detail);

    }

    @Override
    public void update(Integer id, OrderDetailUpdateForm form) {
        OrderDetail detail = orderDetailRepository.findById(id).orElseThrow(() -> new RuntimeException("OrderDetail not found!"));


        detail.setQuantity(form.getQuantity());


        detail.setPrice(form.getPrice());


        orderDetailRepository.save(detail);

    }

    @Override
    public void delete(Integer id) {
        OrderDetail detail = orderDetailRepository.findById(id).orElseThrow(() -> new RuntimeException("OrderDetail not found!"));


        orderDetailRepository.delete(detail);

    }


}

