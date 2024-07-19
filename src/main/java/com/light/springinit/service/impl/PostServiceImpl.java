package com.light.springinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.light.springinit.domain.entity.Post;
import com.light.springinit.service.PostService;
import com.light.springinit.mapper.PostMapper;
import org.springframework.stereotype.Service;

/**
* @author Ycri
* @description 针对表【post(帖子)】的数据库操作Service实现
* @createDate 2024-07-19 17:43:33
*/
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post>
    implements PostService {

}




