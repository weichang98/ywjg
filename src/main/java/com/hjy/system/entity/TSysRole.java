package com.hjy.system.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * (TSysRole)实体类
 *
 * @author liuchun
 * @since 2020-07-23 12:59:10
 */
@Data
public class TSysRole implements Serializable {
    private static final long serialVersionUID = -45694671485464516L;
    private String pkRoleId;
    /**
    * 角色名称
    */
    private String roleName;
    /**
    * 角色描述
    */
    private String roleDesc;
    /**
    * 创建时间
    */
    private Date createDate;
    /**
    * 启用状态，1启用，0禁用
    */
    private Integer enableStatus;
    /**
    * 修改时间
    */
    private Date modifyTime;
}