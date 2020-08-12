package com.hjy.system.service;

import com.hjy.system.entity.TSysBusinesstype;
import java.util.List;

/**
 * (TSysBusinesstype)表服务接口
 *
 * @author liuchun
 * @since 2020-07-28 16:54:27
 */
public interface TSysBusinesstypeService {

    /**
     * 通过ID查询单条数据
     *
     * @param pkBusinesstypeId 主键
     * @return 实例对象
     */
    TSysBusinesstype selectById(Object pkBusinesstypeId)throws Exception;


    /**
     * 新增数据
     * @param tSysBusinesstype 实例对象
     * @return 实例对象
     */
    int insert(TSysBusinesstype tSysBusinesstype) throws Exception;

    /**
     * 修改数据
     *
     * @param tSysBusinesstype 实例对象
     * @return 实例对象
     */
    int updateById(TSysBusinesstype tSysBusinesstype) throws Exception;

    /**
     * 通过主键删除数据
     *
     * @param pkBusinesstypeId 主键
     * @return 是否成功
     */
    int deleteById(String pkBusinesstypeId) throws Exception;

    /**
     * 查询所有数据
     * @return list
     */
     List<TSysBusinesstype> selectAll() throws Exception;
     /**
     * 通过实体查询所有数据
     * @return list
     */
     List<TSysBusinesstype> selectAllByEntity(TSysBusinesstype tSysBusinesstype)throws Exception;

    List<String> selectBusinessName();
}