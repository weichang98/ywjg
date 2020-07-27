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
    private String pk_role_id;
    /**
    * 角色名称
    */
    private String role_name;
    /**
    * 角色描述
    */
    private String role_desc;
    /**
    * 创建时间
    */
    private Date create_date;
    /**
    * 启用状态，1启用，0禁用
    */
    private Integer enable_status;
    /**
    * 修改时间
    */
    private Date modify_time;
}