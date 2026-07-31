package com.vti.gold.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO {

    private Integer id;

    private Integer quantity;

    private BigDecimal price;


    private Integer goldId;

    private String goldName;

    private String goldImage;
}