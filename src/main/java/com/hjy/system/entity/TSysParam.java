package com.hjy.system.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * (TSysParam)实体类
 *
 * @author liuchun
 * @since 2020-08-11 15:51:59
 */
@Data
public class TSysParam implements Serializable {
    private static final long serialVersionUID = 800017878587187039L;
    private String pkParamId;
    /**
     * 参数名
     */
    private String paramKey;
    /**
     * 参数值
     */
    private String paramValue;
    /**
     * 操作人
     */
    private String operatorPeople;
    /**
     * 操作时间
     */
    private Date operatorTime;
}