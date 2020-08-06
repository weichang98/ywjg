package com.hjy.system.service;

import com.hjy.common.utils.page.PageRequest;
import com.hjy.common.utils.page.PageResult;
import com.hjy.system.entity.ActiveUser;
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
     * @return 实例对象
     */
    TSysUser selectById(String pkUserId)throws Exception;


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
    int deleteById(String pkUserId) throws Exception;

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
    /**
     * 通过userId查询已分配角色数据
     */
    String selectUserRoleByUserId(String fk_user_id);
    /**
     * 通过userId删除已分配角色数据
     */
    int deleteUserRoleByUserId(String fk_user_id);
    /**
     * 分页查询所有数据
     * @return list
     */
    PageResult selectAllPage(PageRequest pageRequest);
    /**
     * 修改密码
     */
    int updatePassword(String parm, ActiveUser activeUser) throws Exception;
}