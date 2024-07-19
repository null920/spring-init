package com.light.springinit.mapper;

import com.light.springinit.domain.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import javax.validation.constraints.NotNull;

/**
 * @author Ycri
 * @description 针对表【user(用户)】的数据库操作Mapper
 * @createDate 2024-07-19 17:37:29
 * @Entity com.light.springinit.domain.entity.User
 */
public interface UserMapper extends BaseMapper<User> {
    /**
     * 根据用户id 查询用户
     *
     * @param userId 用户id
     * @return 用户信息
     */
    User findUserById(@NotNull Long userId);

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return 用户操作响应
     */
    User findByUsername(@NotNull String username);
}




