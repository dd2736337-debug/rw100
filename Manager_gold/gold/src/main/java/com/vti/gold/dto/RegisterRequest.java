package com.vti.gold.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotBlank(message = "Username không được để trống")
    private String username;


    @NotBlank(message = "Password không được để trống")
    private String password;


    @NotBlank(message = "Họ tên không được để trống")
    private String fullName;


    @Email(message = "Email không đúng định dạng")
    private String email;


    @NotBlank(message = "Số điện thoại không được để trống")
    @Pattern(
            regexp = "0[0-9]{9}",
            message = "Số điện thoại phải có 10 số"
    )
    private String phone;


    @NotBlank(message = "Địa chỉ không được để trống")
    private String address;
}
