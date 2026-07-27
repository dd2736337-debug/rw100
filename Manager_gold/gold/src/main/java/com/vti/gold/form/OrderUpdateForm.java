package com.vti.gold.form;

import com.vti.gold.entity.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderUpdateForm {
    @NotNull(message = "Tổng tiền không được để trống")
    private Double totalPrice;


    @NotNull(message = "Trạng thái không được để trống")
    private OrderStatus status;

}
