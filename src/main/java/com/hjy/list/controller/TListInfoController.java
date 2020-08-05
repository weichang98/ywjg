package com.hjy.list.controller;

import com.hjy.common.domin.CommonResult;
import com.hjy.common.exception.FebsException;
import com.hjy.list.entity.TListInfo;
import com.hjy.list.service.TListInfoService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.List;

/**
 * (TListInfo)表控制层
 *
 * @author liuchun
 * @since 2020-08-05 12:45:38
 */
@Slf4j
@RestController
public class TListInfoController {
    /**
     * 服务对象
     */
    @Autowired
    private TListInfoService tListInfoService;

    /**
     * 1 跳转到新增页面
     */
     @GetMapping(value = "/tListInfo/addPage")
     public CommonResult tListInfoAddPage() throws FebsException {
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
     * @param tListInfo 实体对象
     * @return 新增结果
     */
    @PostMapping("/tListInfo/add")
    public CommonResult tListInfoAdd(@RequestBody TListInfo tListInfo) throws FebsException{
        System.err.println(tListInfo);
        try {
            //
            tListInfoService.insert(tListInfo);
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
    @GetMapping("/tListInfo/list")
    public CommonResult tListInfoList() throws FebsException{
        try {
            //
            List<TListInfo> tListInfoList = tListInfoService.selectAll();
            System.err.println(tListInfoList);
            return new CommonResult(200,"success","查询数据成功!",tListInfoList);
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
    @PostMapping("/tListInfo/listByEntity")
    public CommonResult tListInfoListByEntity(@RequestBody TListInfo tListInfo) throws FebsException{
        try {
            //
            List<TListInfo> tListInfoList = tListInfoService.selectAllByEntity(tListInfo);
            System.err.println(tListInfoList);
            return new CommonResult(200,"success","查询数据成功!",tListInfoList);
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
    @DeleteMapping("/tListInfo/del")
    public CommonResult tListInfoDel(@RequestBody String parm) throws FebsException{
        JSONObject jsonObject = JSON.parseObject(parm);
        String idStr=String.valueOf(jsonObject.get("pk_id"));
        try {
            //
            tListInfoService.deleteById(idStr);
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
    @PostMapping("/tListInfo/getOne")
    public CommonResult tListInfogetOne(@RequestBody String parm) throws FebsException{
        JSONObject jsonObject = JSON.parseObject(parm);
        String idStr=String.valueOf(jsonObject.get("pk_id"));
        try {
            //
            TListInfo tListInfo = tListInfoService.selectById(idStr);
            System.err.println(tListInfo);
            return new CommonResult(200,"success","数据获取成功!",tListInfo);
        } catch (Exception e) {
            String message = "数据获取失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
    
    /**
     * 4 修改数据
     * @param tListInfo 实体对象
     * @return 修改结果
     */
    @PutMapping("/tListInfo/update")
    public CommonResult tListInfoUpdate(@RequestBody TListInfo tListInfo) throws FebsException{
        System.err.println(tListInfo);
        try {
            //
            tListInfoService.updateById(tListInfo);
            return new CommonResult(200,"success","修改成功!",null);
        } catch (Exception e) {
            String message = "修改失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

}