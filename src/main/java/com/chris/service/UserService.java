package com.chris.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chris.dao.UserDAO;
import com.chris.model.User;
import com.chris.util.ApplySystemUtils;

@Service
public class UserService {
	@Autowired
	private UserDAO userDAO;
	
	public User getUser(int userId) {
		User user = userDAO.selectById(userId);
		return user;
	}
	public User getUser(String mail) {
		User user = userDAO.selectByMail(mail);
		return user;
	}
	
	public Map<String, Object> register(String mail, String password, String ipAddr) {
		Map<String, Object> map = new HashMap<>();
		if(StringUtils.isBlank(mail)) {
			map.put("msg", "email为空");
			return map;
		}
		if(StringUtils.isBlank(password)) {
			map.put("msg", "密码为空");
			return map;
		}
		User user = getUser(mail);
		if(user != null) {
			map.put("msg", "用户的email已经被注册过");
			return map;
		}
		user = new User();
        user.setMail(mail);
        user.setSalt(UUID.randomUUID().toString().substring(0, 5));
        user.setPassword(ApplySystemUtils.md5(password + user.getSalt()));
        user.setIpAddr(ipAddr);

        userDAO.addUser(user);
        map.put("msg", "注册成功");
		return map;
	}
	
    public Map<String, Object> login(String mail, String password) {
        Map<String, Object> map = new HashMap<>();
        if(StringUtils.isBlank(mail)) {
            map.put("msg", "错误：邮箱为空!");
            return map;
        }
        if (StringUtils.isBlank(password)) {
            map.put("msg", "错误：密码为空！");
            return map;
        }
        User user = userDAO.selectByMail(mail);

        if(!mail.equals(user.getMail())
                || !ApplySystemUtils.md5(password + user.getSalt()).equals(user.getPassword())) {
            map.put("msg", "邮箱或者密码不正确！");
            return map;
        }
        //Each time login, store a temp ticket(token) for this session, which will expire soon
        int userId = getUser(mail).getUserId();
        map.put("userId", userId);
        return map;
    }
}
