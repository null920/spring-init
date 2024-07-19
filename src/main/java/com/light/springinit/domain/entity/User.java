package com.light.springinit.domain.entity;

import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.light.springinit.constant.UserRole;
import com.light.springinit.domain.dto.UserRegisterRequest;
import com.light.springinit.param.UserRegisterParam;
import lombok.Data;

/**
 * 用户
 *
 * @TableName user
 */
@TableName(value = "user")
@Data
public class User implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * user_id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

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

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除
     */
    @TableLogic
    private Integer deleted;

    public User register(UserRegisterRequest userRegisterRequest) {
        this.setUsername(userRegisterRequest.getUsername());
        this.setPassword(DigestUtil.md5Hex(userRegisterRequest.getPassword()));
        this.setPhone(userRegisterRequest.getPhone());
        this.setEmail(userRegisterRequest.getEmail());
        this.setUserRole(UserRole.USER);
        return this;
    }
}