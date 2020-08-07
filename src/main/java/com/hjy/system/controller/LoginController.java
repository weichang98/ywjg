package com.hjy.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.hjy.common.domin.CommonResult;
import com.hjy.common.exception.FebsException;
import com.hjy.common.utils.IPUtil;
import com.hjy.common.utils.PasswordEncryptUtils;
import com.hjy.hall.service.THallTakenumberService;
import com.hjy.system.entity.ActiveUser;
import com.hjy.system.entity.TSysUser;
import com.hjy.system.service.ShiroService;
import com.hjy.system.service.TSysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "Shiro权限管理")
@Slf4j
@RestController
public class LoginController {
    @Autowired
    private ShiroService shiroService;
    @Autowired
    private THallTakenumberService takeNumberService;
    /**
     * 登录
     */
    @ApiOperation(value = "登陆", notes = "参数:用户名 密码")
    @PostMapping("/login")
    public CommonResult login(@RequestBody TSysUser tSysUser,HttpServletRequest request) throws UnknownHostException, SocketException {
        boolean rememberMe =true;
        String username = tSysUser.getUsername();
        String passwordN0 = tSysUser.getPassword();
        String password = PasswordEncryptUtils.MyPasswordEncryptUtil(username,passwordN0);
        //用户信息
        TSysUser user = shiroService.selectUserByUsername(username);
        String ip1= IPUtil.getIpAddress1(request);
        String ip2= IPUtil.getIpAddress2(request);
        String ip3= IPUtil.getIpAddress3(request);
        String ip4= IPUtil.getIpAddress4(request);
        String ip5= IPUtil.getIpAddress5();
        String ip6= IPUtil.getIpAddress6();
        System.err.println("ip1：--------"+ip1);
        System.err.println("ip2：--------"+ip2);
        System.err.println("ip3：--------"+ip3);
        System.err.println("ip4：--------"+ip4);
        System.err.println("ip5：--------"+ip5);
        System.err.println("ip6：--------"+ip6);
        user.setIp(ip3);
        //账号不存在、密码错误
        if (user == null) {
            return new CommonResult(444,"error","账号不存在");
        } else if(!user.getPassword().equals(password)) {
            return new CommonResult(445,"error","密码错误");
        }else if(user.getEnableStatus().equals("0")){
            return new CommonResult(446,"error","该账户已被管理员禁用，请联系管理员");
        }else {
            //生成token，并保存到数据库
            Map<String, Object> result = shiroService.createToken(user);
            return new CommonResult(200,"success","登陆成功",result);
        }
    }
    /**
     *登录成功
     * @return
     * throws FebsException
     */
    @RequiresPermissions({"index"})
    @PostMapping("/index")
    public CommonResult index(HttpSession session,HttpServletResponse resp) throws FebsException {
        //获取当前登录用户
        ActiveUser activeUser = (ActiveUser) session.getAttribute("activeUser");
        //获取主页统计数据
        //顶部各个业务类型统计
        Map<String,Object> businessMap = new HashMap<>();
//        try {
//        businessMap = takeNumberService.selectBusinessNum();
//        }catch (Exception e) {
//            String message = "系统内部异常";
//            log.error(message, e);
//            throw new FebsException(message);
//        }
//        Cookie cookie = new Cookie("businessType", "");
//        // 设置一天有效
//        cookie.setMaxAge(60*60*24);
//        cookie.setPath("/");
//        // 服务器返回给浏览器cookie
//        resp.addCookie(cookie);
//        session.setAttribute("businessType",businessMap);
//        session.setMaxInactiveInterval(60*60*24);
        return new CommonResult(200,"success","获取数据成功!",activeUser);
    }
    /**
     *退出系统
     * @return
     */
    @PostMapping("/logout")
    public CommonResult logout(HttpSession session) throws FebsException {
        ActiveUser activeUser = (ActiveUser) session.getAttribute("activeUser");
        //清空缓存
        //取出当前验证主体
        Subject subject = SecurityUtils.getSubject();
        //不为空，执行一次logout的操作，将session全部清空
        if (subject != null) {
            subject.logout();
        }
        try{
            //删除token
            shiroService.deleteToken(activeUser.getTokenId());
            //更新最后一次登录时间
            shiroService.updateLoginTime(activeUser.getUserId());
            return new CommonResult(200,"success","成功退出系统!",null);
        }catch (Exception e) {
            String message = "系统内部异常";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
}
