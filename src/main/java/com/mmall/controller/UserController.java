package com.mmall.controller;

import com.mmall.model.SysUser;
import com.mmall.service.SysUserService;
import com.mmall.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * permission
 * com.mmall.controller
 * Created by Zhang Chen
 * 4/26/2018
 */

@Controller
public class UserController {

    @Resource
    private SysUserService sysUserService;

    @RequestMapping("/login.page")
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        SysUser sysUser = sysUserService.findUserByKeyword(username);
        String errorMsg = "";
        String ret = request.getParameter("ret");
        // preconditions for log in
        if(StringUtils.isBlank(username)) {
            errorMsg = "用户名不能为空";
        }
        else if(StringUtils.isBlank(password)) {
            errorMsg = "密码不能为空";
        }
        else if(sysUser == null) {
            errorMsg = "查询不到指定用户";
        }
        else if(!sysUser.getUserPassword().equals(MD5Util.encrypt(password))) {
            errorMsg = "用户名或密码错误";
        }
        else if(sysUser.getUserStatus() != 1) {
            errorMsg = "用户已被冻结，请联系管理员";
        }
        else {
            // login success
            request.getSession().setAttribute("user", sysUser);
            if(StringUtils.isNotBlank(ret)) {
                response.sendRedirect(ret);
                return;
            }
            else {
                response.sendRedirect("/admin/index.page");
                return;
            }
        }
        request.setAttribute("error", errorMsg);
        request.setAttribute("username", username);
        if(StringUtils.isNotBlank(ret)) {
            request.setAttribute("ret", ret);
        }
        String path = "/signin.jsp";
        request.getRequestDispatcher(path).forward(request, response);
    }

    @RequestMapping("/logout.page")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getSession().invalidate();
        String path = "/signin.jsp";
        response.sendRedirect(path);
    }

}
