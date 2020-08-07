package com.hjy.list.service.impl;

import com.hjy.common.utils.IDUtils;
import com.hjy.common.utils.file.MyFileUtil;
import com.hjy.list.entity.TListInfo;
import com.hjy.list.dao.TListInfoMapper;
import com.hjy.list.service.TListInfoService;
import com.hjy.system.entity.ActiveUser;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * (TListInfo)表服务实现类
 *
 * @author liuchun
 * @since 2020-08-05 12:45:38
 */
@Service
public class TListInfoServiceImpl implements TListInfoService {
    @Autowired
    private TListInfoMapper tListInfoMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param pkListId 主键
     * @return 实例对象
     */
    @Override
    public TListInfo selectById(String pkListId) throws Exception{
        return this.tListInfoMapper.selectById(pkListId);
    }

    /**
     * 新增数据
     *
     * @param tListInfo 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(TListInfo tListInfo) throws Exception{
        tListInfo.setPkListId(IDUtils.currentTimeMillis());
        return tListInfoMapper.insertSelective(tListInfo);
    }

    /**
     * 修改数据
     *
     * @param tListInfo 实例对象
     * @return 实例对象
     */
    @Override
    public int updateById(TListInfo tListInfo) throws Exception{
        return tListInfoMapper.updateById(tListInfo);
    }

    /**
     * 通过主键删除数据
     *
     * @param pkListId 主键
     * @return 是否成功
     */
    @Override
    public int deleteById(String pkListId) throws Exception{
        return tListInfoMapper.deleteById(pkListId);
    }
    
    /**
     * 查询多条数据
     * @return 对象列表
     */
    @Override
    public List<TListInfo> selectAll() throws Exception{
        return this.tListInfoMapper.selectAll();
    }
    /**
     * 通过实体查询多条数据
     * @return 对象列表
     */
    @Override
    public List<TListInfo> selectAllByEntity(TListInfo tListInfo) throws Exception{
        System.err.println("-----"+tListInfo);
        return this.tListInfoMapper.selectAllByEntity(tListInfo);
    }

    @Override
    public List<TListInfo> selectWaitApproval() throws Exception{
        return tListInfoMapper.selectWaitApproval();
    }

    @Override
    public int insertFile(TListInfo tListInfo, MultipartFile[] files)throws Exception {
        tListInfo.setPkListId(IDUtils.currentTimeMillis());
        if(files!=null){
            String []strings = MyFileUtil.FileUtil(tListInfo.getListType(),files,tListInfo.getIdCard());
            tListInfo.setApplyBook(strings[0]);
            tListInfo.setCodeCertificates(strings[1]);
        }else{
            tListInfo.setApplyBook(null);
            tListInfo.setCodeCertificates(null);
        }
        System.err.println(tListInfo);
        return tListInfoMapper.insertSelective(tListInfo);
    }

}