package com.hjy.hall.service.impl;

import com.alibaba.druid.sql.visitor.functions.If;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hjy.common.domin.CommonResult;
import com.hjy.common.utils.IDUtils;
import com.hjy.common.utils.typeTransUtil;
import com.hjy.hall.dao.THallQueueMapper;
import com.hjy.hall.dao.THallTakenumberMapper;
import com.hjy.hall.entity.Statistics;
import com.hjy.hall.entity.THallQueue;
import com.hjy.hall.entity.THallQueueCount;
import com.hjy.hall.entity.THallTakenumber;
import com.hjy.hall.service.THallQueueService;
import com.hjy.hall.service.THallTakenumberService;
import com.hjy.list.dao.TListAgentMapper;
import com.hjy.list.dao.TListInfoMapper;
import com.hjy.list.entity.TListAgent;
import com.hjy.list.entity.TListInfo;
import com.hjy.system.dao.TSysBusinesstypeMapper;
import com.hjy.system.dao.TSysParamMapper;
import com.hjy.system.dao.TSysTokenMapper;
import com.hjy.system.dao.TSysWindowMapper;
import com.hjy.system.entity.*;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * (THallQueue)表服务实现类
 *
 * @author makejava
 * @since 2020-07-29 14:33:20
 */
@Service
public class THallQueueServiceImpl implements THallQueueService {


    @Autowired
    private THallQueueMapper tHallQueueMapper;
    @Autowired
    private TSysTokenMapper tSysTokenMapper;
    @Autowired
    private THallTakenumberService tHallTakenumberService;
    @Autowired
    private TListInfoMapper tListInfoMapper;
    @Autowired
    private THallTakenumberMapper tHallTakenumberMapper;
    @Autowired
    private TSysWindowMapper tSysWindowMapper;
    @Autowired
    private TSysBusinesstypeMapper businesstypeMapper;
    @Autowired
    private TListAgentMapper tListAgentMapper;
    @Autowired
    private TSysParamMapper tSysParamMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param pkQueueId 主键
     * @return 实例对象
     */
    @Override
    public THallQueue selectById(String pkQueueId) {
        return this.tHallQueueMapper.selectById(pkQueueId);
    }

    /**
     * 新增数据
     *
     * @param tHallQueue 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(THallQueue tHallQueue) {
        return tHallQueueMapper.insertSelective(tHallQueue);
    }

    /**
     * 修改数据
     *
     * @param tHallQueue 实例对象
     * @return 实例对象
     */
    @Override
    public int updateById(THallQueue tHallQueue) {
        return tHallQueueMapper.updateById(tHallQueue);
    }

    /**
     * 通过主键删除数据
     *
     * @param pkQueueId 主键
     * @return 是否成功
     */
    @Override
    public int deleteById(String pkQueueId) {
        return tHallQueueMapper.deleteById(pkQueueId);
    }

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    @Override
    public List<THallQueue> selectAll() {
        return this.tHallQueueMapper.selectAll();
    }

    /**
     * 通过实体查询多条数据
     *
     * @return 对象列表
     */
    @Override
    public List<THallQueue> selectAllByEntity(THallQueue tHallQueue) {
        return this.tHallQueueMapper.selectAllByEntity(tHallQueue);
    }

    @Override
    public List<THallQueue> queryByTime(String startTime, String endTime) {
        return this.tHallQueueMapper.queryByTime(startTime, endTime);
    }

    @Override
    public THallQueue getByOrdinalAndDatestr(String Ordinal, String DateStr) {
        return this.tHallQueueMapper.getByOrdinalAndDatestr(Ordinal, DateStr);
    }

    @Override
    public THallQueue getNowNum(String windowName) {
        return this.tHallQueueMapper.getNowNum(windowName);
    }

    @Override
    public List<THallQueueCount> totalCount(String queryStart, String queryEnd) {
        return this.tHallQueueMapper.totalCount(queryStart, queryEnd);
    }

    @Override
    public List<THallQueueCount> backCount(String queryStart, String queryEnd) {
        return this.tHallQueueMapper.backCount(queryStart, queryEnd);
    }

