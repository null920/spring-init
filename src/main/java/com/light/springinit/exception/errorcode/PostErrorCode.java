package com.light.springinit.exception.errorcode;

/**
 * 帖子错误码
 *
 * @author null&&
 * @Date 2024/7/16 16:46
 */
public enum PostErrorCode implements ErrorCode {
    /**
     * 帖子不存在
     */
    POST_NOT_EXIST("POST_NOT_EXIST", "帖子不存在"),

    /**
     * 帖子查询参数错误
     */
    POST_QUERY_PARAM_ERROR("POST_QUERY_PARAM_ERROR", "帖子查询参数错误"),

    /**
     * 帖子查询参数为空
     */
    POST_QUERY_PARAM_IS_NULL("POST_QUERY_PARAM_IS_NULL", "帖子查询参数为空"),

    /**
     * 没有权限进行此操作
     */
    POST_OPERATE_NO_AUTH("POST_OPERATE_NO_AUTH", "没有权限进行此操作"),

    /**
     * 帖子操作失败
     */
    POST_OPERATE_FAILED("POST_OPERATE_FAILED", "帖子操作失败"),

    /**
     * 帖子查询失败
     */
    POST_QUERY_FAIL("POST_QUERY_FAIL", "帖子查询失败");


    private String code;

    private String message;

    PostErrorCode(String code, String message) {
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
