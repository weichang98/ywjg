package com.hjy.system.service.impl;

import com.hjy.common.utils.IDUtils;
import com.hjy.system.entity.TSysDept;
import com.hjy.system.dao.TSysDeptMapper;
import com.hjy.system.service.TSysDeptService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * (TSysDept)表服务实现类
 *
 * @author liuchun
 * @since 2020-07-27 16:15:29
 */
@Service
public class TSysDeptServiceImpl implements TSysDeptService {
    @Autowired
    private TSysDeptMapper tSysDeptMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param pkDeptId 主键
     * @return 实例对象
     */
    @Override
    public TSysDept selectById(Object pkDeptId) throws Exception{
        return this.tSysDeptMapper.selectById(pkDeptId);
    }

    /**
     * 新增数据
     *
     * @param tSysDept 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(TSysDept tSysDept) throws Exception{
        tSysDept.setPkDeptId(IDUtils.currentTimeMillis());
        tSysDept.setCreateTime(new Date());
        return tSysDeptMapper.insertSelective(tSysDept);
    }

    /**
     * 修改数据
     *
     * @param tSysDept 实例对象
     * @return 实例对象
     */
    @Override
    public int updateById(TSysDept tSysDept) throws Exception{
        return tSysDeptMapper.updateById(tSysDept);
    }

    /**
     * 通过主键删除数据
     *
     * @param pkDeptId 主键
     * @return 是否成功
     */
    @Override
    public int deleteById(Object pkDeptId) throws Exception{
        return tSysDeptMapper.deleteById(pkDeptId);
    }
    
    /**
     * 查询多条数据
     * @return 对象列表
     */
    @Override
    public List<TSysDept> selectAll() throws Exception{
        return this.tSysDeptMapper.selectAll();
    }
    /**
     * 通过实体查询多条数据
     * @return 对象列表
     */
    @Override
    public List<TSysDept> selectAllByEntity(TSysDept tSysDept) throws Exception{
        return this.tSysDeptMapper.selectAllByEntity(tSysDept);
    }
}