    @Override
    public List<THallQueueCount> nullCount(String queryStart, String queryEnd) {
        return this.tHallQueueMapper.nullCount(queryStart, queryEnd);
    }

    @Override
    public List<THallQueueCount> realCount(String queryStart, String queryEnd) {
        return this.tHallQueueMapper.realCount(queryStart, queryEnd);
    }

    @Override
    public SysToken findByToken(String accessToken) {
        return tSysTokenMapper.findByToken(accessToken);
    }

    @Override
    public List<Statistics> StatisticsNumMethod(THallQueue tHallQueue) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date queryStart = tHallQueue.getQueryStart();
        Date queryEnd = tHallQueue.getQueryEnd();
        String queryStartStr = sdf.format(queryStart);
        String queryEndStr = sdf.format(queryEnd);
        //总业务量
        List<THallQueueCount> totalList = this.totalCount(queryStartStr, queryEndStr);
        //实际业务量统计
        List<THallQueueCount> realList = this.realCount(queryStartStr, queryEndStr);
        //空号统计
        List<THallQueueCount> nullList = this.nullCount(queryStartStr, queryEndStr);
        //退办统计
        List<THallQueueCount> backLsit = this.backCount(queryStartStr, queryEndStr);
        List<Statistics> statisticsList = new ArrayList<>();

