package com.mmall.controller;

import com.google.common.collect.Maps;
import com.mmall.beans.PageQuery;
import com.mmall.beans.PageResult;
import com.mmall.common.JsonData;
import com.mmall.param.UserParam;
import com.mmall.service.SysRoleService;
import com.mmall.service.SysTreeService;
import com.mmall.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * permission
 * com.mmall.controller
 * Created by Zhang Chen
 * 4/25/2018
 */
@Controller
@RequestMapping("/sys/user")
@Slf4j
public class SysUserController {

    @Resource
    private SysUserService sysUserService;

    @Resource
    private SysTreeService sysTreeService;

    @Resource
    private SysRoleService sysRoleService;

    @RequestMapping("/save.json")
    @ResponseBody
    public JsonData saveUser(UserParam param) {
        sysUserService.save(param);
        return JsonData.success();
    }

    @RequestMapping("/update.json")
    @ResponseBody
    public JsonData updateUser(UserParam param) {
        sysUserService.update(param);
        return JsonData.success();
    }

    @RequestMapping("/page.json")
    @ResponseBody
    public JsonData pagination(@RequestParam("deptId")int deptId, PageQuery pageQuery) {
        PageResult result = sysUserService.getPageByDeptId(deptId, pageQuery);
        return JsonData.success(result);
    }

    @RequestMapping("/permissionAndRole.json")
    @ResponseBody
    public JsonData permissions(@RequestParam("userId") int userId) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("permissions", sysTreeService.userPermissionTree(userId));
        map.put("roles", sysRoleService.getRoleListByUserId(userId));
        return JsonData.success(map);
    }

}
