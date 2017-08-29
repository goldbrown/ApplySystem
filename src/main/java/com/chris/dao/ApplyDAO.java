package com.chris.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.chris.model.Apply;

@Repository
@Mapper
public interface ApplyDAO {
	
	String TABLE_NAME = " apply ";
	String INSERT_FIELD = " period_id, company_id, user_id, apply_date,"
			+ " end_date, recommend, recommend_remark, status, anticipate, result ";
	String SELECT_FIELD = " apply_id, " + INSERT_FIELD;
	
	@Insert({" insert into ", TABLE_NAME, " ( ", INSERT_FIELD,  " ) values ( ", 
		"(#{periodId}, #{companyId}, #{userId}, #{applyDate}, #{endDate}, #{recommend},"
		+ "#{recommend_remark}, #{status}, #{anticipate}, #{result})"})
	public int addApply(Apply apply);
	
	@Select({"select ", SELECT_FIELD, " from ", TABLE_NAME, " where mail = #{mail}"})
	public List<Apply> selectByMail(String mail);
	
	@Update({"update ", TABLE_NAME, " set period_id = #{periodId}, company_id = #{companyId},"
			+ "user_id = #{userId}, apply_date = #{applyDate}, end_date = #{applyDate},"
			+ "recommend = #{recommend}, recommendRemark = #{recommendRemark},"
			+ "status = #{status}, anticipate = #{anticipate}, result = #{result} where apply_id = #{applyId}"})
	public void updateApply(Apply apply);
	
	@Delete({"delete from ", TABLE_NAME, " where apply_id = #{applyId}"})
	public void deleteApply(int applyId);
}
