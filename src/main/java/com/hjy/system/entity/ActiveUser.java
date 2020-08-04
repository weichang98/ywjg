package com.hjy.system.entity;

import lombok.Data;

import java.util.List;

@Data
public class ActiveUser {
    /**  */
    private static final long serialVersionUID = -4500748422849176791L;
    /** 用户id（主键） */
    private int userId;
    /** 用户姓名 */
    private String username;
    /** 用户密码 */
    private String password;
    /**
     * 联系电话
     */    private String tel;
    /**
     * 身份证
     */    private String IDcard;
    /**
     * 真实姓名
     */    private String fullName;
    /**
     * 单位
     */    private String unit;
    /** 用户警号 */
    private String policeNum;
    /** 用户角色 */
    private String roleName;
    //权限类型
    private String permsType;
    private List<TSysPerms> menu;
    private List<TSysPerms> permission;
}
