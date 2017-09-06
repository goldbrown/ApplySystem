package com.chris.interceptor;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.chris.dao.LoginTicketDAO;
import com.chris.dao.UserDAO;
import com.chris.model.HostHolder;
import com.chris.model.LoginTicket;
import com.chris.model.User;

@Component
public class PassportInterceptor implements HandlerInterceptor {
    Logger logger = LoggerFactory.getLogger(PassportInterceptor.class);

    @Autowired
    private LoginTicketDAO loginTicketDAO;

    @Autowired
    private UserDAO userDAO;

    //The hostHolder will be used in preHandle() and postHandle()
    @Autowired
    private HostHolder hostHolder;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String ticket = null;
        //判断是否存在要求的cookie名，来自本网站的
        if (httpServletRequest.getCookies() != null) {
            for(Cookie cookie: httpServletRequest.getCookies()) {
                //logger.info(cookie.toString());
                if (cookie.getName().equals("ticket")) {
                    ticket = cookie.getValue();
                   // logger.info(ticket.toString());
                    break;
                }
            }
        }

        //根据cookie值识别用户
        if (ticket != null) {
            //如果ticket过期，直接返回
            LoginTicket loginTicket = loginTicketDAO.selectByTicket(ticket);
            if (loginTicket == null) {
                return false;
            }
            Date expired = loginTicket.getExpired();
            if (loginTicket == null || expired.before(new Date()) || loginTicket.getStatus() != 0) {
                return true;
            }
            int userId = loginTicket.getUserId();
            User user = userDAO.selectById(userId);
            //将user放入hostHolder中很关键，后面根据这个来判断
            hostHolder.setUser(user);
            return true;
        }
        //如果request中没有ticket，则用户没有提交ticket，直接返回
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, 
    		HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
 
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, 
    		HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        //After rendering the view, the user info can be cleared，因为当前线程已经结束，被放回了线程池
        hostHolder.clear();
    }
}