package com.hjy.system.dao;

import com.hjy.system.entity.ReRolePerms;
import com.hjy.system.entity.ReUserRole;
import com.hjy.system.entity.TSysPerms;
import com.hjy.system.entity.TSysRole;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (TSysRole)表数据库访问层
 *
 * @author liuchun
 * @since 2020-07-23 12:59:10
 */
public interface TSysRoleMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param pkRoleId 主键
     * @return 实例对象
     */
    TSysRole selectById(String pkRoleId);

    /**
     * 新增数据
     *
     * @param tSysRole 实例对象
     * @return 影响行数
     */
    int insert(TSysRole tSysRole);
    int insertSelective(TSysRole tSysRole);

    /**
     * 修改数据
     *
     * @param tSysRole 实例对象
     * @return 影响行数
     */
    int updateById(TSysRole tSysRole);

    /**
     * 通过主键删除数据
     * @param pkRoleId 主键
     * @return 影响行数
     */
    int deleteById(String pkRoleId);

    /**
     * 查询所有行数据
     * @return 对象列表
     */
    List<TSysRole> selectAll();
     /**
     * 通过实体作为筛选条件查询
     * @param tSysRole 实例对象
     * @return 对象列表
     */
    List<TSysRole> selectAllByEntity(TSysRole tSysRole);
    /**
     * 通过roleId删除原有的所有菜单
     * @param fk_role_id
     */
    int deleteRolePermsByRoleId(@Param("fkRoleId") String fk_role_id);

    int addRoleMenu(@Param("pk_rolePerms_id")String pk_rolePerms_id,@Param("fk_role_id")String fk_role_id, @Param("fk_perms_id")String fk_perms_id);
    /**
     * 通过roleId查询为该角色的所有用户
     * @param fk_role_id
     */
    List<String> selectUserRoleByrole_id(@Param("fkRoleId")String fk_role_id);
    /**
     * 通过roleId删除用户角色
     */
    int deleteUserRoleByRoleId(@Param("fkRoleId")String fk_role_id);
    /**
     * 添加用户角色
     */
    int addUserRole(@Param("pk_userRole_id")String pk_userRole_id,@Param("fk_user_id") String fk_user_id,@Param("fk_role_id") String fk_role_id);
    /**
     * 批量添加用户角色
     */
    int addUserRoleByList(@Param("idList")List<ReUserRole> idList);
    /**
     * 批量添加角色权限
     */
    int addRoleMenuByList(@Param("idList")List<ReRolePerms> rolePermsList);
    /**
     * 添加用户角色
     * @param userRole 实体对象
     */
    int addUserRoleByUserRole(ReUserRole userRole);
    /**
     * 通过userid获取角色信息
     * @param pkUserId
     */
    TSysRole selectRoleByUserId(@Param("pkUserId")String pkUserId);
    /**
     * 通过roleid获取权限码信息
     * @param fkRoleId
     */
    List<String> selectPermsCodeByRole(@Param("fkRoleId")String fkRoleId);
    /**
     * 通过roleid获取权限信息
     * @param fkRoleId
     */
    List<TSysPerms> selectPermsByRole(@Param("fkRoleId")String fkRoleId);

    List<String> selectUserRole_userIded();

    String selectRoleIdByUserId(@Param("fkUserId")String fkUserId);
}