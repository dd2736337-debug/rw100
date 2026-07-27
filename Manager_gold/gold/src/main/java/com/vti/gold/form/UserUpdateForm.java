package com.vti.gold.form;

import com.vti.gold.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateForm {

    private String fullName;

    private String email;

    private String phone;

    private String address;

    private Role role;
}
