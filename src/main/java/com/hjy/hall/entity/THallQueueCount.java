package com.hjy.hall.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class THallQueueCount implements Serializable {

    /**
     * 窗口
     */
    private String windowName;
    /**
     * 经办人
     */
    private String agent;
    /**
     * 业务类型
     */
    private String businessType;
    /**
     * 统计日期
     */
    private Date countDate;
    /**
     * 总业务量(用于装载数据库自定义字段的实体)
     */
    private Integer totalCount;
    /**
     * 实际业务量(用于装载数据库自定义字段的实体)
     */
    private Integer realCount;
    /**
     * 空号统计(用于装载数据库自定义字段的实体)
     */
    private Integer nullCount;
    /**
     * 退办统计(用于装载数据库自定义字段的实体)
     */
    private Integer backCount;
    /**
     * 查出的排队信息
     */
    private List<THallQueue> queueList;
    /**
     * 群众平均等待时间(秒)
     */
    private Long avgWaitTime;
    /**
     * 平均办理时间(秒)
     */
    private Long avgServiceTime;
    /**
     * 总业务量
     */
    private Integer totalServiceNum;
    /**
     * 空号数
     */
    private Integer nullMark;
    /**
     * 退办数
     */
    private Integer backMark;

    /**
     * 大厅实时等待人数
     */
    private Integer nowWaitNum;
    /**
     * 办理超时数
     */
    private Integer serviceOverTimeNum;
    /**
     * 等待超时数
     */
    private Integer waitOverTimeNum;

}
