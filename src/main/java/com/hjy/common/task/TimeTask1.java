package com.hjy.common.task;

import com.hjy.hall.dao.THallTakenumberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TimeTask1 {

    //注入mapper
    @Autowired
    THallTakenumberMapper takenumberMapper;
    /**
     * 执行定时任务1
     * 0 0 0 * * ?
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void task(){
        System.err.println("正在执行定时任务1：");
        takenumberMapper.deleteAll();
    }
}
