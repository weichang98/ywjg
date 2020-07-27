package com.hjy.hall.service;

import com.hjy.hall.entity.THallJiashizheng;

import java.util.List;

/**
 * (THallJiashizheng)表服务接口
 *
 * @author makejava
 * @since 2020-07-27 14:17:48
 */
public interface THallJiashizhengService {

    /**
     * 通过ID查询单条数据
     *
     * @param pkJiashiId 主键
     * @return 实例对象
     */
    THallJiashizheng selectById(String pkJiashiId);


    /**
     * 新增数据
     *
     * @param tHallJiashizheng 实例对象
     * @return 实例对象
     */
    int insert(THallJiashizheng tHallJiashizheng);

    /**
     * 修改数据
     *
     * @param tHallJiashizheng 实例对象
     * @return 实例对象
     */
    int updateById(THallJiashizheng tHallJiashizheng);

    /**
     * 通过主键删除数据
     *
     * @param pkJiashiId 主键
     * @return 是否成功
     */
    int deleteById(String pkJiashiId);

    /**
     * 查询所有数据
     *
     * @return list
     */
    List<THallJiashizheng> selectAll();

    /**
     * 通过实体查询所有数据
     *
     * @return list
     */
    List<THallJiashizheng> selectAllByEntity(THallJiashizheng tHallJiashizheng);

}