package com.light.springinit.domain.dto;

import com.light.springinit.common.BaseRequest;
import lombok.*;

/**
 * 用户登录请求
 *
 * @author null&&
 * @Date 2024/7/19 20:15
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginRequest extends BaseRequest {

    private static final long serialVersionUID = 1L;
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;
}
