package com.vti.gold.dto;

import com.vti.gold.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class OrderDTO {  private Integer id;

    private Double totalPrice;

    private OrderStatus status;

    private LocalDateTime createdAt;

    private Integer userId;

    private String username;
}
