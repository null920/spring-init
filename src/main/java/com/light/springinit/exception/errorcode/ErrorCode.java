package com.light.springinit.exception.errorcode;

/**
 * 错误码
 *
 * @author null&&
 * @Date 2024/7/16 16:42
 */
public interface ErrorCode {
    /**
     * 错误码
     *
     * @return 错误码
     */
    String getCode();

    /**
     * 错误信息
     *
     * @return 错误信息
     */
    String getMessage();
}
