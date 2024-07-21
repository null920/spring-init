package com.light.springinit.domain.vo;

import com.light.springinit.domain.info.PostInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 帖子视图VO
 *
 * @author null&&
 * @Date 2024/7/17 11:03
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class PostVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
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
    private UserVO userVO;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    public PostVO(PostInfo postInfo) {
        this.postId = postInfo.getPostId();
        this.title = postInfo.getTitle();
        this.content = postInfo.getContent();
        this.tags = postInfo.getTags();
        this.thumbNum = postInfo.getThumbNum();
        this.favourNum = postInfo.getFavourNum();
        this.userVO = new UserVO(postInfo.getUserInfo());
        this.createTime = postInfo.getCreateTime();
        this.updateTime = postInfo.getUpdateTime();
    }
}
