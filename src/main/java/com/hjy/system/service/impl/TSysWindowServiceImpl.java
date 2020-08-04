package com.hjy.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hjy.common.utils.IDUtils;
import com.hjy.system.entity.TSysBusinesstype;
import com.hjy.system.entity.TSysWindow;
import com.hjy.system.dao.TSysWindowMapper;
import com.hjy.system.service.TSysWindowService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Array;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * (TSysWindow)表服务实现类
 *
 * @author liuchun
 * @since 2020-07-28 14:56:45
 */
@Service
public class TSysWindowServiceImpl implements TSysWindowService {
    @Autowired
    private TSysWindowMapper tSysWindowMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param pkWindowId 主键
     * @return 实例对象
     */
    @Override
    public TSysWindow selectById(Object pkWindowId) throws Exception{
        return this.tSysWindowMapper.selectById(pkWindowId);
    }

    /**
     * 新增数据
     *
     * @param parm
     * @return 实例对象
     */
    @Override
    public int insert(String parm) throws Exception{
        TSysWindow tSysWindow = new TSysWindow();
        JSONObject jsonObject = JSON.parseObject(parm);
        String deptName=String.valueOf(jsonObject.get("deptName"));
        String windowName=String.valueOf(jsonObject.get("windowName"));
        String ip=String.valueOf(jsonObject.get("ip"));
        String operatorPeople=String.valueOf(jsonObject.get("operatorPeople"));
        String controlCard=String.valueOf(jsonObject.get("controlCard"));
        String branchNumber=String.valueOf(jsonObject.get("branchNumber"));
        String registrationWindow=String.valueOf(jsonObject.get("registrationWindow"));
        String com=String.valueOf(jsonObject.get("com"));
        tSysWindow.setPkWindowId(IDUtils.currentTimeMillis());
        tSysWindow.setDeptName(deptName);
        tSysWindow.setWindowName(windowName);
        tSysWindow.setIp(ip);
        if(operatorPeople != null || operatorPeople != ""){
            tSysWindow.setOperatorPeople(operatorPeople);
        }
        if(controlCard != null || controlCard != ""){
            tSysWindow.setControlCard(controlCard);
        }
        if(branchNumber != null || branchNumber != ""){
            tSysWindow.setBranchNumber(branchNumber);
        }
        if(registrationWindow != null || registrationWindow != ""){
            tSysWindow.setRegistrationWindow(registrationWindow);
        }
        if(com != null || com != ""){
            tSysWindow.setCom(com);
        }
        //业务类型
        JSONArray jsonArray = jsonObject.getJSONArray("businesstypes");
        String businesstypes = jsonArray.toString();
        List<TSysBusinesstype> businesstypeList = JSONArray.parseArray(businesstypes, TSysBusinesstype.class);
        if(businesstypeList != null){
            StringBuffer businessType = new StringBuffer();
            //先排序
            Collections.sort(businesstypeList, new Comparator<TSysBusinesstype>() {
                @Override
                public int compare(TSysBusinesstype o1, TSysBusinesstype o2) {
                    //升序
                    return o1.getTypeLevel().compareTo(o2.getTypeLevel());
                }
            });
            List<TSysBusinesstype> businesstypeList2 = businesstypeList;
            System.err.println("排序后的TSysBusinesstype："+businesstypeList2);
            for(TSysBusinesstype obj:businesstypeList2){
                if(obj.getTypeLevel().equals("1")){
                    businessType.append(obj.getTypeName());
                }else {
                    businessType.append("/"+obj.getTypeName());
                }
            }
            tSysWindow.setBusinessType(businessType.toString());
        }
        return tSysWindowMapper.insertSelective(tSysWindow);
    }

    /**
     * 修改数据
     *
     * @param tSysWindow 实例对象
     * @return 实例对象
     */
    @Override
    public int updateById(TSysWindow tSysWindow) throws Exception{
        return tSysWindowMapper.updateById(tSysWindow);
    }

    /**
     * 通过主键删除数据
     *
     * @param pkWindowId 主键
     * @return 是否成功
     */
    @Override
    public int deleteById(Object pkWindowId) throws Exception{
        return tSysWindowMapper.deleteById(pkWindowId);
    }
    
    /**
     * 查询多条数据
     * @return 对象列表
     */
    @Override
    public List<TSysWindow> selectAll() throws Exception{
        return this.tSysWindowMapper.selectAll();
    }
    /**
     * 通过实体查询多条数据
     * @return 对象列表
     */
    @Override
    public List<TSysWindow> selectAllByEntity(TSysWindow tSysWindow) throws Exception{
        return this.tSysWindowMapper.selectAllByEntity(tSysWindow);
    }
}