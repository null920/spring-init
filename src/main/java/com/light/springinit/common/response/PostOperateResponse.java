package com.light.springinit.common.response;

import com.light.springinit.common.BaseResponse;
import com.light.springinit.domain.info.PostInfo;
import lombok.Getter;
import lombok.Setter;

/**
 * @author null&&
 * @Date 2024/7/20 19:33
 */
@Getter
@Setter
public class PostOperateResponse extends BaseResponse {
    private static final long serialVersionUID = 1L;
    /**
     * 帖子信息
     */
    private PostInfo postInfo;
}
