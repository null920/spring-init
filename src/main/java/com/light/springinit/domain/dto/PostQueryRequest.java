package com.light.springinit.domain.dto;

import com.light.springinit.common.PageRequest;
import lombok.*;

/**
 * 帖子查询请求
 *
 * @author null&&
 * @Date 2024/7/20 19:47
 */

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PostQueryRequest extends PageRequest {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 帖子id
     */
    private Long postId;
}
