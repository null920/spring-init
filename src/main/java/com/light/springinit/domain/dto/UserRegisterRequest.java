package com.light.springinit.domain.dto;

import com.light.springinit.common.BaseRequest;
import lombok.*;

/**
 * 用户注册请求
 *
 * @author null&&
 * @Date 2024/7/19 19:11
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequest extends BaseRequest {
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 确认密码
     */
    private String checkPassword;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;
}
