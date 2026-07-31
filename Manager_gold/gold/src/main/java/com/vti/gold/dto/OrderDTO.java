package com.vti.gold.dto;

import com.vti.gold.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class OrderDTO {

    private Integer id;

    private BigDecimal totalPrice;

    private OrderStatus status;

    private LocalDateTime createdAt;

    private Integer userId;

    private String username;

    private String fullName;

    private List<OrderDetailDTO> orderDetails;
}