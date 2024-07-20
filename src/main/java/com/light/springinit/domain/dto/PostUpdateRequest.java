package com.light.springinit.domain.dto;

import lombok.*;

import java.util.List;

/**
 * @author null&&
 * @Date 2024/7/20 20:05
 */

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PostUpdateRequest {
    /**
     * 帖子id
     */
    private Long postId;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 标签列表（json 数组）
     */
    private List<String> tags;
}
