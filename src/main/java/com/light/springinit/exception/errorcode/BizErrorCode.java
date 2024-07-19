package com.light.springinit.exception.errorcode;

/**
 * 业务通用错误码
 *
 * @author null&&
 * @Date 2024/7/16 16:44
 */
public enum BizErrorCode implements ErrorCode {

    /**
     * HTTP 客户端错误
     */
    HTTP_CLIENT_ERROR("HTTP_CLIENT_ERROR", "HTTP 客户端错误"),

    /**
     * HTTP 服务端错误
     */
    HTTP_SERVER_ERROR("HTTP_SERVER_ERROR", "HTTP 服务端错误"),


    /**
     * 重复请求
     */
    DUPLICATED("DUPLICATED", "重复请求");


    private String code;


    private String message;

    BizErrorCode(String code, String message) {
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
