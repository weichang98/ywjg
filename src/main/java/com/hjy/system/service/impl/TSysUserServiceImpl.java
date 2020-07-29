package com.hjy.system.service.impl;

import com.github.pagehelper.PageInfo;
import com.hjy.common.utils.IDUtils;
import com.hjy.common.utils.page.PageObjectUtils;
import com.hjy.common.utils.page.PageRequest;
import com.hjy.common.utils.page.PageResult;
import com.hjy.common.utils.page.PageUtils;
import com.hjy.system.entity.TSysUser;
import com.hjy.system.dao.TSysUserMapper;
import com.hjy.system.service.TSysUserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * (TSysUser)表服务实现类
 *
 * @author liuchun
 * @since 2020-07-24 17:05:59
 */
@Service
public class TSysUserServiceImpl implements TSysUserService {
    @Autowired
    private TSysUserMapper tSysUserMapper;

    /**
     * 通过ID查询单条数据
     * @return 实例对象
     */
    @Override
    public TSysUser selectById(String pkUserId) throws Exception{
        return this.tSysUserMapper.selectById(pkUserId);
    }

    /**
     * 新增数据
     *
     * @param tSysUser 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(TSysUser tSysUser) throws Exception{
        tSysUser.setPkUserId(IDUtils.currentTimeMillis());
        tSysUser.setCreateTime(new Date());
        tSysUser.setModifyTime(new Date());
        return tSysUserMapper.insertSelective(tSysUser);
    }

    /**
     * 修改数据
     *
     * @param tSysUser 实例对象
     * @return 实例对象
     */
    @Override
    public int updateById(TSysUser tSysUser) throws Exception{
        return tSysUserMapper.updateById(tSysUser);
    }

    /**
     * 通过主键删除数据
     *
     * @param pkUserId 主键
     * @return 是否成功
     */
    @Override
    public int deleteById(String pkUserId) throws Exception{
        return tSysUserMapper.deleteById(pkUserId);
    }

    /**
     * 查询多条数据
     * @return 对象列表
     */
    @Override
    public List<TSysUser> selectAll() throws Exception{
        return this.tSysUserMapper.selectAll();
    }
    /**
     * 通过实体查询多条数据
     * @return 对象列表
     */
    @Override
    public List<TSysUser> selectAllByEntity(TSysUser tSysUser) throws Exception{
        return this.tSysUserMapper.selectAllByEntity(tSysUser);
    }

    @Override
    public String selectUserRoleByUserId(String fk_user_id) {
        return tSysUserMapper.selectUserRoleByUserId(fk_user_id);
    }

    @Override
    public int deleteUserRoleByUserId(String fk_user_id) {
        return tSysUserMapper.deleteUserRoleByUserId(fk_user_id);
    }

    @Override
    public PageResult selectAllPage(PageRequest pageRequest) {
        PageRequest pageRequest2 = PageObjectUtils.getRequest(pageRequest);
        System.err.println("pageRequest"+pageRequest2);
        return tSysUserMapper.selectAllPage(pageRequest2);
    }
}