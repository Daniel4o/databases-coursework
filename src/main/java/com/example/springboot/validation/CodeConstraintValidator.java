package com.example.springboot.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CodeConstraintValidator implements ConstraintValidator<EmployeeCode, String> {

    private String employeePrefix;

    @Override
    public void initialize(EmployeeCode employeeCode) {
        employeePrefix = employeeCode.value();
    }

    @Override
    public boolean isValid(String code, ConstraintValidatorContext constraintValidatorContext) {
       
        boolean result;

        if(code != null) {
       result = code.startsWith(employeePrefix);
        }
        else {
            result = true;
        }
        return result;
  
    }
    
}
