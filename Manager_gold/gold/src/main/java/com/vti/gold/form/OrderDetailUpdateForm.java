package com.vti.gold.form;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailUpdateForm {
    @NotNull(message = "Số lượng không được để trống")
    private Integer quantity;


    @NotNull(message = "Giá không được để trống")
    private Double price;
}
