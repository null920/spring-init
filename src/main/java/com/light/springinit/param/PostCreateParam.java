package com.light.springinit.param;

import lombok.Getter;
import lombok.Setter;


import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author null&&
 * @Date 2024/7/17 10:53
 */
@Setter
@Getter
public class PostCreateParam {
    /**
     * 标题
     */
    @NotBlank(message = "标题不能为空")
    private String title;

    /**
     * 内容
     */
    @NotBlank(message = "内容不能为空")
    private String content;

    /**
     * 标签列表（json 数组）
     */
    private List<String> tags;
}
