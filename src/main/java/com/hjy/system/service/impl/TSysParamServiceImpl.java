package com.hjy.system.service.impl;

import com.hjy.system.entity.ActiveUser;
import com.hjy.system.entity.TSysParam;
import com.hjy.system.dao.TSysParamMapper;
import com.hjy.system.service.TSysParamService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * (TSysParam)表服务实现类
 *
 * @author liuchun
 * @since 2020-08-11 15:51:59
 */
@Service
public class TSysParamServiceImpl implements TSysParamService {
    @Autowired
    private TSysParamMapper tSysParamMapper;



    /**
     * 修改数据
     *
     * @param tSysParam 实例对象
     * @return 实例对象
     */
    @Override
    public int updateById(TSysParam tSysParam, HttpSession session) throws Exception{
        ActiveUser activeUser = (ActiveUser) session.getAttribute("activeUser");
        tSysParam.setOperatorPeople(activeUser.getFullName());
        tSysParam.setOperatorTime(new Date());
        System.err.println(tSysParam);
        return tSysParamMapper.updateById(tSysParam);
    }


    /**
     * 查询多条数据
     * @return 对象列表
     */
    @Override
    public List<TSysParam> selectAll() throws Exception{
        return this.tSysParamMapper.selectAll();
    }

    /**
     * 查询单条条数据
     * @return 对象
     */
    @Override
    public TSysParam selectById(String pkParamId) throws Exception{
        return this.tSysParamMapper.selectById(pkParamId);
    }
}