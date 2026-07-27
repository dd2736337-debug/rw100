package com.vti.gold.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoldDTO {

    private Integer id;

    private String name;

    private String type;

    private Double weight;

    private Double price;

    private Integer quantity;

    private String image;

    private Integer categoryId;

    private String categoryName;
}
