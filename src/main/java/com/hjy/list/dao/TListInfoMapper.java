package com.hjy.list.dao;

import com.hjy.common.utils.page.PageRequest;
import com.hjy.list.entity.TListInfo;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (TListInfo)表数据库访问层
 *
 * @author liuchun
 * @since 2020-08-05 12:45:38
 */
public interface TListInfoMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param pkListId 主键
     * @return 实例对象
     */
    TListInfo selectById(String pkListId);

    /**
     * 新增数据
     *
     * @param tListInfo 实例对象
     * @return 影响行数
     */
    int insertSelective(TListInfo tListInfo);

    /**
     * 修改数据
     *
     * @param tListInfo 实例对象
     * @return 影响行数
     */
    int updateById(TListInfo tListInfo);

    /**
     * 通过主键删除数据
     *
     * @param pkListId 主键
     * @return 影响行数
     */
    int deleteById(String pkListId);

    /**
     * 查询所有行数据
     * @return 对象列表
     */
    List<TListInfo> selectAll();
    /**
     * 查询所有待审批数据
     * @return TListInfo 对象列表
     */
    List<TListInfo> selectWaitApproval(Integer startRow, Integer endRow);

    TListInfo selectByIdCard(@Param("IdCard") String IdCard);

    int selectSize(TListInfo listInfo);

    List<TListInfo> selectAllPage(Integer startRow, Integer endRow,@Param("listType")String listType,@Param("fullName")String fullName,@Param("IdCard")String IdCard);

    int selectWaitApprovalSize();
}