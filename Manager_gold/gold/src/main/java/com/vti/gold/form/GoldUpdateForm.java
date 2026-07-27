package com.vti.gold.form;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoldUpdateForm {


    @NotBlank(message = "Tên vàng không được để trống")
    private String name;


    @NotBlank(message = "Loại vàng không được để trống")
    private String type;


    @NotNull(message = "Khối lượng không được để trống")
    @Positive(message = "Khối lượng phải lớn hơn 0")
    private Double weight;


    @NotNull(message = "Giá không được để trống")
    @Positive(message = "Giá phải lớn hơn 0")
    private Double price;


    @NotNull(message = "Số lượng không được để trống")
    @Min(value = 0, message = "Số lượng không được âm")
    private Integer quantity;


    private MultipartFile image;


    @NotNull(message = "Category không được để trống")
    private Integer categoryId;


}
