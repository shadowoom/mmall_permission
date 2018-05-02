package com.mmall.controller;

import com.google.common.collect.Maps;
import com.mmall.beans.PageQuery;
import com.mmall.common.JsonData;
import com.mmall.model.SysRole;
import com.mmall.param.PermissionParam;
import com.mmall.service.SysPermissionService;
import com.mmall.service.SysRoleService;
import com.mmall.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * permission
 * com.mmall.controller
 * Created by Zhang Chen
 * 4/26/2018
 */

@Controller
@RequestMapping("/sys/permission")
@Slf4j
public class SysPermissionController {

    @Resource
    private SysPermissionService sysPermissionService;

    @Resource
    private SysRoleService sysRoleService;

    @Resource
    private SysUserService sysUserService;

    @RequestMapping("/save.json")
    @ResponseBody
    public JsonData savePermission(PermissionParam param) {
        sysPermissionService.save(param);
        return JsonData.success();
    }

    @RequestMapping("/update.json")
    @ResponseBody
    public JsonData updatePermission(PermissionParam param) {
        sysPermissionService.update(param);
        return JsonData.success();
    }

    @RequestMapping("/page.json")
    @ResponseBody
    public JsonData list(@RequestParam("permissionModuleId")Integer permissionModuleId, PageQuery pageQuery) {
        return JsonData.success(sysPermissionService.getPageByPermissionModuleId(permissionModuleId, pageQuery));
    }

    @RequestMapping("/roleAndUser.json")
    @ResponseBody
    public JsonData permissions(@RequestParam("permissionId") int permissionId) {
        Map<String, Object> map = Maps.newHashMap();
        List<SysRole> roleList = sysRoleService.getRoleListByPermissionId(permissionId);
        map.put("roles", roleList);
        map.put("users", sysUserService.getUserListByRoleList(roleList));
        return JsonData.success(map);
    }

}
