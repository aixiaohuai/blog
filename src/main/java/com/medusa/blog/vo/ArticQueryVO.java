package com.medusa.blog.vo;

import lombok.Data;


@Data
public class ArticQueryVO {
    /**
     * 每页展示大小
     */
    private long pageSize;
    /**
     * 当前页码
     */
    private long currentPage;
    /**
     * 专栏ID
     */
    private Long columnId;

}
