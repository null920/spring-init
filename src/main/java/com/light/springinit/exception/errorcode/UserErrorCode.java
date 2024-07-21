package com.light.springinit.exception.errorcode;

/**
 * 用户错误码
 *
 * @author null&&
 * @Date 2024/7/16 16:46
 */
public enum UserErrorCode implements ErrorCode {
    /**
     * 用户不存在
     */
    USER_NOT_EXIST("USER_NOT_EXIST", "用户不存在"),

    /**
     * 用户未登录
     */
    USER_NOT_LOGIN("USER_NOT_LOGIN", "用户未登录"),

    /**
     * 用户密码错误
     */
    USER_PASSWORD_ERROR("USER_PASSWORD_ERROR", "用户密码错误"),

    /**
     * 确认密码不一致
     */
    USER_CHECK_PASSWORD_NOT_SAME("USER_CHECK_PASSWORD_NOT_SAME", "确认密码不一致"),

    /**
     * 用户状态不能进行操作
     */
    USER_STATUS_CANT_OPERATE("USER_STATUS_CANT_OPERATE", "用户状态不能进行操作"),

    /**
     * 用户状态未激活成功
     */
    USER_STATUS_IS_NOT_ACTIVE("USER_STATUS_IS_NOT_ACTIVE", "用户状态未激活成功"),

    /**
     * 用户操作失败
     */
    USER_OPERATE_FAILED("USER_OPERATE_FAILED", "用户操作失败"),

    /**
     * 用户密码校验失败
     */
    USER_PASSWD_CHECK_FAIL("USER_PASSWD_CHECK_FAIL", "用户密码校验失败"),

    /**
     * 用户查询失败
     */
    USER_QUERY_FAIL("USER_QUERY_FAIL", "用户查询失败"),

    /**
     * 用户查询参数为空
     */
    USER_QUERY_PARAM_IS_NULL("USER_QUERY_PARAM_IS_NULL", "用户查询参数为空"),

    /**
     * 用户名已存在
     */
    USER_NAME_EXIST("USER_NAME_EXIST", "用户名已存在"),

    /**
     * 用户被封禁
     */
    USER_STATUS_IS_BAN("USER_STATUS_IS_BAN", "用户被封禁"),

    /**
     * 用户状态不能进行激活
     */
    USER_STATUS_IS_NOT_AUTH("USER_STATUS_IS_NOT_AUTH", "用户状态不能进行激活");

    private String code;

    private String message;

    UserErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
