package com.hjy.system.controller;

import com.hjy.common.domin.CommonResult;
import com.hjy.common.exception.FebsException;
import com.hjy.system.entity.TSysPerms;
import com.hjy.system.service.TSysPermsService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.List;

/**
 * (TSysPerms)表控制层
 *
 * @author liuchun
 * @since 2020-07-24 09:55:41
 */
@Slf4j
@RestController
public class TSysPermsController {
    /**
     * 服务对象
     */
    @Autowired
    private TSysPermsService tSysPermsService;

    /**
     * 1 跳转到新增页面
     */
     @GetMapping(value = "/system/perms/addPage")
     public CommonResult tSysPermsAddPage() throws FebsException {
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
     * @param tSysPerms 实体对象
     * @return 新增结果
     */
    @PostMapping("/system/perms/add")
    public CommonResult tSysPermsAdd(@RequestBody TSysPerms tSysPerms) throws FebsException{
        System.err.println(tSysPerms);
        try {
            //
            tSysPermsService.insert(tSysPerms);
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
    @GetMapping("/system/perms/list")
    public CommonResult tSysPermsList() throws FebsException{
        try {
            //
            List<TSysPerms> tSysPermsList = tSysPermsService.selectAll();
            System.err.println(tSysPermsList);
            return new CommonResult(200,"success","查询数据成功!",tSysPermsList);
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
    @GetMapping("/system/perms/listByEntity")
    public CommonResult tSysPermsListByEntity(@RequestBody TSysPerms tSysPerms) throws FebsException{
        try {
            //
            List<TSysPerms> tSysPermsList = tSysPermsService.selectAllByEntity(tSysPerms);
            System.err.println(tSysPermsList);
            return new CommonResult(200,"success","查询数据成功!",tSysPermsList);
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
    @DeleteMapping("/system/perms/del")
    public CommonResult tSysPermsDel(@RequestBody String parm) throws FebsException{
        JSONObject jsonObject = JSON.parseObject(parm);
        String idStr=String.valueOf(jsonObject.get("pk_id"));
        try {
            //
            tSysPermsService.deleteById(idStr);
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
    @GetMapping("/system/perms/getOne")
    public CommonResult tSysPermsgetOne(@RequestBody String parm) throws FebsException{
        JSONObject jsonObject = JSON.parseObject(parm);
        String idStr=String.valueOf(jsonObject.get("pk_id"));
        try {
            //
            TSysPerms tSysPerms = tSysPermsService.selectById(idStr);
            System.err.println(tSysPerms);
            return new CommonResult(200,"success","数据获取成功!",tSysPerms);
        } catch (Exception e) {
            String message = "数据获取失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
    
    /**
     * 4 修改数据
     * @param tSysPerms 实体对象
     * @return 修改结果
     */
    @PutMapping("/system/perms/update")
    public CommonResult tSysPermsUpdate(@RequestBody TSysPerms tSysPerms) throws FebsException{
        System.err.println(tSysPerms);
        try {
            //
            tSysPermsService.updateById(tSysPerms);
            return new CommonResult(200,"success","修改成功!",null);
        } catch (Exception e) {
            String message = "修改失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

}