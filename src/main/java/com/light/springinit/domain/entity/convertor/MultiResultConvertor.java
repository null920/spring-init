package com.light.springinit.domain.entity.convertor;

import com.light.springinit.common.PageResponse;
import com.light.springinit.common.result.MultiResult;

import static com.light.springinit.common.response.ResponseCode.SUCCESS;

/**
 * @author null&&
 * @Date 2024/7/20 22:38
 */
public class MultiResultConvertor {
    public static <T> MultiResult<T> convert(PageResponse<T> pageResponse) {
        MultiResult<T> multiResult = new MultiResult<T>(true, SUCCESS.name(), SUCCESS.name(), pageResponse.getDatas(), pageResponse.getTotal(), pageResponse.getCurrentPage(), pageResponse.getPageSize());
        return multiResult;
    }
}
