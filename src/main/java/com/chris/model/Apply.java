package com.chris.model;

import java.util.Date;

public class Apply {
	private int applyId;
	private int periodId;
	private int companyId;
	private int userId;
	private Date applyDate;
	private Date endDate;
	private String recommend;
	private String recommendRemark;
	private String status;
	private String anticipate;
	private String result;
	public int getApplyId() {
		return applyId;
	}
	public void setApplyId(int applyId) {
		this.applyId = applyId;
	}
	public int getPeriodId() {
		return periodId;
	}
	public void setPeriodId(int periodId) {
		this.periodId = periodId;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Date getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getRecommend() {
		return recommend;
	}
	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}
	public String getRecommendRemark() {
		return recommendRemark;
	}
	public void setRecommendRemark(String recommendRemark) {
		this.recommendRemark = recommendRemark;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAnticipate() {
		return anticipate;
	}
	public void setAnticipate(String anticipate) {
		this.anticipate = anticipate;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
}
