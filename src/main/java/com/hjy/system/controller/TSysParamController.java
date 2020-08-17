package com.hjy.system.controller;

import com.hjy.common.domin.CommonResult;
import com.hjy.common.exception.FebsException;
import com.hjy.system.entity.TSysParam;
import com.hjy.system.service.TSysParamService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * (TSysParam)表控制层
 *
 * @author liuchun
 * @since 2020-08-11 15:51:59
 */
@Slf4j
@RestController
public class TSysParamController {
    /**
     * 服务对象
     */
    @Autowired
    private TSysParamService tSysParamService;

    /**
     * 2 查询所有数据
     * @return 所有数据
     */

    @GetMapping("/system/param/list")
    public CommonResult tSysParamList() throws FebsException{
        try {
            //
            List<TSysParam> tSysParamList = tSysParamService.selectAll();
            return new CommonResult(200,"success","查询数据成功!",tSysParamList);
        } catch (Exception e) {
            String message = "查询数据失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    /**
     * 4 修改数据
     * @param tSysParam 实体对象
     * @return 修改结果
     */
    @PutMapping("/system/param/update")
    public CommonResult tSysParamUpdate(@RequestBody TSysParam tSysParam, HttpSession session) throws FebsException{
        try {
            //
            tSysParamService.updateById(tSysParam,session);
            return new CommonResult(200,"success","修改成功!",null);
        } catch (Exception e) {
            String message = "修改失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

}