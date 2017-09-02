package com.chris.controller;

import java.text.ParseException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chris.model.Apply;
import com.chris.service.ApplyService;
@Controller
public class ApplyController {
	@Autowired
	private ApplyService applyService;
	
	@RequestMapping(path = "/apply/add", method = RequestMethod.POST)
	@ResponseBody
	public String addApply(Model model, 
			 @RequestParam("user_id") String userId,
			 @RequestParam("mail") String mail,
             @RequestParam("period_name") String periodName,
             @RequestParam("company_name") String companyName,
             @RequestParam("apply_date") String applyDate,
             @RequestParam(value = "end_date", required=false) String endDate,
             @RequestParam("recommend") String recommend,
             @RequestParam("status") String status,
             @RequestParam(value = "anticipate", required=false) String anticipate,
             @RequestParam(value = "result", required=false) String result,
             HttpServletRequest request,
             HttpServletResponse response) throws ParseException {
		Apply apply = new Apply();
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
}
