package com.vti.testtingsystem.exception;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BusinessException extends RuntimeException{
    private Integer status =500;
    private String message;
}
