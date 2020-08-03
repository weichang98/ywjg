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

import java.text.SimpleDateFormat;
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
            List<String> type=typeTransUtil.typeTrans(tHallQueue.getBusinessType());
            String ordinal=type.get(0)+ordinal_num;
            THallTakenumber takenumber=new THallTakenumber();
            takenumber.setPkTakenumId(IDUtils.currentTimeMillis());
            takenumber.setOrdinal(ordinal);
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
     *
     * @return 叫号结果
     */
    @PostMapping("/hall/queue/call")
    public CommonResult call() throws FebsException {
        try {
            //*****判断当前窗口业务类型，通过token拿到当前窗口名称、工作人员姓名及可办理的业务类型********
               String agent="工作人员小刘";
               String window="2号窗口";
               String businessType="三平台专用/满分、恢复驾驶证资格考试预约";
            List<String> typeList=typeTransUtil.typeTrans(businessType);//通过此工具类可以将该窗口可办理的业务类型转化为字母显示且已经排序的List集合

            //*****判断该窗口所办理的业务类型是否有号
            String type="";//type设为实际应该叫号的字母式业务类型
            int mark=0;//mark为判断该窗口所办理的业务类型是否有号的标识

            for(String typeSingle : typeList){//通过foreach判断是否有号，如果typeList所包含的所有业务类型都在数据库中查询不到号码,则该窗口已无号可办,即typeList.size()= mark
                if(tHallTakenumberService.queryNumList(typeSingle)!=null) {
                    type=tHallTakenumberService.queryNumList(typeSingle);
                    break;
                }else{
                    mark++;
                }
            }
            if(typeList.size()==mark){
                return new CommonResult(200, "success", "该窗口办理业务类型已无号!", "该窗口办理业务类型已无号!");
            }

                //*********取得应叫的号码
                String num = tHallTakenumberService.queryNumList(type);
                System.err.print("呼叫的号码是:"+num);
                //*********取得应叫的号码

                //*********处理取号表的flag标识
                THallTakenumber tHallTakenumber=tHallTakenumberService.getByOrdinal(num);
               tHallTakenumber.setFlag(1);
               tHallTakenumberService.updateById(tHallTakenumber);

                //*********处理取号表的flag标识


                //******更新排队信息表
            Date date = new Date();//通过当前日期和序号拿到当前号的排队信息
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateStr = formatter.format(date);//format()方法bai将Date转换成指定格式的String
            THallQueue queueUpdate=tHallQueueService.getByOrdinalAndDatestr(num,dateStr);
            queueUpdate.setStartTime(new Date());
            queueUpdate.setWindowName(window);
            queueUpdate.setAgent(agent);
            tHallQueueService.updateById(queueUpdate);
                //******更新排队信息表
                return new CommonResult(200, "success", "叫号成功!", "请" + num + "到" + window + "进行业务办理");
        } catch (Exception e) {
            String message = "叫号失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }




}