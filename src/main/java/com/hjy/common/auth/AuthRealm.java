package com.hjy.common.auth;

import com.google.common.collect.Sets;
import com.hjy.system.entity.SysToken;
import com.hjy.system.entity.TSysPerms;
import com.hjy.system.entity.TSysRole;
import com.hjy.system.entity.TSysUser;
import com.hjy.system.service.ShiroService;
import com.hjy.system.service.TSysRoleService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Shiro自定义Realm
 */
@Component
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private ShiroService shiroService;
    @Autowired
    private TSysRoleService tSysRoleService;
    /**
     * 授权 获取用户的角色和权限
     *@param  principals
     *@return org.apache.shiro.authz.AuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.err.println("授权开始----------");
        //1. 从 PrincipalCollection 中来获取登录用户的信息
        TSysUser user = (TSysUser) principals.getPrimaryPrincipal();
        System.err.println("授权获取的用户信息---:"+user);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //从数据库获取角色信息
//        TSysRole role = shiroService.selectRoleByUserId(user.getPkUserId());
        String roleId = shiroService.selectRoleIdByUserId(user.getPkUserId());
        TSysRole role = tSysRoleService.selectById(roleId);
        System.err.println("授权获取的角色信息---:"+role);
        //2.添加角色
        simpleAuthorizationInfo.addRole(role.getRoleName());
        //从数据库获取权限信息
        List<String> perms = new ArrayList<String>();
        try {
            perms = shiroService.selectPermsByRole(role.getPkRoleId());
        }catch (Exception e) {
            e.printStackTrace();
        }
        //3.添加权限
        List<String> percodes = perms;
        System.err.println(percodes);
        simpleAuthorizationInfo.addStringPermissions(percodes);
        return simpleAuthorizationInfo;
    }

    @Override
    /**
     * 认证 判断token的有效性
     *@param  [token]
     *@return org.apache.shiro.authc.AuthenticationInfo
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.err.println("认证开始----------token:");
        //获取token，既前端传入的token
        String accessToken = (String) token.getPrincipal();
        System.err.println("获取的token---:"+accessToken);
        //1. 根据accessToken，查询用户信息
        SysToken tokenEntity = shiroService.findByToken(accessToken);
        Date date = new Date();
        //2. token失效
        if (tokenEntity == null || tokenEntity.getExpireTime().getTime()<date.getTime()) {
            throw new IncorrectCredentialsException("token失效，请重新登录");
        }
        //3. 调用数据库的方法, 从数据库中查询 username 对应的用户记录
        TSysUser user = shiroService.selectUserByUsername(tokenEntity.getUsername());
        //4. 若用户不存在, 则可以抛出 UnknownAccountException 异常
        if (user == null) {
            throw new UnknownAccountException("用户不存在!");
        }
        System.err.println("获取的用户信息---:"+user);
        //5. 根据用户的情况, 来构建 AuthenticationInfo 对象并返回. 通常使用的实现类为: SimpleAuthenticationInfo
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, accessToken, this.getName());
        //将信息放入session中
        SecurityUtils.getSubject().getSession().setAttribute("activeUser",null);
        return info;
    }
}