        HashMap<String, Integer> realmap = new HashMap();
        for (THallQueueCount realSingle : realList) {
            String agent = realSingle.getAgent();
            String businessType = typeTransUtil.typeTrans(realSingle.getBusinessType()).get(0);
            realSingle.setBusinessType(businessType);
            if (realmap.containsKey(agent) == false) {
                realmap.put(agent, null);
                Statistics statistics = new Statistics();
                statistics.setAgent(agent);
                //通过反射的invoke方法动态调用对应的方法
                Class<?> clazz = Statistics.class;
                Method method = clazz.getMethod("set" + businessType, Integer.class);
                method.invoke(statistics, realSingle.getRealCount());
                statisticsList.add(statistics);
            } else {
                for (Statistics statistics : statisticsList) {
                    if (agent.equals(statistics.getAgent())) {
                        Class<?> clazz = Statistics.class;
                        Method method = clazz.getMethod("set" + businessType, Integer.class);
                        method.invoke(statistics, realSingle.getRealCount());
                    }
                }
            }
        }
        for (THallQueueCount totalSingle : totalList) {
            String agent = totalSingle.getAgent();
            for (Statistics statistics : statisticsList) {
                if (agent.equals(statistics.getAgent())) {
                    statistics.setTotalNum(totalSingle.getTotalCount());
                }
            }
        }
        for (THallQueueCount nullSingle : nullList) {
            String agent = nullSingle.getAgent();
            for (Statistics statistics : statisticsList) {
                if (agent.equals(statistics.getAgent())) {
                    statistics.setNullNum(nullSingle.getNullCount());
                }
            }
        }
        return statisticsList;
    }



    @Override
    public THallQueueCount StatisticsTime(THallQueue tHallQueue) throws Exception {
        Date queryEnd = tHallQueue.getQueryEnd();
//        将endTime日期加1便于数据库统计
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(queryEnd);
        calendar.add(calendar.DATE, 1);//日期向后+1天，整数往后推，负数向前推
        queryEnd = calendar.getTime();//这个时间就是日期向后推一天的结果
        tHallQueue.setQueryEnd(queryEnd);
        List<THallQueue> queueList = this.selectAllByEntity(tHallQueue);
        //业务统计
        long TotalWaitTime = 0;//查询信息中总的等待时间
        long TotalServiceTime = 0;//总的办理时间
        int mark = 0;//mark为业务办理已办结的数据数量
        int nullMark = 0;//nullMark为空号次数
        int backMark = 0;//退号次数
        for (THallQueue queueSingle : queueList) {
            if (queueSingle.getEndTime() == null && queueSingle.getRemarks() == null) {
                //结束时间与备注均为空，则该号正在办理
                continue;
            }
            if (queueSingle.getEndTime() == null && "退号".equals(queueSingle.getRemarks())) {
                backMark++;
                continue;
            }
            if (queueSingle.getEndTime() == null && "空号".equals(queueSingle.getRemarks())) {
                nullMark++;
                continue;
            }
            mark++;
            Date startTime = queueSingle.getStartTime();
            Date endTime = queueSingle.getEndTime();
            Date getTime = queueSingle.getGetTime();

            long SingleServiceTime = endTime.getTime() - startTime.getTime();
            long SingleWaitTime = startTime.getTime() - getTime.getTime();
            TotalWaitTime += SingleWaitTime;
            TotalServiceTime += SingleServiceTime;
        }
        if(mark==0){
            return  null;
        }
        long avgWaitTime = (TotalWaitTime / mark) / 1000;//算出的long类型除以1000则为实际秒数
        long avgServiceTime = (TotalServiceTime / mark) / 1000;
        System.out.print("总等待时间为：" + TotalWaitTime / 1000 + "秒，平均等待时间为:" + avgWaitTime + "秒");
        THallQueueCount tHallQueueCount = new THallQueueCount();
        tHallQueueCount.setQueueList(queueList);
        tHallQueueCount.setAvgWaitTime(avgWaitTime);
        tHallQueueCount.setAvgServiceTime(avgServiceTime);
        tHallQueueCount.setTotalServiceNum(queueList.size());
        tHallQueueCount.setNullMark(nullMark);
        tHallQueueCount.setBackMark(backMark);
        System.err.print("排队信息数据：" + queueList + ",群众平均等待时间为" + avgWaitTime + "秒,约" + avgWaitTime / 60 + "分,平均业务办理时间为" + avgServiceTime +
                "秒,前台受理总业务量为" + queueList.size() + "次,空号次数为" + nullMark + "次,退号次数为" + backMark + "次");

        return tHallQueueCount;
    }


    @Override
    public Map<String ,Object> downNum(TSysWindow window, String param, HttpSession session) {
        JSONObject jsonObject = JSON.parseObject(param);
        Map<String ,Object> map = new HashMap<>();
        String whether = String.valueOf(jsonObject.get("whether"));
        int agentNum = Integer.parseInt(String.valueOf(jsonObject.get("agentNum")));
        //查询当前窗口正在办理的业务
        THallQueue nowQueue = this.getNowNum(window.getWindowName());
        if (nowQueue == null) {
            map.put("code", 444);
            map.put("status", "error");
            map.put("msg", "该窗口无号可办结！");
            return map;
        }
        nowQueue.setRemarks("办结");
        nowQueue.setEndTime(new Date());
        try{
            this.updateById(nowQueue);
            //更新取号表状态flag
            String ordinal = nowQueue.getOrdinal();
            THallTakenumber tnum = tHallTakenumberService.getByOrdinal(ordinal);
            tnum.setFlag(2);
            tnum.setOrdinal(ordinal);
            tHallTakenumberService.updateById(tnum);
            map.put("code", 200);
            map.put("status", "success");
            map.put("msg", ordinal+"号办结成功！");
            map.put("data", "下一张号码：");
        }catch (Exception e){
            map.put("code", 445);
            map.put("status", "error");
            map.put("msg", "系统内部异常，办结失败！");
            return map;
        }
        //是否录入代办信息
        if(nowQueue.getAIdcard()== null ||nowQueue.getAIdcard().equals("")||nowQueue.getAIdcard().equals("null")){
            //办理的为本人业务，不录入
        }else {
            TListAgent agent = new TListAgent();
            agent.setPkAgentId(IDUtils.currentTimeMillis());
            agent.setBusinessType(nowQueue.getBusinessType());
            agent.setACertificatesType(nowQueue.getACertificatesType());
            agent.setAName(nowQueue.getAName());
            agent.setAIdcard(nowQueue.getAIdcard());
            agent.setBCertificatesType(nowQueue.getBCertificatesType());
            agent.setBName(nowQueue.getBName());
            agent.setBIdcard(nowQueue.getBIdcard());
            agent.setAddTime(new Date());
            agent.setAgent(nowQueue.getAgent());
            try{
                tListAgentMapper.insertSelective(agent);
            }catch (Exception e){
                map.put("code", 446);
                map.put("status", "error");
                map.put("msg", "录入代办信息异常，办结成功！");
                return map;
            }
            //是否自动录入黑名单
            String tSysParam = tSysParamMapper.selectParamById("JRHMDDBCSXZ");
            int maxNum = Integer.parseInt(tSysParam);
            if(agentNum >= maxNum){
                TListInfo blackList = new TListInfo();
                blackList.setListType("黑名单");
                blackList.setFullName(nowQueue.getAName());
                blackList.setIdCard(nowQueue.getAIdcard());
                blackList.setExplain("代办次数过多");
                blackList.setReason("代办次数过多");
                blackList.setWhetherPass("通过");
                blackList.setApprovalPeople("系统添加");
                blackList.setOperator("系统");
                blackList.setCreateTime(new Date());
                blackList.setApprovalTime(new Date());
                try{
                    tListInfoMapper.insertSelective(blackList);
                    return map;
                }catch (Exception e){
                    map.put("code", 447);
                    map.put("status", "error");
                    map.put("msg", "录入黑名单异常，办结成功！");
                    return map;
                }
            }
        }
        return map;
    }

    @Override
    public String backNum(TSysWindow window, HttpSession session) {
        //查询当前窗口正在办理的业务
        THallQueue nowQueue = this.getNowNum(window.getWindowName());
        if (nowQueue == null) {
            String message = "已无号";
            return message;
        }
        nowQueue.setRemarks("退号");
        this.updateById(nowQueue);
        //更新取号表状态flag
        String ordinal = nowQueue.getOrdinal();
        THallTakenumber tnum = tHallTakenumberService.getByOrdinal(ordinal);
        tnum.setFlag(2);
        tnum.setOrdinal(ordinal);
        tHallTakenumberService.updateById(tnum);
        return ordinal;
    }

    @Override
    public String nullNum(TSysWindow window, HttpSession session) {
        //查询当前窗口正在办理的业务
        THallQueue nowQueue = this.getNowNum(window.getWindowName());
        if (nowQueue == null) {
            String message = "已无号";
            return message;
        }
        nowQueue.setRemarks("空号");
        this.updateById(nowQueue);
        //更新取号表状态flag
        String ordinal = nowQueue.getOrdinal();
        THallTakenumber tnum = tHallTakenumberService.getByOrdinal(ordinal);
        tnum.setFlag(2);
        tnum.setOrdinal(ordinal);
        tHallTakenumberService.updateById(tnum);
        return ordinal;
    }

    @Override
    public int handleNum(THallQueue tHallQueue) {
        return this.tHallQueueMapper.handleNum(tHallQueue);
    }

    @Override
    public int agentNum(THallQueue tHallQueue) {
        return this.tHallQueueMapper.agentNum(tHallQueue);
    }

    @Override
    public THallQueue vipCall(TSysWindow window, String vip_ordinal) throws Exception {
        String agent = window.getOperatorPeople();
        String windowName = window.getWindowName();
        String businessType = window.getBusinessType();
        List<String> typeList = typeTransUtil.typeTrans(businessType);//通过此工具类可以将该窗口可办理的业务类型转化为字母显示且已经排序的List集合

        //*********处理取号表的flag标识
        THallTakenumber tHallTakenumber = tHallTakenumberService.getByOrdinal(vip_ordinal);
        tHallTakenumber.setFlag(1);
        tHallTakenumberService.updateById(tHallTakenumber);
        //******更新排队信息表
        Date date = new Date();//通过当前日期和序号拿到当前号的排队信息
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = formatter.format(date);//format()方法bai将Date转换成指定格式的String
        THallQueue queueVip = this.getByOrdinalAndDatestr(vip_ordinal, dateStr);
        //需要将endtime置空，queueVIP.setEndTime(null)无用，故新建个对象
        THallQueue queueinsert = new THallQueue();
        queueinsert.setPkQueueId(IDUtils.currentTimeMillis());
        queueinsert.setOrdinal(queueVip.getOrdinal());
        queueinsert.setWindowName(queueVip.getWindowName());
        queueinsert.setAgent(queueVip.getAgent());
        queueinsert.setBusinessType(queueVip.getBusinessType());
        if (queueVip.getAIdcard() != null) {
            queueinsert.setAIdcard(queueVip.getAIdcard());
            queueinsert.setAName(queueVip.getAName());
            queueinsert.setACertificatesType(queueVip.getACertificatesType());
        }
        queueinsert.setBIdcard(queueVip.getBIdcard());
        queueinsert.setBName(queueVip.getBName());
        queueinsert.setBCertificatesType(queueVip.getBCertificatesType());
        queueinsert.setGetTime(queueVip.getGetTime());
        queueinsert.setStartTime(new Date());
        queueinsert.setRemarks("特呼");
        queueinsert.setIsVip(1);
        this.insert(queueinsert);
        return queueinsert;
    }

    @Override
    public Map<String, Object> getOrdinal(String param) throws Exception {
        Map<String, Object> map = new HashMap<>();
        THallQueue tHallQueue = new THallQueue();
        TListInfo tListInfoB = new TListInfo();
        JSONObject jsonObject = JSON.parseObject(param);
        String businessType = String.valueOf(jsonObject.get("businessType"));
        if (businessType == null || businessType.equals("")) {
            map.put("code", 447);
            map.put("status", "error");
            map.put("msg", "您还没有选择办理业务！");
            return map;
        }
        String bCertificatesType = String.valueOf(jsonObject.get("bCertificatesType"));
        String bName = String.valueOf(jsonObject.get("bName"));
        String bIdCard = String.valueOf(jsonObject.get("bIdCard"));
        String isAgent = String.valueOf(jsonObject.get("isAgent"));
        //查询代理次数
        int handleNum = tHallQueueMapper.handleNum(tHallQueue);
        int agentNum = tHallQueueMapper.agentNum(tHallQueue);
        tHallQueue.setHandleNum(handleNum);
        tHallQueue.setAgentNum(agentNum);
        //本人信息
        tHallQueue.setBIdcard(bIdCard);
        tHallQueue.setBusinessType(businessType);
        tHallQueue.setBCertificatesType(bCertificatesType);
        tHallQueue.setBCertificatesType(bCertificatesType);
        tHallQueue.setBName(bName);
        //本人业务
        if (isAgent.equals("1")) {
            //查询办理本人是否在黑名单中
            tListInfoB.setIdCard(bIdCard);
            TListInfo infoB = tListInfoMapper.selectByIdCard(bIdCard);
            if (infoB != null) {
                map.put("code", 444);
                map.put("status", "error");
                map.put("msg", "办理本人在黑名单中！不予取号");
                return map;
            }
        } else {
            //代理业务
            String aIdcard = String.valueOf(jsonObject.get("aIdCard"));
            String aName = String.valueOf(jsonObject.get("aName"));
            String aCertificatesType = String.valueOf(jsonObject.get("aCertificatesType"));
            TListInfo tListInfoA = new TListInfo();
            tListInfoA.setIdCard(aIdcard);
            //查询代办人是否在黑名单中
            TListInfo infoA = tListInfoMapper.selectByIdCard(aIdcard);
            if (infoA != null) {
                map.put("code", 445);
                map.put("status", "error");
                map.put("msg", "该代办人在黑名单中！不予取号");
                return map;
            }
            if (agentNum >= 4) {
                map.put("code", 446);
                map.put("status", "error");
                map.put("msg", "该代办人代次数超过5次！不予取号");
                return map;
            }
            //代办人信息
            tHallQueue.setAName(aName);
            tHallQueue.setAIdcard(aIdcard);
            tHallQueue.setACertificatesType(aCertificatesType);
        }
        //开始取号
        //调用取号工具将取号结果放入ordinalQueue
        //序号
        int ordinal_num = tHallTakenumberMapper.count() + 1;
        //拿到该业务类型的标识
        List<TSysBusinesstype> typeList = businesstypeMapper.selectAll();
        String type = null;
        if (typeList != null) {
            for (TSysBusinesstype obj : typeList) {
                if (obj.getTypeName().equals(businessType)) {
                    type = obj.getTypeLevel();
                }
            }
        }
        String ordinal = "";
        if (ordinal_num < 10) {
            ordinal = type + "00" + ordinal_num;
        } else if (ordinal_num <= 99) {
            ordinal = type + "0" + ordinal_num;
        } else {
            ordinal = type + ordinal_num;
        }
        THallTakenumber takenumber = new THallTakenumber();
        takenumber.setPkTakenumId(IDUtils.currentTimeMillis());
        takenumber.setOrdinal(ordinal);
        takenumber.setFlag(0);
        takenumber.setGetTime(new Date());
        tHallTakenumberMapper.insertSelective(takenumber);
        //*****取号**************************************
        //*****存储排队信息*******
        tHallQueue.setGetTime(new Date());
        tHallQueue.setOrdinal(ordinal);
        tHallQueue.setIsVip(0);
        tHallQueue.setPkQueueId(IDUtils.currentTimeMillis());
        tHallQueueMapper.insertSelective(tHallQueue);
        map.put("code", 200);
        map.put("status", "success");
        map.put("msg", "取号成功");
        map.put("ordinalQueue", tHallQueue);
        return map;
    }

    @Override
    public String selectWindowNameByIp(String ip) {
        return tSysWindowMapper.selectWindowNameByIp(ip);
    }

    @Override
    public TSysWindow selectWindowByIp(String ip) {
        return tSysWindowMapper.selectWindowByIp(ip);
    }

    @Override
    public THallQueue call(TSysWindow window,HttpSession session) throws Exception {
        ActiveUser activeUser = (ActiveUser) session.getAttribute("activeUser");
        String agent=activeUser.getFullName();
//        String agent=window.getOperatorPeople();
        String windowName = window.getWindowName();
        String businessType = window.getBusinessType();
        List<String> typeList = typeTransUtil.typeTrans(businessType);//通过此工具类可以将该窗口可办理的业务类型转化为字母显示且已经排序的List集合
        //*****判断该窗口所办理的业务类型是否有号
        String type = "";//type设为实际应该叫号的字母式业务类型
        int mark = 0;//mark为判断该窗口所办理的业务类型是否有号的标识
        for (String typeSingle : typeList) {//通过foreach判断是否有号，如果typeList所包含的所有业务类型都在数据库中查询不到号码,则该窗口已无号可办,即typeList.size()= mark
            if (tHallTakenumberService.queryNumList(typeSingle) != null) {
                type = tHallTakenumberService.queryNumList(typeSingle);
                break;
            } else {
                mark++;
            }
        }
        if (typeList.size() == mark) {
            return null;
        }
        //*********取得应叫的号码
        String ordinal = tHallTakenumberService.queryNumList(type);
        System.err.println("叫号：呼叫的号码是:" + ordinal);
        //*********处理取号表的flag标识
        THallTakenumber tHallTakenumber = tHallTakenumberService.getByOrdinal(ordinal);
        tHallTakenumber.setFlag(1);
        tHallTakenumberService.updateById(tHallTakenumber);
        //******更新排队信息表
        THallQueue queueUpdate = this.getCallNum(ordinal);
        queueUpdate.setStartTime(new Date());
        queueUpdate.setWindowName(windowName);
        queueUpdate.setAgent(agent);
        this.updateById(queueUpdate);
        return queueUpdate;
    }

    @Override
    public Date Datepush(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(calendar.DATE, 1);//日期向后+1天，整数往后推，负数向前推
        date = calendar.getTime();//这个时间就是日期向后推一天的结果
        return date;
    }

    @Override
    public THallQueue getCallNum(String ordinal) {
        return tHallQueueMapper.getCallNum(ordinal);
    }

    @Override
    public List<THallQueueCount> windowNumToday(String startTimeStr, String endTimeStr) {
        return tHallQueueMapper.windowNumToday(startTimeStr,endTimeStr);
    }

    @Override
    public List<THallQueueCount> agentNumToday(String startTimeStr, String endTimeStr,int overTime) {
        return tHallQueueMapper.agentNumToday(startTimeStr,endTimeStr,overTime);
    }
}