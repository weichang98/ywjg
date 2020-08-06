package com.hjy.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.hjy.common.domin.CommonResult;
import com.hjy.common.exception.FebsException;
import com.hjy.common.utils.IPUtil;
import com.hjy.common.utils.PasswordEncryptUtils;
import com.hjy.system.entity.ActiveUser;
import com.hjy.system.entity.TSysUser;
import com.hjy.system.service.ShiroService;
import com.hjy.system.service.TSysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@RestController
public class LoginController {
    @Autowired
    private ShiroService shiroService;
    /**
     *处理登录请求
     * @return
     */
    /**
     * 登录
     */
    @ApiOperation(value = "登陆", notes = "参数:用户名 密码")
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody TSysUser tSysUser,HttpServletRequest request) throws UnknownHostException, SocketException {
        Map<String, Object> result = new HashMap<>();
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
            result.put("code", 444);
            result.put("status", "error");
            result.put("msg", "账号不存在");
        } else if(!user.getPassword().equals(password)) {
            result.put("code", 445);
            result.put("status", "error");
            result.put("msg", "密码错误");
        }else {
            //生成token，并保存到数据库
            result = shiroService.createToken(user);
            result.put("code", 200);
            result.put("status", "success");
            result.put("msg", "登陆成功");
        }
        return result;
    }
    /**
     *登录成功
     * @return
     * throws FebsException
     */
    @RequiresPermissions({"index"})
    @PostMapping("/index")
    public CommonResult index(HttpSession session) throws FebsException {
        //获取当前登录用户
        ActiveUser activeUser = (ActiveUser) session.getAttribute("activeUser");
        return new CommonResult(200,"success","获取数据成功!",activeUser);
    }
    /**
     *退出系统
     * @return
     */
    @PostMapping("/logout")
    public CommonResult logout() throws FebsException {
        //清空缓存
        //取出当前验证主体
        Subject subject = SecurityUtils.getSubject();
        //不为空，执行一次logout的操作，将session全部清空
        if (subject != null) {
            subject.logout();
        }
        return new CommonResult(200,"success","成功退出系统!",null);
    }
    /**
     *处理登录请求
     * @return
     */
//    @PostMapping(value = "/login")
//    public CommonResult login(HttpServletRequest request, HttpServletResponse resp, RedirectAttributes attr, HttpSession session,
//                              @RequestBody TSysUser user
//    ) throws IOException {
//        boolean rememberMe =true;
//        String username = user.getUsername();
//        String passwordN0 = user.getPassword();
//        String password = PasswordEncryptUtils.MyPasswordEncryptUtil(username,passwordN0);
//        //1、获取subject
//        Subject subject = SecurityUtils.getSubject();
//        //2、封装用户数据
//        UsernamePasswordToken token = new UsernamePasswordToken(username,password,rememberMe);
//        //3、执行登录方法
//        try {
//            //交给Realm处理--->执行它的认证方法
//            subject.login(token);
//            //登录成功
//            TSysUser dbuser = shiroService.selectUserByUsername(username);
////            shiroService.createToken(dbuser.getPkUserId());
////            session.setAttribute("currentUser",dbuser);
////            session.setMaxInactiveInterval(60*60*24);
////            Cookie cookie = new Cookie("username", dbuser.getUsername());
////            // 设置一个月有效
////            cookie.setMaxAge(60*60*24*30);
////            cookie.setPath("/");
////            // 服务器返回给浏览器cookie以便下次判断
////            resp.addCookie(cookie);
////            String sessionId = (String) subject.getSession().getId();
//            JSONObject jsonObject = new JSONObject();
////            jsonObject.put("token",sessionId);
////            jsonObject.put("username",dbuser.getUsername());
//            jsonObject.put("user",dbuser);
//            return new CommonResult(200,"success！","登录成功",jsonObject);
//        }catch (UnknownAccountException e){
//            //登录失败:用户名不存在
//            return new CommonResult(444,"error","用户名不存在！",null);
//        }catch (IncorrectCredentialsException e){
//            //登录失败：密码错误
//            return new CommonResult(445,"error","密码错误！",null);
//        }
//    }
}
