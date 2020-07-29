package com.hjy.hall.service.impl;

import com.hjy.hall.dao.THallQueueMapper;
import com.hjy.hall.entity.THallQueue;
import com.hjy.hall.service.THallQueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (THallQueue)表服务实现类
 *
 * @author makejava
 * @since 2020-07-29 14:33:20
 */
@Service
public class THallQueueServiceImpl implements THallQueueService {
    @Autowired
    private THallQueueMapper tHallQueueMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param pkQueueId 主键
     * @return 实例对象
     */
    @Override
    public THallQueue selectById(String pkQueueId) {
        return this.tHallQueueMapper.selectById(pkQueueId);
    }

    /**
     * 新增数据
     *
     * @param tHallQueue 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(THallQueue tHallQueue) {
        return tHallQueueMapper.insertSelective(tHallQueue);
    }

    /**
     * 修改数据
     *
     * @param tHallQueue 实例对象
     * @return 实例对象
     */
    @Override
    public int updateById(THallQueue tHallQueue) {
        return tHallQueueMapper.updateById(tHallQueue);
    }

    /**
     * 通过主键删除数据
     *
     * @param pkQueueId 主键
     * @return 是否成功
     */
    @Override
    public int deleteById(String pkQueueId) {
        return tHallQueueMapper.deleteById(pkQueueId);
    }

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    @Override
    public List<THallQueue> selectAll() {
        return this.tHallQueueMapper.selectAll();
    }

    /**
     * 通过实体查询多条数据
     *
     * @return 对象列表
     */
    @Override
    public List<THallQueue> selectAllByEntity(THallQueue tHallQueue) {
        return this.tHallQueueMapper.selectAllByEntity(tHallQueue);
    }
}