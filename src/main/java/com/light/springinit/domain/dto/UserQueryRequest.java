package com.light.springinit.domain.dto;

import com.light.springinit.common.BaseRequest;
import lombok.*;

/**
 * 用户查询请求
 *
 * @author null&&
 * @Date 2024/7/19 19:22
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserQueryRequest extends BaseRequest {
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户名
     */
    private String username;
}
