package com.hjy.hall.service.impl;

import com.hjy.hall.dao.THallJidongcheMapper;
import com.hjy.hall.entity.THallJidongche;
import com.hjy.hall.service.THallJidongcheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (THallJidongche)表服务实现类
 *
 * @author makejava
 * @since 2020-07-27 15:51:25
 */
@Service
public class THallJidongcheServiceImpl implements THallJidongcheService {
    @Autowired
    private THallJidongcheMapper tHallJidongcheMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param pkJidongcheId 主键
     * @return 实例对象
     */
    @Override
    public THallJidongche selectById(String pkJidongcheId) {
        return this.tHallJidongcheMapper.selectById(pkJidongcheId);
    }

    /**
     * 新增数据
     *
     * @param tHallJidongche 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(THallJidongche tHallJidongche) {
        return tHallJidongcheMapper.insertSelective(tHallJidongche);
    }

    /**
     * 修改数据
     *
     * @param tHallJidongche 实例对象
     * @return 实例对象
     */
    @Override
    public int updateById(THallJidongche tHallJidongche) {
        return tHallJidongcheMapper.updateById(tHallJidongche);
    }

    /**
     * 通过主键删除数据
     *
     * @param pkJidongcheId 主键
     * @return 是否成功
     */
    @Override
    public int deleteById(String pkJidongcheId) {
        return tHallJidongcheMapper.deleteById(pkJidongcheId);
    }

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    @Override
    public List<THallJidongche> selectAll() {
        return this.tHallJidongcheMapper.selectAll();
    }

    /**
     * 通过实体查询多条数据
     *
     * @return 对象列表
     */
    @Override
    public List<THallJidongche> selectAllByEntity(THallJidongche tHallJidongche) {
        return this.tHallJidongcheMapper.selectAllByEntity(tHallJidongche);
    }
}