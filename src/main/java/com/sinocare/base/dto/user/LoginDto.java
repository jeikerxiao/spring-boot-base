package com.sinocare.base.dto.user;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Description: spring-boot-base
 * Created by jeikerxiao on 2018/6/27 下午4:10
 */
@Data
public class LoginDto {

    @NotBlank(message = "账号不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;
}
