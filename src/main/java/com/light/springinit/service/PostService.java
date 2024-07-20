package com.light.springinit.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.light.springinit.common.PageResponse;
import com.light.springinit.common.response.PostOperateResponse;
import com.light.springinit.common.response.PostQueryResponse;
import com.light.springinit.domain.dto.PostCreateRequest;
import com.light.springinit.domain.dto.PostQueryRequest;
import com.light.springinit.domain.dto.PostUpdateRequest;
import com.light.springinit.domain.entity.Post;
import com.baomidou.mybatisplus.extension.service.IService;
import com.light.springinit.domain.vo.PostVO;
import com.light.springinit.param.PostUpdateParam;

/**
 * @author Ycri
 * @description 针对表【post(帖子)】的数据库操作Service
 * @createDate 2024-07-19 17:43:33
 */
public interface PostService extends IService<Post> {
    /**
     * 创建帖子
     *
     * @param postCreateRequest 创建帖子请求
     * @return 帖子操作响应
     */
    PostOperateResponse createPost(PostCreateRequest postCreateRequest);

    /**
     * 获取某个用户的所有帖子列表（分页）
     *
     * @param postQueryRequest 查询帖子请求
     * @return 帖子列表
     */
    PageResponse<PostVO> getPagePostByUserId(PostQueryRequest postQueryRequest);

    /**
     * 根据id 获取帖子详情
     *
     * @param postQueryRequest 查询帖子请求
     * @return 帖子详情
     */
    PostQueryResponse<PostVO> getPostById(PostQueryRequest postQueryRequest);

    /**
     * 更新帖子
     *
     * @param postUpdateRequest 更新帖子请求
     * @return 更新结果
     */
    Boolean updatePost(PostUpdateRequest postUpdateRequest);

    /**
     * 删除帖子
     *
     * @param postId 删除帖子参数
     * @return 删除结果
     */
    Boolean deletePost(Long postId);
}
