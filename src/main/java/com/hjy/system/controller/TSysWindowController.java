package com.hjy.system.controller;

import com.hjy.common.domin.CommonResult;
import com.hjy.common.exception.FebsException;
import com.hjy.system.entity.TSysBusinesstype;
import com.hjy.system.entity.TSysDept;
import com.hjy.system.entity.TSysWindow;
import com.hjy.system.service.TSysBusinesstypeService;
import com.hjy.system.service.TSysDeptService;
import com.hjy.system.service.TSysWindowService;
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
 * (TSysWindow)表控制层
 *
 * @author liuchun
 * @since 2020-07-28 14:56:45
 */
@Slf4j
@RestController
public class TSysWindowController {
    /**
     * 服务对象
     */
    @Autowired
    private TSysWindowService tSysWindowService;
    @Autowired
    private TSysBusinesstypeService tSysBusinesstypeService;
    @Autowired
    private TSysDeptService tSysDeptService;
    /**
     * 1 跳转到新增页面
     */
     @GetMapping(value = "/system/window/addPage")
     public CommonResult tSysWindowAddPage() throws FebsException {
        try {
            //查询所有业务类型
            List<TSysBusinesstype> businesstypeList = tSysBusinesstypeService.selectAll();
            JSONObject json = new JSONObject();
            List<String> businesstypes = new ArrayList<>();
            for(TSysBusinesstype obj:businesstypeList){
                businesstypes.add(obj.getTypeName());
            }
            json.put("businesstypeList",businesstypes);
            List<TSysDept> deptList = tSysDeptService.selectAll();
            json.put("deptList",deptList);
            return new CommonResult(200,"success","成功!",json);
        } catch (Exception e) {
            String message = "失败";
            log.error(message, e);
            throw new FebsException(message);
        }
     }
    /**
     * 1 新增数据
     * @param parm
     * @return 新增结果
     */
    @RequiresPermissions({"window:add"})
    @PostMapping("/system/window/add")
    public CommonResult tSysWindowAdd(@RequestBody String parm ) throws FebsException{
        try {
            //添加窗口数据
            tSysWindowService.insert(parm);
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
    @RequiresPermissions({"window:view"})
    @GetMapping("/system/window/list")
    public CommonResult tSysWindowList() throws FebsException{
        try {
            //
            List<TSysWindow> tSysWindowList = tSysWindowService.selectAll();
            System.err.println(tSysWindowList);
            return new CommonResult(200,"success","查询数据成功!",tSysWindowList);
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
    @GetMapping("/system/window/listByEntity")
    public CommonResult tSysWindowListByEntity(@RequestBody TSysWindow tSysWindow) throws FebsException{
        try {
            //
            List<TSysWindow> tSysWindowList = tSysWindowService.selectAllByEntity(tSysWindow);
            System.err.println(tSysWindowList);
            return new CommonResult(200,"success","查询数据成功!",tSysWindowList);
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
    @RequiresPermissions({"window:del"})
    @DeleteMapping("/system/window/del")
    public CommonResult tSysWindowDel(@RequestBody String parm) throws FebsException{
        JSONObject jsonObject = JSON.parseObject(parm);
        String idStr=String.valueOf(jsonObject.get("pk_id"));
        try {
            //
            tSysWindowService.deleteById(idStr);
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
    @GetMapping("/system/window/getOne")
    public CommonResult tSysWindowgetOne(@RequestBody String parm) throws FebsException{
        JSONObject jsonObject = JSON.parseObject(parm);
        String idStr=String.valueOf(jsonObject.get("pk_id"));
        try {
            //
            TSysWindow tSysWindow = tSysWindowService.selectById(idStr);
            System.err.println(tSysWindow);
            return new CommonResult(200,"success","数据获取成功!",tSysWindow);
        } catch (Exception e) {
            String message = "数据获取失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
    
    /**
     * 4 修改数据
     * @param tSysWindow 实体对象
     * @return 修改结果
     */
    @RequiresPermissions({"window:update"})
    @PutMapping("/system/window/update")
    public CommonResult tSysWindowUpdate(@RequestBody TSysWindow tSysWindow) throws FebsException{
        System.err.println(tSysWindow);
        try {
            //
            tSysWindowService.updateById(tSysWindow);
            return new CommonResult(200,"success","修改成功!",null);
        } catch (Exception e) {
            String message = "修改失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

}