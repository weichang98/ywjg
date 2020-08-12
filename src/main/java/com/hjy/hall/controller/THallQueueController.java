package com.hjy.hall.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hjy.common.domin.CommonResult;
import com.hjy.common.exception.FebsException;
import com.hjy.common.utils.IDUtils;
import com.hjy.common.utils.IPUtil;
import com.hjy.common.utils.TokenUtil;
import com.hjy.common.utils.typeTransUtil;
import com.hjy.hall.entity.Statistics;
import com.hjy.hall.entity.THallQueue;
import com.hjy.hall.entity.THallQueueCount;
import com.hjy.hall.entity.THallTakenumber;
import com.hjy.hall.service.THallQueueService;
import com.hjy.hall.service.THallTakenumberService;
import com.hjy.list.entity.TListAgent;
import com.hjy.list.entity.TListInfo;
import com.hjy.list.service.TListAgentService;
import com.hjy.list.service.TListInfoService;
import com.hjy.system.entity.SysToken;
import com.hjy.system.entity.TSysBusinesstype;
import com.hjy.system.entity.TSysWindow;
import com.hjy.system.service.ShiroService;
import com.hjy.system.service.TSysBusinesstypeService;
import com.hjy.system.service.TSysWindowService;
import lombok.extern.slf4j.Slf4j;
import oracle.sql.DATE;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.schema.Model;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

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
    @Autowired
    private TSysWindowService tSysWindowService;
    @Autowired
    private TSysBusinesstypeService tSysBusinesstypeService;
    @Autowired
    private TListAgentService tListAgentService;
    @Autowired
    private TListInfoService tListInfoService;

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
        try {
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
    @RequiresPermissions({"queue:view"})
    @GetMapping("/hall/queue/list")
    public CommonResult tHallQueueList() throws FebsException {
        try {
            List<THallQueue> tHallQueueList = tHallQueueService.selectAll();
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
            List<THallQueue> tHallQueueList = tHallQueueService.selectAllByEntity(tHallQueue);
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
        try {
            tHallQueueService.updateById(tHallQueue);
            return new CommonResult(200, "success", "修改成功!", null);
        } catch (Exception e) {
            String message = "修改失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }


    /**
     * 取号页面
     *
     * @return 跳转结果
     */
    @RequiresPermissions({"takeNumber:view"})
    @GetMapping("/hall/queue/getOrdinal/page")
    public CommonResult getOrdinalpage(HttpServletRequest request) throws FebsException {
        try {
            //获取业务类型
            List<TSysBusinesstype> businesstypes = tSysBusinesstypeService.selectAll();
            List<String> businesstypeList = new ArrayList<>();
            for (TSysBusinesstype obj : businesstypes) {
                businesstypeList.add(obj.getTypeName());
            }
            return new CommonResult(200, "success", "获取业务类型数据成功", businesstypeList);
        } catch (Exception e) {
            String message = "获取业务类型数据失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    /**
     * 取号
     *
     * @return 取号结果
     */
    @PostMapping("/hall/queue/getOrdinal")
    public Map<String, Object> getOrdinal(@RequestBody String param) throws FebsException {
        try {
            Map<String, Object> map = tHallQueueService.getOrdinal(param);
            return map;
        } catch (Exception e) {
            String message = "获取业务类型数据失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    /**
     * 叫号页面
     */
    @RequiresPermissions({"queue:call"})
    @GetMapping("/hall/queue/call/page")
    public CommonResult callPage(HttpServletRequest request) throws FebsException {
        try {
            JSONObject jsonObject = new JSONObject();
            //从token中拿到当前窗口信息
            String tokenStr = TokenUtil.getRequestToken(request);
            SysToken token = tHallQueueService.findByToken(tokenStr);
            String ip = token.getIp();
            TSysWindow window = tHallQueueService.selectWindowByIp(ip);
            jsonObject.put("windowName", window.getWindowName());
            //获取当前窗口可办理的业务类型
            String businesstypeList = window.getBusinessType();
            String[] strings = businesstypeList.split("/");
            jsonObject.put("businessTypes", strings);
            return new CommonResult(200, "success", "获取窗口及业务数据成功", jsonObject);
        } catch (Exception e) {
            String message = "获取窗口及业务数据失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    /**
     * 顺序叫号
     * @return 叫号结果
     */
    @PostMapping("/hall/queue/call")
    public CommonResult call(HttpServletRequest request, HttpSession session) throws FebsException {
        try {
            //从token中拿到当前窗口信息
            String tokenStr = TokenUtil.getRequestToken(request);
            SysToken token = tHallQueueService.findByToken(tokenStr);
            String ip = token.getIp();
            TSysWindow window = tHallQueueService.selectWindowByIp(ip);
            String windowName = window.getWindowName();
            System.err.println("叫号窗口："+windowName);
            //业务方法
            THallQueue queue = tHallQueueService.call(window,session);
            if (queue == null) {
                return new CommonResult(444, "error", "该窗口已无号", null);
            }
            int handleNum = tHallQueueService.handleNum(queue);
            int agentNum = tHallQueueService.agentNum(queue);
            queue.setAgentNum(agentNum);
            queue.setHandleNum(handleNum);
            System.err.println("叫号的办理业务信息：" + queue);
            return new CommonResult(200, "success", windowName + ":" + queue.getOrdinal(), queue);
        } catch (Exception e) {
            String message = "叫号失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    /**
     * 特呼
     *
     * @return 叫号结果
     */
    @PostMapping("/hall/queue/vipCall")
    public CommonResult vipCall(HttpServletRequest request, @RequestBody THallQueue tHallQueue) throws FebsException {
        try {
            //从token中拿到当前窗口信息
            String tokenStr = TokenUtil.getRequestToken(request);
            SysToken token = tHallQueueService.findByToken(tokenStr);
            String ip = token.getIp();
            TSysWindow window = tHallQueueService.selectWindowByIp(ip);
            String windowName = window.getWindowName();
            //判断是否存在输入的号码
            String vip_ordinal = tHallQueue.getOrdinal();
            if (tHallTakenumberService.getByOrdinal(vip_ordinal) == null) {
                return new CommonResult(444, "error", "特呼的号码不存在！", null);
            }
//            if(tHallTakenumberService.getByOrdinal(vip_ordinal).getFlag()==1 ||tHallTakenumberService.getByOrdinal(vip_ordinal).getFlag()==2){
//                return new CommonResult(201, "failed", "特呼的号码正在处理或已办理", null);
//            }
            //业务方法
            THallQueue queue = tHallQueueService.vipCall(window, vip_ordinal);
            int handleNum = tHallQueueService.handleNum(queue);
            int agentNum = tHallQueueService.agentNum(queue);
            queue.setAgentNum(agentNum);
            queue.setHandleNum(handleNum);
            return new CommonResult(200, "success", windowName + "特呼:" + vip_ordinal, queue);
        } catch (Exception e) {
            String message = "叫号失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }


    /**
     * 4 业务时间统计
     *
     * @return 统计结果
     */
    @PostMapping("/hall/queue/StatisticsTime")
    public CommonResult StatisticsTime(@RequestBody THallQueue tHallQueue) throws FebsException {
        try {
            //业务方法
            THallQueueCount tHallQueueCount = tHallQueueService.StatisticsTime(tHallQueue);
            return new CommonResult(200, "success", "查询成功!", tHallQueueCount);
        } catch (Exception e) {
            String message = "查询失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    /**
     * 跳转页面 查询当日办理人员业务量
     *
     * @return 统计结果
     */
    @GetMapping("/hall/queue/StatisticsPage")
    public CommonResult StatisticsNumToday() throws FebsException {
        try {
          THallQueue tHallQueue=new THallQueue();
          tHallQueue.setQueryStart(new Date());
          tHallQueue.setQueryEnd(new Date());
          List<Statistics> statisticsList=tHallQueueService.StatisticsNumMethod(tHallQueue);
            return new CommonResult(200, "success", "查询成功!", statisticsList);
        } catch (Exception e) {
            String message = "查询失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    /**
     * 4 根据时间查询办理人员业务量统计
     *
     * @return 统计结果
     */
    @PostMapping("/hall/queue/StatisticsNum")
    public CommonResult StatisticsNum(@RequestBody THallQueue tHallQueue) throws FebsException {
        try {
            List<Statistics> statisticsList = tHallQueueService.StatisticsNumMethod(tHallQueue);
            return new CommonResult(200, "success", "查询成功!", statisticsList);
        } catch (Exception e) {
            String message = "查询失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    /**
     * 空号
     *
     * @return 空号结果
     */
    @PostMapping("/hall/queue/nullNum")
    public CommonResult nullNum(HttpServletRequest request, HttpSession session) throws FebsException {
        try {
            //从token中拿到当前窗口号
            String tokenStr = TokenUtil.getRequestToken(request);
            SysToken token = tHallQueueService.findByToken(tokenStr);
            String ip = token.getIp();
            TSysWindow window = tHallQueueService.selectWindowByIp(ip);
            //业务方法
            String ordinal = tHallQueueService.nullNum(window, session);
            return new CommonResult(200, "success", "设置空号成功", ordinal);
        } catch (Exception e) {
            String message = "设置空号失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    /**
     * 退号
     *
     * @return 退号结果
     */
    @PostMapping("/hall/queue/backNum")
    public CommonResult backNum(HttpServletRequest request, HttpSession session) throws FebsException {
        try {
            //从token中拿到当前窗口号
            String tokenStr = TokenUtil.getRequestToken(request);
            SysToken token = tHallQueueService.findByToken(tokenStr);
            String ip = token.getIp();
            TSysWindow window = tHallQueueService.selectWindowByIp(ip);
            //业务方法
            String ordinal = tHallQueueService.backNum(window, session);
            return new CommonResult(200, "success", ordinal + "退号", ordinal);
        } catch (Exception e) {
            String message = "退办失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    /**
     * 办结
     *
     * @return 办结结果
     */
    @PostMapping("/hall/queue/downNum")
    public CommonResult downNum(HttpServletRequest request, HttpSession session) throws FebsException {
        try {
            //从token中拿到当前窗口号
            String tokenStr = TokenUtil.getRequestToken(request);
            SysToken token = tHallQueueService.findByToken(tokenStr);
            String ip = token.getIp();
            TSysWindow window = tHallQueueService.selectWindowByIp(ip);
            System.err.println("window" + window);
            String ordinalDown = tHallQueueService.downNum(window, session);
            return new CommonResult(200, "success", ordinalDown + "办结!", ordinalDown);
        } catch (Exception e) {
            String message = "办结失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    /**
     * 大厅等待人数
     * @return 查询结果
     */
    @PostMapping("/hall/queue/nowWaitNum")
    public CommonResult nowWaitNum() throws FebsException {
        try {
            THallTakenumber tHallTakenumber = new THallTakenumber();
            tHallTakenumber.setFlag(0);
            List<THallTakenumber> takenumberList = tHallTakenumberService.selectAllByEntity(tHallTakenumber);
            int num = takenumberList.size();
            return new CommonResult(200, "success", "查询成功!", num);
        } catch (Exception e) {
            String message = "查询失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    /**
     * 查询当日排队信息
     *
     * @return 排队数据
     */
    @GetMapping("/hall/queue/listToday")
    public CommonResult listToday() throws FebsException {
        try {
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            String startStr=sdf.format(new Date());
            String endStr=sdf.format(new Date());
            List<THallQueue> tHallQueueList = tHallQueueService.queryByTime(startStr,endStr);
            return new CommonResult(200, "success", "查询数据成功!", tHallQueueList);
        } catch (Exception e) {
            String message = "查询数据失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

}