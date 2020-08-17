package com.hjy.synthetical.dao;

import com.hjy.synthetical.entity.THallMakecard;

import java.util.List;

/**
 * (THallMakecard)表数据库访问层
 *
 * @author makejava
 * @since 2020-08-17 09:53:44
 */
public interface THallMakecardMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param pkCardId 主键
     * @return 实例对象
     */
    THallMakecard selectById(String pkCardId);

    /**
     * 新增数据
     *
     * @param tHallMakecard 实例对象
     * @return 影响行数
     */
    int insert(THallMakecard tHallMakecard);

    int insertSelective(THallMakecard tHallMakecard);

    /**
     * 修改数据
     *
     * @param tHallMakecard 实例对象
     * @return 影响行数
     */
    int updateById(THallMakecard tHallMakecard);

    /**
     * 通过主键删除数据
     *
     * @param pkCardId 主键
     * @return 影响行数
     */
    int deleteById(String pkCardId);

    /**
     * 查询所有行数据
     *
     * @return 对象列表
     */
    List<THallMakecard> selectAll();

    /**
     * 通过实体作为筛选条件查询
     *
     * @param tHallMakecard 实例对象
     * @return 对象列表
     */
    List<THallMakecard> selectAllByEntity(THallMakecard tHallMakecard);
}