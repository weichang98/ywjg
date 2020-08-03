package com.hjy.hall.service.impl;

import com.hjy.hall.dao.THallTakenumberMapper;
import com.hjy.hall.entity.THallTakenumber;
import com.hjy.hall.service.THallTakenumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (THallTakenumber)表服务实现类
 *
 * @author makejava
 * @since 2020-07-29 10:28:25
 */
@Service
public class THallTakenumberServiceImpl implements THallTakenumberService {
    @Autowired
    private THallTakenumberMapper tHallTakenumberMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param pkTakenumId 主键
     * @return 实例对象
     */
    @Override
    public THallTakenumber selectById(String pkTakenumId) {
        return this.tHallTakenumberMapper.selectById(pkTakenumId);
    }

    /**
     * 新增数据
     *
     * @param tHallTakenumber 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(THallTakenumber tHallTakenumber) {
        return tHallTakenumberMapper.insertSelective(tHallTakenumber);
    }

    /**
     * 修改数据
     *
     * @param tHallTakenumber 实例对象
     * @return 实例对象
     */
    @Override
    public int updateById(THallTakenumber tHallTakenumber) {
        return tHallTakenumberMapper.updateById(tHallTakenumber);
    }

    /**
     * 通过主键删除数据
     *
     * @param pkTakenumId 主键
     * @return 是否成功
     */
    @Override
    public int deleteById(String pkTakenumId) {
        return tHallTakenumberMapper.deleteById(pkTakenumId);
    }

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    @Override
    public List<THallTakenumber> selectAll() {
        return this.tHallTakenumberMapper.selectAll();
    }

    /**
     * 通过实体查询多条数据
     *
     * @return 对象列表
     */
    @Override
    public List<THallTakenumber> selectAllByEntity(THallTakenumber tHallTakenumber) {
        return this.tHallTakenumberMapper.selectAllByEntity(tHallTakenumber);
    }


    /**
     *
     * @return 序号
     */
    @Override
    public int count(){
        return tHallTakenumberMapper.count();
    };

    /**
     * 查询窗口需要办理业务列表
     *
     * @return 对象列表
     */
    @Override
    public String queryNumList(String type) {
        return this.tHallTakenumberMapper.queryNumList(type);
    }


    /**
     * 查询窗口需要办理业务列表
     *
     * @return 对象列表
     */
    @Override
    public THallTakenumber getByOrdinal(String Ordinal) {
        return this.tHallTakenumberMapper.getByOrdinal(Ordinal);
    }

}