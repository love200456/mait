package com.wingfac.MaitreyaRim.po;

import java.io.Serializable;

public class ShopRecommendation implements Serializable {

	private static final long serialVersionUID = -3689255471198141649L;
	private Integer sp_id;
	private Integer s_id;
	private String s_name;
	private String picture;
	private String describe;
	private Double s_longitude;
	private Double s_latitude;
	private String sp_level;

	public ShopRecommendation() {
		super();
	}

	public ShopRecommendation(Integer sp_id, Integer s_id, String s_name,
			String picture, String describe, Double s_longitude,
			Double s_latitude, String sp_level) {
		super();
		this.sp_id = sp_id;
		this.s_id = s_id;
		this.s_name = s_name;
		this.picture = picture;
		this.describe = describe;
		this.s_longitude = s_longitude;
		this.s_latitude = s_latitude;
		this.sp_level = sp_level;
	}

	@Override
	public String toString() {
		return "ShopRecommendation [sp_id=" + sp_id + ", s_id=" + s_id
				+ ", s_name=" + s_name + ", picture=" + picture + ", describe="
				+ describe + ", s_longitude=" + s_longitude + ", s_latitude="
				+ s_latitude + ", sp_level=" + sp_level + "]";
	}

	public Integer getSp_id() {
		return sp_id;
	}

	public void setSp_id(Integer sp_id) {
		this.sp_id = sp_id;
	}

	public Integer getS_id() {
		return s_id;
	}

	public void setS_id(Integer s_id) {
		this.s_id = s_id;
	}

	public String getS_name() {
		return s_name;
	}

	public void setS_name(String s_name) {
		this.s_name = s_name;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public Double getS_longitude() {
		return s_longitude;
	}

	public void setS_longitude(Double s_longitude) {
		this.s_longitude = s_longitude;
	}

	public Double getS_latitude() {
		return s_latitude;
	}

	public void setS_latitude(Double s_latitude) {
		this.s_latitude = s_latitude;
	}

	public String getSp_level() {
		return sp_level;
	}

	public void setSp_level(String sp_level) {
		this.sp_level = sp_level;
	}

}
