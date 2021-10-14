package net.imyan.demo.spring.bean.scope.controller;

import net.imyan.demo.spring.bean.scope.entity.RequestBean;
import net.imyan.demo.spring.bean.scope.entity.SessionBean;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/")
public class Hello {

    @RequestMapping("/setRequestBean")
    public String setRequestBean(HttpServletRequest request, String requestValue) {
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
        Assert.notNull(webApplicationContext, "webApplicationContext不存在");
        RequestBean requestBean = (RequestBean) webApplicationContext.getBean("requestBean");
        requestBean.setValue(requestValue);
        return "requestValue:" + ((RequestBean) webApplicationContext.getBean("requestBean")).getValue();
    }

    @RequestMapping("/getRequestBean")
    public String getRequestBean(HttpServletRequest request) {
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
        Assert.notNull(webApplicationContext, "webApplicationContext不存在");
        return "requestValue:" + ((RequestBean) webApplicationContext.getBean("requestBean")).getValue();
    }

    @RequestMapping("/setSessionBean")
    public String setSessionBean(HttpServletRequest request, String sessionValue) {
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
        Assert.notNull(webApplicationContext, "webApplicationContext不存在");
        SessionBean sessionBean = (SessionBean) webApplicationContext.getBean("sessionBean");
        sessionBean.setValue(sessionValue);
        return "sessionValue:" + ((SessionBean) webApplicationContext.getBean("sessionBean")).getValue();
    }

    @RequestMapping("/getSessionBean")
    public String getSessionBean(HttpServletRequest request) {
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
        Assert.notNull(webApplicationContext, "webApplicationContext不存在");
        return "sessionValue:" + ((SessionBean) webApplicationContext.getBean("sessionBean")).getValue();
    }
}
