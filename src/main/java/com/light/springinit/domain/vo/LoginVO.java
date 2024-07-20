package com.light.springinit.domain.vo;

import cn.dev33.satoken.stp.StpUtil;
import com.light.springinit.constant.UserRole;
import com.light.springinit.domain.info.UserInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 登录视图VO
 *
 * @author null&&
 * @Date 2024/7/16 17:10
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class LoginVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户标识，如用户ID
     */
    private String userId;

    /**
     * 用户角色
     */
    private UserRole userRole;

    /**
     * 访问令牌
     */
    private String token;

    /**
     * 令牌过期时间
     */
    private Long tokenExpiration;


    public LoginVO(UserInfo userInfo) {
        this.userId = userInfo.getUserId().toString();
        this.userRole = userInfo.getUserRole();
        this.token = StpUtil.getTokenValue();
        this.tokenExpiration = StpUtil.getTokenTimeout();
    }
}
