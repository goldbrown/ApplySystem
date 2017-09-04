package com.chris.service;

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
