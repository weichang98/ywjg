package com.hjy.hall.service;

import com.hjy.hall.entity.THallQueue;

import java.util.List;

/**
 * (THallQueue)表服务接口
 *
 * @author makejava
 * @since 2020-07-29 14:33:20
 */
public interface THallQueueService {

    /**
     * 通过ID查询单条数据
     *
     * @param pkQueueId 主键
     * @return 实例对象
     */
    THallQueue selectById(String pkQueueId);


    /**
     * 新增数据
     *
     * @param tHallQueue 实例对象
     * @return 实例对象
     */
    int insert(THallQueue tHallQueue);

    /**
     * 修改数据
     *
     * @param tHallQueue 实例对象
     * @return 实例对象
     */
    int updateById(THallQueue tHallQueue);

    /**
     * 通过主键删除数据
     *
     * @param pkQueueId 主键
     * @return 是否成功
     */
    int deleteById(String pkQueueId);

    /**
     * 查询所有数据
     *
     * @return list
     */
    List<THallQueue> selectAll();

    /**
     * 通过实体查询所有数据
     *
     * @return list
     */
    List<THallQueue> selectAllByEntity(THallQueue tHallQueue);

    THallQueue getByOrdinalAndDatestr(String Ordinal,String DateStr);

}