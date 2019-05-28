package com.wingfac.MaitreyaRim.po;

import java.io.Serializable;

public class AverageUser implements Serializable {

	private static final long serialVersionUID = 7449565266051342529L;
	private Integer auId;
	private String auMobile;
	private String auPassword;
	private String auAvatar;
	private String auBuyerNick;
	private String auIdentity;
	private Double limit_integral;
	private Double permanent_points;
	private String auAddress;
	private String remarks;
	private String integral_remark;
	private Double money;
	
	public AverageUser() {
		super();
	}

	public AverageUser(Integer auId, String auMobile, String auPassword,
			String auAvatar, String auBuyerNick, String auIdentity,
			Double limit_integral, Double permanent_points, String auAddress,
			String remarks, String integral_remark) {
		super();
		this.auId = auId;
		this.auMobile = auMobile;
		this.auPassword = auPassword;
		this.auAvatar = auAvatar;
		this.auBuyerNick = auBuyerNick;
		this.auIdentity = auIdentity;
		this.limit_integral = limit_integral;
		this.permanent_points = permanent_points;
		this.auAddress = auAddress;
		this.remarks = remarks;
		this.integral_remark = integral_remark;
	}

	@Override
	public String toString() {
		return "AverageUser [auId=" + auId + ", auMobile=" + auMobile
				+ ", auPassword=" + auPassword + ", auAvatar=" + auAvatar
				+ ", auBuyerNick=" + auBuyerNick + ", auIdentity=" + auIdentity
				+ ", limit_integral=" + limit_integral + ", permanent_points="
				+ permanent_points + ", auAddress=" + auAddress + ", remarks="
				+ remarks + ", integral_remark=" + integral_remark + "]";
	}

	public Integer getAuId() {
		return auId;
	}

	public void setAuId(Integer auId) {
		this.auId = auId;
	}

	public String getAuMobile() {
		return auMobile;
	}

	public void setAuMobile(String auMobile) {
		this.auMobile = auMobile;
	}

	public String getAuPassword() {
		return auPassword;
	}

	public void setAuPassword(String auPassword) {
		this.auPassword = auPassword;
	}

	public String getAuAvatar() {
		return auAvatar;
	}

	public void setAuAvatar(String auAvatar) {
		this.auAvatar = auAvatar;
	}

	public String getAuBuyerNick() {
		return auBuyerNick;
	}

	public void setAuBuyerNick(String auBuyerNick) {
		this.auBuyerNick = auBuyerNick;
	}

	public String getAuIdentity() {
		return auIdentity;
	}

	public void setAuIdentity(String auIdentity) {
		this.auIdentity = auIdentity;
	}

	public Double getLimit_integral() {
		return limit_integral;
	}

	public void setLimit_integral(Double limit_integral) {
		this.limit_integral = limit_integral;
	}

	public Double getPermanent_points() {
		return permanent_points;
	}

	public void setPermanent_points(Double permanent_points) {
		this.permanent_points = permanent_points;
	}

	public String getAuAddress() {
		return auAddress;
	}

	public void setAuAddress(String auAddress) {
		this.auAddress = auAddress;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getIntegral_remark() {
		return integral_remark;
	}

	public void setIntegral_remark(String integral_remark) {
		this.integral_remark = integral_remark;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

}
