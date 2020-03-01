package com.nuon.goamall.validators;

import com.nuon.goamall.dto.PersonDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 输入两次密码校验
 */
public class PasswordValidator implements ConstraintValidator<PasswordEqual, PersonDTO> {

    /**
     * 密码最小长度
     */
    private int min;

    /**
     * 密码最大长度
     */
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
