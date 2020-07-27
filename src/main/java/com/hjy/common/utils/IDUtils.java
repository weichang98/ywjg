package com.hjy.common.utils;

public class IDUtils {

    public static String currentTimeMillis(){
        //防止时间戳相同，让线程等待1毫秒
        try {
            Thread.currentThread().sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return String.valueOf(System.currentTimeMillis());
    }

}
