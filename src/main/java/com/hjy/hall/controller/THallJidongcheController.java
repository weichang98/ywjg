package com.hjy.hall.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hjy.common.domin.CommonResult;
import com.hjy.common.exception.FebsException;
import com.hjy.hall.entity.THallJidongche;
import com.hjy.hall.service.THallJidongcheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * (THallJidongche)表控制层
 *
 * @author makejava
 * @since 2020-07-27 15:51:24
 */
@Slf4j
@RestController
public class THallJidongcheController {
    /**
     * 服务对象
     */
    @Autowired
    private THallJidongcheService tHallJidongcheService;

    /**
     * 1 跳转到新增页面
     */
    @GetMapping(value = "/tHallJidongche/addPage")
    public CommonResult tHallJidongcheAddPage() throws FebsException {
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
     * @param tHallJidongche 实体对象
     * @return 新增结果
     */
    @PostMapping("/tHallJidongche/add")
    public CommonResult tHallJidongcheAdd(@RequestBody THallJidongche tHallJidongche) throws FebsException {
        System.err.println(tHallJidongche);
        try {
            //
            tHallJidongcheService.insert(tHallJidongche);
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
    @GetMapping("/tHallJidongche/list")
    public CommonResult tHallJidongcheList() throws FebsException {
        try {
            //
            List<THallJidongche> tHallJidongcheList = tHallJidongcheService.selectAll();
            System.err.println(tHallJidongcheList);
            return new CommonResult(200, "success", "查询数据成功!", tHallJidongcheList);
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
    @GetMapping("/tHallJidongche/listByEntity")
    public CommonResult tHallJidongcheListByEntity(@RequestBody THallJidongche tHallJidongche) throws FebsException {
        try {
            //
            List<THallJidongche> tHallJidongcheList = tHallJidongcheService.selectAllByEntity(tHallJidongche);
            System.err.println(tHallJidongcheList);
            return new CommonResult(200, "success", "查询数据成功!", tHallJidongcheList);
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
    @DeleteMapping("/tHallJidongche/del")
    public CommonResult tHallJidongcheDel(@RequestBody String parm) throws FebsException {
        JSONObject jsonObject = JSON.parseObject(parm);
        String idStr = String.valueOf(jsonObject.get("pk_id"));
        try {
            //
            tHallJidongcheService.deleteById(idStr);
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
    @GetMapping("/tHallJidongche/getOne")
    public CommonResult tHallJidongchegetOne(@RequestBody String parm) throws FebsException {
        JSONObject jsonObject = JSON.parseObject(parm);
        String idStr = String.valueOf(jsonObject.get("pk_id"));
        try {
            //
            THallJidongche tHallJidongche = tHallJidongcheService.selectById(idStr);
            System.err.println(tHallJidongche);
            return new CommonResult(200, "success", "数据获取成功!", tHallJidongche);
        } catch (Exception e) {
            String message = "数据获取失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    /**
     * 4 修改数据
     *
     * @param tHallJidongche 实体对象
     * @return 修改结果
     */
    @PutMapping("/tHallJidongche/update")
    public CommonResult tHallJidongcheUpdate(@RequestBody THallJidongche tHallJidongche) throws FebsException {
        System.err.println(tHallJidongche);
        try {
            //
            tHallJidongcheService.updateById(tHallJidongche);
            return new CommonResult(200, "success", "修改成功!", null);
        } catch (Exception e) {
            String message = "修改失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

}