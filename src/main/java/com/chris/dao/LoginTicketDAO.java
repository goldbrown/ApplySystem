package com.chris.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.chris.model.LoginTicket;

@Repository
@Mapper
public interface LoginTicketDAO {
	final String TABLE_NAME = " login_ticket ";
	final String INSERT_FIELD = " user_id, ticket, expired, status ";
	final String SELECT_FIELD = " id, " + INSERT_FIELD;

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELD, ") values " +
            "( #{userId},  #{ticket}, #{expired}, #{status})"})
    public int addTicket(LoginTicket loginTicket);

    @Select({"select ", SELECT_FIELD, "from ", TABLE_NAME, "where ticket=#{ticket}"})
    public LoginTicket selectByTicket(String ticket);

    @Update({"update ", TABLE_NAME, "set status=#{status} where ticket=#{ticket}"})
    public void updateStatus(@Param("ticket") String ticket, @Param("status") int status);
}
