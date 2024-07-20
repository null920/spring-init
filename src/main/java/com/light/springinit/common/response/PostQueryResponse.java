package com.light.springinit.common.response;

import com.light.springinit.common.BaseResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author null&&
 * @Date 2024/7/20 20:00
 */
@Setter
@Getter
@ToString
public class PostQueryResponse<T> extends BaseResponse {
    private static final long serialVersionUID = 1L;
    private T data;
}

