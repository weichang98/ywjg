package com.hjy.hall.service.impl;

import com.hjy.hall.dao.THallJiashizhengMapper;
import com.hjy.hall.entity.THallJiashizheng;
import com.hjy.hall.service.THallJiashizhengService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (THallJiashizheng)表服务实现类
 *
 * @author makejava
 * @since 2020-07-27 14:17:48
 */
@Service
public class THallJiashizhengServiceImpl implements THallJiashizhengService {
    @Autowired
    private THallJiashizhengMapper tHallJiashizhengMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param pkJiashiId 主键
     * @return 实例对象
     */
    @Override
    public THallJiashizheng selectById(String pkJiashiId) {
        return this.tHallJiashizhengMapper.selectById(pkJiashiId);
    }

    /**
     * 新增数据
     *
     * @param tHallJiashizheng 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(THallJiashizheng tHallJiashizheng) {
        return tHallJiashizhengMapper.insertSelective(tHallJiashizheng);
    }

    /**
     * 修改数据
     *
     * @param tHallJiashizheng 实例对象
     * @return 实例对象
     */
    @Override
    public int updateById(THallJiashizheng tHallJiashizheng) {
        return tHallJiashizhengMapper.updateById(tHallJiashizheng);
    }

    /**
     * 通过主键删除数据
     *
     * @param pkJiashiId 主键
     * @return 是否成功
     */
    @Override
    public int deleteById(String pkJiashiId) {
        return tHallJiashizhengMapper.deleteById(pkJiashiId);
    }

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    @Override
    public List<THallJiashizheng> selectAll() {
        return this.tHallJiashizhengMapper.selectAll();
    }

    /**
     * 通过实体查询多条数据
     *
     * @return 对象列表
     */
    @Override
    public List<THallJiashizheng> selectAllByEntity(THallJiashizheng tHallJiashizheng) {
        return this.tHallJiashizhengMapper.selectAllByEntity(tHallJiashizheng);
    }
}