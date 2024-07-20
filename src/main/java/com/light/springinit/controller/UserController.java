package com.light.springinit.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;
import com.light.springinit.common.result.Result;
import com.light.springinit.common.response.UserOperateResponse;
import com.light.springinit.domain.dto.UserLoginRequest;
import com.light.springinit.domain.dto.UserQueryRequest;
import com.light.springinit.domain.dto.UserRegisterRequest;
import com.light.springinit.domain.entity.User;
import com.light.springinit.domain.entity.convertor.UserConvertor;
import com.light.springinit.domain.info.UserInfo;
import com.light.springinit.domain.vo.LoginVO;
import com.light.springinit.exception.UserException;
import com.light.springinit.param.UserLoginParam;
import com.light.springinit.param.UserRegisterParam;
import com.light.springinit.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;

import static com.light.springinit.exception.errorcode.UserErrorCode.USER_NOT_EXIST;

/**
 * @author null&&
 * @Date 2024/7/20 09:12
 */
@RestController
@RequestMapping("user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    /**
     * 默认登录超时时间：3 天
     */
    private static final Integer DEFAULT_LOGIN_SESSION_TIMEOUT = 60 * 60 * 24 * 3;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result<Boolean> register(@Valid @RequestBody UserRegisterParam userRegisterParam) {
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest(userRegisterParam);
        UserOperateResponse registerResult = userService.userRegister(userRegisterRequest);
        if (registerResult.getSuccess() != null) {
            return Result.success(true);
        }
        return Result.error(registerResult.getResponseCode(), registerResult.getResponseMessage());
    }

    @PostMapping("/login")
    public Result<LoginVO> login(@Valid @RequestBody UserLoginParam userLoginParam) {
        UserLoginRequest userLoginRequest = new UserLoginRequest();
        userLoginRequest.setUsername(userLoginParam.getUsername());
        userLoginRequest.setPassword(userLoginParam.getPassword());
        UserInfo userInfo = userService.userLogin(userLoginRequest);
        // 登录
        StpUtil.login(userInfo.getUserId(), new SaLoginModel()
                .setExtra("roles", Collections.singletonList(userInfo.getUserRole()))
                .setIsLastingCookie(userLoginParam.getRememberMe())
                .setTimeout(DEFAULT_LOGIN_SESSION_TIMEOUT));
        StpUtil.getSession().set(userInfo.getUserId().toString(), userInfo);
        LoginVO loginVO = new LoginVO(userInfo);
        return Result.success(loginVO);
    }

    @GetMapping("/me")
    @SaCheckLogin
    public Result<UserInfo> getMyselfInfo() {
        String userId = (String) StpUtil.getLoginId();
        UserQueryRequest userQueryRequest = new UserQueryRequest();
        userQueryRequest.setUserId(Long.valueOf(userId));
        User user = userService.findUserById(Long.valueOf(userId));
        if (user == null) {
            throw new UserException(USER_NOT_EXIST);
        }
        user.setPassword(null);
        return Result.success(UserConvertor.INSTANCE.mapToVo(user));
    }

    @PostMapping("/logout")
    @SaCheckLogin
    public Result<Boolean> logout() {
        StpUtil.logout();
        return Result.success(true);
    }
}
