package com.hjy.list.service.impl;

import com.hjy.list.entity.TListInfo;
import com.hjy.list.dao.TListInfoMapper;
import com.hjy.list.service.TListInfoService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * (TListInfo)表服务实现类
 *
 * @author liuchun
 * @since 2020-08-05 12:45:38
 */
@Service
public class TListInfoServiceImpl implements TListInfoService {
    @Autowired
    private TListInfoMapper tListInfoMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param pkListId 主键
     * @return 实例对象
     */
    @Override
    public TListInfo selectById(String pkListId) throws Exception{
        return this.tListInfoMapper.selectById(pkListId);
    }

    /**
     * 新增数据
     *
     * @param tListInfo 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(TListInfo tListInfo) throws Exception{
        return tListInfoMapper.insertSelective(tListInfo);
    }

    /**
     * 修改数据
     *
     * @param tListInfo 实例对象
     * @return 实例对象
     */
    @Override
    public int updateById(TListInfo tListInfo) throws Exception{
        return tListInfoMapper.updateById(tListInfo);
    }

    /**
     * 通过主键删除数据
     *
     * @param pkListId 主键
     * @return 是否成功
     */
    @Override
    public int deleteById(String pkListId) throws Exception{
        return tListInfoMapper.deleteById(pkListId);
    }
    
    /**
     * 查询多条数据
     * @return 对象列表
     */
    @Override
    public List<TListInfo> selectAll() throws Exception{
        return this.tListInfoMapper.selectAll();
    }
    /**
     * 通过实体查询多条数据
     * @return 对象列表
     */
    @Override
    public List<TListInfo> selectAllByEntity(TListInfo tListInfo) throws Exception{
        return this.tListInfoMapper.selectAllByEntity(tListInfo);
    }
}