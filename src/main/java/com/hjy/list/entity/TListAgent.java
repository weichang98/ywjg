package com.hjy.list.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * (TListAgent)实体类
 *
 * @author liuchun
 * @since 2020-08-05 12:45:38
 */
@Data
public class TListAgent implements Serializable {
    private static final long serialVersionUID = 893970663744638733L;
    /**
     * 代办信息表
     */
    private String pkAgentId;
    /**
     * 业务类型
     */
    private String businessType;
    /**
     * 代理人身份证类型
     */
    private String aCertificatesType;
    /**
     * 代理人名字
     */
    private String aName;
    /**
     * 代理人证件号
     */
    private String aIdcard;
    /**
     * 本人证件类型
     */
    private String bCertificatesType;
    /**
     * 本人名称
     */
    private String bName;
    /**
     * 本人证件
     */
    private String bIdcard;
    /**
     * 添加时间
     */
    private Date addTime;
    /**
     * 经办人
     */
    private String agent;
    /**
     * 备注
     */
    private String remarks;
}