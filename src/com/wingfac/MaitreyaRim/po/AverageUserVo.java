package com.wingfac.MaitreyaRim.po;

import java.io.Serializable;

public class AverageUserVo implements Serializable {

	private static final long serialVersionUID = 2754752084845417203L;
	private String auId;
	private String auMobile;
	private String auPassword;
	private String auAvatar;
	private String auBuyerNick;
	private String auIdentity;
	private Integer limit_integral;
	private Integer permanent_points;
	private String auAddress;

	public AverageUserVo() {
		super();
	}

	public AverageUserVo(String auId, String auMobile, String auPassword,
			String auAvatar, String auBuyerNick, String auIdentity,
			Integer limit_integral, Integer permanent_points, String auAddress) {
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
	}

	@Override
	public String toString() {
		return "AverageUserVo [auId=" + auId + ", auMobile=" + auMobile
				+ ", auPassword=" + auPassword + ", auAvatar=" + auAvatar
				+ ", auBuyerNick=" + auBuyerNick + ", auIdentity=" + auIdentity
				+ ", limit_integral=" + limit_integral + ", permanent_points="
				+ permanent_points + ", auAddress=" + auAddress + "]";
	}

	public String getAuId() {
		return auId;
	}

	public void setAuId(String auId) {
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

	public Integer getLimit_integral() {
		return limit_integral;
	}

	public void setLimit_integral(Integer limit_integral) {
		this.limit_integral = limit_integral;
	}

	public Integer getPermanent_points() {
		return permanent_points;
	}

	public void setPermanent_points(Integer permanent_points) {
		this.permanent_points = permanent_points;
	}

	public String getAuAddress() {
		return auAddress;
	}

	public void setAuAddress(String auAddress) {
		this.auAddress = auAddress;
	}

}
