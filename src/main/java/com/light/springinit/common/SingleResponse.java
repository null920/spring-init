package com.light.springinit.common;

import lombok.Getter;
import lombok.Setter;

/**
 * 单个响应
 *
 * @author null&&
 * @Date 2024/7/16 17:04
 */
@Setter
@Getter
public class SingleResponse<T> extends BaseResponse {
    private static final long serialVersionUID = 1L;

    private T data;

    public static <T> SingleResponse<T> of(T data) {
        SingleResponse<T> singleResponse = new SingleResponse<>();
        singleResponse.setSuccess(true);
        singleResponse.setData(data);
        return singleResponse;
    }

    public static <T> SingleResponse<T> fail(String errorCode, String errorMessage) {
        SingleResponse<T> singleResponse = new SingleResponse<>();
        singleResponse.setSuccess(false);
        singleResponse.setResponseCode(errorCode);
        singleResponse.setResponseMessage(errorMessage);
        return singleResponse;
    }
}
