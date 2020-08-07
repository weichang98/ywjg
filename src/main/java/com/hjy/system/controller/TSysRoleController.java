package com.hjy.system.controller;

import com.hjy.common.domin.CommonResult;
import com.hjy.common.exception.FebsException;
import com.hjy.system.entity.TSysPerms;
import com.hjy.system.entity.TSysRole;
import com.hjy.system.entity.TSysUser;
import com.hjy.system.service.TSysPermsService;
import com.hjy.system.service.TSysRoleService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hjy.system.service.TSysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.List;

/**
 * (TSysRole)表控制层
 *
 * @author liuchun
 * @since 2020-07-23 12:59:10
 */
@Slf4j
@RestController
public class TSysRoleController {
    /**
     * 服务对象
     */
    @Autowired
    private TSysRoleService tSysRoleService;
    @Autowired
    private TSysPermsService tSysPermsService;
    @Autowired
    private TSysUserService tSysUserService;

    /**
     * 1 跳转到新增页面
     */
     @GetMapping(value = "/system/role/addPage")
     public CommonResult tSysRoleAddPage() throws FebsException{
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
     * @param tSysRole 实体对象
     * @return 新增结果
     */
    @RequiresPermissions({"role:add"})
    @PostMapping("/system/role/add")
    public CommonResult tSysRoleAdd(@RequestBody TSysRole tSysRole) throws FebsException{
        System.err.println(tSysRole);
        try {
            //
            tSysRoleService.insert(tSysRole);
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
    @RequiresPermissions({"role:view"})
    @GetMapping("/system/role/list")
    public CommonResult tSysRoleList() throws FebsException {
        try {
            //
            JSONObject jsonObject = new JSONObject();
            //查询所有角色
            List<TSysRole> tSysRoleList = tSysRoleService.selectAll();
            jsonObject.put("roleList",tSysRoleList);
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
    @GetMapping("/system/role/listByEntity")
    public CommonResult tSysRoleListByEntity(@RequestBody TSysRole tSysRole) throws FebsException{
        System.err.println(tSysRole);
        try {
            //
            List<TSysRole> tSysRoleList = tSysRoleService.selectAllByEntity(tSysRole);
            System.err.println(tSysRoleList);
            return new CommonResult(200,"success","查询数据成功!",tSysRoleList);
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
    @RequiresPermissions({"role:del"})
    @DeleteMapping("/system/role/del")
    public CommonResult tSysRoleDel(@RequestBody String parm) throws FebsException{
        JSONObject jsonObject = JSON.parseObject(parm);
        String idStr=String.valueOf(jsonObject.get("pk_id"));
        if(idStr.equals("1595564064909")){
            return new CommonResult(444,"error","超级管理员不可删除!",null);
        }
        try {
            //删除角色表里的数据
            tSysRoleService.deleteById(idStr);
            //删除用户角色表里的数据
            tSysRoleService.deleteUserRoleByRoleId(idStr);
            //删除角色权限表里的数据
            tSysRoleService.deleteRolePermsByRoleId(idStr);
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
    @PostMapping("/system/role/getOne")
    public CommonResult tSysRolegetOne(@RequestBody String parm) throws FebsException{
        JSONObject jsonObject = JSON.parseObject(parm);
        String idStr=String.valueOf(jsonObject.get("pk_id"));
        try {
            //
            TSysRole tSysRole = tSysRoleService.selectById(idStr);
            System.err.println(tSysRole);
            return new CommonResult(200,"success","数据获取成功!",tSysRole);
        } catch (Exception e) {
            String message = "数据获取失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
    
    /**
     * 4 修改数据
     * @param tSysRole 实体对象
     * @return 修改结果
     */
    @RequiresPermissions({"role:update"})
    @PutMapping("/system/role/update")
    public CommonResult tSysRoleUpdate(@RequestBody TSysRole tSysRole) throws FebsException{
        System.err.println(tSysRole);
        try {
            //
            tSysRoleService.updateById(tSysRole);
            return new CommonResult(200,"success","修改成功!",null);
        } catch (Exception e) {
            String message = "修改失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
    /**
     * 5 给角色分配菜单权限
     */
    @PostMapping("/system/role/distributeUI")
    public CommonResult FPRoleMenuUI(@RequestBody String parm) throws FebsException{
        JSONObject json = JSON.parseObject(parm);
        String roleIdStr=String.valueOf(json.get("pk_id"));
        JSONObject jsonObject = new JSONObject();
        try {
            //通过角色id查找角色
            TSysRole role = tSysRoleService.selectById(roleIdStr);
            jsonObject.put("role",role);
            //查找所有权限
            List<TSysPerms> permsList = tSysPermsService.selectAll();
            jsonObject.put("permsList",permsList);
            //查询已分配的菜单并进行回显
            List<String> permsXZList = tSysPermsService.selectDistributeByrole_id(roleIdStr);
            jsonObject.put("ids",permsXZList);
            return new CommonResult(200,"success","获取已分配菜单权限成功!",jsonObject);
        } catch (Exception e) {
            String message = "获取已分配菜单权限失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
    /**
     * 5 给角色分配菜单权限
     */
    @RequiresPermissions({"role:distributePerms"})
    @PostMapping("/system/role/distribute")
    public CommonResult FPRoleMenu(@RequestBody String parm) throws FebsException{
        JSONObject json = JSON.parseObject(parm);
        String fk_role_id=String.valueOf(json.get("fk_role_id"));
        JSONArray jsonArray = json.getJSONArray("ids");
        String permsIdsStr = jsonArray.toString();
        List<String> idList = JSONArray.parseArray(permsIdsStr,String.class);
        try {
            //通过role_id删除原有的perms
            tSysRoleService.deleteRolePermsByRoleId(fk_role_id);
            //添加选中的权限菜单
            tSysRoleService.addRoleMenuByList(fk_role_id,idList);
            return new CommonResult(200,"success","分配菜单权限成功!",null);
        } catch (Exception e) {
            String message = "分配菜单权限失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
    /**
     * 6 给角色下发用户
     */
    @PostMapping("/system/role/addUserUI")
    public CommonResult systemRoleAddUserUI(@RequestBody String parm) throws FebsException{
        JSONObject json = JSON.parseObject(parm);
        String roleIdStr=String.valueOf(json.get("fk_role_id"));
        JSONObject jsonObject = new JSONObject();
        try {
            //通过角色id查找角色
            TSysRole role = tSysRoleService.selectById(roleIdStr);
            jsonObject.put("role",role);
            //查找所有用户
            List<TSysUser> tSysUserList = tSysUserService.selectAll();
            jsonObject.put("userList",tSysUserList);
            //查询已分配的用户角色并进行回显
            List<String> userRoleList = tSysRoleService.selectUserRole_userIded();
            List<String> userRoleList2 = tSysRoleService.selectUserRoleByrole_id(roleIdStr);
            jsonObject.put("ids",userRoleList);
            jsonObject.put("idsFP",userRoleList2);
            return new CommonResult(200,"success","获取角色已分配用户成功!",jsonObject);
        } catch (Exception e) {
            String message = "获取角色已分配用户失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
    /**
     * 6 给角色下发用户
     */
    @RequiresPermissions({"role:addUser"})
    @PostMapping("/system/role/addUser")
    public CommonResult systemRoleAddUser(@RequestBody String parm) throws FebsException{
        JSONObject jsonObject = JSON.parseObject(parm);
        String fk_role_id=String.valueOf(jsonObject.get("fk_role_id"));
        JSONArray jsonArray = jsonObject.getJSONArray("ids");
        String userIdsStr = jsonArray.toString();
        List<String> idList = JSONArray.parseArray(userIdsStr,String.class);
        try {
            //删除原有的用户角色
            tSysRoleService.deleteUserRoleByRoleId(fk_role_id);
            //添加用户角色
            tSysRoleService.addUserRoleByList(fk_role_id,idList);
            return new CommonResult(200,"success","角色添加用户成功!",jsonObject);
        } catch (Exception e) {
            String message = "角色添加用户失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

}