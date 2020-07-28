package com.hjy.system.controller;

import com.hjy.common.domin.CommonResult;
import com.hjy.common.exception.FebsException;
import com.hjy.common.utils.IDUtils;
import com.hjy.system.entity.ReUserRole;
import com.hjy.system.entity.TSysRole;
import com.hjy.system.entity.TSysUser;
import com.hjy.system.service.TSysRoleService;
import com.hjy.system.service.TSysUserService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
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
    /**
     * 1 跳转到新增页面
     */
     @GetMapping(value = "/system/user/addPage")
     public CommonResult tSysUserAddPage() throws FebsException {
        try {
            //
            return new CommonResult(200,"success","成功!",null);
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
    @GetMapping("/system/user/list")
    public CommonResult tSysUserList() throws FebsException{
        try {
            //
            List<TSysUser> tSysUserList = tSysUserService.selectAll();
            System.err.println(tSysUserList);
            return new CommonResult(200,"success","查询数据成功!",tSysUserList);
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
    @GetMapping("/system/user/listByEntity")
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
    @DeleteMapping("/system/user/del")
    public CommonResult tSysUserDel(@RequestBody String parm) throws FebsException{
        JSONObject jsonObject = JSON.parseObject(parm);
        String idStr=String.valueOf(jsonObject.get("pk_id"));
        try {
            //
            tSysUserService.deleteById(idStr);
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
    @GetMapping("/system/user/getOne")
    public CommonResult tSysUsergetOne(@RequestBody String parm) throws FebsException{
        JSONObject jsonObject = JSON.parseObject(parm);
        String idStr=String.valueOf(jsonObject.get("pk_id"));
        try {
            //
            TSysUser tSysUser = tSysUserService.selectById(idStr);
            System.err.println(tSysUser);
            return new CommonResult(200,"success","数据获取成功!",tSysUser);
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
    @PutMapping("/system/user/update")
    public CommonResult tSysUserUpdate(@RequestBody TSysUser tSysUser) throws FebsException{
        System.err.println(tSysUser);
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
}