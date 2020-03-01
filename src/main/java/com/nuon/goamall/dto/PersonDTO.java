package com.nuon.goamall.dto;

import com.nuon.goamall.validators.PasswordEqual;
import lombok.Getter;
import lombok.Setter;

/**
 * 个人信息
 */
@Getter
@Setter
@PasswordEqual(message = "两次密码输入不一致")
public class PersonDTO {

    private String name;

    private String age;

    private String password1;

    private String password2;
}
