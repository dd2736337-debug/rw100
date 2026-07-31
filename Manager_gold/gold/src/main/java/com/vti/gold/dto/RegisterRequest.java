package com.vti.gold.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {


    @NotBlank(
            message = "Username không được để trống"
    )
    private String username;


    @NotBlank(
            message = "Password không được để trống"
    )
    @Size(
            min = 6,
            message = "Password phải có ít nhất 6 ký tự"
    )
    private String password;


    @NotBlank(
            message = "Họ tên không được để trống"
    )
    private String fullName;


    @Email(
            message = "Email không đúng định dạng"
    )
    private String email;


    @NotBlank(
            message = "Số điện thoại không được để trống"
    )
    @Pattern(
            regexp = "^0[0-9]{9}$",
            message = "Số điện thoại phải có 10 số và bắt đầu bằng 0"
    )
    private String phone;


    @NotBlank(
            message = "Địa chỉ không được để trống"
    )
    private String address;

}