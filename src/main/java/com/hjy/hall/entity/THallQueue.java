package com.hjy.hall.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * (THallQueue)实体类
 *
 * @author makejava
 * @since 2020-07-29 14:33:19
 */
@Data
public class THallQueue implements Serializable {
    private static final long serialVersionUID = 768936314850217156L;
    private String pkQueueId;
    /**
     * 排队号
     */
    private String ordinal;
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
     * 代理人证件类型
     */
    @JsonProperty
    private String aCertificatesType;
    /**
            * 代理人姓名
     */
    @JsonProperty
    private String aName;
    /**
     * 代理人身份证件号
     */
    @JsonProperty
    private String aIdcard;
    /**
     * 本人证件类型
     */
    @JsonProperty
    private String bCertificatesType;
    /**
     * 本人姓名
     */
    @JsonProperty
    private String bName;
    /**
     * 本人身份证件号
     */
    @JsonProperty
    private String bIdcard;
    /**
     * 取号时间
     */
    private Date getTime;
    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 查询开始时间
     */
    private Date queryStart;
    /**
     * 查询结束时间
     */
    private Date queryEnd;
    /**
     * 备注是否为空号或者退办
     */
    private String remarks;
    /**
     * 办理次数
     */
    private Integer handleNum;
    /**
     * 代办次数
     */
    private Integer agentNum;
    /**
     * 是否为特呼，0是普通，1是特呼
     */
    private Integer isVip;

}