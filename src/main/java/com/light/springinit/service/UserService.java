package com.light.springinit.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.light.springinit.common.response.UserOperateResponse;
import com.light.springinit.common.response.UserQueryResponse;
import com.light.springinit.domain.dto.UserLoginRequest;
import com.light.springinit.domain.dto.UserQueryRequest;
import com.light.springinit.domain.dto.UserRegisterRequest;
import com.light.springinit.domain.entity.User;
import com.light.springinit.domain.info.UserInfo;
import com.light.springinit.domain.vo.LoginVO;

/**
 * 用户服务接口
 *
 * @author Ycri
 * @description 针对表【user(用户)】的数据库操作Service
 * @createDate 2024-07-19 17:37:29
 */
public interface UserService extends IService<User> {

    /**
     * 用户注册
     *
     * @param userRegisterRequest 用户注册请求
     * @return 注册结果
     */
    UserOperateResponse userRegister(UserRegisterRequest userRegisterRequest);


    /**
     * 用户登录
     *
     * @param userLoginRequest 用户登录请求
     * @return 登录信息VO
     */
    UserInfo userLogin(UserLoginRequest userLoginRequest);

    /**
     * 查询用户
     *
     * @param userQueryRequest 用户查询请求
     * @return 用户查询响应
     */
    UserQueryResponse<UserInfo> query(UserQueryRequest userQueryRequest);

    /**
     * 根据 id 获取用户信息
     *
     * @param userId 用户id
     * @return 用户信息
     */
    User findUserById(Long userId);


    /**
     * 检查用户是否存在和检查用户状态
     *
     * @param userId 用户id
     */
    void checkUserExistAndStatus(Long userId);

}
