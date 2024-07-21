package com.light.springinit.param;

import com.light.springinit.common.PageRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * @author null&&
 * @Date 2024/7/20 20:10
 */
@Setter
@Getter
public class PostQueryParam extends PageRequest {
    /**
     * 用户id
     */
    private String userId;

    /**
     * 帖子id
     */
    private String postId;
}
