package com.light.springinit.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.light.springinit.common.PageRequest;
import com.light.springinit.common.PageResponse;
import com.light.springinit.common.response.PostOperateResponse;
import com.light.springinit.common.response.PostQueryResponse;
import com.light.springinit.constant.PageConstant;
import com.light.springinit.constant.UserRole;
import com.light.springinit.domain.dto.PostCreateRequest;
import com.light.springinit.domain.dto.PostQueryRequest;
import com.light.springinit.domain.dto.PostUpdateRequest;
import com.light.springinit.domain.entity.Post;
import com.light.springinit.domain.entity.User;
import com.light.springinit.domain.entity.convertor.PostConvertor;
import com.light.springinit.domain.entity.convertor.UserConvertor;
import com.light.springinit.domain.info.PostInfo;
import com.light.springinit.domain.vo.PostVO;
import com.light.springinit.exception.PostException;
import com.light.springinit.exception.UserException;
import com.light.springinit.exception.errorcode.PostErrorCode;
import com.light.springinit.exception.errorcode.UserErrorCode;
import com.light.springinit.mapper.PostMapper;
import com.light.springinit.mapper.UserMapper;
import com.light.springinit.service.PostService;
import com.light.springinit.service.UserService;
import com.light.springinit.utils.SqlUtils;
import org.checkerframework.checker.units.qual.N;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ycri
 * @description 针对表【post(帖子)】的数据库操作Service实现
 * @createDate 2024-07-19 17:43:33
 */
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post>
        implements PostService {

    @Autowired
    private UserService userService;

    @Autowired
    private PostMapper postMapper;

    /**
     * 创建帖子
     *
     * @param postCreateRequest 创建帖子请求
     * @return 帖子操作响应
     */
    @Override
    public PostOperateResponse createPost(PostCreateRequest postCreateRequest) {
        Post post = new Post();
        post.add(postCreateRequest);
        post.setUserId(Long.valueOf((String) StpUtil.getLoginId()));
        PostOperateResponse createPostResult = new PostOperateResponse();
        if (save(post)) {
            Post findPost = postMapper.findPostById(post.getPostId());
            if (findPost == null) {
                throw new PostException(PostErrorCode.POST_OPERATE_FAILED);
            }
            createPostResult.setSuccess(true);
        }
        return createPostResult;
    }

    /**
     * 获取当前用户帖子列表（分页）
     *
     * @param pageRequest 分页参数
     * @return 帖子列表
     */
    @Override
    public PageResponse<PostVO> getMyPagePost(PageRequest pageRequest) {
        int currentPage = pageRequest.getCurrentPage();
        int pageSize = pageRequest.getPageSize();
        String sortField = pageRequest.getSortField();
        String sortOrder = pageRequest.getSortOrder();
        Long loginId = Long.valueOf((String) StpUtil.getLoginId());

        userService.checkUserExistAndStatus(loginId);
        // 构造查询条件
        QueryWrapper<Post> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", loginId);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(PageConstant.SORT_ORDER_ASC), sortField);

        Page<Post> postPage = postMapper.selectPage(new Page<>(currentPage, pageSize), queryWrapper);
        // 如果用户没有帖子
        if (postPage.getTotal() == 0) {
            return PageResponse.of(null, 0, pageSize, currentPage);
        }
        // 关联帖子创建者的信息
        List<PostVO> pagePostResult = postPage.getRecords()
                .stream()
                .map(post -> {
                    PostVO postVO = PostConvertor.INSTANCE.mapToVo(post);
                    User postUser = userService.findUserById(post.getUserId());
                    postVO.setUserVO(UserConvertor.INSTANCE.mapToVo(postUser));
                    return postVO;
                }).collect(Collectors.toList());
        return PageResponse.of(pagePostResult, (int) postPage.getTotal(), pageSize, currentPage);
    }

    /**
     * 获取某个用户的所有帖子列表（分页）
     *
     * @param postQueryRequest 查询帖子请求
     * @return 帖子列表
     */
    @Override
    public PageResponse<PostVO> getPagePostByUserId(PostQueryRequest postQueryRequest) {
        int currentPage = postQueryRequest.getCurrentPage();
        int pageSize = postQueryRequest.getPageSize();
        String sortField = postQueryRequest.getSortField();
        String sortOrder = postQueryRequest.getSortOrder();
        Long userId = postQueryRequest.getUserId();
        // 校验参数
        if (null == userId) {
            throw new PostException(PostErrorCode.POST_QUERY_PARAM_ERROR);
        }
        userService.checkUserExistAndStatus(userId);
        // 构造查询条件
        QueryWrapper<Post> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(PageConstant.SORT_ORDER_ASC), sortField);

        Page<Post> postPage = postMapper.selectPage(new Page<>(currentPage, pageSize), queryWrapper);
        // 如果用户没有帖子
        if (postPage.getTotal() == 0) {
            return PageResponse.of(null, 0, pageSize, currentPage);
        }
        // 关联帖子创建者的信息
        List<PostVO> pagePostResult = postPage.getRecords()
                .stream()
                .map(post -> {
                    PostVO postVO = PostConvertor.INSTANCE.mapToVo(post);
                    User postUser = userService.findUserById(post.getUserId());
                    postVO.setUserVO(UserConvertor.INSTANCE.mapToVo(postUser));
                    return postVO;
                }).collect(Collectors.toList());
        return PageResponse.of(pagePostResult, (int) postPage.getTotal(), pageSize, currentPage);
    }


    /**
     * 根据id 获取帖子详情
     *
     * @param postQueryRequest 查询帖子请求
     * @return 帖子详情
     */
    @Override
    public PostQueryResponse<PostVO> getPostById(PostQueryRequest postQueryRequest) {
        Post post;
        Long postId = postQueryRequest.getPostId();
        // 校验参数
        if (null == postId) {
            throw new PostException(PostErrorCode.POST_QUERY_PARAM_ERROR);
        }
        post = postMapper.findPostById(postId);
        PostQueryResponse<PostVO> response = new PostQueryResponse<>();
        // 校验帖子是否存在
        if (post == null) {
            throw new PostException(PostErrorCode.POST_NOT_EXIST);
        }
        response.setSuccess(true);
        PostVO postVO = PostConvertor.INSTANCE.mapToVo(post);
        User postUser = userService.findUserById(post.getUserId());
        postVO.setUserVO(UserConvertor.INSTANCE.mapToVo(postUser));
        response.setData(postVO);
        return response;
    }

    /**
     * 更新帖子
     *
     * @param postUpdateRequest 更新帖子请求
     * @return 更新结果
     */
    @Override
    public Boolean updatePost(PostUpdateRequest postUpdateRequest) {
        Long postId = postUpdateRequest.getPostId();
        Long loginId = Long.valueOf((String) StpUtil.getLoginId());
        Post postById = postMapper.findPostById(postId);
        User userById = userService.findUserById(loginId);
        // 校验帖子是否存在
        if (postById == null) {
            throw new PostException(PostErrorCode.POST_NOT_EXIST);
        }
        // 校验用户状态
        if (userById.getUserRole() == UserRole.BAN) {
            throw new UserException(UserErrorCode.USER_STATUS_IS_BAN);
        }
        // 校验权限
        if (!postById.getUserId().equals(loginId) && !userById.getUserRole().equals(UserRole.ADMIN)) {
            throw new PostException(PostErrorCode.POST_OPERATE_NO_AUTH);
        }
        Post post = new Post();
        post.update(postUpdateRequest);
        if (postMapper.updateById(post) == 0) {
            throw new PostException(PostErrorCode.POST_OPERATE_FAILED);
        }
        return true;
    }

    /**
     * 删除帖子
     *
     * @param postId 删除帖子参数
     * @return 删除结果
     */
    @Override
    public Boolean deletePost(Long postId) {
        Long loginId = Long.valueOf((String) StpUtil.getLoginId());
        Post postById = postMapper.findPostById(postId);
        User userById = userService.findUserById(loginId);
        // 校验帖子是否存在
        if (postById == null) {
            throw new PostException(PostErrorCode.POST_NOT_EXIST);
        }
        // 校验用户状态
        if (userById.getUserRole() == UserRole.BAN) {
            throw new UserException(UserErrorCode.USER_STATUS_IS_BAN);
        }
        // 校验权限
        if (!postById.getUserId().equals(loginId) && !userById.getUserRole().equals(UserRole.ADMIN)) {
            throw new PostException(PostErrorCode.POST_OPERATE_NO_AUTH);
        }
        if (postMapper.deleteById(postId) == 0) {
            throw new PostException(PostErrorCode.POST_OPERATE_FAILED);
        }
        return true;
    }
}




