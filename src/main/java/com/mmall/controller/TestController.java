package com.mmall.controller;

import com.mmall.common.ApplicationContextHelper;
import com.mmall.common.JsonData;
import com.mmall.dao.SysPermissionModuleMapper;
import com.mmall.exception.ParamException;
import com.mmall.exception.PermissionException;
import com.mmall.model.SysPermissionModule;
import com.mmall.param.TestVo;
import com.mmall.util.BeanValidator;
import com.mmall.util.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * permission
 * com.mmall.controller
 * Created by Zhang Chen
 * 4/21/2018
 */
@Controller
@RequestMapping("/test")
@Slf4j
public class TestController {

    @RequestMapping("/hello.json")
    @ResponseBody
    public JsonData hello() {
        log.info("hello");
        throw new PermissionException("test exception");
        // return JsonData.success("hello, permission");
    }

    @RequestMapping("/validate.json")
    @ResponseBody
    public JsonData validate(TestVo vo) throws ParamException{
        log.info("validate");
        SysPermissionModuleMapper permissionModuleMapper = ApplicationContextHelper.popBean(SysPermissionModuleMapper.class);
        SysPermissionModule permissionModule = permissionModuleMapper.selectByPrimaryKey(1);
        log.info(JsonMapper.obj2String(permissionModule));
        BeanValidator.check(vo);
        return JsonData.success("test validate");
    }
}
