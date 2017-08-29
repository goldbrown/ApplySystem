package com.chris.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.chris.model.User;

@Repository
@Mapper
public interface UserDAO {
	String TABLE_NAME = " user ";
	String INSERT_FIELD = " mail, password, salt, ip_address ";
	String SELECT_FIELD = " user_id, " + INSERT_FIELD;
	
	@Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELD, ") values" , 
		"(#{mail}, #{password}, #{salt}, #{ipAddr})"})
	public int addUser(User user);
	
	@Select({"select ", SELECT_FIELD, " from ", TABLE_NAME, " where user_id = #{userId}"})
	User selectById(int userId);
	
	@Select({"select ", SELECT_FIELD, " from ", TABLE_NAME, " where mail = #{mail}"})
	User selectByMail(String mail);


	@Update({"update ", TABLE_NAME, "set password=#{password} where user_id = #{userId}"})
	void updatePassword(User user);

	@Delete({"delete from ", TABLE_NAME, " where user_id = #{userId}"})
	void deleteUser(int userId);
}
