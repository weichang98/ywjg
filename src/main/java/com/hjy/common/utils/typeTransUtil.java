package com.hjy.common.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class  typeTransUtil {

   public static List<String> typeTrans(String business_type){

       String types[]=business_type.split("/");
       List<String> typeList=new ArrayList<>();
       for(String type : types){
           String typeTrans="";
           if("普通车驾管理业务".equals(type)){
               typeTrans="A";
               typeList.add(typeTrans);
           }else if("军转、外籍换证".equals(type)){
               typeTrans="B";
               typeList.add(typeTrans);
           }else if("满分、恢复驾驶证资格考试预约".equals(type)){
               typeTrans="C";
               typeList.add(typeTrans);
           }else if("三平台专用".equals(type)){
               typeTrans="D";
               typeList.add(typeTrans);
           }else if("绿色窗口".equals(type)){
               typeTrans="E";
               typeList.add(typeTrans);
           }else if("业务咨询查询".equals(type)){
               typeTrans="F";
               typeList.add(typeTrans);
           }
       }

       Collections.sort (typeList);

       System.out.print("工具类：该窗口可办理的号码类型有:"+typeList+",应优先办理:"+typeList.get(0)+"\n");

        return  typeList;
    }
}
