package com.hjy.common.utils.page;

import lombok.Data;

/**
 * 分页请求
 */
@Data
public class PageRequest {
    /**
     * 当前页码
     */
    private int pageNum;
    /**
     * 每页数量
     */
    private int pageSize;
    /**
     * 起始行页码
     */
    private int startRow;
    /**
     * 结束行页码
     */
    private int endtRow;
}