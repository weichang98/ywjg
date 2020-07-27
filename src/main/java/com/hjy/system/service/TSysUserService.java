package com.hjy.system.service;

import com.hjy.system.entity.TSysUser;
import java.util.List;

/**
 * (TSysUser)表服务接口
 *
 * @author liuchun
 * @since 2020-07-24 17:05:59
 */
public interface TSysUserService {

    /**
     * 通过ID查询单条数据
     *
     * @param pkUserId 主键
     * @return 实例对象
     */
    TSysUser selectById(Object pkUserId)throws Exception;


    /**
     * 新增数据
     * @param tSysUser 实例对象
     * @return 实例对象
     */
    int insert(TSysUser tSysUser) throws Exception;

    /**
     * 修改数据
     *
     * @param tSysUser 实例对象
     * @return 实例对象
     */
    int updateById(TSysUser tSysUser) throws Exception;

    /**
     * 通过主键删除数据
     *
     * @param pkUserId 主键
     * @return 是否成功
     */
    int deleteById(Object pkUserId) throws Exception;

    /**
     * 查询所有数据
     * @return list
     */
     List<TSysUser> selectAll() throws Exception;
     /**
     * 通过实体查询所有数据
     * @return list
     */
     List<TSysUser> selectAllByEntity(TSysUser tSysUser)throws Exception;
     
}