package com.hjy.hall.entity;

import lombok.Data;

import java.io.Serializable;


@Data
public class Statistics implements Serializable {

    /**
     *受理人员姓名
     */
     private  String agent;

    /**
     *A类型业务数
     */
    private Integer A=0;
    /**
     *B类型业务数
     */
    private Integer B=0;
    /**
     *C类型业务数
     */
    private Integer C=0;
    /**
     *D类型业务数
     */
    private Integer D=0;
    /**
     *E类型业务数
     */
    private Integer E=0;
    /**
     *F类型业务数
     */
    private Integer F=0;
    /**
     *G类型业务数
     */
    private Integer G=0;
    /**
     *H类型业务数
     */
    private Integer H=0;
    /**
     *I类型业务数
     */
    private Integer I=0;
    /**
     *空号数
     */
    private Integer nullNum=0;
    /**
     *实际办理业务数
     */
    private Integer trueNum=0;
    /**
     * 总业务数
     */
    private Integer totalNum=0;


}
