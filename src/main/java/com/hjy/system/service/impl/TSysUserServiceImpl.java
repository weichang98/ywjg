package com.hjy.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hjy.common.utils.IDUtils;
import com.hjy.common.utils.PasswordEncryptUtils;
import com.hjy.common.utils.page.PageObjectUtils;
import com.hjy.common.utils.page.PageRequest;
import com.hjy.common.utils.page.PageResult;
import com.hjy.common.utils.page.PageUtil;
import com.hjy.list.entity.TListInfo;
import com.hjy.system.dao.TSysRoleMapper;
import com.hjy.system.entity.ActiveUser;
import com.hjy.system.entity.ReUserRole;
import com.hjy.system.entity.TSysUser;
import com.hjy.system.dao.TSysUserMapper;
import com.hjy.system.service.TSysUserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (TSysUser)表服务实现类
 *
 * @author liuchun
 * @since 2020-07-24 17:05:59
 */
@Service
public class TSysUserServiceImpl implements TSysUserService {
    @Autowired
    private TSysUserMapper tSysUserMapper;
    @Autowired
    private TSysRoleMapper tSysRoleMapper;
    /**
     * 通过ID查询单条数据
     * @return 实例对象
     */
    @Override
    public TSysUser selectById(String pkUserId) throws Exception{
        return this.tSysUserMapper.selectById(pkUserId);
    }

    /**
     * 新增数据
     *
     * @param tSysUser 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(TSysUser tSysUser) throws Exception{
        tSysUser.setPkUserId(IDUtils.currentTimeMillis());
        //加密
        //默认密码
        String password = "123456";
        String passwordMd5 = PasswordEncryptUtils.MyPasswordEncryptUtil(tSysUser.getUsername(),password);
        tSysUser.setPassword(passwordMd5);
        tSysUser.setCreateTime(new Date());
        tSysUser.setModifyTime(new Date());
        return tSysUserMapper.insertSelective(tSysUser);
    }

    /**
     * 修改数据
     *
     * @param tSysUser 实例对象
     * @return 实例对象
     */
    @Override
    public int updateById(TSysUser tSysUser) throws Exception{
        return tSysUserMapper.updateById(tSysUser);
    }

    /**
     * 通过主键删除数据
     *
     * @param pkUserId 主键
     * @return 是否成功
     */
    @Override
    public int deleteById(String pkUserId) throws Exception{
        return tSysUserMapper.deleteById(pkUserId);
    }

    /**
     * 查询多条数据
     * @return 对象列表
     */
    @Override
    public List<TSysUser> selectAll() throws Exception{
        return this.tSysUserMapper.selectAll();
    }
    /**
     * 通过实体查询多条数据
     * @return 对象列表
     */
    @Override
    public List<TSysUser> selectAllByEntity(TSysUser tSysUser) throws Exception{
        return this.tSysUserMapper.selectAllByEntity(tSysUser);
    }

    @Override
    public String selectUserRoleByUserId(String fk_user_id) {
        return tSysUserMapper.selectUserRoleByUserId(fk_user_id);
    }

    @Override
    public int deleteUserRoleByUserId(String fk_user_id) {
        return tSysUserMapper.deleteUserRoleByUserId(fk_user_id);
    }

    @Override
    public PageResult selectAllPage(String param) {
        JSONObject json = JSON.parseObject(param);
        //实体数据
        String unit = String.valueOf(json.get("unit"));
        String fullName = String.valueOf(json.get("fullName"));
        String IDcard = String.valueOf(json.get("IDcard"));
        String policeNum = String.valueOf(json.get("policeNum"));
        TSysUser user = new TSysUser();
        user.setUnit(unit);
        user.setFullName(fullName);
        user.setIDcard(IDcard);
        user.setPoliceNum(policeNum);
        //分页记录条数
        int total = tSysUserMapper.selectSize(user);
        PageResult result = PageUtil.getPageResult(param,total);
        List<TSysUser> users = tSysUserMapper.selectAllPage(result.getStartRow(),result.getEndRow(),unit,fullName,IDcard,policeNum);
        result.setContent(users);
        return result;
    }

    @Override
    public int updatePassword(String parm, ActiveUser activeUser) throws Exception{
        //用户名
        String username = activeUser.getUsername();
        //数据库旧密码
        String oldPasswordMd5 = activeUser.getPassword();
        JSONObject json = JSON.parseObject(parm);
        //输入的旧密码
        String inputOldPassword = String.valueOf(json.get("oldPassword"));
        //输入的旧密码加密
        String inputOldPasswordMd5 = PasswordEncryptUtils.MyPasswordEncryptUtil(username,inputOldPassword);
        if(!inputOldPasswordMd5.equals(oldPasswordMd5)){
            return 2;
        }
        //输入的新密码
        String inputNewPassword = String.valueOf(json.get("newPassword"));
        //输入的新密码加密
        String inputNewPasswordMd5 = PasswordEncryptUtils.MyPasswordEncryptUtil(username,inputNewPassword);
        TSysUser user = new TSysUser();
        user.setPkUserId(activeUser.getUserId());
        user.setPassword(inputNewPasswordMd5);
        return tSysUserMapper.updateById(user);
    }

