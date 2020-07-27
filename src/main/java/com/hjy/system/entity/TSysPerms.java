package com.hjy.system.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * (TSysPerms)实体类
 *
 * @author liuchun
 * @since 2020-07-24 09:55:41
 */
@Data
public class TSysPerms implements Serializable {
    private static final long serialVersionUID = -72432537446108401L;
        private String pk_perms_id;
        /**
    * 父级菜单
    */    private String p_id;
        /**
    * 菜单名称
    */    private String menu_name;
        /**
    * 路由路径
    */    private String path;
        /**
    * 权限码
    */    private String perms_code;
        /**
    * 菜单类型，菜单或按钮
    */    private String type;
        /**
    * 创建时间
    */    private Date create_time;
        /**
    * 修改时间
    */    private Date modify_time;
        /**
    * 修改人名字
    */    private String modify_username;
        /**
    * 修改人id
    */    private String fk_user_id;
}