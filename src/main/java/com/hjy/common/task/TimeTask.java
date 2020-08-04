package com.hjy.common.task;

import com.hjy.hall.dao.THallTakenumberMapper;
import com.hjy.system.dao.TSysTokenMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import java.time.LocalDateTime;

@Configuration
@EnableScheduling
public class TimeTask implements SchedulingConfigurer {

    //注入mapper
    @Autowired
    THallTakenumberMapper takenumberMapper;
    @Autowired
    TSysTokenMapper tokenMapper;
    /**
     * 执行定时任务.
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.addTriggerTask(
                //1.添加任务内容(Runnable)
                () -> takenumberMapper.deleteAll(),
                //2.设置执行周期(Trigger)
                triggerContext -> {
                    //2.1 设置执行周期-每天0点0分0秒执行
                    String cron = "0 0 0 * * ?";
                    //2.2 合法性校验.
                    if (StringUtils.isEmpty(cron)) {
                        // Omitted Code ..
                    }
                    //2.3 返回执行周期(Date)
                    return new CronTrigger(cron).nextExecutionTime(triggerContext);
                }
        );
    }
}
