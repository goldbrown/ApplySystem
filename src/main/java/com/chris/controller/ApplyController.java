package com.chris.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.chris.model.Apply;
import com.chris.model.User;
import com.chris.service.ApplyService;
import com.chris.service.UserService;

@Controller
public class ApplyController {
	@Autowired
	private ApplyService applyService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(path = "/apply/add", method = RequestMethod.POST)
	@ResponseBody
	public String addApply(Model model, 
			 @RequestParam("user_id") int userId,
			 @RequestParam("mail") String mail,
             @RequestParam("period_name") String periodName,
             @RequestParam("company_name") String companyName,
             @RequestParam("apply_date") String applyDate,
             @RequestParam(value = "end_date", required = false) String endDate,
             @RequestParam("recommend") String recommend,
             @RequestParam("status") String status,
             @RequestParam(value = "anticipate", required = false) String anticipate,
             @RequestParam(value = "result", required = false) String result,
             HttpServletRequest request,
             HttpServletResponse response) throws ParseException {
		Apply apply = new Apply();
		apply.setUserId(userId);
		apply.setPeriodName(periodName);
		apply.setCompanyName(companyName);
		apply.setApplyDate(DateUtils.parseDate(applyDate, "yyyy-MM-dd"));
		if(endDate != null) {
			apply.setEndDate(DateUtils.parseDate(endDate, "yyyy-MM-dd"));
		}
		apply.setRecommend(recommend);
		apply.setStatus(status);
		apply.setAnticipate(anticipate);
		apply.setResult(result);
		applyService.addApply(apply);
		return "OK";
	}
	@RequestMapping(path = "/apply/get", method = RequestMethod.POST)
	@ResponseBody
	public String getApply(Model model,
			 @RequestParam("user_id") int userId,
             @RequestParam("period_name") String periodName,
             HttpServletRequest request,
             HttpServletResponse response) throws ParseException {
		List<Apply> applies = applyService.selectByUserId(userId);
		User user = userService.getUser(userId);
		Map<String, Object> res = new HashMap<>();
		res.put("user_id", user.getUserId());
		res.put("mail", user.getMail());
		res.put("apply", applies);
		return JSONObject.toJSONString(res);
	}
	
	
	@RequestMapping(path = "/apply/update", method = RequestMethod.POST)
	@ResponseBody
	public String addApply(Model model, 
			 @RequestParam("user_id") int userId,
			 @RequestParam(value = "apply_id") int applyId,
             @RequestParam(value = "period_name", required = false) String periodName,
             @RequestParam(value = "company_name", required = false) String companyName,
             @RequestParam(value = "apply_date", required = false) String applyDate,
             @RequestParam(value = "end_date", required = false) String endDate,
             @RequestParam(value = "recommend", required = false) String recommend,
             @RequestParam(value = "status", required = false) String status,
             @RequestParam(value = "anticipate", required = false) String anticipate,
             @RequestParam(value = "result", required = false) String result,
             HttpServletRequest request,
             HttpServletResponse response) throws ParseException {
		Apply apply = new Apply();
		apply.setApplyId(applyId);
		if(periodName != null) apply.setPeriodName(periodName);
		if(companyName != null) apply.setCompanyName(companyName);
		if(applyDate != null) apply.setApplyDate(DateUtils.parseDate(applyDate, "yyyy-MM-dd"));
		if(endDate != null) apply.setEndDate(DateUtils.parseDate(endDate, "yyyy-MM-dd"));
		if(recommend != null) apply.setRecommend(recommend);
		if(status != null) apply.setStatus(status);
		if(anticipate != null) apply.setAnticipate(anticipate);
		if(result != null) apply.setResult(result);
		applyService.updateApply(apply);
		
		List<Apply> applies = applyService.selectByUserId(userId);
		User user = userService.getUser(userId);
		Map<String, Object> res = new HashMap<>();
		res.put("user_id", user.getUserId());
		res.put("mail", user.getMail());
		res.put("apply", applies);
		return JSONObject.toJSONString(res);
	}
	
	@RequestMapping(path = "/apply/remove", method = RequestMethod.POST)
	@ResponseBody
	public String removeApply(Model model,
			 @RequestParam("user_id") int userId,
			 @RequestParam(value = "apply_id") int applyId,
             HttpServletRequest request,
             HttpServletResponse response) throws ParseException {
		applyService.deleteApply(applyId);
		return "OK";
	}
	@RequestMapping(path = "/apply/removeList", method = RequestMethod.POST)
	@ResponseBody
	public String removeApplyList(Model model,
			 @RequestParam("user_id") int userId,
			 @RequestParam(value = "apply_ids") String applyIds,
             HttpServletRequest request,
             HttpServletResponse response) throws ParseException {
		List<Integer> idList = new ArrayList<>();
		String[] idStrs = applyIds.split(",");
		for(int i = 0; i < idStrs.length; i++) {
			String tmp = StringUtils.trim(idStrs[i]);
			idList.add(Integer.parseInt(tmp));
		}
		applyService.deleteApplyList(idList);
		return "OK";
	}
	
	
}
