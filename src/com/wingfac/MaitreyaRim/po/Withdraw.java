package com.wingfac.MaitreyaRim.po;

import java.io.Serializable;
import java.util.Date;

public class Withdraw implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String userName;
	private Integer userID;
	private Double money;
	private Date createTime;

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	
	
	

}
