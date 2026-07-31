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

import java.util.List;

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

        return orderDetailRepository.findAll(pageable)
                .map(this::convertToDTO);
    }

    @Override
    public OrderDetailDTO findById(Integer id) {

        OrderDetail detail = orderDetailRepository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException("OrderDetail not found!")
                );

        return convertToDTO(detail);
    }

    @Override
    public List<OrderDetailDTO> findByOrderId(Integer orderId) {

        return orderDetailRepository.findByOrder_Id(orderId)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }



    @Override
    public void update(Integer id, OrderDetailUpdateForm form) {

        OrderDetail detail = orderDetailRepository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException("OrderDetail not found!")
                );

        detail.setQuantity(form.getQuantity());

        orderDetailRepository.save(detail);
    }

    @Override
    public void delete(Integer id) {

        OrderDetail detail = orderDetailRepository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException("OrderDetail not found!")
                );

        orderDetailRepository.delete(detail);
    }

    private OrderDetailDTO convertToDTO(OrderDetail detail) {

        OrderDetailDTO dto =
                modelMapper.map(detail, OrderDetailDTO.class);



        if (detail.getGold() != null) {

            dto.setGoldId(
                    detail.getGold().getId()
            );

            dto.setGoldName(
                    detail.getGold().getName()
            );

            dto.setGoldImage(
                    detail.getGold().getImage()
            );
        }

        return dto;
    }
}