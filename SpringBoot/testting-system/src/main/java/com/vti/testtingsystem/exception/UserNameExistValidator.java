package com.vti.testtingsystem.exception;

import com.vti.testtingsystem.repository.IAccountRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UserNameExistValidator implements ConstraintValidator<UserNameExist,String> {

    @Autowired
    private IAccountRepository repository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !repository.existsByUserName(value);
    }
}
