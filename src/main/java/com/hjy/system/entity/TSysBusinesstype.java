package com.hjy.system.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * (TSysBusinesstype)实体类
 *
 * @author liuchun
 * @since 2020-07-28 16:54:27
 */
@Data
public class TSysBusinesstype implements Serializable {
    private static final long serialVersionUID = 557486037568649991L;
        /**
    * 业务类型主键
    */    private String pkBusinesstypeId;
        /**
    * 业务名称
    */    private String typeName;
        /**
    * 业务级别
    */    private Integer typeLevel;
        /**
    * 流水号是否可以为空
    */    private Integer whetherNull;
        /**
    * 备注
    */    private String remarks;
}