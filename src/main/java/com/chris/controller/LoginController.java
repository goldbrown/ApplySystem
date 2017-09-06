package com.chris.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.chris.service.UserService;
import com.chris.util.ApplySystemUtils;

//@CrossOrigin
@CrossOrigin
@Controller
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private UserService userService;
	
	@RequestMapping(path = {"/"})
    public String index() {
        return "index";
    }
	
	
	@RequestMapping(path = "/reg")
	@ResponseBody
    public String reg(Model model,
    				  @RequestParam(value = "username") String username,
                      @RequestParam(value = "mail", required = false) String mail,
                      @RequestParam("password") String password,  
                      HttpServletRequest request,
                      HttpServletResponse response) {
		
        try {
        	String ipAddr = request.getRemoteAddr();
            Map<String, Object> map = userService.register(username, password, ipAddr);
            if(map.containsKey("ticket")) {
            	Cookie cookie = new Cookie("ticket", (String) map.get("ticket"));
            	cookie.setPath("/");
            	cookie.setMaxAge(3600 * 24 * 360);
            	response.addCookie(cookie);
            	return ApplySystemUtils.getJSONString(ApplySystemUtils.CODE_OK, map);
            } else {
            	return ApplySystemUtils.getJSONString(ApplySystemUtils.CODE_ERR, map);
            }
            
        } catch (Exception e) {
            logger.error("注册异常" + e.getMessage());
            return ApplySystemUtils.getJSONString(ApplySystemUtils.CODE_ERR, "注册异常");
        }
    }
	
	@RequestMapping(value = "/login")
	@ResponseBody
    public String login(Model model,
    					@RequestParam("username") String username,
                        @RequestParam(value = "mail", required = false) String mail,
                        @RequestParam("password") String password,
                        HttpServletRequest request,
                        HttpServletResponse response) {
            
		try {
			Map<String, Object> map = userService.login(username, password);
			if(map.containsKey("ticket")) {
            	Cookie cookie = new Cookie("ticket", (String) map.get("ticket"));
            	cookie.setPath("/");
            	cookie.setMaxAge(3600 * 24 * 360);
            	response.addCookie(cookie);
            	return ApplySystemUtils.getJSONString(ApplySystemUtils.CODE_OK, map);
            } else {
            	return ApplySystemUtils.getJSONString(ApplySystemUtils.CODE_ERR, map);
            }
		} catch(Exception e) {
			logger.error("登录失败" + e);
			return ApplySystemUtils.getJSONString(ApplySystemUtils.CODE_ERR, "登录失败");
		}
           
    }
	
	@RequestMapping(value = "/logout")
    public String logout(@CookieValue("ticket") String ticket) {
		try {
			userService.logout(ticket);
//	        System.out.println("成功退出");
	        return ApplySystemUtils.getJSONString(ApplySystemUtils.CODE_OK, "成功退出");
		} catch(Exception e) {
			logger.error("退出失败" + e);
			return ApplySystemUtils.getJSONString(ApplySystemUtils.CODE_ERR, "退出失败");
		}
        
    }
	
	
}
