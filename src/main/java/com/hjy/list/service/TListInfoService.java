package com.hjy.list.service;

import com.hjy.list.entity.TListInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * (TListInfo)表服务接口
 *
 * @author liuchun
 * @since 2020-08-05 12:45:38
 */
public interface TListInfoService {

    /**
     * 通过ID查询单条数据
     *
     * @param pkListId 主键
     * @return 实例对象
     */
    TListInfo selectById(String pkListId)throws Exception;


    /**
     * 新增数据
     * @param tListInfo 实例对象
     * @return 实例对象
     */
    int insert(TListInfo tListInfo) throws Exception;

    /**
     * 修改数据
     *
     * @param tListInfo 实例对象
     * @return 实例对象
     */
    int updateById(TListInfo tListInfo) throws Exception;

    /**
     * 通过主键删除数据
     *
     * @param pkListId 主键
     * @return 是否成功
     */
    int deleteById(String pkListId) throws Exception;

    /**
     * 查询所有数据
     * @return list
     */
     List<TListInfo> selectAll() throws Exception;
     /**
     * 通过实体查询所有数据
     * @return list
     */
     List<TListInfo> selectAllByEntity(TListInfo tListInfo)throws Exception;

    List<TListInfo> selectWaitApproval()throws Exception;

    int insertFile(TListInfo tListInfo, MultipartFile[] files)throws Exception;
}