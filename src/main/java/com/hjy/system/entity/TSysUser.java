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
        private String pk_user_id;
        /**
    * 用户名
    */    private String username;
        /**
    * 密码
    */    private String password;
        /**
    * 最后一次登录时间
    */    private Date last_login_date;
        /**
    * 部门id
    */    private String fk_dept_id;
        /**
    * 邮箱
    */    private String email;
        /**
    * 联系电话
    */    private String tel;
        /**
    * 状态,1启用 0禁用
    */    private String enable_status;
        /**
    * 创建时间
    */    private Date create_time;
        /**
    * 身份证
    */    private String IDcard;
        /**
    * 修改时间
    */    private Date modify_time;
        /**
    * 真实姓名
    */    private String full_name;
        /**
    * 警号
    */    private String police_num;
        /**
    * 单位
    */    private String unit;
        /**
    * 在线状态，1在线0离线
    */    private String on_line;
}