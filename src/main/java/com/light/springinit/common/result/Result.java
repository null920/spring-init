package com.light.springinit.common.result;

import com.light.springinit.common.SingleResponse;
import lombok.Getter;
import lombok.Setter;

import static com.light.springinit.common.response.ResponseCode.SUCCESS;


/**
 * 返回结果
 *
 * @author null&&
 * @Date 2024/7/16 17:01
 */
@Getter
@Setter
public class Result<T> {
    /**
     * 状态码
     */
    private String code;

    /**
     * 是否成功
     */
    private Boolean success;

    /**
     * 消息描述
     */
    private String message;

    /**
     * 数据，可以是任何类型的VO
     */
    private T data;

    public Result() {
    }

    public Result(Boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public Result(Boolean success, String code, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.code = code;
    }

    public Result(SingleResponse<T> singleResponse) {
        this.success = singleResponse.getSuccess();
        this.data = singleResponse.getData();
        this.code = singleResponse.getResponseCode();
        this.message = singleResponse.getResponseMessage();
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(true, SUCCESS.name(), SUCCESS.name(), data);
    }

    public static <T> Result<T> error(String errorCode, String errorMsg) {
        return new Result<>(false, errorCode, errorMsg, null);
    }
}
