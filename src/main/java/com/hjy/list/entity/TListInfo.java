package com.hjy.list.entity;

import java.io.Serializable;

import lombok.Data;

/**
 * (TListInfo)实体类
 *
 * @author liuchun
 * @since 2020-08-05 12:45:38
 */
@Data
public class TListInfo implements Serializable {
    private static final long serialVersionUID = -71922283266423251L;
    /**
     * 主键
     */
    private String pkListId;
    /**
     * 名单类型,黑名单或者红名单
     */
    private String listType;
    /**
     * 全名
     */
    private String fullName;
    /**
     * 身份证
     */
    private String IdCard;
    /**
     * 联系电话
     */
    private String tel;
    /**
     * 说明
     */
    private String explain;
    /**
     * 其他
     */
    private String other;
    /**
     * 理由
     */
    private String reason;
    /**
     * 是否通过
     */
    private String whetherPass;
    /**
     * 审批人
     */
    private String approvalPeople;
    /**
     * 单位名称
     */
    private String unit;
    /**
     * 组织机构代码
     */
    private String organizationCode;
    /**
     * 申请书
     */
    private String applyBook;
    /**
     * 组织机构代码证
     */
    private String codeCertificates;
    /**
     * 操作人
     */
    private String operator;
}