package com.hjy.list.controller;

import com.hjy.common.domin.CommonResult;
import com.hjy.common.exception.FebsException;
import com.hjy.common.utils.page.PageRequest;
import com.hjy.common.utils.page.PageResult;
import com.hjy.list.entity.TListInfo;
import com.hjy.list.service.TListInfoService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hjy.system.entity.ActiveUser;
import lombok.AccessLevel;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
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
     @RequiresPermissions({"list:addPage"})
     @GetMapping(value = "/list/info/addPage")
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
    @RequiresPermissions({"list:add"})
    @PostMapping("/list/info/add")
    public CommonResult tListInfoAdd(TListInfo tListInfo,@RequestParam(value = "file", required = false) MultipartFile[] files,HttpSession session) throws FebsException{
        ActiveUser activeUser = (ActiveUser) session.getAttribute("activeUser");
        tListInfo.setOperator(activeUser.getFullName());
        System.err.println(tListInfo);
        try {
            //
//            tListInfoService.insert(tListInfo);
            tListInfoService.insertFile(tListInfo,files);
            return new CommonResult(200,"success","黑/红名单添加成功!",null);
        } catch (Exception e) {
            String message = "黑/红名单添加失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
    /**
     * 2 查询所有数据
     * @return 所有数据
     */
    @RequiresPermissions({"list:view"})
    @PostMapping("/list/info/list")
    public CommonResult tListInfoList(@RequestBody String param) throws FebsException{
        try {
            //
            PageResult result = tListInfoService.selectAllPage(param);
            System.err.println(result);
            return new CommonResult(200,"success","查询数据成功!",result);
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
    @PostMapping("/list/info/listByEntity")
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
    @RequiresPermissions({"list:del"})
    @DeleteMapping("/list/info/del")
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
    @PostMapping("/list/info/getOne")
    public CommonResult tListInfogetOne(@RequestBody String parm) throws FebsException{
        JSONObject jsonObject = JSON.parseObject(parm);
        String idStr=String.valueOf(jsonObject.get("pk_id"));
        try {
            //
            TListInfo tListInfo = tListInfoService.selectById(idStr);
//            //将文件从本地加载
//            //申请书路径
//            String applyBookPath = tListInfo.getApplyBook();
//            //组织机构代码证
//            String codeCertificates = tListInfo.getCodeCertificates();
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
    @RequiresPermissions({"list:update"})
    @PutMapping("/list/info/update")
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
    /**
     * 5 待审批
     * @return 修改结果
     */
    @RequiresPermissions({"approval:view"})
    @GetMapping("/list/info/waitApproval")
    public CommonResult tListInfoApprovalList() throws FebsException{
        try {
            //
            List<TListInfo> listInfos = tListInfoService.selectWaitApproval();
            return new CommonResult(200,"success","待审批记录获取成功!",listInfos);
        } catch (Exception e) {
            String message = "待审批记录获取失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
    /**
     * 5 审批
     * @param tListInfo 实体对象
     * @return 修改结果
     */
    @RequiresPermissions({"approval:add"})
    @PutMapping("/list/info/approval")
    public CommonResult tListInfoApproval(@RequestBody TListInfo tListInfo, HttpSession session) throws FebsException{
        ActiveUser activeUser = (ActiveUser) session.getAttribute("activeUser");
        tListInfo.setApprovalPeople(activeUser.getFullName());
        System.err.println(tListInfo);
        try {
            //
            tListInfoService.updateById(tListInfo);
            return new CommonResult(200,"success","审批成功!",null);
        } catch (Exception e) {
            String message = "审批失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

}