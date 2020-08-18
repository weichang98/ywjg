package com.hjy.synthetical.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (THallMakecard)实体类
 *
 * @author makejava
 * @since 2020-08-17 09:53:43
 */
@Data
public class THallMakecard implements Serializable {
    private static final long serialVersionUID = -53680261268235094L;
    /**
     * 制证主键id
     */
    private String pkCardId;
    /**
     * 代理人姓名
     */
    @JsonProperty
    private String aName;
    /**
     * 代理人身份证件号
     */
    @JsonProperty
    private String aIdcard;
    /**
     * 本人姓名
     */
    @JsonProperty
    private String bName;
    /**
     * 本人身份证件号
     */
    @JsonProperty
    private String bIdcard;
    /**
     * 车牌号
     */
    private String licensePlate;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 操作人
     */
    private String opratorPeple;
    /**
     * 状态
     */
    private String status;
    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 制作完成时间
     */
    private Date endTime;
    /**
     * 领证时间
     */
    private Date getTime;
}