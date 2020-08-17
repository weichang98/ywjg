package com.hjy.system.controller;

import com.hjy.common.domin.CommonResult;
import com.hjy.common.exception.FebsException;
import com.hjy.system.entity.TSysBusinesstype;
import com.hjy.system.service.TSysBusinesstypeService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.List;

/**
 * (TSysBusinesstype)表控制层
 *
 * @author liuchun
 * @since 2020-07-28 16:54:27
 */
@Slf4j
@RestController
public class TSysBusinesstypeController {
    /**
     * 服务对象
     */
    @Autowired
    private TSysBusinesstypeService tSysBusinesstypeService;

    /**
     * 1 跳转到新增页面
     */
     @GetMapping(value = "/system/businessType/addPage")
     public CommonResult tSysBusinesstypeAddPage() throws FebsException {
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
     * @param tSysBusinesstype 实体对象
     * @return 新增结果
     */
    @RequiresPermissions({"businessType:view"})
//    @RequiresPermissions({"businessType:add"})
    @PostMapping("/system/businessType/add")
    public CommonResult tSysBusinesstypeAdd(@RequestBody TSysBusinesstype tSysBusinesstype) throws FebsException{
        try {
            //
            tSysBusinesstypeService.insert(tSysBusinesstype);
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
    @RequiresPermissions({"businessType:view"})
    @GetMapping("/system/businessType/list")
    public CommonResult tSysBusinesstypeList() throws FebsException{
        try {
            //
            List<TSysBusinesstype> tSysBusinesstypeList = tSysBusinesstypeService.selectAll();
            System.err.println(tSysBusinesstypeList);
            return new CommonResult(200,"success","查询数据成功!",tSysBusinesstypeList);
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
    @PostMapping("/system/businessType/listByEntity")
    public CommonResult tSysBusinesstypeListByEntity(@RequestBody TSysBusinesstype tSysBusinesstype) throws FebsException{
        try {
            //
            List<TSysBusinesstype> tSysBusinesstypeList = tSysBusinesstypeService.selectAllByEntity(tSysBusinesstype);
            return new CommonResult(200,"success","查询数据成功!",tSysBusinesstypeList);
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
    @RequiresPermissions({"businessType:view"})
//    @RequiresPermissions({"businessType:del"})
    @DeleteMapping("/system/businessType/del")
    public CommonResult tSysBusinesstypeDel(@RequestBody String parm) throws FebsException{
        JSONObject jsonObject = JSON.parseObject(parm);
        String idStr=String.valueOf(jsonObject.get("pk_id"));
        try {
            //
            tSysBusinesstypeService.deleteById(idStr);
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
    @GetMapping("/system/businessType/getOne")
    public CommonResult tSysBusinesstypegetOne(@RequestBody String parm) throws FebsException{
        JSONObject jsonObject = JSON.parseObject(parm);
        String idStr=String.valueOf(jsonObject.get("pk_id"));
        try {
            //
            TSysBusinesstype tSysBusinesstype = tSysBusinesstypeService.selectById(idStr);
            return new CommonResult(200,"success","数据获取成功!",tSysBusinesstype);
        } catch (Exception e) {
            String message = "数据获取失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
    
    /**
     * 4 修改数据
     * @param tSysBusinesstype 实体对象
     * @return 修改结果
     */
    @RequiresPermissions({"businessType:view"})
//    @RequiresPermissions({"businessType:update"})
    @PutMapping("/system/businessType/update")
    public CommonResult tSysBusinesstypeUpdate(@RequestBody TSysBusinesstype tSysBusinesstype) throws FebsException{
        try {
            //
            tSysBusinesstypeService.updateById(tSysBusinesstype);
            return new CommonResult(200,"success","修改成功!",null);
        } catch (Exception e) {
            String message = "修改失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

}