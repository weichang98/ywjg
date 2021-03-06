package com.hjy.list.controller;

import com.hjy.common.domin.CommonResult;
import com.hjy.common.exception.FebsException;
import com.hjy.list.entity.TListAgent;
import com.hjy.list.service.TListAgentService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hjy.system.entity.ActiveUser;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * (TListAgent)表控制层
 *
 * @author liuchun
 * @since 2020-08-05 12:45:38
 */
@Slf4j
@RestController
public class TListAgentController {
    /**
     * 服务对象
     */
    @Autowired
    private TListAgentService tListAgentService;

    /**
     * 1 跳转到新增页面
     */
     @GetMapping(value = "/agentInfo/addPage")
     public CommonResult tListAgentAddPage() throws FebsException {
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
     * @param tListAgent 实体对象
     * @return 新增结果
     */
    @PostMapping("/agentInfo/add")
    public CommonResult tListAgentAdd(@RequestBody TListAgent tListAgent, HttpSession session) throws FebsException{
        ActiveUser activeUser = (ActiveUser) session.getAttribute("activeUser");
        tListAgent.setAgent(activeUser.getFullName());
        try {
            //
            tListAgentService.insert(tListAgent);
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
    @RequiresPermissions({"agentInfo:view"})
    @GetMapping("/agentInfo/list")
    public CommonResult tListAgentList() throws FebsException{
        try {
            //
            List<TListAgent> tListAgentList = tListAgentService.selectAll();
            return new CommonResult(200,"success","查询数据成功!",tListAgentList);
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
    @PostMapping("/agentInfo/listByEntity")
    public CommonResult tListAgentListByEntity(@RequestBody TListAgent tListAgent) throws FebsException{
        try {
            //
            List<TListAgent> tListAgentList = tListAgentService.selectAllByEntity(tListAgent);
            return new CommonResult(200,"success","查询数据成功!",tListAgentList);
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
    @RequiresPermissions({"agentInfo:view"})
//    @RequiresPermissions({"agentInfo:del"})
    @DeleteMapping("/agentInfo/del")
    public CommonResult tListAgentDel(@RequestBody String parm) throws FebsException{
        JSONObject jsonObject = JSON.parseObject(parm);
        String idStr=String.valueOf(jsonObject.get("pk_id"));
        try {
            //
            tListAgentService.deleteById(idStr);
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
    @PostMapping("/agentInfo/getOne")
    public CommonResult tListAgentgetOne(@RequestBody String parm) throws FebsException{
        JSONObject jsonObject = JSON.parseObject(parm);
        String idStr=String.valueOf(jsonObject.get("pk_id"));
        try {
            //
            TListAgent tListAgent = tListAgentService.selectById(idStr);
            return new CommonResult(200,"success","数据获取成功!",tListAgent);
        } catch (Exception e) {
            String message = "数据获取失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
    
    /**
     * 4 修改数据
     * @param tListAgent 实体对象
     * @return 修改结果
     */
    @RequiresPermissions({"agentInfo:view"})
//    @RequiresPermissions({"agentInfo:update"})
    @PutMapping("/agentInfo/update")
    public CommonResult tListAgentUpdate(@RequestBody TListAgent tListAgent) throws FebsException{
        try {
            //
            tListAgentService.updateById(tListAgent);
            return new CommonResult(200,"success","修改成功!",null);
        } catch (Exception e) {
            String message = "修改失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

}