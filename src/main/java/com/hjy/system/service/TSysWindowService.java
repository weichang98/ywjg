package com.hjy.system.service;

import com.hjy.system.entity.TSysBusinesstype;
import com.hjy.system.entity.TSysWindow;
import java.util.List;

/**
 * (TSysWindow)表服务接口
 *
 * @author liuchun
 * @since 2020-07-28 14:56:45
 */
public interface TSysWindowService {

    /**
     * 通过ID查询单条数据
     *
     * @param pkWindowId 主键
     * @return 实例对象
     */
    TSysWindow selectById(Object pkWindowId)throws Exception;


    /**
     * 新增数据
     * @param parm
     * @return 实例对象
     */
    int insert(String parm) throws Exception;

    /**
     * 修改数据
     *
     * @param tSysWindow 实例对象
     * @return 实例对象
     */
    int updateById(TSysWindow tSysWindow) throws Exception;

    /**
     * 通过主键删除数据
     *
     * @param pkWindowId 主键
     * @return 是否成功
     */
    int deleteById(Object pkWindowId) throws Exception;

    /**
     * 查询所有数据
     * @return list
     */
     List<TSysWindow> selectAll() throws Exception;
     /**
     * 通过实体查询所有数据
     * @return list
     */
     List<TSysWindow> selectAllByEntity(TSysWindow tSysWindow)throws Exception;
     
}