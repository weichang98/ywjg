package com.hjy.hall.service;

import com.hjy.hall.entity.THallJidongche;

import java.util.List;

/**
 * (THallJidongche)表服务接口
 *
 * @author makejava
 * @since 2020-07-27 15:51:25
 */
public interface THallJidongcheService {

    /**
     * 通过ID查询单条数据
     *
     * @param pkJidongcheId 主键
     * @return 实例对象
     */
    THallJidongche selectById(String pkJidongcheId);


    /**
     * 新增数据
     *
     * @param tHallJidongche 实例对象
     * @return 实例对象
     */
    int insert(THallJidongche tHallJidongche);

    /**
     * 修改数据
     *
     * @param tHallJidongche 实例对象
     * @return 实例对象
     */
    int updateById(THallJidongche tHallJidongche);

    /**
     * 通过主键删除数据
     *
     * @param pkJidongcheId 主键
     * @return 是否成功
     */
    int deleteById(String pkJidongcheId);

    /**
     * 查询所有数据
     *
     * @return list
     */
    List<THallJidongche> selectAll();

    /**
     * 通过实体查询所有数据
     *
     * @return list
     */
    List<THallJidongche> selectAllByEntity(THallJidongche tHallJidongche);

}