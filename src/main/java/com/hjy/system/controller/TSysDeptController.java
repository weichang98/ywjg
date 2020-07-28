package com.hjy.system.controller;

import com.hjy.common.domin.CommonResult;
import com.hjy.common.exception.FebsException;
import com.hjy.system.entity.TSysDept;
import com.hjy.system.service.TSysDeptService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.List;

/**
 * (TSysDept)表控制层
 *
 * @author liuchun
 * @since 2020-07-27 16:15:29
 */
@Slf4j
@RestController
public class TSysDeptController {
    /**
     * 服务对象
     */
    @Autowired
    private TSysDeptService tSysDeptService;

    /**
     * 1 跳转到新增页面
     */
     @GetMapping(value = "/system/dept/addPage")
     public CommonResult tSysDeptAddPage() throws FebsException {
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
     * @param tSysDept 实体对象
     * @return 新增结果
     */
    @PostMapping("/system/dept/add")
    public CommonResult tSysDeptAdd(@RequestBody TSysDept tSysDept) throws FebsException{
        System.err.println(tSysDept);
        try {
            //
            tSysDeptService.insert(tSysDept);
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
    @GetMapping("/system/dept/list")
    public CommonResult tSysDeptList() throws FebsException{
        try {
            //
            List<TSysDept> tSysDeptList = tSysDeptService.selectAll();
            System.err.println(tSysDeptList);
            return new CommonResult(200,"success","查询数据成功!",tSysDeptList);
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
    @GetMapping("/system/dept/listByEntity")
    public CommonResult tSysDeptListByEntity(@RequestBody TSysDept tSysDept) throws FebsException{
        try {
            //
            List<TSysDept> tSysDeptList = tSysDeptService.selectAllByEntity(tSysDept);
            System.err.println(tSysDeptList);
            return new CommonResult(200,"success","查询数据成功!",tSysDeptList);
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
    @DeleteMapping("/system/dept/del")
    public CommonResult tSysDeptDel(@RequestBody String parm) throws FebsException{
        JSONObject jsonObject = JSON.parseObject(parm);
        String idStr=String.valueOf(jsonObject.get("pk_id"));
        try {
            //
            tSysDeptService.deleteById(idStr);
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
    @GetMapping("/system/dept/getOne")
    public CommonResult tSysDeptgetOne(@RequestBody String parm) throws FebsException{
        JSONObject jsonObject = JSON.parseObject(parm);
        String idStr=String.valueOf(jsonObject.get("pk_id"));
        try {
            //
            TSysDept tSysDept = tSysDeptService.selectById(idStr);
            System.err.println(tSysDept);
            return new CommonResult(200,"success","数据获取成功!",tSysDept);
        } catch (Exception e) {
            String message = "数据获取失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
    
    /**
     * 4 修改数据
     * @param tSysDept 实体对象
     * @return 修改结果
     */
    @PutMapping("/system/dept/update")
    public CommonResult tSysDeptUpdate(@RequestBody TSysDept tSysDept) throws FebsException{
        System.err.println(tSysDept);
        try {
            //
            tSysDeptService.updateById(tSysDept);
            return new CommonResult(200,"success","修改成功!",null);
        } catch (Exception e) {
            String message = "修改失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

}