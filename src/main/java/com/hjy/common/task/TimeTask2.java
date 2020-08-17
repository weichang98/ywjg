package com.hjy.common.task;

import com.hjy.list.dao.TListInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TimeTask2 {

    //注入mapper
    @Autowired
    TListInfoMapper TListInfoMapper;
    /**
     * 执行定时任务2
     */
    @Scheduled(cron = "0 0 0 ? 1 MON")
    public void task(){
        System.err.println("正在执行定时任务2：");
        TListInfoMapper.deleteBlackByYear();
    }
}
