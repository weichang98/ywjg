package com.hjy.common.utils;

public class  typeTransUtil {

   public static String typeTrans(String business_type){
        String typeTrans="";
        if("普通车驾管理业务".equals(business_type)){
            typeTrans="A";
        }else if("军转、外籍换证".equals(business_type)){
            typeTrans="B";
        }else if("满分、恢复驾驶证资格考试预约".equals(business_type)){
            typeTrans="C";
        }else if("三平台专用".equals(business_type)){
            typeTrans="D";
        }else if("绿色窗口".equals(business_type)){
            typeTrans="E";
        }else if("业务咨询查询".equals(business_type)){
            typeTrans="F";
        }
        return  typeTrans;
    }
}
