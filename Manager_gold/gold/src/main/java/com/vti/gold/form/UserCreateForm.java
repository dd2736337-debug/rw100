package com.vti.gold.form;

import com.vti.gold.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateForm {

    @NotBlank(message = "Username không được để trống")
    private String username;

    @Size(min = 6, message = "Password tối thiểu 6 ký tự")
    @NotBlank(message = "Password không được để trống")
    private String password;
    @NotBlank(message = "Họ tên không được để trống")
    private String fullName;
    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không đúng định dạng")
    private String email;
    @Pattern(regexp = "^0\\d{9}$", message = "Số điện thoại không hợp lệ")
    @NotBlank(message = "Số điện thoại không được để trống")
    private String phone;
    @NotBlank(message = "Địa chỉ  không được để trống")
    private String address;
    private Role role;


}
