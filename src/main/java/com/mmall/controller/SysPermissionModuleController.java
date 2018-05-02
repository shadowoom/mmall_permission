package com.mmall.controller;

import com.mmall.common.JsonData;
import com.mmall.param.PermissionModuleParam;
import com.mmall.service.SysPermissionModuleService;
import com.mmall.service.SysTreeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * permission
 * com.mmall.controller
 * Created by Zhang Chen
 * 4/26/2018
 */

@Controller
@RequestMapping("/sys/permissionModule")
public class SysPermissionModuleController {

    @Resource
    private SysPermissionModuleService sysPermissionModuleService;

    @Resource
    private SysTreeService sysTreeService;

    @RequestMapping("/permission.page")
    public ModelAndView page() {
        return new ModelAndView("permission");
    }

    @RequestMapping("/save.json")
    @ResponseBody
    public JsonData savePermissionModule(PermissionModuleParam param) {
        sysPermissionModuleService.save(param);
        return JsonData.success();
    }

    @RequestMapping("/update.json")
    @ResponseBody
    public JsonData updatePermissionModule(PermissionModuleParam param) {
        sysPermissionModuleService.update(param);
        return JsonData.success();
    }

    @RequestMapping("/tree.json")
    @ResponseBody
    public JsonData tree() {
        return JsonData.success(sysTreeService.permissionModuleTree());
    }

    @RequestMapping("/delete.json")
    @ResponseBody
    public JsonData delete(@RequestParam("id") int id) {
        sysPermissionModuleService.delete(id);
        return JsonData.success();
    }

}
