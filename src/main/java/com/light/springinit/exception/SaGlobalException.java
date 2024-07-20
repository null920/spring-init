package com.light.springinit.exception;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.util.SaResult;
import com.light.springinit.exception.errorcode.UserErrorCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Sa-Token 全局异常处理
 *
 * @author null&&
 * @Date 2024/7/16 21:53
 */
@RestControllerAdvice
public class SaGlobalException {
    // 拦截：未登录异常
    @ExceptionHandler(NotLoginException.class)
    public SaResult handlerException(NotLoginException e) {
        e.printStackTrace();
        return SaResult.error(UserErrorCode.USER_NOT_LOGIN.getMessage());
    }

    // 拦截：缺少权限异常
    @ExceptionHandler(NotPermissionException.class)
    public SaResult handlerException(NotPermissionException e) {
        e.printStackTrace();
        return SaResult.error("缺少权限：" + e.getPermission());
    }
}
