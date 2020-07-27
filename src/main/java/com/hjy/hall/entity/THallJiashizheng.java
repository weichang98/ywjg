package com.hjy.hall.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (THallJiashizheng)实体类
 *
 * @author makejava
 * @since 2020-07-27 14:17:46
 */
@Data
public class THallJiashizheng implements Serializable {
    private static final long serialVersionUID = -35077458231588864L;
    private String pkJiashiId;
    /**
     * 申请人
     */
    private String applicant;
    /**
     * 身份证件号
     */
    private String idcard;
    /**
     * 经办人
     */
    private String handlePeople;
    /**
     * 业务类型
     */
    private String businessType;
    /**
     * 部门名称
     */
    private String deptName;
    /**
     * 退办类型
     */
    private String withdrawType;
    /**
     * 退办时间
     */
    private Object withdrawTime;
    /**
     * 缺少项
     */
    private String lack;
    /**
     * 备注
     */
    private String remarks;
}