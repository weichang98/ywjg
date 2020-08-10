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
    private Integer A;
    /**
     *B类型业务数
     */
    private Integer B;
    /**
     *C类型业务数
     */
    private Integer C;
    /**
     *D类型业务数
     */
    private Integer D;
    /**
     *E类型业务数
     */
    private Integer E;
    /**
     *F类型业务数
     */
    private Integer F;
    /**
     *G类型业务数
     */
    private Integer G;
    /**
     *H类型业务数
     */
    private Integer H;
    /**
     *I类型业务数
     */
    private Integer I;
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
