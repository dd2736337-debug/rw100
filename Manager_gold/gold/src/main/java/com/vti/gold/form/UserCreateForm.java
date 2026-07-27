package com.vti.gold.form;

import com.vti.gold.entity.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateForm {

    private Integer id;

    private String username;

    private String password;

    private String fullName;

    private String email;

    private String phone;

    private String address;

    private Role role;
}
