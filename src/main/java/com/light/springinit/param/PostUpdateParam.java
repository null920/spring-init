package com.light.springinit.param;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author null&&
 * @Date 2024/7/20 20:01
 */
@Setter
@Getter
public class PostUpdateParam {

    @NotBlank(message = "帖子id不能为空")
    private String postId;
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
