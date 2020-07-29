package com.hjy.common.utils.page;

import lombok.Data;

import java.util.List;

/**
 * 分页返回结果
 */
@Data
public class PageResult {
    /**
     * 当前页码
     */
    private int pageNum;
    /**
     * 每页数量
     */
    private int pageSize;
    /**
     * 记录总数
     */
    private long total;
    /**
     * 页码总数
     */
    private int pages;
    /**
     * 开始行码
     */
    private int startRow;
    /**
     * 结束行码
     */
    private int endRow;
    /**
     * 数据模型
     */
    private List<?> content;

}