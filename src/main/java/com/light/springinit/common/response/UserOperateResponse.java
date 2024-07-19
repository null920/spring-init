package com.light.springinit.common.response;

import com.light.springinit.common.BaseResponse;
import com.light.springinit.domain.info.UserInfo;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户操作响应
 *
 * @author null&&
 * @Date 2024/7/16 17:59
 */
@Getter
@Setter
public class UserOperateResponse extends BaseResponse {
    /**
     * 用户信息
     */
    private UserInfo userInfo;
}
