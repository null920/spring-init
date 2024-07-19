package com.light.springinit.exception;

import com.light.springinit.exception.errorcode.ErrorCode;

/**
 * 帖子异常
 *
 * @author null&&
 * @Date 2024/7/16 16:46
 */
public class PostException extends BizException {
    public PostException(ErrorCode errorCode) {
        super(errorCode);
    }

    public PostException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public PostException(String message, Throwable cause, ErrorCode errorCode) {
        super(message, cause, errorCode);
    }

    public PostException(Throwable cause, ErrorCode errorCode) {
        super(cause, errorCode);
    }

    public PostException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, ErrorCode errorCode) {
        super(message, cause, enableSuppression, writableStackTrace, errorCode);
    }
}
