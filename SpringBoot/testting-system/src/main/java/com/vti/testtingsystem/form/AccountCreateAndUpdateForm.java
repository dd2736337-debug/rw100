package com.vti.testtingsystem.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountCreateAndUpdateForm {
    @NotBlank(message = "UserName phải có giá trị")
    private String userName;
    @NotBlank(message = "FullName phải có giá trị")
    @Length(max = 100,message = "Không dài quá 100 kí tự")
    private String fullName;
    @NotBlank(message = "Email phải có giá trị")
    @Email(message = "Email không đúng định dạng")
    private String email;
    @NotNull(message = "Depaartment phải có giá trị")
    @PositiveOrZero(message = "DepartmentId phải lớn hơn không")
    private Integer departmentId;
    @NotNull(message = "Position phải có giá trị")
    private Integer positionId;
}
