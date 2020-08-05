package com.hjy.list.dao;

import com.hjy.list.entity.TListAgent;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (TListAgent)表数据库访问层
 *
 * @author liuchun
 * @since 2020-08-05 12:45:38
 */
public interface TListAgentMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param pkAgentId 主键
     * @return 实例对象
     */
    TListAgent selectById(String pkAgentId);

    /**
     * 新增数据
     *
     * @param tListAgent 实例对象
     * @return 影响行数
     */
    int insert(TListAgent tListAgent);
    int insertSelective(TListAgent tListAgent);

    /**
     * 修改数据
     *
     * @param tListAgent 实例对象
     * @return 影响行数
     */
    int updateById(TListAgent tListAgent);

    /**
     * 通过主键删除数据
     *
     * @param pkAgentId 主键
     * @return 影响行数
     */
    int deleteById(String pkAgentId);

    /**
     * 查询所有行数据
     * @return 对象列表
     */
    List<TListAgent> selectAll();
     /**
     * 通过实体作为筛选条件查询
     *
     * @param tListAgent 实例对象
     * @return 对象列表
     */
    List<TListAgent> selectAllByEntity(TListAgent tListAgent);
}