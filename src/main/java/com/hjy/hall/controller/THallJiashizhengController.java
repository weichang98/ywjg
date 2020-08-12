package com.hjy.hall.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hjy.common.exception.FebsException;
import com.hjy.hall.entity.THallJiashizheng;
import com.hjy.hall.service.THallJiashizhengService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.hjy.common.domin.CommonResult;

import java.util.List;

/**
 * (THallJiashizheng)表控制层
 *
 * @author makejava
 * @since 2020-07-27 14:17:45
 */
@Slf4j
@RestController
public class THallJiashizhengController {
    /**
     * 服务对象
     */
    @Autowired
    private THallJiashizhengService tHallJiashizhengService;

    /**
     * 1 跳转到新增页面
     */
    @GetMapping(value = "/hall/jiashizheng/addPage")
    public CommonResult tHallJiashizhengAddPage() throws FebsException {
        try {
            //
            return new CommonResult(200, "success", "成功!", null);
        } catch (Exception e) {
            String message = "失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    /**
     * 1 新增数据
     *
     * @param tHallJiashizheng 实体对象
     * @return 新增结果
     */
    @PostMapping("/hall/jiashizheng/add")
    public CommonResult tHallJiashizhengAdd(@RequestBody THallJiashizheng tHallJiashizheng) throws FebsException {
        System.err.println(tHallJiashizheng);
        try {
            //
            tHallJiashizhengService.insert(tHallJiashizheng);
            return new CommonResult(200, "success", "数据添加成功!", null);
        } catch (Exception e) {
            String message = "数据添加失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    /**
     * 2 查询所有数据
     *
     * @return 所有数据
     */
    @RequiresPermissions({"jiashizheng:view"})
    @GetMapping("/hall/jiashizheng/list")
    public CommonResult tHallJiashizhengList() throws FebsException {
        try {
            //
            List<THallJiashizheng> tHallJiashizhengList = tHallJiashizhengService.selectAll();
            System.err.println(tHallJiashizhengList);
            return new CommonResult(200, "success", "查询数据成功!", tHallJiashizhengList);
        } catch (Exception e) {
            String message = "查询数据失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    /**
     * 2 通过实体查询所有数据
     *
     * @return 所有数据
     */
    @GetMapping("/hall/jiashizheng/listByEntity")
    public CommonResult tHallJiashizhengListByEntity(@RequestBody THallJiashizheng tHallJiashizheng) throws FebsException {
        try {
            //
            List<THallJiashizheng> tHallJiashizhengList = tHallJiashizhengService.selectAllByEntity(tHallJiashizheng);
            System.err.println(tHallJiashizhengList);
            return new CommonResult(200, "success", "查询数据成功!", tHallJiashizhengList);
        } catch (Exception e) {
            String message = "查询数据失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    /**
     * 3 删除数据
     *
     * @return 删除结果
     */
    @RequiresPermissions({"jiashizheng:view"})
    @DeleteMapping("/hall/jiashizheng/del")
    public CommonResult tHallJiashizhengDel(@RequestBody String parm) throws FebsException {
        JSONObject jsonObject = JSON.parseObject(parm);
        String idStr = String.valueOf(jsonObject.get("pkJiashiId"));
        try {
            //
            tHallJiashizhengService.deleteById(idStr);
            return new CommonResult(200, "success", "数据删除成功!", null);
        } catch (Exception e) {
            String message = "数据删除失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    /**
     * 4 通过主键查询单条数据
     *
     * @return 单条数据
     */
    @GetMapping("/hall/jiashizheng/getOne")
    public CommonResult tHallJiashizhenggetOne(@RequestBody String parm) throws FebsException {
        JSONObject jsonObject = JSON.parseObject(parm);
        String idStr = String.valueOf(jsonObject.get("pkJiashiId"));
        try {
            //
            THallJiashizheng tHallJiashizheng = tHallJiashizhengService.selectById(idStr);
            System.err.println(tHallJiashizheng);
            return new CommonResult(200, "success", "数据获取成功!", tHallJiashizheng);
        } catch (Exception e) {
            String message = "数据获取失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    /**
     * 4 修改数据
     *
     * @param tHallJiashizheng 实体对象
     * @return 修改结果
     */
    @RequiresPermissions({"jiashizheng:view"})
    @PutMapping("/hall/jiashizheng/update")
    public CommonResult tHallJiashizhengUpdate(@RequestBody THallJiashizheng tHallJiashizheng) throws FebsException {
        System.err.println(tHallJiashizheng);
        try {
            //
            tHallJiashizhengService.updateById(tHallJiashizheng);
            return new CommonResult(200, "success", "修改成功!", null);
        } catch (Exception e) {
            String message = "修改失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

}