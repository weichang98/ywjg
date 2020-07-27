package com.hjy.system.dao;

import com.hjy.system.entity.TSysUser;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (TSysUser)表数据库访问层
 *
 * @author liuchun
 * @since 2020-07-24 17:05:59
 */
public interface TSysUserMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param pkUserId 主键
     * @return 实例对象
     */
    TSysUser selectById(Object pkUserId);

    /**
     * 新增数据
     *
     * @param tSysUser 实例对象
     * @return 影响行数
     */
    int insert(TSysUser tSysUser);
    int insertSelective(TSysUser tSysUser);

    /**
     * 修改数据
     *
     * @param tSysUser 实例对象
     * @return 影响行数
     */
    int updateById(TSysUser tSysUser);

    /**
     * 通过主键删除数据
     *
     * @param pkUserId 主键
     * @return 影响行数
     */
    int deleteById(Object pkUserId);

    /**
     * 查询所有行数据
     * @return 对象列表
     */
    List<TSysUser> selectAll();
     /**
     * 通过实体作为筛选条件查询
     *
     * @param tSysUser 实例对象
     * @return 对象列表
     */
    List<TSysUser> selectAllByEntity(TSysUser tSysUser);
}