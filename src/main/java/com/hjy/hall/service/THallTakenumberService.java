package com.hjy.hall.service;

import com.hjy.hall.entity.THallQueue;
import com.hjy.hall.entity.THallTakenumber;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (THallTakenumber)表服务接口
 *
 * @author makejava
 * @since 2020-07-29 10:28:25
 */
public interface THallTakenumberService {

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
     * @return 实例对象
     */
    int insert(THallTakenumber tHallTakenumber);

    /**
     * 修改数据
     *
     * @param tHallTakenumber 实例对象
     * @return 实例对象
     */
    int updateById(THallTakenumber tHallTakenumber);

    /**
     * 通过主键删除数据
     *
     * @param pkTakenumId 主键
     * @return 是否成功
     */
    int deleteById(String pkTakenumId);

    /**
     * 查询所有数据
     *
     * @return list
     */
    List<THallTakenumber> selectAll();

    /**
     * 通过实体查询所有数据
     *
     * @return list
     */
    List<THallTakenumber> selectAllByEntity(THallTakenumber tHallTakenumber);


    int count();

    /**
     * 查询窗口需要办理业务列表
     *
     * @return list
     */
    String queryNumList(String type);

    THallTakenumber getByOrdinal(String Ordinal);

    String getOrdinal(THallQueue tHallQueue);
}