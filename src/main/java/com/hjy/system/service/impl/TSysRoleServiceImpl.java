package com.hjy.system.service.impl;

import com.hjy.common.utils.IDUtils;
import com.hjy.system.entity.ReRolePerms;
import com.hjy.system.entity.ReUserRole;
import com.hjy.system.entity.TSysRole;
import com.hjy.system.dao.TSysRoleMapper;
import com.hjy.system.service.TSysRoleService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * (TSysRole)表服务实现类
 *
 * @author liuchun
 * @since 2020-07-23 12:59:10
 */
@Service
public class TSysRoleServiceImpl implements TSysRoleService {
    @Autowired
    private TSysRoleMapper tSysRoleMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param pkRoleId 主键
     * @return 实例对象
     */
    @Override
    public TSysRole selectById(String pkRoleId) {
        return this.tSysRoleMapper.selectById(pkRoleId);
    }

    /**
     * 新增数据
     *
     * @param tSysRole 实例对象
     * @return 实例对象
     */
    @Override
    public void insert(TSysRole tSysRole) {
        //通过工具类IDUtils获取主键
        String uuid= IDUtils.currentTimeMillis();
        //首页菜单id
        String indexId = "1596706636946";
        tSysRole.setPkRoleId(uuid);
        //设置创建时间和更改时间
        tSysRole.setCreateDate(new Date());
        tSysRole.setModifyTime(new Date());
        tSysRoleMapper.insertSelective(tSysRole);
        //为新角色默认添加主页权限
        ReRolePerms rolePerms = new ReRolePerms();
        rolePerms.setPk_rolePerms_id(IDUtils.currentTimeMillis());
        rolePerms.setFk_role_id(uuid);
        rolePerms.setFk_perms_id(indexId);
        tSysRoleMapper.insertRolePerms(rolePerms);

    }

    /**
     * 修改数据
     *
     * @param tSysRole 实例对象
     * @return 实例对象
     */
    @Override
    public int updateById(TSysRole tSysRole) {
        tSysRole.setModifyTime(new Date());
        return tSysRoleMapper.updateById(tSysRole);
    }

    /**
     * 通过主键删除数据
     *
     * @param pkRoleId 主键
     * @return 是否成功
     */
    @Override
    public int deleteById(String pkRoleId) {
        return tSysRoleMapper.deleteById(pkRoleId);
    }
    
    /**
     * 查询多条数据
     * @return 对象列表
     */
    @Override
    public List<TSysRole> selectAll() {
        return this.tSysRoleMapper.selectAll();
    }
    /**
     * 通过实体查询多条数据
     * @return 对象列表
     */
    @Override
    public List<TSysRole> selectAllByEntity(TSysRole tSysRole) {
        return this.tSysRoleMapper.selectAllByEntity(tSysRole);
    }

    @Override
    public int deleteRolePermsByRoleId(String fk_role_id) {
        return tSysRoleMapper.deleteRolePermsByRoleId(fk_role_id);
    }

    @Override
    public int addRoleMenu(String fk_role_id,String str1) {
        //此处字符串比正常数组多出一对[],要先去除；
        String str2 = str1.substring(1);
        String idsStr = str2.substring(0,str2.length()-1);
        //此时得到的数组还有双引号
        String[] idsStrArray = idsStr.split(",");
        String[] ids = new String[idsStrArray.length];
        for(int i=0; i < idsStrArray.length; i++)
        {
            ids[i] = String.valueOf(idsStrArray[i]);
        }
        int i=0;
        for (String str : ids) {
            String fk_perms_id= str.replace("\"", "");
            String pk_rolePerms_id = IDUtils.currentTimeMillis();
            i = tSysRoleMapper.addRoleMenu(pk_rolePerms_id,fk_role_id,fk_perms_id);
        }
        return i;
    }

    @Override
    public int addRoleMenuByList(String fk_role_id, List<String> idList) {
        List<ReRolePerms> rolePermsList = new ArrayList<>();
        for (String s:idList){
            ReRolePerms rolePerms = new ReRolePerms();
            rolePerms.setPk_rolePerms_id(IDUtils.currentTimeMillis());
            rolePerms.setFk_role_id(fk_role_id);
            rolePerms.setFk_perms_id(s);
            rolePermsList.add(rolePerms);
        }
        return tSysRoleMapper.addRoleMenuByList(rolePermsList);
    }

    @Override
    public List<String> selectUserRoleByrole_id(String fk_role_id) {
        return tSysRoleMapper.selectUserRoleByrole_id(fk_role_id);
    }

    @Override
    public int deleteUserRoleByRoleId(String fk_role_id) {
        return tSysRoleMapper.deleteUserRoleByRoleId(fk_role_id);
    }

    @Override
    public int addUserRole(String fk_role_id, String userIds) {
        //此处字符串比正常数组多出一对[],要先去除；
        String str2 = userIds.substring(1);
        String idsStr = str2.substring(0,str2.length()-1);
        //此时得到的数组还有双引号
        String[] idsStrArray = idsStr.split(",");
        String[] ids = new String[idsStrArray.length];
        for(int i=0; i < idsStrArray.length; i++)
        {
            ids[i] = String.valueOf(idsStrArray[i]);
        }
        int i=0;
        //普通逐条插入
        for (String str : ids) {
            String fk_user_id= str.replace("\"", "");
            String pk_userRole_id = IDUtils.currentTimeMillis();
            i = tSysRoleMapper.addUserRole(pk_userRole_id,fk_user_id,fk_role_id);
        }
        return i;
    }

    @Override
    public int addUserRoleByList(String fk_role_id, List<String> idList) {
        System.err.println("idList"+idList);
        List<ReUserRole> userRoles = new ArrayList<>();
        for (String s:idList){
            ReUserRole userRole = new ReUserRole();
            userRole.setPk_userRole_id(IDUtils.currentTimeMillis());
            userRole.setFk_user_id(s);
            userRole.setFk_role_id(fk_role_id);
            userRoles.add(userRole);
        }
       return tSysRoleMapper.addUserRoleByList(userRoles);
    }

    @Override
    public int addUserRoleByUserRole(ReUserRole userRole) {
        return tSysRoleMapper.addUserRoleByUserRole(userRole);
    }

    @Override
    public List<String> selectUserRole_userIded() {
        return tSysRoleMapper.selectUserRole_userIded();
    }

    @Override
    public TSysRole selectRoleByUserId(String pkUserId) {
        return tSysRoleMapper.selectRoleByUserId(pkUserId);
    }
}