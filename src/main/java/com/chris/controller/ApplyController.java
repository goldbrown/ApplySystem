package com.chris.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.chris.model.Apply;
import com.chris.model.HostHolder;
import com.chris.model.User;
import com.chris.service.ApplyService;
import com.chris.service.UserService;
import com.chris.util.ApplySystemUtils;
import com.opencsv.CSVWriter;

//@CrossOrigin(origins = "http://localhost:8080")
@CrossOrigin
@Controller
public class ApplyController {
	@Autowired
	private ApplyService applyService;
	@Autowired
	private UserService userService;
	@Autowired
	private HostHolder hostHolder;
	
	@RequestMapping(path = "/apply/add") 
	@ResponseBody
	public String addApply(Model model, 
             @RequestParam(value = "periodName", required = false) String periodName,
             @RequestParam("companyName") String companyName,
             @RequestParam(value = "website", required = false) String website,
             @RequestParam("applyDate") String applyDate,
             @RequestParam(value = "endDate", required = false) String endDate,
             @RequestParam(value = "remark", required = false) String remark,
             @RequestParam("status") String status,
             @RequestParam(value = "anticipate", required = false) String anticipate,
             @RequestParam(value = "result", required = false) String result,
             HttpServletRequest request,
             HttpServletResponse response) throws ParseException {
		User user = hostHolder.getUser();
		//判断用户是否登录
		if(user == null) {
			return ApplySystemUtils.getJSONString(ApplySystemUtils.CODE_ERR, "请登录后操作");
		}
//		System.out.println(request.getContentType());
//		System.out.println(request.getCharacterEncoding());
//		System.out.println(request.getQueryString());
		Apply apply = new Apply();
		apply.setUserId(user.getUserId());
		apply.setUsername(user.getUsername());
		apply.setPeriodName(periodName);
		apply.setCompanyName(companyName);
		apply.setWebsite(website);
		apply.setApplyDate(DateUtils.parseDate(applyDate, "yyyy-MM-dd"));
		if(endDate != null) {
			apply.setEndDate(DateUtils.parseDate(endDate, "yyyy-MM-dd"));
		}
		apply.setRemark(remark);
		apply.setStatus(status);
		apply.setAnticipate(anticipate);
		apply.setResult(result);
		
		try {
			applyService.addApply(apply);
			return ApplySystemUtils.getJSONString(ApplySystemUtils.CODE_OK, "添加成功");
		} catch(Exception e) {
			return ApplySystemUtils.getJSONString(ApplySystemUtils.CODE_ERR, "添加失败");
		}	
	}
	
	
	@RequestMapping(path = "/apply/get")
	@ResponseBody
	public String getApply(Model model,
             @RequestParam(value = "periodName", required = false) String periodName,
             HttpServletRequest request,
             HttpServletResponse response) throws ParseException {
		User user = hostHolder.getUser();
		//判断用户是否登录
		if(user == null) {
			return ApplySystemUtils.getJSONString(ApplySystemUtils.CODE_ERR, "请登录后操作");
		}
		int userId = user.getUserId();
		List<Apply> applies = applyService.selectByUserId(userId);
		Map<String, Object> res = new HashMap<>();
		res.put("userId", user.getUserId());
		res.put("username", user.getUsername());
		res.put("mail", user.getMail());
		res.put("apply", applies);
		String resStr = JSONObject.toJSONString(res);
		return resStr;
	}
	@RequestMapping(path = "/apply/download", produces = "text/csv")
	public String downloadApply(Model model,
             @RequestParam(value = "periodName", required = false) String periodName,
             HttpServletRequest request,
             HttpServletResponse response) throws ParseException, IOException {
		User user = hostHolder.getUser();
		//判断用户是否登录
		if(user == null) {
			return ApplySystemUtils.getJSONString(ApplySystemUtils.CODE_ERR, "请登录后操作");
		}
		int userId = user.getUserId();
		List<String[]> applies = applyService.getApplyStrByUserId(userId);
		
		// write csv to response
		response.setCharacterEncoding("GBK");  //考虑到大多数人使用windows系统，设置中文编码环境
		CSVWriter csvWriter = new CSVWriter(response.getWriter(), ',');
		csvWriter.writeAll(applies);
		csvWriter.close();
		return ApplySystemUtils.getJSONString(ApplySystemUtils.CODE_OK, "导出数据成功");
	}
	
	
	
	
	@RequestMapping(path = "/apply/update")
	@ResponseBody
	public String updateApply(Model model, 
			 @RequestParam(value = "applyId") int applyId,
             @RequestParam(value = "periodName", required = false) String periodName,
             @RequestParam(value = "companyName", required = false) String companyName,
             @RequestParam(value = "website", required = false) String website,
             @RequestParam(value = "applyDate", required = false) String applyDate,
             @RequestParam(value = "endDate", required = false) String endDate,
             @RequestParam(value = "remark", required = false) String remark,
             @RequestParam(value = "status", required = false) String status,
             @RequestParam(value = "anticipate", required = false) String anticipate,
             @RequestParam(value = "result", required = false) String result,
             HttpServletRequest request,
             HttpServletResponse response) throws ParseException {
		User user = hostHolder.getUser();
		//判断用户是否登录
		if(user == null) {
			return ApplySystemUtils.getJSONString(ApplySystemUtils.CODE_ERR, "请登录后操作");
		}
		Apply apply = new Apply();
		apply.setApplyId(applyId);
		//是否要判断为空???
		apply.setPeriodName(periodName);
		apply.setWebsite(website);
		apply.setCompanyName(companyName);
		if(StringUtils.isNotEmpty(applyDate)) apply.setApplyDate(DateUtils.parseDate(applyDate,"yyyy-MM-dd"));
		else apply.setApplyDate(null);
		if(StringUtils.isNotEmpty(endDate)) apply.setEndDate(DateUtils.parseDate(endDate,"yyyy-MM-dd"));
		else apply.setEndDate(null);
		apply.setRemark(remark);
		apply.setStatus(status);
		apply.setAnticipate(anticipate);
		apply.setResult(result);
		applyService.updateApply(apply);
		
		List<Apply> applies = applyService.selectByUserId(user.getUserId());
		Map<String, Object> res = new HashMap<>();
		res.put("userId", user.getUserId());
		res.put("mail", user.getMail());
		res.put("apply", applies);
		return JSONObject.toJSONString(res);
	}
	
