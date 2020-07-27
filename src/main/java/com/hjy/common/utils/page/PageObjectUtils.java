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

    /**
     * @author: lc
     * @desc: 传入起始的页和每一页限制的数量获取数据开始的位置
     * @date: 2018/9/19 17:35
     * @params: [pageIndex, limit]
     * @return: java.lang.Integer
     */
    public static Integer calcStartIndex(Integer pageIndex, Integer limit) {
        pageIndex = (pageIndex == null || pageIndex < PAGE_START_INDEX) ? PAGE_START_INDEX : pageIndex;
        limit = calcPageLimit(limit);
        return (pageIndex - 1) * limit;
    }

    public static Integer calcPageLimit(Integer limit) {
        return (limit == null || limit < PAGE_NUM_MIN || limit > PAGE_MUN_MAX) ? PAGE_MUN_MAX : limit;
    }
}
