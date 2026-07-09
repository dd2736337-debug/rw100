package com.vti.testtingsystem.form;

import com.vti.testtingsystem.Enum.PositionName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PositionCreateAndUpdateForm {
    private PositionName name;
}
