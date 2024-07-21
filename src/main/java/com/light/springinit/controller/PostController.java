package com.light.springinit.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.light.springinit.common.PageRequest;
import com.light.springinit.common.PageResponse;
import com.light.springinit.common.response.PostOperateResponse;
import com.light.springinit.common.response.PostQueryResponse;
import com.light.springinit.common.result.MultiResult;
import com.light.springinit.common.result.Result;
import com.light.springinit.domain.dto.PostCreateRequest;
import com.light.springinit.domain.dto.PostQueryRequest;
import com.light.springinit.domain.dto.PostUpdateRequest;
import com.light.springinit.domain.entity.convertor.MultiResultConvertor;
import com.light.springinit.domain.vo.PostVO;
import com.light.springinit.exception.errorcode.PostErrorCode;
import com.light.springinit.param.PostCreateParam;
import com.light.springinit.param.PostQueryParam;
import com.light.springinit.param.PostUpdateParam;
import com.light.springinit.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * @author null&&
 * @Date 2024/7/20 22:26
 */
@RestController
@RequestMapping("post")
@RequiredArgsConstructor
@Slf4j
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/create")
    @SaCheckLogin
    public Result<Boolean> createPost(@Valid @RequestBody PostCreateParam postCreateParam) {
        PostCreateRequest postCreateRequest = new PostCreateRequest();
        postCreateRequest.setTitle(postCreateParam.getTitle());
        postCreateRequest.setContent(postCreateParam.getContent());
        postCreateRequest.setTags(postCreateParam.getTags());
        PostOperateResponse createPostResult = postService.createPost(postCreateRequest);
        if (createPostResult.getSuccess() != null) {
            return Result.success(true);
        }
        return Result.error(createPostResult.getResponseCode(), createPostResult.getResponseMessage());
    }

    /**
     * 获取当前用户帖子列表（分页）
     *
     * @param pageRequest 分页参数
     * @return 帖子列表
     */
    @PostMapping("/list/my")
    @SaCheckLogin
    public MultiResult<PostVO> getMyPagePost(@RequestBody PageRequest pageRequest) {
        if (pageRequest.getPageSize() == 0) {
            pageRequest.setPageSize(10);
        }
        PageResponse<PostVO> myPagePostResult = postService.getMyPagePost(pageRequest);
        if (myPagePostResult.getSuccess() != null) {
            return MultiResultConvertor.convert(myPagePostResult);
        }
        return MultiResult.errorMulti(myPagePostResult.getResponseCode(), myPagePostResult.getResponseMessage());
    }

    /**
     * 获取帖子列表（分页，默认获取第1 ，1 页10 条）
     *
     * @param postQueryParam 帖子查询参数
     * @return 帖子列表
     */
    @PostMapping("/list/by_user_id")
    @SaCheckLogin
    public MultiResult<PostVO> getPagePostByUserId(@Valid @RequestBody PostQueryParam postQueryParam) {
        if (postQueryParam.getPageSize() == 0) {
            postQueryParam.setPageSize(10);
        }
        Long userId = Long.valueOf(postQueryParam.getUserId());
        PostQueryRequest postQueryRequest = new PostQueryRequest();
        postQueryRequest.setUserId(userId);
        PageResponse<PostVO> pagePostByUserIdResult = postService.getPagePostByUserId(postQueryRequest);
        if (pagePostByUserIdResult.getSuccess() != null) {
            return MultiResultConvertor.convert(pagePostByUserIdResult);
        }
        return MultiResult.errorMulti(pagePostByUserIdResult.getResponseCode(), pagePostByUserIdResult.getResponseMessage());
    }

    @GetMapping("/{postId}")
    public Result<PostVO> getPostById(@PathVariable("postId") @NotBlank(message = "帖子id不能为空") String postId) {
        if (postId == null) {
            return Result.error(PostErrorCode.POST_QUERY_PARAM_IS_NULL.getCode(), PostErrorCode.POST_QUERY_PARAM_IS_NULL.getMessage());
        }
        PostQueryRequest postQueryRequest = new PostQueryRequest();
        postQueryRequest.setPostId(Long.valueOf(postId));
        PostQueryResponse<PostVO> postByIdResult = postService.getPostById(postQueryRequest);
        if (postByIdResult.getSuccess() != null) {
            return Result.success(postByIdResult.getData());
        }
        return Result.error(postByIdResult.getResponseCode(), postByIdResult.getResponseMessage());
    }

    @PostMapping("/update")
    @SaCheckLogin
    public Result<Boolean> updatePost(@Valid @RequestBody PostUpdateParam postUpdateParam) {
        PostUpdateRequest postUpdateRequest = new PostUpdateRequest();
        postUpdateRequest.setPostId(Long.valueOf(postUpdateParam.getPostId()));
        postUpdateRequest.setTitle(postUpdateParam.getTitle());
        postUpdateRequest.setContent(postUpdateParam.getContent());
        postUpdateRequest.setTags(postUpdateParam.getTags());
        Boolean updateResult = postService.updatePost(postUpdateRequest);
        if (Boolean.TRUE.equals(updateResult)) {
            return Result.success(true);
        }
        return Result.error(PostErrorCode.POST_OPERATE_FAILED.getCode(), PostErrorCode.POST_OPERATE_FAILED.getMessage());
    }

    @DeleteMapping("/delete")
    @SaCheckLogin
    public Result<Boolean> deletePost(@Valid @RequestParam(value = "postId") @NotBlank(message = "帖子id不能为空") String postId) {
        Boolean deleteResult = postService.deletePost(Long.valueOf(postId));
        if (Boolean.TRUE.equals(deleteResult)) {
            return Result.success(true);
        }
        return Result.error(PostErrorCode.POST_OPERATE_FAILED.getCode(), PostErrorCode.POST_OPERATE_FAILED.getMessage());
    }
}
