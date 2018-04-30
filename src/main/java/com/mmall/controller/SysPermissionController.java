package com.mmall.controller;

import com.mmall.beans.PageQuery;
import com.mmall.common.JsonData;
import com.mmall.param.PermissionParam;
import com.mmall.service.SysPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

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

}
