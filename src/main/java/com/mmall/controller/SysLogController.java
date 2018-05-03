package com.mmall.controller;

import com.mmall.beans.LogType;
import com.mmall.beans.PageQuery;
import com.mmall.common.JsonData;
import com.mmall.common.RequestHolder;
import com.mmall.model.*;
import com.mmall.param.SearchLogParam;
import com.mmall.service.SysLogService;
import com.mmall.util.IpUtil;
import com.mmall.util.JsonMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * permission
 * com.mmall.controller
 * Created by Zhang Chen
 * 5/3/2018
 */

@Controller
@RequestMapping("/sys/log")
public class SysLogController {

    @Resource
    private SysLogService sysLogService;

    @RequestMapping("/page.json")
    @ResponseBody
    public JsonData searchPage(SearchLogParam param, PageQuery page) {
        return JsonData.success(sysLogService.searchPageList(param,page));
    }

    @RequestMapping("/log.page")
    public ModelAndView page(){
        return new ModelAndView("log");
    }

    @RequestMapping("/recover.json")
    @ResponseBody
    public JsonData recoverLog(@RequestParam("id") int id) {
        sysLogService.recoverLig(id);
        return JsonData.success();
    }

}
