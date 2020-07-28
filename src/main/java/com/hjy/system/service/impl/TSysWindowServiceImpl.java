package com.hjy.system.service.impl;

import com.hjy.common.utils.IDUtils;
import com.hjy.system.entity.TSysWindow;
import com.hjy.system.dao.TSysWindowMapper;
import com.hjy.system.service.TSysWindowService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
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
     * @param tSysWindow 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(TSysWindow tSysWindow) throws Exception{
        tSysWindow.setPkWindowId(IDUtils.currentTimeMillis());
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