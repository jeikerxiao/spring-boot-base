package com.sinocare.base.dto.user;

import lombok.Data;

/**
 * Description: spring-boot-base
 * Created by jeikerxiao on 2018/6/28 下午3:46
 */
@Data
public class PasswordDto {

    /**
     * 原密码
     */
    private String password;
    /**
     * 新密码
     */
    private String newPassword;
}
