package com.hjy.hall.dao;

import com.hjy.hall.entity.THallQueue;
import com.hjy.hall.entity.THallQueueCount;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * (THallQueue)表数据库访问层
 *
 * @author makejava
 * @since 2020-07-29 14:33:19
 */
public interface THallQueueMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param pkQueueId 主键
     * @return 实例对象
     */
    THallQueue selectById(String pkQueueId);

    /**
     * 新增数据
     *
     * @param tHallQueue 实例对象
     * @return 影响行数
     */
    int insert(THallQueue tHallQueue);

    int insertSelective(THallQueue tHallQueue);

    /**
     * 修改数据
     *
     * @param tHallQueue 实例对象
     * @return 影响行数
     */
    int updateById(THallQueue tHallQueue);

    /**
     * 通过主键删除数据
     *
     * @param pkQueueId 主键
     * @return 影响行数
     */
    int deleteById(String pkQueueId);

    /**
     * 查询所有行数据
     *
     * @return 对象列表
     */
    List<THallQueue> selectAll();

    /**
     * 通过实体作为筛选条件查询
     *
     * @param tHallQueue 实例对象
     * @return 对象列表
     */
    List<THallQueue> selectAllByEntity(THallQueue tHallQueue);

    THallQueue getByOrdinalAndDatestr(@Param("Ordinal") String Ordinal,@Param("DateStr") String DateStr);

    List<THallQueue> queryByTime(@Param("startTime") String startTime,@Param("endTime") String endTime);

    THallQueue getNowNum(@Param("windowName") String windowName);

    List<THallQueueCount> totalCount(@Param("queryStart") String queryStart, @Param("queryEnd") String queryEnd);

    List<THallQueueCount> realCount(@Param("queryStart") String queryStart, @Param("queryEnd") String queryEnd);

    List<THallQueueCount> nullCount(@Param("queryStart")String queryStart, @Param("queryEnd")String queryEnd);

    List<THallQueueCount> backCount(@Param("queryStart")String queryStart, @Param("queryEnd")String queryEnd);

    List<THallQueueCount> windowNumToday(@Param("queryStart") String startTimeStr,  @Param("queryEnd")String endTimeStr);

    List<THallQueueCount> agentNumToday(@Param("queryStart") String startTimeStr,  @Param("queryEnd")String endTimeStr,@Param("overTime") int overTime);

    int handleNum(THallQueue tHallQueue);

    int agentNum(THallQueue tHallQueue);
}