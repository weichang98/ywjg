package com.hjy.hall.service;

import com.hjy.hall.entity.THallQueue;
import com.hjy.hall.entity.THallQueueCount;
import com.hjy.system.entity.SysToken;
import com.hjy.system.entity.TSysWindow;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * (THallQueue)表服务接口
 *
 * @author makejava
 * @since 2020-07-29 14:33:20
 */
public interface THallQueueService {

    /**
     * 通过ID查询单条数据
     *
     * @param pkQueueId 主键
     * @return 实例对象
     */
    THallQueue selectById(String pkQueueId);


    /**
     * 新增数据
     *
     * @param tHallQueue 实例对象
     * @return 实例对象
     */
    int insert(THallQueue tHallQueue);

    /**
     * 修改数据
     *
     * @param tHallQueue 实例对象
     * @return 实例对象
     */
    int updateById(THallQueue tHallQueue);

    /**
     * 通过主键删除数据
     *
     * @param pkQueueId 主键
     * @return 是否成功
     */
    int deleteById(String pkQueueId);

    /**
     * 查询所有数据
     *
     * @return list
     */
    List<THallQueue> selectAll();

    /**
     * 通过实体查询所有数据
     *
     * @return list
     */
    List<THallQueue> selectAllByEntity(THallQueue tHallQueue);

    //通过号码与日期字符串查询数据
    THallQueue getByOrdinalAndDatestr(String Ordinal,String DateStr);
    //根据日期字符串查询数据
    List<THallQueue> queryByTime(String startTime,String endTime);
    //查询现在正在办理的号码
    THallQueue getNowNum(String windowName,String nowDateStr);
    //根据日期字符串查询实际业务量
    List<THallQueueCount> realCount(String queryStart, String queryEnd);
    //根据日期字符串查询空号业务量
    List<THallQueueCount> nullCount(String queryStart, String queryEnd);
    //根据日期字符串查询退号业务量
    List<THallQueueCount> backCount(String queryStart, String queryEnd);
    //token
    SysToken findByToken(String accessToken);
    //叫号
    String call(TSysWindow window )  throws Exception;
    //业务查询与时间统计
    THallQueueCount StatisticsTime(THallQueue tHallQueue);
    //设置空号
    String nullNum(TSysWindow window);
    //退号
    String backNum(TSysWindow window);
    //办结
    String downNum(TSysWindow window);
    //办理次数
   int handleNum(THallQueue tHallQueue);
    //代办次数
    int agentNum(THallQueue tHallQueue);
}