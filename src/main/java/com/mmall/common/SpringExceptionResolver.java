package com.mmall.common;

import com.mmall.exception.ParamException;
import com.mmall.exception.PermissionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * permission
 * com.mmall.common
 * Created by Zhang Chen
 * 4/21/2018
 */

@Slf4j
public class SpringExceptionResolver implements HandlerExceptionResolver{

    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                          HttpServletResponse response,
                                          Object handler, Exception ex) {
        String url = request.getRequestURL().toString();
        ModelAndView mv;
        String defaultMsg = "System Error";
        // 这里我们要求项目中所有请求json数据，都使用.json结尾
        if(url.endsWith(".json")) {
            if(ex instanceof PermissionException || ex instanceof ParamException) {
                JsonData jsonData = JsonData.fail(ex.getLocalizedMessage());
                mv = new ModelAndView("jsonView", jsonData.toMap());
            }
            else {
                log.error("Unknown json exception at url: " + url, ex);
                JsonData jsonData = JsonData.fail(defaultMsg);
                mv = new ModelAndView("jsonView", jsonData.toMap());
            }
        }
        else if(url.endsWith(".page")) {  // 这里我们要求项目中所有请求page页面，都使用.page结尾
            log.error("Unknown page exception at url: " + url, ex);
            JsonData jsonData = JsonData.fail(defaultMsg);
            mv = new ModelAndView("exception", jsonData.toMap());
        }
        else {
            log.error("Unknown exception at url: " + url, ex);
            JsonData jsonData = JsonData.fail(defaultMsg);
            mv = new ModelAndView("jsonView", jsonData.toMap());
        }
        return mv;
    }
}
