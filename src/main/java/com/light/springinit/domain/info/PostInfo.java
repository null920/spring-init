package com.light.springinit.domain.info;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 帖子信息
 *
 * @author null&&
 * @Date 2024/7/17 10:51
 */
@Getter
@Setter
@NoArgsConstructor
public class PostInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * post_id
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
    private UserInfo userInfo;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
