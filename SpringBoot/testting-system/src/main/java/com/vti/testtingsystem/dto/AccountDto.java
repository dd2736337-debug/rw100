package com.vti.testtingsystem.dto;

import com.vti.testtingsystem.entity.Department;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
    private String userName;
    private String fullName;
    private String email;
    private Integer departmentId;
    private LocalDateTime createDate;
}
