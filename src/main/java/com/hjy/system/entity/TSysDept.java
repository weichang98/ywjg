package com.hjy.system.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * (TSysDept)实体类
 *
 * @author liuchun
 * @since 2020-07-27 16:15:29
 */
@Data
public class TSysDept implements Serializable {
    private static final long serialVersionUID = -61905538519563058L;
        private String pkDeptId;
        /**
    * 部门名称
    */    private String deptName;
        /**
    * 部门单位
    */    private String deptUnit;
        /**
    * 部门地址
    */    private String deptAddress;
        /**
    * 创建时间
    */    private Date createTime;
        /**
    * 备案机构
    */    private String mechanism;
        /**
    * 部门级别
    */    private String deptLevel;
        /**
    * 上级部门
    */    private String superiorDept;
        /**
    * 联系人
    */    private String people;
        /**
    * 联系电话
    */    private String tel;
        /**
    * 发证机关
    */    private String issuingAuthority;
        /**
    * 传真
    */    private String fax;
        /**
    * 备注
    */    private String remarks;
}