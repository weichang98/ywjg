package com.hjy.system.service;

import com.hjy.system.entity.ReUserRole;
import com.hjy.system.entity.TSysRole;
import java.util.List;

/**
 * (TSysRole)表服务接口
 *
 * @author liuchun
 * @since 2020-07-23 12:59:10
 */
public interface TSysRoleService {

    /**
     * 通过ID查询单条数据
     *
     * @param pkRoleId 主键
     * @return 实例对象
     */
    TSysRole selectById(String pkRoleId);


    /**
     * 新增数据
     * @param tSysRole 实例对象
     * @return 实例对象
     */
    int insert(TSysRole tSysRole);

    /**
     * 修改数据
     *
     * @param tSysRole 实例对象
     * @return 实例对象
     */
    int updateById(TSysRole tSysRole);

    /**
     * 通过主键删除数据
     *
     * @param pkRoleId 主键
     * @return 是否成功
     */
    int deleteById(String pkRoleId);

    /**
     * 查询所有数据
     * @return list
     */
     List<TSysRole> selectAll();
     /**
     * 通过实体查询所有数据
     * @return list
     */
     List<TSysRole> selectAllByEntity(TSysRole tSysRole);
    /**
     * 通过role_id删除原有的perms
     */
    int deleteRolePermsByRoleId(String roleIdStr);
    /**
     * 添加角色权限
     */
    int addRoleMenu(String fk_role_id,String str1);
    /**
     * 批量添加角色权限
     */
    int addRoleMenuByList(String fk_role_id, List<String> idList);

    List<String> selectUserRoleByrole_id(String fk_role_id);
    /**
     * 通过roleId删除用户角色
     */
    int deleteUserRoleByRoleId(String fk_role_id);
    /**
     * 添加用户角色
     */
    int addUserRole(String fk_role_id, String userIds);
    /**
     * 批量添加用户角色
     */
    int addUserRoleByList(String fk_role_id, List<String> idList);
    /**
     * 添加用户角色
     */
    int addUserRoleByUserRole(ReUserRole userRole);

    List<String> selectUserRole_userIded();

    TSysRole selectRoleByUserId(String pkUserId);
}