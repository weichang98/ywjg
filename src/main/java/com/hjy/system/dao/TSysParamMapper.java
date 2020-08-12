package com.hjy.system.dao;

import com.hjy.system.entity.TSysParam;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (TSysParam)表数据库访问层
 *
 * @author liuchun
 * @since 2020-08-11 15:51:59
 */
public interface TSysParamMapper {

    /**
     * 修改数据
     *
     * @param tSysParam 实例对象
     * @return 影响行数
     */
    int updateById(TSysParam tSysParam);

    /**
     * 查询所有行数据
     * @return 对象列表
     */
    List<TSysParam> selectAll();

    TSysParam selectById(@Param("pkParamId") String pkParamId) throws Exception;
}