package com.light.springinit.common;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 多数据响应
 *
 * @author null&&
 * @Date 2024/7/19 18:38
 */
@Setter
@Getter
public class MultiResponse<T> extends BaseResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<T> datas;

    public static <T> MultiResponse<T> of(List<T> datas) {
        MultiResponse<T> multiResponse = new MultiResponse<>();
        multiResponse.setSuccess(true);
        multiResponse.setDatas(datas);
        return multiResponse;
    }
}
