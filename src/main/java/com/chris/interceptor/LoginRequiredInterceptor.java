package com.chris.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.chris.model.HostHolder;
import com.chris.util.ApplySystemUtils;

@Component
public class LoginRequiredInterceptor extends HandlerInterceptorAdapter {
	@Autowired
    private HostHolder hostHolder;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest,
    		HttpServletResponse httpServletResponse, Object o) throws Exception {
        if(hostHolder.getUser() == null) {
        	httpServletResponse.sendError(ApplySystemUtils.CODE_ERR, "登录后再操作");
//        	System.out.println("prehandle");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
    		HttpServletResponse httpServletResponse,
    		Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
