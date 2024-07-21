package com.light.springinit.common;

import com.light.springinit.constant.PageConstant;
import lombok.Getter;
import lombok.Setter;

/**
 * 分页请求
 *
 * @author null&&
 * @Date 2024/7/19 19:08
 */
@Setter
@Getter
public class PageRequest extends BaseRequest {
    private static final long serialVersionUID = 1L;

    /**
     * 当前页
     */
    private int currentPage = 1;
    /**
     * 每页结果数
     */
    private int pageSize = 10;

    /**
     * 排序字段
     */
    private String sortField;

    /**
     * 排序顺序（默认升序）
     */
    private String sortOrder = PageConstant.SORT_ORDER_ASC;
}
