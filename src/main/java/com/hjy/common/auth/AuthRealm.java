package com.hjy.common.auth;

import com.google.common.collect.Sets;
import com.hjy.system.entity.*;
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
        //1. 从 PrincipalCollection 中来获取登录用户的信息
        TSysUser user = (TSysUser) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //从数据库获取角色信息
        try{
            String roleId = shiroService.selectRoleIdByUserId(user.getPkUserId());
            TSysRole role = tSysRoleService.selectById(roleId);
            //2.添加角色
            simpleAuthorizationInfo.addRole(role.getRoleName());
            //从数据库获取权限码信息
            List<String> perms = new ArrayList<String>();
            try {
                perms = shiroService.selectPermsCodeByRole(role.getPkRoleId());
            }catch (Exception e) {
                e.printStackTrace();
            }
            //3.添加权限
            List<String> percodes = perms;
            simpleAuthorizationInfo.addStringPermissions(percodes);
            ActiveUser activeUser = (ActiveUser) SecurityUtils.getSubject().getSession().getAttribute("activeUser");
            activeUser.setRoleName(role.getRoleName());
            //权限类型,1代表角色权限，0代表自由权限
            if(role != null){
                activeUser.setPermsType("0");
            }
            activeUser.setPermsType("1");
            //从数据库获取权限信息
            List<TSysPerms> permsList = new ArrayList<>();
            try {
                permsList = shiroService.selectPermsByRole(role.getPkRoleId());
            }catch (Exception e) {
                e.printStackTrace();
            }
            //设置菜单和权限
            activeUser.setMenu(permsList);
            activeUser.setPermsCode(percodes);
            SecurityUtils.getSubject().getSession().setAttribute("activeUser",activeUser);
        }catch (Exception e){

        }
        return simpleAuthorizationInfo;
    }

    @Override
    /**
     * 认证 判断token的有效性
     *@param  [token]
     *@return org.apache.shiro.authc.AuthenticationInfo
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取token，既前端传入的token
        String accessToken = (String) token.getPrincipal();
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
        //5. 根据用户的情况, 来构建 AuthenticationInfo 对象并返回. 通常使用的实现类为: SimpleAuthenticationInfo
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, accessToken, this.getName());
        //将信息放入session中
        ActiveUser activeUser = new ActiveUser();
        activeUser.setUserId(user.getPkUserId());
        activeUser.setUsername(user.getUsername());
        activeUser.setPassword(user.getPassword());
        activeUser.setTel(user.getTel());
        activeUser.setIDcard(user.getIDcard());
        activeUser.setFullName(user.getFullName());
        activeUser.setUnit(user.getUnit());
        activeUser.setPoliceNum(user.getPoliceNum());
        activeUser.setTokenId(accessToken);
        SecurityUtils.getSubject().getSession().setAttribute("activeUser",activeUser);
        return info;
    }
}
