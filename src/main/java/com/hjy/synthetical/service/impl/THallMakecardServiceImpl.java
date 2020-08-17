package com.hjy.synthetical.service.impl;

import com.hjy.synthetical.dao.THallMakecardMapper;
import com.hjy.synthetical.entity.THallMakecard;
import com.hjy.synthetical.service.THallMakecardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (THallMakecard)表服务实现类
 *
 * @author makejava
 * @since 2020-08-17 09:53:44
 */
@Service
public class THallMakecardServiceImpl implements THallMakecardService {
    @Autowired
    private THallMakecardMapper tHallMakecardMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param pkCardId 主键
     * @return 实例对象
     */
    @Override
    public THallMakecard selectById(String pkCardId) {
        return this.tHallMakecardMapper.selectById(pkCardId);
    }

    /**
     * 新增数据
     *
     * @param tHallMakecard 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(THallMakecard tHallMakecard) {
        return tHallMakecardMapper.insertSelective(tHallMakecard);
    }

    /**
     * 修改数据
     *
     * @param tHallMakecard 实例对象
     * @return 实例对象
     */
    @Override
    public int updateById(THallMakecard tHallMakecard) {
        return tHallMakecardMapper.updateById(tHallMakecard);
    }

    /**
     * 通过主键删除数据
     *
     * @param pkCardId 主键
     * @return 是否成功
     */
    @Override
    public int deleteById(String pkCardId) {
        return tHallMakecardMapper.deleteById(pkCardId);
    }

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    @Override
    public List<THallMakecard> selectAll() {
        return this.tHallMakecardMapper.selectAll();
    }

    /**
     * 通过实体查询多条数据
     *
     * @return 对象列表
     */
    @Override
    public List<THallMakecard> selectAllByEntity(THallMakecard tHallMakecard) {
        return this.tHallMakecardMapper.selectAllByEntity(tHallMakecard);
    }
}