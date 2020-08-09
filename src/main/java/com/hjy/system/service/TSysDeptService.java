package com.hjy.system.service;

import com.hjy.system.entity.TSysDept;
import java.util.List;

/**
 * (TSysDept)表服务接口
 *
 * @author liuchun
 * @since 2020-07-27 16:15:29
 */
public interface TSysDeptService {

    /**
     * 通过ID查询单条数据
     *
     * @param pkDeptId 主键
     * @return 实例对象
     */
    TSysDept selectById(Object pkDeptId)throws Exception;


    /**
     * 新增数据
     * @param tSysDept 实例对象
     * @return 实例对象
     */
    int insert(TSysDept tSysDept) throws Exception;

    /**
     * 修改数据
     *
     * @param tSysDept 实例对象
     * @return 实例对象
     */
    int updateById(TSysDept tSysDept) throws Exception;

    /**
     * 通过主键删除数据
     *
     * @param pkDeptId 主键
     * @return 是否成功
     */
    int deleteById(Object pkDeptId) throws Exception;

    /**
     * 查询所有数据
     * @return list
     */
     List<TSysDept> selectAll() throws Exception;
     /**
     * 通过实体查询所有数据
     * @return list
     */
     List<TSysDept> selectAllByEntity(TSysDept tSysDept)throws Exception;

    List<String> selectDeptUnit();
}