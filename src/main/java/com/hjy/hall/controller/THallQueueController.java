package com.hjy.hall.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hjy.common.domin.CommonResult;
import com.hjy.common.exception.FebsException;
import com.hjy.common.utils.IDUtils;
import com.hjy.common.utils.typeTransUtil;
import com.hjy.hall.entity.THallQueue;
import com.hjy.hall.entity.THallTakenumber;
import com.hjy.hall.service.THallQueueService;
import com.hjy.hall.service.THallTakenumberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * (THallQueue)表控制层
 *
 * @author makejava
 * @since 2020-07-29 14:33:19
 */
@Slf4j
@RestController
public class THallQueueController {
    /**
     * 服务对象
     */
    @Autowired
    private THallQueueService tHallQueueService;
    @Autowired
    private THallTakenumberService tHallTakenumberService;

    /**
     * 1 跳转到新增页面
     */
    @GetMapping(value = "/hall/queue/addPage")
    public CommonResult tHallQueueAddPage() throws FebsException {
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
     * @param tHallQueue 实体对象
     * @return 新增结果
     */
    @PostMapping("/hall/queue/add")
    public CommonResult tHallQueueAdd(@RequestBody THallQueue tHallQueue) throws FebsException {
        System.err.println(tHallQueue);
        try {
            //
            tHallQueueService.insert(tHallQueue);
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
    @GetMapping("/hall/queue/list")
    public CommonResult tHallQueueList() throws FebsException {
        try {
            //
            List<THallQueue> tHallQueueList = tHallQueueService.selectAll();
            System.err.println(tHallQueueList);
            return new CommonResult(200, "success", "查询数据成功!", tHallQueueList);
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
    @GetMapping("/hall/queue/listByEntity")
    public CommonResult tHallQueueListByEntity(@RequestBody THallQueue tHallQueue) throws FebsException {
        try {
            //
            List<THallQueue> tHallQueueList = tHallQueueService.selectAllByEntity(tHallQueue);
            System.err.println(tHallQueueList);
            return new CommonResult(200, "success", "查询数据成功!", tHallQueueList);
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
    @DeleteMapping("/hall/queue/del")
    public CommonResult tHallQueueDel(@RequestBody String parm) throws FebsException {
        JSONObject jsonObject = JSON.parseObject(parm);
        String idStr = String.valueOf(jsonObject.get("pkQueueId"));
        try {
            //
            tHallQueueService.deleteById(idStr);
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
    @GetMapping("/hall/queue/getOne")
    public CommonResult tHallQueuegetOne(@RequestBody String parm) throws FebsException {
        JSONObject jsonObject = JSON.parseObject(parm);
        String idStr = String.valueOf(jsonObject.get("pkQueueId"));
        try {
            //
            THallQueue tHallQueue = tHallQueueService.selectById(idStr);
            System.err.println(tHallQueue);
            return new CommonResult(200, "success", "数据获取成功!", tHallQueue);
        } catch (Exception e) {
            String message = "数据获取失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    /**
     * 4 修改数据
     *
     * @param tHallQueue 实体对象
     * @return 修改结果
     */
    @PutMapping("/hall/queue/update")
    public CommonResult tHallQueueUpdate(@RequestBody THallQueue tHallQueue) throws FebsException {
        System.err.println(tHallQueue);
        try {
            //
            tHallQueueService.updateById(tHallQueue);
            return new CommonResult(200, "success", "修改成功!", null);
        } catch (Exception e) {
            String message = "修改失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }


    /**
     *  取号
     *
     *
     * @return 取号结果
     */
    @PostMapping("/hall/queue/getOrdinal")
    public CommonResult getOrdinal(@RequestBody THallQueue tHallQueue) throws FebsException {

        try {
            //*****取号**************************************
            int ordinal_num=tHallTakenumberService.count()+1;
            String type=typeTransUtil.typeTrans(tHallQueue.getBusinessType());
            String ordinal=type+ordinal_num;
            THallTakenumber takenumber=new THallTakenumber();
            takenumber.setPkTakenumId(IDUtils.currentTimeMillis());
            takenumber.setOrdinal(ordinal);
//            if(判断条件){
//                takenumber.setIsVip(1);
//            }else{
//                takenumber.setIsVip(0);
//            }
            takenumber.setIsVip(0);
            takenumber.setFlag(0);
            takenumber.setGetTime(new Date());
            tHallTakenumberService.insert(takenumber);
            System.err.println("您的号码是:"+ordinal+"号!");
            //*****取号**************************************

            //*****存储排队信息*******
            tHallQueue.setGetTime(new Date());
            tHallQueue.setOrdinal(ordinal);
            tHallQueue.setPkQueueId(IDUtils.currentTimeMillis());
            tHallQueueService.insert(tHallQueue);
            //*****存储排队信息*******

            return new CommonResult(200, "success", "取号成功!", "您的号码是:"+ordinal);
        } catch (Exception e) {
            String message = "取号失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    /**
     * 顺序叫号
     *
     * @param tHallQueue 实体对象
     * @return 叫号结果
     */
    @PutMapping("/hall/queue/call")
    public CommonResult call(@RequestBody THallQueue tHallQueue) throws FebsException {
        System.err.println(tHallQueue);
        try {
            //
            return new CommonResult(200, "success", "叫号成功", null);
        } catch (Exception e) {
            String message = "叫号失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }




}