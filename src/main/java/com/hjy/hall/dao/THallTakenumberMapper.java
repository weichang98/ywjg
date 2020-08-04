package com.hjy.hall.dao;

import com.hjy.hall.entity.THallTakenumber;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (THallTakenumber)表数据库访问层
 *
 * @author makejava
 * @since 2020-07-29 10:28:25
 */
public interface THallTakenumberMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param pkTakenumId 主键
     * @return 实例对象
     */
    THallTakenumber selectById(String pkTakenumId);

    /**
     * 新增数据
     *
     * @param tHallTakenumber 实例对象
     * @return 影响行数
     */
    int insert(THallTakenumber tHallTakenumber);

    int insertSelective(THallTakenumber tHallTakenumber);

    /**
     * 修改数据
     *
     * @param tHallTakenumber 实例对象
     * @return 影响行数
     */
    int updateById(THallTakenumber tHallTakenumber);

    /**
     * 通过主键删除数据
     *
     * @param pkTakenumId 主键
     * @return 影响行数
     */
    int deleteById(String pkTakenumId);

    /**
     * 查询所有行数据
     *
     * @return 对象列表
     */
    List<THallTakenumber> selectAll();

    /**
     * 通过实体作为筛选条件查询
     *
     * @param tHallTakenumber 实例对象
     * @return 对象列表
     */
    List<THallTakenumber> selectAllByEntity(THallTakenumber tHallTakenumber);

    /**
     * 查询计数
     *
     *
     * @return 号码
     */
    int count( );

    /**
     * 查询所有行数据
     *
     * @return 对象列表
     */
    String queryNumList(@Param("ordinal") String type);

    THallTakenumber getByOrdinal(@Param("Ordinal") String Ordinal);

    void deleteAll();
}