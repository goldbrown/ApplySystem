package com.chris.model;

import java.util.Date;

public class LoginTicket {
	private Integer id;
    private int userId;
    private Date expired;
    private int status;
    private String ticket;

    public LoginTicket() {
    }

    public LoginTicket(int userId, Date expired, int status, String ticket) {
        this.userId = userId;
        this.expired = expired;
        this.status = status;
        this.ticket = ticket;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Date getExpired() {
		return expired;
	}

	public void setExpired(Date expired) {
		this.expired = expired;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
    
}