	@RequestMapping(path = "/apply/remove")
	@ResponseBody
	public String removeApply(Model model,
			 @RequestParam(value = "applyId") int applyId,
             HttpServletRequest request,
             HttpServletResponse response) throws ParseException {
		User user = hostHolder.getUser();
		//判断用户是否登录
		if(user == null) {
			return ApplySystemUtils.getJSONString(ApplySystemUtils.CODE_ERR, "请登录后操作");
		}
		applyService.deleteApply(applyId);
		return ApplySystemUtils.getJSONString(ApplySystemUtils.CODE_OK, "成功删除");
	}
	@RequestMapping(path = "/apply/removeList")
	@ResponseBody
	public String removeApplyList(Model model,
			 @RequestParam("userId") int userId,
			 @RequestParam(value = "applyIds") String applyIds,
             HttpServletRequest request,
             HttpServletResponse response) throws ParseException {
		User user = hostHolder.getUser();
		//判断用户是否登录
		if(user == null) {
			return ApplySystemUtils.getJSONString(ApplySystemUtils.CODE_ERR, "请登录后操作");
		}
		List<Integer> idList = new ArrayList<>();
		String[] idStrs = applyIds.split(",");
		for(int i = 0; i < idStrs.length; i++) {
			String tmp = StringUtils.trim(idStrs[i]);
			idList.add(Integer.parseInt(tmp));
		}
		applyService.deleteApplyList(idList);
		return ApplySystemUtils.getJSONString(ApplySystemUtils.CODE_OK, "成功批量删除");
	}
}
