package com.hjy.system.service.impl;

import com.hjy.common.utils.IDUtils;
import com.hjy.system.entity.TSysPerms;
import com.hjy.system.dao.TSysPermsMapper;
import com.hjy.system.service.TSysPermsService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * (TSysPerms)表服务实现类
 *
 * @author liuchun
 * @since 2020-07-24 09:55:41
 */
@Service
public class TSysPermsServiceImpl implements TSysPermsService {
    @Autowired
    private TSysPermsMapper tSysPermsMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param pkPermsId 主键
     * @return 实例对象
     */
    @Override
    public TSysPerms selectById(Object pkPermsId) {
        return this.tSysPermsMapper.selectById(pkPermsId);
    }

    /**
     * 新增数据
     *
     * @param tSysPerms 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(TSysPerms tSysPerms) {
        tSysPerms.setPkPermsId(IDUtils.currentTimeMillis());
        tSysPerms.setCreateTime(new Date());
        tSysPerms.setModifyTime(new Date());
        return tSysPermsMapper.insertSelective(tSysPerms);
    }

    /**
     * 修改数据
     *
     * @param tSysPerms 实例对象
     * @return 实例对象
     */
    @Override
    public int updateById(TSysPerms tSysPerms) {
        tSysPerms.setModifyTime(new Date());
        //修改人
//        tSysPerms.setModifyUsername();
//        tSysPerms.setFkUserId();
        return tSysPermsMapper.updateById(tSysPerms);
    }

    /**
     * 通过主键删除数据
     *
     * @return 是否成功
     */
    @Override
    public int deleteById(String pk_perms_id) {
        return tSysPermsMapper.deleteById(pk_perms_id);
    }
    
    /**
     * 查询多条数据
     * @return 对象列表
     */
    @Override
    public List<TSysPerms> selectAll() {
        return this.tSysPermsMapper.selectAll();
    }
    /**
     * 通过实体查询多条数据
     * @return 对象列表
     */
    @Override
    public List<TSysPerms> selectAllByEntity(TSysPerms tSysPerms) {
        return this.tSysPermsMapper.selectAllByEntity(tSysPerms);
    }

    @Override
    public List<String> selectDistributeByrole_id(String roleIdStr) {
        return tSysPermsMapper.selectDistributeByrole_id(roleIdStr);
    }
}