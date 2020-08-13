package com.hjy.list.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.hjy.common.utils.IDUtils;
import com.hjy.common.utils.file.MyFileUtil;
import com.hjy.common.utils.page.PageObjectUtils;
import com.hjy.common.utils.page.PageRequest;
import com.hjy.common.utils.page.PageResult;
import com.hjy.common.utils.page.PageUtil;
import com.hjy.list.entity.TListInfo;
import com.hjy.list.dao.TListInfoMapper;
import com.hjy.list.service.TListInfoService;
import com.hjy.system.entity.ActiveUser;
import com.hjy.system.entity.TSysUser;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
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
        tListInfo.setApprovalTime(new Date());
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

    @Override
    public PageResult selectWaitApproval(String param) throws Exception{
        int total = tListInfoMapper.selectWaitApprovalSize();
        PageResult result = PageUtil.getPageResult(param,total);
        List<TListInfo> listInfos = tListInfoMapper.selectWaitApproval(result.getStartRow(),result.getEndRow());
        result.setContent(listInfos);
        return result;
    }

    @Override
    public int insertFile(TListInfo tListInfo, MultipartFile[] files)throws Exception {
        tListInfo.setPkListId(IDUtils.currentTimeMillis());
        tListInfo.setCreateTime(new Date());
        if(files!=null){
            String []strings = MyFileUtil.FileUtil(tListInfo.getListType(),files,tListInfo.getIdCard());
            tListInfo.setApplyBook(strings[0]);
            tListInfo.setCodeCertificates(strings[1]);
        }else{
            tListInfo.setApplyBook(null);
            tListInfo.setCodeCertificates(null);
        }
        return tListInfoMapper.insertSelective(tListInfo);
    }

    @Override
    public PageResult selectAllPage(String param) {
        JSONObject json = JSON.parseObject(param);
        //实体数据
        String listType = String.valueOf(json.get("listType"));
        String fullName = String.valueOf(json.get("fullName"));
        String IdCard = String.valueOf(json.get("IdCard"));
        TListInfo listInfo = new TListInfo();
        listInfo.setListType(listType);
        listInfo.setFullName(fullName);
        listInfo.setIdCard(IdCard);
        //分页记录条数
        int total = tListInfoMapper.selectSize(listInfo);
        PageResult result = PageUtil.getPageResult(param,total);
        List<TListInfo> listInfos = tListInfoMapper.selectAllPage(result.getStartRow(),result.getEndRow(),listType,fullName,IdCard);
        result.setContent(listInfos);
        return result;
    }

}