package com.hjy.hall.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * (THallTakenumber)实体类
 *
 * @author makejava
 * @since 2020-07-29 10:28:25
 */
@Data
public class THallTakenumber implements Serializable {
    private static final long serialVersionUID = -49029962571639952L;
    /**
     * 取号表主键
     */
    private String pkTakenumId;
    /**
     * 序号
     */
    private String ordinal;

    /**
     * 处理标识，0是未处理，1是已处理
     */
    private Integer flag;
    /**
     * 取号时间
     */
    private Date getTime;
}