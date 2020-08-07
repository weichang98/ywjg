package com.hjy.system.service;


import com.hjy.system.entity.SysToken;
import com.hjy.system.entity.TSysPerms;
import com.hjy.system.entity.TSysRole;
import com.hjy.system.entity.TSysUser;

import java.util.List;
import java.util.Map;

/**
 * @Author 大誌
 * @Date 2019/3/30 22:18
 * @Version 1.0
 */
public interface ShiroService {
//     /**
//      * Find user by username
//      * @param username
//      * @return
//      */
//     TSysUser findByUsername(String username);
//
//     /**
//      * create token by userId
//      * @param userId
//      * @return
//      */
//     Map<String,Object> createToken(Integer userId);
//
//     /**
//      * logout
//      * @param token
//      */
//     void logout(String token);

     /**
      * find token by token
      * @param accessToken
      * @return
      */
     SysToken findByToken(String accessToken);

     //通过角色id从数据库获取权限信息,只含权限码
     List<String> selectPermsCodeByRole(String fkRoleId);
     List<TSysPerms> selectPermsByRole(String fkRoleId);
     /**
      * find user by username
      * @param username
      * @return
      */
     TSysUser selectUserByUsername(String username);

     Map<String ,Object> createToken(TSysUser user);
     //从数据库获取角色信息
     TSysRole selectRoleByUserId(String pkUserId);

     String selectRoleIdByUserId(String pkUserId);
     //删除token
     void deleteToken(String tokenId);
     //更新最后一次登录时间
     void updateLoginTime(String userId);
}