    @Override
    public Map<String,Object> insertUserAndRole(String param) {
        TSysUser tSysUser = new TSysUser();
        Map<String,Object> result = new HashMap<>();
        JSONObject json = JSON.parseObject(param);
        String pkUserId = IDUtils.currentTimeMillis();
        tSysUser.setPkUserId(pkUserId);
        String username = String.valueOf(json.get("username"));
        String unit = String.valueOf(json.get("unit"));
        String fullName = String.valueOf(json.get("fullName"));
        String email = String.valueOf(json.get("email"));
        String tel = String.valueOf(json.get("tel"));
        String enableStatus = String.valueOf(json.get("enableStatus"));
        String IDcard = String.valueOf(json.get("IDcard"));
        String policeNum = String.valueOf(json.get("policeNum"));
        String ip = String.valueOf(json.get("ip"));
        String address = String.valueOf(json.get("address"));
        tSysUser.setUsername(username);
        tSysUser.setUnit(unit);
        tSysUser.setFullName(fullName);
        tSysUser.setEmail(email);
        tSysUser.setTel(tel);
        tSysUser.setEnableStatus(enableStatus);
        tSysUser.setIDcard(IDcard);
        tSysUser.setPoliceNum(policeNum);
        tSysUser.setIp(ip);
        tSysUser.setAddress(address);
        //加密
        //默认密码
        String password = "123456";
        String passwordMd5 = PasswordEncryptUtils.MyPasswordEncryptUtil(tSysUser.getUsername(),password);
        tSysUser.setPassword(passwordMd5);
        tSysUser.setCreateTime(new Date());
        tSysUser.setModifyTime(new Date());
        tSysUserMapper.insertSelective(tSysUser);

        //是否直接分配角色
        String roleId = String.valueOf(json.get("roleId"));
        if(!roleId.equals("0")){
            ReUserRole userRole = new ReUserRole();
            userRole.setPk_userRole_id(IDUtils.currentTimeMillis());
            userRole.setFk_user_id(pkUserId);
            userRole.setFk_role_id(roleId);
            tSysRoleMapper.addUserRoleByUserRole(userRole);
            result.put("message","添加用户与分配角色成功");
        }else {
            result.put("message","暂未授予角色，该账号还不能正常使用");
        }
        return result;
    }

    @Override
    public int updateUser(String param) {
        JSONObject json = JSON.parseObject(param);
        TSysUser user = new TSysUser();
        //用户基本信息
        String pkUserId = String.valueOf(json.get("pkUserId"));
        user.setPkUserId(pkUserId);
        String username = String.valueOf(json.get("username"));
        user.setUsername(username);
        String email = String.valueOf(json.get("email"));
        user.setEmail(email);
        String tel = String.valueOf(json.get("tel"));
        user.setTel(tel);
        String IDcard = String.valueOf(json.get("IDcard"));
        user.setIDcard(IDcard);
        String fullName = String.valueOf(json.get("fullName"));
        user.setFullName(fullName);
        String policeNum = String.valueOf(json.get("policeNum"));
        user.setPoliceNum(policeNum);
        String unit = String.valueOf(json.get("unit"));
        user.setUnit(unit);
        String ip = String.valueOf(json.get("ip"));
        user.setIp(ip);
        String address = String.valueOf(json.get("address"));
        user.setIp(ip);
        String enableStatus = String.valueOf(json.get("enableStatus"));
        user.setEnableStatus(enableStatus);
        user.setAddress(address);
        //修改用户信息
        tSysUserMapper.updateById(user);
        //用户角色信息
        String fkRoleId = String.valueOf(json.get("roleId"));
        //删除原有角色
        tSysUserMapper.deleteUserRoleByUserId(pkUserId);
        if(fkRoleId.equals("0")){
            //暂不分配角色
        }
        if(!fkRoleId.equals("") ||fkRoleId !=null ||fkRoleId != "null"){
            ReUserRole userRole = new ReUserRole();
            userRole.setPk_userRole_id(IDUtils.currentTimeMillis());
            userRole.setFk_role_id(fkRoleId);
            userRole.setFk_user_id(pkUserId);
            //更改用户角色
            tSysRoleMapper.addUserRoleByUserRole(userRole);
        }
        return 0;
    }
}