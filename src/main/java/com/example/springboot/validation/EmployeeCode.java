package com.example.springboot.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = CodeConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EmployeeCode {
    
    public String value() default "DAN40";

    public String message() default "Must start with DAN40!";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};

}
