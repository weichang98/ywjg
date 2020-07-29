package com.hjy.hall.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hjy.common.domin.CommonResult;
import com.hjy.common.exception.FebsException;
import com.hjy.common.utils.IDUtils;
import com.hjy.hall.entity.THallQueue;
import com.hjy.hall.entity.THallTakenumber;
import com.hjy.hall.service.THallTakenumberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * (THallTakenumber)表控制层
 *
 * @author makejava
 * @since 2020-07-29 10:28:25
 */
@Slf4j
@RestController
public class THallTakenumberController {
    /**
     * 服务对象
     */
    @Autowired
    private THallTakenumberService tHallTakenumberService;

    /**
     * 1 跳转到新增页面
     */
    @GetMapping(value = "/hall/takenumber/addPage")
    public CommonResult tHallTakenumberAddPage() throws FebsException {
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
     * @param tHallTakenumber 实体对象
     * @return 新增结果
     */
    @PostMapping("/hall/takenumber/add")
    public CommonResult tHallTakenumberAdd(@RequestBody THallTakenumber tHallTakenumber) throws FebsException {
        System.err.println(tHallTakenumber);
        try {
            //
            tHallTakenumberService.insert(tHallTakenumber);
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
    @GetMapping("/hall/takenumber/list")
    public CommonResult tHallTakenumberList() throws FebsException {
        try {
            //
            List<THallTakenumber> tHallTakenumberList = tHallTakenumberService.selectAll();
            System.err.println(tHallTakenumberList);
            return new CommonResult(200, "success", "查询数据成功!", tHallTakenumberList);
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
    @GetMapping("/hall/takenumber/listByEntity")
    public CommonResult tHallTakenumberListByEntity(@RequestBody THallTakenumber tHallTakenumber) throws FebsException {
        try {
            //
            List<THallTakenumber> tHallTakenumberList = tHallTakenumberService.selectAllByEntity(tHallTakenumber);
            System.err.println(tHallTakenumberList);
            return new CommonResult(200, "success", "查询数据成功!", tHallTakenumberList);
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
    @DeleteMapping("/hall/takenumber/del")
    public CommonResult tHallTakenumberDel(@RequestBody String parm) throws FebsException {
        JSONObject jsonObject = JSON.parseObject(parm);
        String idStr = String.valueOf(jsonObject.get("pkTakenumId"));
        try {
            //
            tHallTakenumberService.deleteById(idStr);
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
    @GetMapping("/hall/takenumber/getOne")
    public CommonResult tHallTakenumbergetOne(@RequestBody String parm) throws FebsException {
        JSONObject jsonObject = JSON.parseObject(parm);
        String idStr = String.valueOf(jsonObject.get("pkTakenumId"));
        try {
            //
            THallTakenumber tHallTakenumber = tHallTakenumberService.selectById(idStr);
            System.err.println(tHallTakenumber);
            return new CommonResult(200, "success", "数据获取成功!", tHallTakenumber);
        } catch (Exception e) {
            String message = "数据获取失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    /**
     * 4 修改数据
     *
     * @param tHallTakenumber 实体对象
     * @return 修改结果
     */
    @PutMapping("/hall/takenumber/update")
    public CommonResult tHallTakenumberUpdate(@RequestBody THallTakenumber tHallTakenumber) throws FebsException {
        System.err.println(tHallTakenumber);
        try {
            //
            tHallTakenumberService.updateById(tHallTakenumber);
            return new CommonResult(200, "success", "修改成功!", null);
        } catch (Exception e) {
            String message = "修改失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }



}