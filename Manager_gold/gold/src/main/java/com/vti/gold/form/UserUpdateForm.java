package com.vti.gold.form;

import com.vti.gold.entity.Role;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateForm {

    private String fullName;

    @Email(message = "Email không đúng định dạng")
    private String email;

    private String phone;

    private String address;

}
