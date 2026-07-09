package com.vti.testtingsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private Integer id;
    private String userName;
    private String fullName;
    private String email;
    private String departmentName;
    private String positionName;
    private LocalDateTime createDate;
}
