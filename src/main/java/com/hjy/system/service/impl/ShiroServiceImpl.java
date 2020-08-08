package com.hjy.system.service.impl;


import com.hjy.common.auth.TokenGenerator;
import com.hjy.common.utils.DateUtil;
import com.hjy.system.dao.TSysRoleMapper;
import com.hjy.system.dao.TSysTokenMapper;
import com.hjy.system.dao.TSysUserMapper;
import com.hjy.system.entity.SysToken;
import com.hjy.system.entity.TSysPerms;
import com.hjy.system.entity.TSysRole;
import com.hjy.system.entity.TSysUser;
import com.hjy.system.service.ShiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author 大誌
 * @Date 2019/3/30 22:18
 * @Version 1.0
 */
@Service
public class ShiroServiceImpl implements ShiroService {
    //12小时后失效
    private final static int EXPIRE = 12;

    @Autowired
    private TSysUserMapper tSysUserMapper;
    @Autowired
    private TSysRoleMapper tSysRoleMapper;
    @Autowired
    private TSysTokenMapper tSysTokenMapper;

    /**
     * 根据userid查找角色
     *
     * @return User
     */
    @Override
    public TSysRole selectRoleByUserId(String pkUserId) {
        return tSysRoleMapper.selectRoleByUserId(pkUserId);
    }

    @Override
    public String selectRoleIdByUserId(String fkUserId) {
        return tSysRoleMapper.selectRoleIdByUserId(fkUserId);
    }

    @Override
    public void deleteToken(String tokenId) {
        tSysTokenMapper.deleteToken(tokenId);
    }
    /**
     * 更新最后一次登录时间
     */
    @Override
    public void updateLoginTime(String userId) {
        TSysUser user = new TSysUser();
        user.setPkUserId(userId);
        user.setLastLoginDate(new Date());
        tSysUserMapper.updateById(user);
    }

    @Override
    public Map<String, Object> selectIndexData(HttpServletResponse resp) {
        return null;
    }

    /**
     * 通过roleid查找权限码
     */
    @Override
    public List<String> selectPermsCodeByRole(String fkRoleId){
        return tSysRoleMapper.selectPermsCodeByRole(fkRoleId);
    }
    @Override
    public List<TSysPerms> selectPermsByRole(String fkRoleId) {
        return tSysRoleMapper.selectPermsByRole(fkRoleId);
    }
    /**
     * 根据username查找用户
     * @return User
     */
    @Override
    public TSysUser selectUserByUsername(String username) {
        return tSysUserMapper.selectUserByUsername(username);
    }

    /**
     * 生成一个token
     *@return Result
     */
    @Override
    public Map<String, Object> createToken(TSysUser tSysUser) {
        Map<String, Object> result = new HashMap<>();
        //生成一个token（session）
        String token = TokenGenerator.generateValue();
        //当前时间
        Date now = new Date();
        //过期时间
        Date expireTime = null;
        try {
            expireTime = DateUtil.addTime(now);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //判断是否生成过token
        SysToken tokenEntity = tSysTokenMapper.selectByUserId(tSysUser.getPkUserId());
        if (tokenEntity == null) {
            tokenEntity = new SysToken();
            tokenEntity.setFkUserId(tSysUser.getPkUserId());
            //保存token
            tokenEntity.setPkTokenId(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);
            tokenEntity.setUsername(tSysUser.getUsername());
            tokenEntity.setPassword(tSysUser.getPassword());
            tokenEntity.setIp(tSysUser.getIp());
            tokenEntity.setFullName(tSysUser.getFullName());
            tokenEntity.setPoliceNum(tSysUser.getPoliceNum());
            tSysTokenMapper.insertToken(tokenEntity);
        } else {
            //更新token
            tokenEntity.setPkTokenId(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);
            tokenEntity.setIp(tSysUser.getIp());
            tSysTokenMapper.updateToken(tokenEntity);
        }
        result.put("token", token);
        result.put("expire", expireTime);
        return result;
    }


    @Override
    public SysToken findByToken(String accessToken) {
        return tSysTokenMapper.findByToken(accessToken);
    }

}
