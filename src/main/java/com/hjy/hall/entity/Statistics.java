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
    private Integer typeA;
    /**
     *B类型业务数
     */
    private Integer typeB;
    /**
     *C类型业务数
     */
    private Integer typeC;
    /**
     *D类型业务数
     */
    private Integer typeD;
    /**
     *E类型业务数
     */
    private Integer typeE;
    /**
     *F类型业务数
     */
    private Integer typeF;
    /**
     *G类型业务数
     */
    private Integer typeG;
    /**
     *H类型业务数
     */
    private Integer typeH;
    /**
     *I类型业务数
     */
    private Integer typeI;
    /**
     *空号数
     */
    private Integer nullNum;
    /**
     *实际办理业务数
     */
    private Integer trueNum;
    /**
     * 总业务数
     */
    private Integer totalNum;


}
