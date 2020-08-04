package com.hjy.system.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * (TSysWindow)实体类
 *
 * @author liuchun
 * @since 2020-07-28 14:56:45
 */
@Data
public class TSysWindow implements Serializable {
    private static final long serialVersionUID = -72423995986961513L;
        private String pkWindowId;
        /**
    * 部门名称
    */    private String deptName;
        /**
    * 窗口名称
    */    private String windowName;
        /**
    * ip
    */    private String ip;
        /**
    * 业务类型
    */    private String businessType;
        /**
    * 操作人
    */    private String operatorPeople;
        /**
    * 操作时间
    */    private Date operatorTime;
        /**
    * 控制卡
    */    private String controlCard;
        /**
    * 备注
    */    private String remarks;
    /**
     * 网点编号
     */    private String branchNumber;
     /**
     * 注册窗口号
     */
     private String registrationWindow;
     /**
     * 评价器com号
     */
     private String com;
}