package com.hjy.system.dao;

import com.hjy.system.entity.TSysWindow;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (TSysWindow)表数据库访问层
 *
 * @author liuchun
 * @since 2020-07-28 14:56:45
 */
public interface TSysWindowMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param pkWindowId 主键
     * @return 实例对象
     */
    TSysWindow selectById(Object pkWindowId);

    /**
     * 新增数据
     *
     * @param tSysWindow 实例对象
     * @return 影响行数
     */
    int insert(TSysWindow tSysWindow);
    int insertSelective(TSysWindow tSysWindow);

    /**
     * 修改数据
     *
     * @param tSysWindow 实例对象
     * @return 影响行数
     */
    int updateById(TSysWindow tSysWindow);

    /**
     * 通过主键删除数据
     *
     * @param pkWindowId 主键
     * @return 影响行数
     */
    int deleteById(Object pkWindowId);

    /**
     * 查询所有行数据
     * @return 对象列表
     */
    List<TSysWindow> selectAll();
     /**
     * 通过实体作为筛选条件查询
     *
     * @param tSysWindow 实例对象
     * @return 对象列表
     */
    List<TSysWindow> selectAllByEntity(TSysWindow tSysWindow);

    String selectWindowNameByIp(@Param("Ip") String Ip);

    TSysWindow selectWindowByIp(@Param("Ip")String ip);
}