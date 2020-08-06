package com.hjy.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hjy.common.utils.IDUtils;
import com.hjy.common.utils.PasswordEncryptUtils;
import com.hjy.common.utils.page.PageObjectUtils;
import com.hjy.common.utils.page.PageRequest;
import com.hjy.common.utils.page.PageResult;
import com.hjy.system.entity.ActiveUser;
import com.hjy.system.entity.TSysUser;
import com.hjy.system.dao.TSysUserMapper;
import com.hjy.system.service.TSysUserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

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
    public PageResult selectAllPage(PageRequest pageRequest) {
        PageRequest pageRequest2 = PageObjectUtils.getRequest(pageRequest);
        System.err.println("pageRequest------"+pageRequest2);
        return tSysUserMapper.selectAllPage(pageRequest2);
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
}