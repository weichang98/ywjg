package com.hjy.synthetical.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hjy.common.domin.CommonResult;
import com.hjy.common.exception.FebsException;
import com.hjy.synthetical.entity.THallMakecard;
import com.hjy.synthetical.service.THallMakecardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * (THallMakecard)表控制层
 *
 * @author makejava
 * @since 2020-08-17 09:53:43
 */
@Slf4j
@RestController
public class THallMakecardController {
    /**
     * 服务对象
     */
    @Autowired
    private THallMakecardService tHallMakecardService;

    /**
     * 1 跳转到新增页面
     */
    @GetMapping(value = "/tHallMakecard/addPage")
    public CommonResult tHallMakecardAddPage() throws FebsException {
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
     * @param tHallMakecard 实体对象
     * @return 新增结果
     */
    @PostMapping("/tHallMakecard/add")
    public CommonResult tHallMakecardAdd(@RequestBody THallMakecard tHallMakecard) throws FebsException {
        System.err.println(tHallMakecard);
        try {
            //
            tHallMakecardService.insert(tHallMakecard);
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
    @GetMapping("/tHallMakecard/list")
    public CommonResult tHallMakecardList() throws FebsException {
        try {
            //
            List<THallMakecard> tHallMakecardList = tHallMakecardService.selectAll();
            System.err.println(tHallMakecardList);
            return new CommonResult(200, "success", "查询数据成功!", tHallMakecardList);
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
    @GetMapping("/tHallMakecard/listByEntity")
    public CommonResult tHallMakecardListByEntity(@RequestBody THallMakecard tHallMakecard) throws FebsException {
        try {
            //
            List<THallMakecard> tHallMakecardList = tHallMakecardService.selectAllByEntity(tHallMakecard);
            System.err.println(tHallMakecardList);
            return new CommonResult(200, "success", "查询数据成功!", tHallMakecardList);
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
    @DeleteMapping("/tHallMakecard/del")
    public CommonResult tHallMakecardDel(@RequestBody String parm) throws FebsException {
        JSONObject jsonObject = JSON.parseObject(parm);
        String idStr = String.valueOf(jsonObject.get("pk_card_id"));
        try {
            //
            tHallMakecardService.deleteById(idStr);
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
    @GetMapping("/tHallMakecard/getOne")
    public CommonResult tHallMakecardgetOne(@RequestBody String parm) throws FebsException {
        JSONObject jsonObject = JSON.parseObject(parm);
        String idStr = String.valueOf(jsonObject.get("pk_card_id"));
        try {
            //
            THallMakecard tHallMakecard = tHallMakecardService.selectById(idStr);
            System.err.println(tHallMakecard);
            return new CommonResult(200, "success", "数据获取成功!", tHallMakecard);
        } catch (Exception e) {
            String message = "数据获取失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    /**
     * 4 修改数据
     *
     * @param tHallMakecard 实体对象
     * @return 修改结果
     */
    @PutMapping("/tHallMakecard/update")
    public CommonResult tHallMakecardUpdate(@RequestBody THallMakecard tHallMakecard) throws FebsException {
        System.err.println(tHallMakecard);
        try {
            //
            tHallMakecardService.updateById(tHallMakecard);
            return new CommonResult(200, "success", "修改成功!", null);
        } catch (Exception e) {
            String message = "修改失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

}