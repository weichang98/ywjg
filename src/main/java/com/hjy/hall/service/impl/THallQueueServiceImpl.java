package com.hjy.hall.service.impl;

import com.alibaba.druid.sql.visitor.functions.If;
import com.hjy.common.domin.CommonResult;
import com.hjy.common.utils.typeTransUtil;
import com.hjy.hall.dao.THallQueueMapper;
import com.hjy.hall.entity.THallQueue;
import com.hjy.hall.entity.THallQueueCount;
import com.hjy.hall.entity.THallTakenumber;
import com.hjy.hall.service.THallQueueService;
import com.hjy.hall.service.THallTakenumberService;
import com.hjy.system.dao.TSysTokenMapper;
import com.hjy.system.entity.SysToken;
import com.hjy.system.entity.TSysWindow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
    public THallQueue getNowNum(String windowName, String nowDateStr) {
        return this.tHallQueueMapper.getNowNum(windowName, nowDateStr);
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
    public THallQueueCount StatisticsTime(THallQueue tHallQueue) {
        Date queryEnd = tHallQueue.getQueryEnd();
        //将endTime日期加1便于数据库统计
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
            if (queueSingle.getEndTime() == null && "退办".equals(queueSingle.getRemarks())) {
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
    public String downNum(TSysWindow window) {
        //查询当前窗口正在办理的业务
        Date nowDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String nowDateStr = sdf.format(nowDate);
        THallQueue nowQueue = this.getNowNum(window.getWindowName(), nowDateStr);
        if(nowQueue==null){
            String message="已无号";
            return message;
        }
        nowQueue.setRemarks("办结");
        nowQueue.setEndTime(new Date());
        this.updateById(nowQueue);
        //更新取号表状态flag
        String ordinal = nowQueue.getOrdinal();
        THallTakenumber tnum = new THallTakenumber();
        tnum.setFlag(2);
        tnum.setOrdinal(ordinal);
        tHallTakenumberService.updateById(tnum);
        return ordinal;
    }

    @Override
    public String backNum(TSysWindow window) {
        //查询当前窗口正在办理的业务
        Date nowDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String nowDateStr = sdf.format(nowDate);
        THallQueue nowQueue = this.getNowNum(window.getWindowName(), nowDateStr);
        if(nowQueue==null){
            String message="已无号";
            return message;
        }
        nowQueue.setRemarks("退办");
        this.updateById(nowQueue);
        //更新取号表状态flag
        String ordinal = nowQueue.getOrdinal();
        THallTakenumber tnum = new THallTakenumber();
        tnum.setFlag(2);
        tnum.setOrdinal(ordinal);
        tHallTakenumberService.updateById(tnum);
        return  ordinal;
    }

    @Override
    public String nullNum(TSysWindow window) {
        //查询当前窗口正在办理的业务
        Date nowDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String nowDateStr = sdf.format(nowDate);
        THallQueue nowQueue = this.getNowNum(window.getWindowName(), nowDateStr);
        if(nowQueue==null){
            String message="已无号";
            return message;
        }
        nowQueue.setRemarks("空号");
        this.updateById(nowQueue);
        //更新取号表状态flag
        String ordinal = nowQueue.getOrdinal();
        THallTakenumber tnum = new THallTakenumber();
        tnum.setFlag(2);
        tnum.setOrdinal(ordinal);
        tHallTakenumberService.updateById(tnum);
        return ordinal;
    }

    @Override
    public String call(TSysWindow window) throws Exception {
        String agent = window.getOperatorPeople();
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
            return "该窗口可办理的业务类型已无号";
        }
        //*********取得应叫的号码
        String num = tHallTakenumberService.queryNumList(type);
        System.err.print("叫号：呼叫的号码是:" + num);
        //*********处理取号表的flag标识
        THallTakenumber tHallTakenumber = tHallTakenumberService.getByOrdinal(num);
        tHallTakenumber.setFlag(1);
        tHallTakenumberService.updateById(tHallTakenumber);
        //******更新排队信息表
        Date date = new Date();//通过当前日期和序号拿到当前号的排队信息
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = formatter.format(date);//format()方法bai将Date转换成指定格式的String
        THallQueue queueUpdate = this.getByOrdinalAndDatestr(num, dateStr);
        queueUpdate.setStartTime(new Date());
        queueUpdate.setWindowName(windowName);
        queueUpdate.setAgent(agent);
        this.updateById(queueUpdate);
        return num;
    }
}