package com.hjy.list.service.impl;

import com.hjy.list.entity.TListAgent;
import com.hjy.list.dao.TListAgentMapper;
import com.hjy.list.service.TListAgentService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * (TListAgent)表服务实现类
 *
 * @author liuchun
 * @since 2020-08-05 12:45:38
 */
@Service
public class TListAgentServiceImpl implements TListAgentService {
    @Autowired
    private TListAgentMapper tListAgentMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param pkAgentId 主键
     * @return 实例对象
     */
    @Override
    public TListAgent selectById(String pkAgentId) throws Exception{
        return this.tListAgentMapper.selectById(pkAgentId);
    }

    /**
     * 新增数据
     *
     * @param tListAgent 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(TListAgent tListAgent) throws Exception{
        return tListAgentMapper.insertSelective(tListAgent);
    }

    /**
     * 修改数据
     *
     * @param tListAgent 实例对象
     * @return 实例对象
     */
    @Override
    public int updateById(TListAgent tListAgent) throws Exception{
        return tListAgentMapper.updateById(tListAgent);
    }

    /**
     * 通过主键删除数据
     *
     * @param pkAgentId 主键
     * @return 是否成功
     */
    @Override
    public int deleteById(String pkAgentId) throws Exception{
        return tListAgentMapper.deleteById(pkAgentId);
    }
    
    /**
     * 查询多条数据
     * @return 对象列表
     */
    @Override
    public List<TListAgent> selectAll() throws Exception{
        return this.tListAgentMapper.selectAll();
    }
    /**
     * 通过实体查询多条数据
     * @return 对象列表
     */
    @Override
    public List<TListAgent> selectAllByEntity(TListAgent tListAgent) throws Exception{
        return this.tListAgentMapper.selectAllByEntity(tListAgent);
    }
}