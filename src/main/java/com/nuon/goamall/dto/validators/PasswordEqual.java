package com.nuon.goamall.dto.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 输入两次密码校验
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
@Constraint(validatedBy = PasswordValidator.class)
public @interface PasswordEqual {

    int min() default 4;

    int max() default 8;

    String message() default "passwords are not equal";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
