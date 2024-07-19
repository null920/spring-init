package com.light.springinit.param;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * 用户登录参数
 *
 * @author null&&
 * @Date 2024/7/19 19:25
 */
@Setter
@Getter
public class UserLoginParam {
    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 记住我
     */
    private Boolean rememberMe;
}
