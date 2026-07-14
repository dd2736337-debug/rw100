package com.vti.testtingsystem.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountSearchForm {
    private String userName;
    private String fullName;
    private String email;
    private Integer departmentId;
    private Integer positionId;


}
