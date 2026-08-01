package com.vti.gold.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {

    private Long cartId;

    private Integer goldId;

    private String goldName;

    private String type;

    private BigDecimal weight;

    private BigDecimal price;

    private String image;

    private Integer quantity;

    private Integer stock;
}