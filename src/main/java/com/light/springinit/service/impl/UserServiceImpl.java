package com.light.springinit.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.light.springinit.common.response.UserOperateResponse;
import com.light.springinit.common.response.UserQueryResponse;
import com.light.springinit.domain.dto.UserLoginRequest;
import com.light.springinit.domain.dto.UserQueryRequest;
import com.light.springinit.domain.dto.UserRegisterRequest;
import com.light.springinit.domain.entity.User;
import com.light.springinit.domain.entity.convertor.UserConvertor;
import com.light.springinit.domain.info.UserInfo;
import com.light.springinit.domain.vo.LoginVO;
import com.light.springinit.exception.UserException;
import com.light.springinit.exception.errorcode.UserErrorCode;
import com.light.springinit.mapper.UserMapper;
import com.light.springinit.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

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

    @Override
    public UserOperateResponse userRegister(UserRegisterRequest userRegisterRequest) {
        if (userMapper.findByUsername(userRegisterRequest.getUsername()) != null) {
            throw new UserException(UserErrorCode.USER_NAME_EXIST);
        }
        User user = new User();
        user.register(userRegisterRequest);
        UserOperateResponse registerResult = new UserOperateResponse();
        if (save(user)) {
            User findUser = userMapper.findByUsername(userRegisterRequest.getUsername());
            Assert.notNull(findUser, UserErrorCode.USER_OPERATE_FAILED.getCode());
            registerResult.setSuccess(true);
        }
        return registerResult;
    }

    @Override
    public LoginVO userLogin(UserLoginRequest userLoginRequest) {
        UserQueryRequest userQueryRequest = new UserQueryRequest();
        userQueryRequest.setUsername(userLoginRequest.getUsername());
        UserQueryResponse<UserInfo> userQueryResponse = this.query(userQueryRequest);
        UserInfo userInfo = userQueryResponse.getData();
        if (userInfo == null) {
            throw new UserException(UserErrorCode.USER_NOT_EXIST);
        }
        if (!userInfo.getPassword().equals(DigestUtil.md5Hex(userLoginRequest.getPassword()))) {
            throw new UserException(UserErrorCode.USER_PASSWORD_ERROR);
        }
        return new LoginVO(userInfo);
    }

    @Override
    public UserQueryResponse<UserInfo> query(UserQueryRequest userQueryRequest) {
        User user = null;
        if (null != userQueryRequest.getUserId()) {
            user = userMapper.findUserById(userQueryRequest.getUserId());
        }

        if (user == null || StringUtils.isNotBlank(userQueryRequest.getUsername())) {
            user = userMapper.findByUsername(userQueryRequest.getUsername());
        }
        if (user == null) {
            throw new UserException(UserErrorCode.USER_NOT_EXIST);
        }
        UserQueryResponse<UserInfo> response = new UserQueryResponse<>();
        response.setSuccess(true);
        UserInfo userInfo = UserConvertor.INSTANCE.mapToVo(user);
        response.setData(userInfo);
        return response;
    }

    @Override
    public User findUserById(Long userId) {
        if (userId == null) {
            throw new UserException(UserErrorCode.USER_QUERY_PARAM_IS_NULL);
        }
        return userMapper.findUserById(userId);
    }
}




