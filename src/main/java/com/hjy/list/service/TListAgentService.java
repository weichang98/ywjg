package com.hjy.list.service;

import com.hjy.list.entity.TListAgent;
import java.util.List;

/**
 * (TListAgent)表服务接口
 *
 * @author liuchun
 * @since 2020-08-05 12:45:38
 */
public interface TListAgentService {

    /**
     * 通过ID查询单条数据
     *
     * @param pkAgentId 主键
     * @return 实例对象
     */
    TListAgent selectById(String pkAgentId)throws Exception;


    /**
     * 新增数据
     * @param tListAgent 实例对象
     * @return 实例对象
     */
    int insert(TListAgent tListAgent) throws Exception;

    /**
     * 修改数据
     *
     * @param tListAgent 实例对象
     * @return 实例对象
     */
    int updateById(TListAgent tListAgent) throws Exception;

    /**
     * 通过主键删除数据
     *
     * @param pkAgentId 主键
     * @return 是否成功
     */
    int deleteById(String pkAgentId) throws Exception;

    /**
     * 查询所有数据
     * @return list
     */
     List<TListAgent> selectAll() throws Exception;
     /**
     * 通过实体查询所有数据
     * @return list
     */
     List<TListAgent> selectAllByEntity(TListAgent tListAgent)throws Exception;
     
}