package com.hjy.system.service.impl;

import com.hjy.common.utils.IDUtils;
import com.hjy.system.entity.TSysBusinesstype;
import com.hjy.system.dao.TSysBusinesstypeMapper;
import com.hjy.system.service.TSysBusinesstypeService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * (TSysBusinesstype)表服务实现类
 *
 * @author liuchun
 * @since 2020-07-28 16:54:27
 */
@Service
public class TSysBusinesstypeServiceImpl implements TSysBusinesstypeService {
    @Autowired
    private TSysBusinesstypeMapper tSysBusinesstypeMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param pkBusinesstypeId 主键
     * @return 实例对象
     */
    @Override
    public TSysBusinesstype selectById(Object pkBusinesstypeId) throws Exception{
        return this.tSysBusinesstypeMapper.selectById(pkBusinesstypeId);
    }

    /**
     * 新增数据
     *
     * @param tSysBusinesstype 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(TSysBusinesstype tSysBusinesstype) throws Exception{
        tSysBusinesstype.setPkBusinesstypeId(IDUtils.currentTimeMillis());
        return tSysBusinesstypeMapper.insertSelective(tSysBusinesstype);
    }

    /**
     * 修改数据
     *
     * @param tSysBusinesstype 实例对象
     * @return 实例对象
     */
    @Override
    public int updateById(TSysBusinesstype tSysBusinesstype) throws Exception{
        return tSysBusinesstypeMapper.updateById(tSysBusinesstype);
    }

    /**
     * 通过主键删除数据
     *
     * @param pkBusinesstypeId 主键
     * @return 是否成功
     */
    @Override
    public int deleteById(String pkBusinesstypeId) throws Exception{
        return tSysBusinesstypeMapper.deleteById(pkBusinesstypeId);
    }
    
    /**
     * 查询多条数据
     * @return 对象列表
     */
    @Override
    public List<TSysBusinesstype> selectAll() throws Exception{
        return this.tSysBusinesstypeMapper.selectAll();
    }
    /**
     * 通过实体查询多条数据
     * @return 对象列表
     */
    @Override
    public List<TSysBusinesstype> selectAllByEntity(TSysBusinesstype tSysBusinesstype) throws Exception{
        return this.tSysBusinesstypeMapper.selectAllByEntity(tSysBusinesstype);
    }

    @Override
    public List<String> selectBusinessName() {
        return tSysBusinesstypeMapper.selectBusinessName();
    }
}