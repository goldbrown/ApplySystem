package com.chris.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chris.dao.ApplyDAO;
import com.chris.model.Apply;

@Service
public class ApplyService {
	@Autowired
	private ApplyDAO applyDAO;
	
	public int addApply(Apply apply) {
		return applyDAO.addApply(apply);
	}
	
	public List<Apply> selectByUserId(int userId) {
		return applyDAO.selectByUserId(userId);
	}
	public List<String[]> getApplyStrByUserId(int userId) {
		List<Apply> applies = applyDAO.selectByUserId(userId);
		List<String[]> res = new ArrayList<>();
		String[] head = {"公司名字", "公司招聘链接", "申请日期", "状态", "备注", "最后一面的日期", "预期结果"};
		res.add(head);
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		for(Apply ele: applies) {
			String applyStr = "";
			Date applyDate = ele.getApplyDate();
			if(applyDate != null) applyStr = df.format(ele.getApplyDate());
			
			String endStr = "";
			Date endDate = ele.getEndDate();
			if(endDate != null) endStr = df.format(ele.getEndDate());
			
			String[] entry = {ele.getCompanyName(), ele.getWebsite(), applyStr,
					ele.getStatus(), ele.getRemark(), endStr, ele.getAnticipate()};
			res.add(entry);
		}
		return res;
		
	}
	public List<Apply> selectByUsername(String username) {
		return applyDAO.selectByUsername(username);
	}
	
	
	
	public void updateApply(Apply apply) {
		applyDAO.updateApply(apply);
	}
	
	public void deleteApply(int applyId) {
		applyDAO.deleteApply(applyId);
	}
	public void deleteApplyList(List<Integer> applyIds) {
		applyDAO.deleteApplyList(applyIds);
	}
}
