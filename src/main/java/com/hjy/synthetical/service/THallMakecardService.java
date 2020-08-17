package com.hjy.synthetical.service;

import com.hjy.synthetical.entity.THallMakecard;

import java.util.List;

/**
 * (THallMakecard)表服务接口
 *
 * @author makejava
 * @since 2020-08-17 09:53:44
 */
public interface THallMakecardService {

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
     * @return 实例对象
     */
    int insert(THallMakecard tHallMakecard);

    /**
     * 修改数据
     *
     * @param tHallMakecard 实例对象
     * @return 实例对象
     */
    int updateById(THallMakecard tHallMakecard);

    /**
     * 通过主键删除数据
     *
     * @param pkCardId 主键
     * @return 是否成功
     */
    int deleteById(String pkCardId);

    /**
     * 查询所有数据
     *
     * @return list
     */
    List<THallMakecard> selectAll();

    /**
     * 通过实体查询所有数据
     *
     * @return list
     */
    List<THallMakecard> selectAllByEntity(THallMakecard tHallMakecard);

}