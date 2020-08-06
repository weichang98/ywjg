package com.hjy.system.controller;

import com.hjy.common.domin.CommonResult;
import com.hjy.common.exception.FebsException;
import com.hjy.common.utils.IDUtils;
import com.hjy.common.utils.PasswordEncryptUtils;
import com.hjy.common.utils.page.PageRequest;
import com.hjy.common.utils.page.PageResult;
import com.hjy.system.entity.*;
import com.hjy.system.service.TSysDeptService;
import com.hjy.system.service.TSysPermsService;
import com.hjy.system.service.TSysRoleService;
import com.hjy.system.service.TSysUserService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * (TSysUser)表控制层
 *
 * @author liuchun
 * @since 2020-07-24 17:05:59
 */
@Slf4j
@RestController
public class TSysUserController {
    /**
     * 服务对象
     */
    @Autowired
    private TSysUserService tSysUserService;
    @Autowired
    private TSysRoleService tSysRoleService;
    @Autowired
    private TSysDeptService tSysDeptService;
    @Autowired
    private TSysPermsService tSysPermsService;

    /**
     * 1 跳转到新增页面
     */
     @GetMapping(value = "/system/user/addPage")
     public CommonResult tSysUserAddPage() throws FebsException {
        try {
            //查询所有单位列表
            List<TSysDept> depts = tSysDeptService.selectAll();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("deptList",depts);
            //所有角色信息
            List<TSysRole> roleList = tSysRoleService.selectAll();
            jsonObject.put("roleList",roleList);
            //所有权限信息
            List<TSysPerms> perms = tSysPermsService.selectAll();
            jsonObject.put("permsList",perms);
            return new CommonResult(200,"success","成功!",jsonObject);
        } catch (Exception e) {
            String message = "失败";
            log.error(message, e);
            throw new FebsException(message);
        }
     }
    /**
     * 1 新增数据
     * @param tSysUser 实体对象
     * @return 新增结果
     */
    @RequiresPermissions({"user:add"})
    @PostMapping("/system/user/add")
    public CommonResult tSysUserAdd(@RequestBody TSysUser tSysUser) throws FebsException{
        System.err.println(tSysUser);
        try {
            //
            tSysUserService.insert(tSysUser);
            return new CommonResult(200,"success","数据添加成功!",null);
        } catch (Exception e) {
            String message = "数据添加失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
    /**
     * 2 查询所有数据
     * @return 所有数据
     */
    @RequiresPermissions({"user:view"})
    @PostMapping("/system/user/list")
    public CommonResult tSysUserList(@RequestBody PageRequest pageRequest ) throws FebsException{
        try {
            //
            List<TSysUser> tSysUserList = tSysUserService.selectAll();
            int total= tSysUserList.size();
            int pageSize = pageRequest.getPageSize();
            PageResult result = new PageResult();
            result.setContent(tSysUserList);
            result.setTotal(total);
            result.setPages((int) Math.ceil(total/pageSize));
            result.setPageSize(pageSize);
            result.setPageNum(pageRequest.getPageNum());
//            PageResult result = tSysUserService.selectAllPage(pageRequest);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("PageResult",result);
            //部门
            List<TSysDept> depts = tSysDeptService.selectAll();
            jsonObject.put("depts",depts);
            return new CommonResult(200,"success","查询数据成功!",jsonObject);
        } catch (Exception e) {
            String message = "查询数据失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
    /**
     * 2 通过实体查询所有数据
     * @return 所有数据
     */
    @RequiresPermissions({"user:view"})
    @PostMapping("/system/user/listByEntity")
    public CommonResult tSysUserListByEntity(@RequestBody TSysUser tSysUser) throws FebsException{
        System.err.println("listByEntity"+tSysUser);
        try {
            //
            List<TSysUser> tSysUserList = tSysUserService.selectAllByEntity(tSysUser);
            System.err.println(tSysUserList);
            return new CommonResult(200,"success","查询数据成功!",tSysUserList);
        } catch (Exception e) {
            String message = "查询数据失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    /**
     * 3 删除数据
     * @return 删除结果
     */
    @RequiresPermissions({"user:del"})
    @DeleteMapping("/system/user/del")
    public CommonResult tSysUserDel(@RequestBody String parm) throws FebsException{
        JSONObject jsonObject = JSON.parseObject(parm);
        String idStr=String.valueOf(jsonObject.get("pk_id"));
        try {
            //删除用户表里的用户
            tSysUserService.deleteById(idStr);
            //删除用户角色表里的用户
            tSysUserService.deleteUserRoleByUserId(idStr);
            return new CommonResult(200,"success","数据删除成功!",null);
        } catch (Exception e) {
            String message = "数据删除失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
    
    /**
     * 4 通过主键查询单条数据
     * @return 单条数据
     */
    @PostMapping("/system/user/getOne")
    public CommonResult tSysUsergetOne(@RequestBody String parm) throws FebsException{
        JSONObject jsonObject = JSON.parseObject(parm);
        String idStr=String.valueOf(jsonObject.get("pk_id"));
        try {
            //
            TSysUser tSysUser = tSysUserService.selectById(idStr);
            System.err.println(tSysUser);
            JSONObject jsonObject2 = new JSONObject();
            jsonObject2.put("tSysUser",tSysUser);
            List<TSysPerms> perms = tSysPermsService.selectAll();
            jsonObject2.put("perms",perms);
            //如果permsType=1为角色权限，permsType=2为自由权限
            jsonObject2.put("permsType","1");
            return new CommonResult(200,"success","数据获取成功!",jsonObject2);
        } catch (Exception e) {
            String message = "数据获取失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
    
    /**
     * 4 修改数据
     * @param tSysUser 实体对象
     * @return 修改结果
     */
    @RequiresPermissions({"user:update"})
    @PutMapping("/system/user/update")
    public CommonResult tSysUserUpdate(@RequestBody TSysUser tSysUser) throws FebsException{
        System.err.println("system/user/update:--"+tSysUser);
        try {
            //
            tSysUserService.updateById(tSysUser);
            return new CommonResult(200,"success","修改成功!",null);
        } catch (Exception e) {
            String message = "修改失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
    /**
     * 5 分配角色UI
     */
    @PostMapping(value = "/system/user/distributeRoleUI")
    public CommonResult roleDistributePage(@RequestBody String parm)throws FebsException{
        JSONObject json = JSON.parseObject(parm);
        String fkUserId=String.valueOf(json.get("fk_user_id"));
        JSONObject jsonObject = new JSONObject();
        try {
            //用户信息
            TSysUser user = tSysUserService.selectById(fkUserId);
            jsonObject.put("FPUser",user);
            //所有角色信息
            List<TSysRole> roleList = tSysRoleService.selectAll();
            jsonObject.put("roleList",roleList);
            //已分配角色信息
            String fk_role_id = tSysUserService.selectUserRoleByUserId(fkUserId);
            jsonObject.put("FPRoleId",fk_role_id);
            return new CommonResult(200,"success","角色数据获取成功!",jsonObject);
        } catch (Exception e) {
            String message = "用户或角色数据获取失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
    /**
     * 5 分配角色
     */
    @RequiresPermissions({"user:distributeRole"})
    @PostMapping(value = "/system/user/distributeRole")
    public CommonResult roleDistribute(@RequestBody String parm) throws FebsException{
        JSONObject json = JSON.parseObject(parm);
        String fk_user_id=String.valueOf(json.get("fk_user_id"));
        String fk_role_id=String.valueOf(json.get("fk_role_id"));
        ReUserRole userRole  = new ReUserRole();
        userRole.setPk_userRole_id(IDUtils.currentTimeMillis());
        userRole.setFk_user_id(fk_user_id);
        userRole.setFk_role_id(fk_role_id);
        try {
            //删除原有用户角色数据
            tSysUserService.deleteUserRoleByUserId(fk_user_id);
            //添加用户角色信息
            tSysRoleService.addUserRoleByUserRole(userRole);
            return new CommonResult(200,"success","角色分配成功!",null);
        } catch (Exception e) {
            String message = "角色分配失败！";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
    /**
     * 6 重置密码
     * @param parm 参数
     * @return 修改结果
     */
    @PutMapping("/system/user/resetPassword")
    public CommonResult resetPassword(@RequestBody String parm) throws FebsException{
        JSONObject json = JSON.parseObject(parm);
        String pkUserId=String.valueOf(json.get("pkUserId"));
        String username=String.valueOf(json.get("username"));
        try {
            //
            TSysUser tSysUser = new TSysUser();
            tSysUser.setPkUserId(pkUserId);
            tSysUser.setUsername(username);
            tSysUser.setPassword(PasswordEncryptUtils.MyPasswordEncryptUtil(username,"123456"));
            tSysUserService.updateById(tSysUser);
            return new CommonResult(200,"success","修改成功!",null);
        } catch (Exception e) {
            String message = "修改失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
    /**
     * 7 修改密码
     * @return 修改结果
     */
    @PutMapping("/system/user/updatePassword")
    public CommonResult updatePassword(HttpSession session,@RequestBody String parm) throws FebsException{
        ActiveUser activeUser = (ActiveUser) session.getAttribute("activeUser");
        try {
            //
            int i = tSysUserService.updatePassword(parm,activeUser);
            if (i==2) {
                return new CommonResult(444,"error","旧密码错误!",null);
            }else {
                return new CommonResult(200,"success","修改密码成功!",null);
            }
        } catch (Exception e) {
            String message = "修改密码失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
}