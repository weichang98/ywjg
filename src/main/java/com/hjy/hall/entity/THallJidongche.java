package com.hjy.hall.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * (THallJidongche)实体类
 *
 * @author makejava
 * @since 2020-07-27 15:51:25
 */
@Data
public class THallJidongche implements Serializable {
    private static final long serialVersionUID = 406215436233562884L;
    private String pkJidongcheId;
    /**
     * 部门名称
     */
    private String deptName;
    /**
     * 退办类型
     */
    private String withdrawType;
    /**
     * 申请人
     */
    private String applicant;
    /**
     * 业务类型
     */
    private String businessType;
    /**
     * 号牌种类
     */
    private String numberType;
    /**
     * 号牌号码
     */
    private String numberPlate;
    /**
     * 缺少项
     */
    private String lack;
    /**
     * 经办人
     */
    private String handlePeople;
    /**
     * 退办时间
     */
    private Date withdrawTime;
    /**
     * 查询开始时间
     */
    private Date startTime;
    /**
     * 查询结束时间
     */
    private Date endTime;


}