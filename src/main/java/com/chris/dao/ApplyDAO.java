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
	String INSERT_FIELD = " period_name, company_name, website, user_id, username, apply_date,"
			+ " end_date, recommend, status, anticipate, result ";
	String SELECT_FIELD = " apply_id, " + INSERT_FIELD;
	
	@Insert({" insert into ", TABLE_NAME, " ( ", INSERT_FIELD,  " ) values ( ", 
		"#{periodName}, #{companyName}, #{website}, #{userId}, #{username}, #{applyDate}, #{endDate}, #{recommend},"
		+ " #{status}, #{anticipate}, #{result})"})
	public int addApply(Apply apply);
	
	@Select({"select ", SELECT_FIELD, " from ", TABLE_NAME, " where user_id = #{userId}"})
	public List<Apply> selectByUserId(int userId);
	
	@Select({"select ", SELECT_FIELD, " from ", TABLE_NAME, " where username = #{username}"})
	public List<Apply> selectByUsername(String username);
	
	@Update({"update ", TABLE_NAME, " set period_name = #{periodName}, company_name = #{companyName},"
			+ " website = #{website}, apply_date = #{applyDate}, end_date = #{endDate},"
			+ "recommend = #{recommend},"
			+ "status = #{status}, anticipate = #{anticipate}, result = #{result} where apply_id = #{applyId}"})
	public void updateApply(Apply apply);
	
	@Delete({"delete from ", TABLE_NAME, " where apply_id = #{applyId}"})
	public void deleteApply(int applyId);
	
	@Delete({"delete from ", TABLE_NAME, " where apply_id in (#{applyIds})"})
	public void deleteApplyList(List<Integer> applyIds);
}
