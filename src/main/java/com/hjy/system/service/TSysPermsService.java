package com.hjy.system.service;

import com.hjy.system.entity.TSysPerms;
import java.util.List;

/**
 * (TSysPerms)表服务接口
 *
 * @author liuchun
 * @since 2020-07-24 09:55:41
 */
public interface TSysPermsService {

    /**
     * 通过ID查询单条数据
     *
     * @param pkPermsId 主键
     * @return 实例对象
     */
    TSysPerms selectById(Object pkPermsId);


    /**
     * 新增数据
     * @param tSysPerms 实例对象
     * @return 实例对象
     */
    int insert(TSysPerms tSysPerms);

    /**
     * 修改数据
     *
     * @param tSysPerms 实例对象
     * @return 实例对象
     */
    int updateById(TSysPerms tSysPerms);

    /**
     * 通过主键删除数据
     *
     * @return 是否成功
     */
    int deleteById(String pk_perms_id);

    /**
     * 查询所有数据
     * @return list
     */
     List<TSysPerms> selectAll();
     /**
     * 通过实体查询所有数据
     * @return list
     */
     List<TSysPerms> selectAllByEntity(TSysPerms tSysPerms);

    List<String> selectDistributeByrole_id(String roleIdStr);
}