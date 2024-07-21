package com.light.springinit.domain.vo;

import com.light.springinit.constant.UserRole;
import com.light.springinit.domain.info.UserInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户视图（脱敏）
 *
 * @author null&&
 * @Date 2024/7/21 16:50
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserVO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * user_id
     */
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 用户简介
     */
    private String userProfile;

    /**
     * 用户角色：USER/ADMIN/BAN
     */
    private UserRole userRole;

    /**
     * 创建时间
     */
    private Date createTime;

    public UserVO(UserInfo userInfo) {
        this.userId = userInfo.getUserId();
        this.username = userInfo.getUsername();
        this.phone = userInfo.getPhone();
        this.email = userInfo.getEmail();
        this.userAvatar = userInfo.getUserAvatar();
        this.userProfile = userInfo.getUserProfile();
        this.userRole = userInfo.getUserRole();
    }
}
