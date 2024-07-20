package com.light.springinit.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.light.springinit.common.PageResponse;
import com.light.springinit.common.result.MultiResult;
import com.light.springinit.common.result.Result;
import com.light.springinit.common.response.PostOperateResponse;
import com.light.springinit.domain.dto.PostCreateRequest;
import com.light.springinit.domain.dto.PostQueryRequest;
import com.light.springinit.domain.entity.convertor.MultiResultConvertor;
import com.light.springinit.domain.vo.PostVO;
import com.light.springinit.exception.errorcode.UserErrorCode;
import com.light.springinit.param.PostCreateParam;
import com.light.springinit.param.PostQueryParam;
import com.light.springinit.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping
    @SaCheckLogin
    public MultiResult<PostVO> getPostListByUserId(@Valid @RequestBody PostQueryParam postQueryParam) {
        Long userId = postQueryParam.getUserId();
        if (userId == null) {
            return MultiResult.errorMulti(UserErrorCode.USER_QUERY_PARAM_IS_NULL.getCode(), UserErrorCode.USER_QUERY_PARAM_IS_NULL.getMessage());
        }
        PostQueryRequest postQueryRequest = new PostQueryRequest();
        postQueryRequest.setUserId(userId);
        PageResponse<PostVO> pagePostByUserIdResult = postService.getPagePostByUserId(postQueryRequest);
        if (Boolean.TRUE.equals(pagePostByUserIdResult.getSuccess())) {
            return MultiResultConvertor.convert(pagePostByUserIdResult);
        }
        return MultiResult.errorMulti(pagePostByUserIdResult.getResponseCode(), pagePostByUserIdResult.getResponseMessage());
    }
}
