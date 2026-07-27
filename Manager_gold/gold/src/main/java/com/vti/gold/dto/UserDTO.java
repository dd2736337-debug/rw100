package com.vti.gold.dto;

import com.vti.gold.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Integer id;

    private String username;


    private String fullName;

    private String email;

    private String phone;

    private String address;

    private Role role;
}
