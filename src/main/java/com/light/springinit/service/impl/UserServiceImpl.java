package com.light.springinit.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.light.springinit.common.response.UserOperateResponse;
import com.light.springinit.common.response.UserQueryResponse;
import com.light.springinit.constant.UserRole;
import com.light.springinit.domain.dto.UserLoginRequest;
import com.light.springinit.domain.dto.UserQueryRequest;
import com.light.springinit.domain.dto.UserRegisterRequest;
import com.light.springinit.domain.entity.User;
import com.light.springinit.domain.entity.convertor.UserConvertor;
import com.light.springinit.domain.info.UserInfo;
import com.light.springinit.exception.UserException;
import com.light.springinit.exception.errorcode.UserErrorCode;
import com.light.springinit.mapper.UserMapper;
import com.light.springinit.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Ycri
 * @description 针对表【user(用户)】的数据库操作Service实现
 * @createDate 2024-07-19 17:37:29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 用户注册
     *
     * @param userRegisterRequest 用户注册请求
     * @return 注册结果
     */
    @Override
    public UserOperateResponse userRegister(UserRegisterRequest userRegisterRequest) {
        // 判断用户名是否重复
        if (userMapper.findByUsername(userRegisterRequest.getUsername()) != null) {
            throw new UserException(UserErrorCode.USER_NAME_EXIST);
        }
        if (!userRegisterRequest.getPassword().equals(userRegisterRequest.getCheckPassword())) {
            throw new UserException(UserErrorCode.USER_CHECK_PASSWORD_NOT_SAME);
        }
        User user = new User();
        user.register(userRegisterRequest);
        UserOperateResponse registerResult = new UserOperateResponse();
        if (save(user)) {
            User findUser = userMapper.findByUsername(userRegisterRequest.getUsername());
            if (findUser == null) {
                throw new UserException(UserErrorCode.USER_OPERATE_FAILED);
            }
            registerResult.setSuccess(true);
        }
        return registerResult;
    }

    /**
     * 用户登录
     *
     * @param userLoginRequest 用户登录请求
     * @return 登录信息VO
     */
    @Override
    public UserInfo userLogin(UserLoginRequest userLoginRequest) {
        UserQueryRequest userQueryRequest = new UserQueryRequest();
        userQueryRequest.setUsername(userLoginRequest.getUsername());
        // 查询用户
        UserQueryResponse<UserInfo> userQueryResponse = this.query(userQueryRequest);
        UserInfo userInfo = userQueryResponse.getData();
        // 用户不存在
        if (userInfo == null) {
            throw new UserException(UserErrorCode.USER_NOT_EXIST);
        }
        // 用户被封禁
        if (userInfo.getUserRole() == UserRole.BAN) {
            throw new UserException(UserErrorCode.USER_STATUS_IS_BAN);
        }
        // 密码错误
        if (!userInfo.getPassword().equals(DigestUtil.md5Hex(userLoginRequest.getPassword()))) {
            throw new UserException(UserErrorCode.USER_PASSWORD_ERROR);
        }
        return userInfo;
    }


    /**
     * 查询用户
     *
     * @param userQueryRequest 用户查询请求
     * @return 用户查询响应
     */
    @Override
    public UserQueryResponse<UserInfo> query(UserQueryRequest userQueryRequest) {
        User user = null;
        // 根据用户id查询
        if (null != userQueryRequest.getUserId()) {
            user = userMapper.findUserById(userQueryRequest.getUserId());
        }
        // 根据用户名查询
        if (user == null || StringUtils.isNotBlank(userQueryRequest.getUsername())) {
            user = userMapper.findByUsername(userQueryRequest.getUsername());
        }
        // 用户不存在
        if (user == null) {
            throw new UserException(UserErrorCode.USER_NOT_EXIST);
        }
        UserQueryResponse<UserInfo> response = new UserQueryResponse<>();
        response.setSuccess(true);
        UserInfo userInfo = UserConvertor.INSTANCE.mapToInfo(user);
        response.setData(userInfo);
        return response;
    }


    /**
     * 根据 id 获取用户信息
     *
     * @param userId 用户id
     * @return 用户信息
     */
    @Override
    public User findUserById(Long userId) {
        if (userId == null) {
            throw new UserException(UserErrorCode.USER_QUERY_PARAM_IS_NULL);
        }
        return userMapper.findUserById(userId);
    }


    /**
     * 检查用户是否存在和检查用户状态
     *
     * @param userId 用户id
     */
    @Override
    public void checkUserExistAndStatus(Long userId) {
        // 校验用户是否存在
        User userById = userMapper.findUserById(userId);
        if (userById == null) {
            throw new UserException(UserErrorCode.USER_NOT_EXIST);
        }
        // 校验用户状态
        if (userById.getUserRole() == UserRole.BAN) {
            throw new UserException(UserErrorCode.USER_STATUS_IS_BAN);
        }
    }
}




