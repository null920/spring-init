package com.light.springinit.domain.entity;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.annotation.*;
import com.light.springinit.domain.dto.PostCreateRequest;
import com.light.springinit.domain.dto.PostUpdateRequest;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 帖子
 *
 * @TableName post
 */
@TableName(value = "post")
@Data
public class Post implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
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
    private String tags;

    /**
     * 点赞数
     */
    private Integer thumbNum;

    /**
     * 收藏数
     */
    private Integer favourNum;

    /**
     * 创建用户 id
     */
    private Long userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除
     */
    @TableLogic
    private Integer deleted;

    public Post add(PostCreateRequest postCreateRequest) {
        this.setTitle(postCreateRequest.getTitle());
        this.setContent(postCreateRequest.getContent());
        List<String> tagList = postCreateRequest.getTags();
        if (tagList != null) {
            this.setTags(JSONUtil.toJsonStr(tagList));
        }
        return this;
    }

    public Post update(PostUpdateRequest postUpdateRequest) {
        this.setPostId(postUpdateRequest.getPostId());
        this.setTitle(postUpdateRequest.getTitle());
        this.setContent(postUpdateRequest.getContent());
        List<String> tagList = postUpdateRequest.getTags();
        if (tagList != null) {
            this.setTags(JSONUtil.toJsonStr(tagList));
        }
        return this;
    }
}