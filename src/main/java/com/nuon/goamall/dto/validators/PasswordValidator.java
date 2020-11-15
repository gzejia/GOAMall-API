package com.nuon.goamall.dto.validators;

import com.nuon.goamall.dto.PersonDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 输入两次密码校验
 */
public class PasswordValidator implements ConstraintValidator<PasswordEqual, PersonDTO> {

    private int min;
    private int max;

    @Override
    public void initialize(PasswordEqual constraintAnnotation) {
        min = constraintAnnotation.min();
        max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(PersonDTO personDTO, ConstraintValidatorContext constraintValidatorContext) {
        String password1 = String.valueOf(personDTO.getPassword1());
        String password2 = String.valueOf(personDTO.getPassword2());
        return password1.equals(password2);
    }
}
