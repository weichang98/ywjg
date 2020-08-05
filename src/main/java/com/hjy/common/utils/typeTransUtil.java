package com.hjy.common.utils;

import com.hjy.system.entity.TSysBusinesstype;
import com.hjy.system.entity.TSysWindow;
import com.hjy.system.service.TSysBusinesstypeService;
import com.hjy.system.service.TSysWindowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Component
public class  typeTransUtil {

    @Autowired
    private TSysBusinesstypeService tSysBusinesstypeService;

    private static typeTransUtil typeTransUtil;

    @PostConstruct
    public void init() {
        typeTransUtil= this;
        typeTransUtil.tSysBusinesstypeService= this.tSysBusinesstypeService;
    }

   public static List<String> typeTrans(String business_type) throws  Exception{
       List<TSysBusinesstype> typeList=typeTransUtil.tSysBusinesstypeService.selectAll();//查询数据库所有数据类型
       String types[]=business_type.split("/");//拿到当前窗口可办理的业务类型，用/分割
       List<String> typeDealList=new ArrayList<>();
       for(String type : types){
          for(TSysBusinesstype businesstype: typeList){
              if( businesstype.getTypeName().equals(type)){
                  typeDealList.add(businesstype.getTypeLevel());
              }
          }
       }
       Collections.sort (typeDealList);
       System.out.print("工具类：该窗口可办理的号码类型有:"+typeDealList+",应优先办理:"+typeDealList.get(0)+"\n");
        return  typeDealList;
    }
}
