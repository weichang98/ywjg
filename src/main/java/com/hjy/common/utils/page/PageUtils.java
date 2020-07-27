package com.hjy.common.utils.page;
import com.github.pagehelper.PageInfo;

public class PageUtils {

    /**
     * 将分页信息封装到统一的接口
     * @param pageRequest
     * @return
     */
    public static PageResult getPageResult(PageRequest pageRequest, PageInfo<?> pageInfo) {
        PageResult pageResult = new PageResult();
        //当前页码
        pageResult.setPageNum(pageInfo.getPageNum());
        //每页数量
        pageResult.setPageSize(pageInfo.getPageSize());
        pageResult.setStartRow(pageInfo.getStartRow());
        pageResult.setEndRow(pageInfo.getEndRow());
        //记录总数
        pageResult.setTotal(pageInfo.getTotal());
        //页码总数
        pageResult.setPages(pageInfo.getPages());
        //数据模型
        pageResult.setContent(pageInfo.getList());
        return pageResult;
    }
}