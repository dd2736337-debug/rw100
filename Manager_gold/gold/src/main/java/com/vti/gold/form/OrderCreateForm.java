package com.vti.gold.form;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreateForm {
    @NotNull(message = "User không được để trống")
    private Integer userId;

    @NotEmpty(message = "Đơn hàng phải có sản phẩm")
    private List<OrderDetailCreateForm> orderDetails;

}
