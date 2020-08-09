package com.hjy.common.utils.page;

/**
 * @Author: lc
 * @Desc:
 * @Date: 2019/9/11 17:31
 **/
public class PageObjectUtils {

    public static Integer PAGE_MUN_MAX = 100;       // 翻页的最大数量
    public static Integer PAGE_NUM_MIN = 2;         // 翻页的最小数量
    public static Integer PAGE_START_INDEX = 1;     // 默认的起始页

    public static PageRequest getRequest(PageRequest pageRequest) {
        Integer pageIndex = pageRequest.getPageNum();
        Integer limit= pageRequest.getPageSize();
        pageIndex = (pageIndex == null || pageIndex < PAGE_START_INDEX) ? PAGE_START_INDEX : pageIndex;
        int startRow = (pageIndex - 1) * limit+1;
        pageRequest.setStartRow(startRow);
        pageRequest.setEndRow(pageIndex*limit);
        return pageRequest;
    }
}
