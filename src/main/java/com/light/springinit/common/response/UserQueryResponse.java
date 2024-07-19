package com.light.springinit.common.response;

import com.light.springinit.common.BaseResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 用户查询响应
 *
 * @author null&&
 * @Date 2024/7/19 19:20
 */
@Setter
@Getter
@ToString
public class UserQueryResponse<T> extends BaseResponse {
    private static final long serialVersionUID = 1L;

    private T data;
}
