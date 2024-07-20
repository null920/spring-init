package com.light.springinit.domain.dto;

import lombok.*;

import java.util.List;

/**
 * 帖子创建请求
 *
 * @author null&&
 * @Date 2024/7/20 19:36
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PostCreateRequest {
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
