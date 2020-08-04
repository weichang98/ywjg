package com.hjy.system.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * (TSysUser)实体类
 *
 * @author liuchun
 * @since 2020-07-24 17:05:59
 */
@Data
public class TSysUser implements Serializable {
    private static final long serialVersionUID = -90457638690976393L;
    private String pkUserId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 最后一次登录时间
     */
    private Date lastLoginDate;
    /**
     * 部门id
     */
    private String fkDeptId;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 联系电话
     */
    private String tel;
    /**
     * 状态,1启用 0禁用
     */
    private String enableStatus;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 身份证
     */
    private String IDcard;
    /**
     * 修改时间
     */
    private Date modifyTime;
    /**
     * 真实姓名
     */
    private String fullName;
    /**
     * 警号
     */
    private String policeNum;
    /**
     * 单位
     */
    private String unit;
    /**
     * ip
     */
    private String ip;
    /**
     * 在线状态，1在线0离线
     */
    private String onLine;
    /**
     * 地址
     */
    private String address;
}