package com.light.springinit.domain.entity.convertor;


import com.light.springinit.domain.entity.Post;
import com.light.springinit.domain.info.PostInfo;
import com.light.springinit.domain.vo.PostVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author null&&
 * @Date 2024/7/17 17:41
 */
@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface PostConvertor {
    PostConvertor INSTANCE = Mappers.getMapper(PostConvertor.class);

    /**
     * 转换为vo
     *
     * @param request
     * @return
     */
    @Mapping(target = "postId", source = "request.postId")
    public PostVO mapToVo(Post request);

    /**
     * 转换为实体
     *
     * @param request
     * @return
     */
    @Mapping(target = "postId", source = "request.postId")
    public Post mapToEntity(PostInfo request);

    /**
     * list转换为vo
     *
     * @param request
     * @return
     */
    public List<PostVO> mapToVo(List<Post> request);
}
