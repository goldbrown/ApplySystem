package com.chris.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chris.service.UserService;

@Controller
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private UserService userService;
	
//	@RequestMapping(path = "/index", method = RequestMethod.GET)
//	@ResponseBody
//    public String index() {
//        return "index";
//    }
	
	
	@RequestMapping(path = "/reg", method = RequestMethod.POST)
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
            return "成功注册";
        } catch (Exception e) {
            logger.error("注册异常" + e.getMessage());
            return "注册异常";
        }
    }
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
    public String login(Model model,
    					@RequestParam("username") String username,
                        @RequestParam(value = "mail", required = false) String mail,
                        @RequestParam("password") String password,
                        HttpServletRequest request,
                        HttpServletResponse response) {
            Map<String, Object> map = userService.login(username, password);
            return "OK";
           
    }
	
	
}
