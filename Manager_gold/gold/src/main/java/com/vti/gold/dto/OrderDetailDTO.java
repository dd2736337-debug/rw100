package com.vti.gold.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO {
    private Integer id;

    private Integer quantity;

    private Double price;


    private Integer orderId;


    private Integer goldId;

    private String goldName;
}
