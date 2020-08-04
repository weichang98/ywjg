package com.hjy.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.hjy.common.domin.CommonResult;
import com.hjy.common.exception.FebsException;
import com.hjy.common.utils.PasswordEncryptUtils;
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
    public Map<String, Object> login(@RequestBody TSysUser tSysUser) {
        Map<String, Object> result = new HashMap<>();
        boolean rememberMe =true;
        String username = tSysUser.getUsername();
        String passwordN0 = tSysUser.getPassword();
        String password = PasswordEncryptUtils.MyPasswordEncryptUtil(username,passwordN0);
//        System.err.println("加密密码：--------"+password);
        //用户信息
        TSysUser user = shiroService.selectUserByUsername(username);
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
     */
    @RequiresPermissions({"index"})
    @PostMapping("/index")
    public CommonResult tSysUserAdd() throws FebsException {

        return new CommonResult(200,"success","获取数据成功!",null);
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
