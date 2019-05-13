package com.wingfac.MaitreyaRim.po;

import java.io.Serializable;

public class AdvertisingCategory implements Serializable {

	private static final long serialVersionUID = -5090300475801487854L;
	private Integer ac_id;
	private Integer s_id;
	private String ac_picture;
	private String ac_level;

	public AdvertisingCategory() {
		super();
	}

	public AdvertisingCategory(Integer ac_id, Integer s_id, String ac_picture,
			String ac_level) {
		super();
		this.ac_id = ac_id;
		this.s_id = s_id;
		this.ac_picture = ac_picture;
		this.ac_level = ac_level;
	}

	@Override
	public String toString() {
		return "AdvertisingCategory [ac_id=" + ac_id + ", s_id=" + s_id
				+ ", ac_picture=" + ac_picture + ", ac_level=" + ac_level + "]";
	}

	public Integer getAc_id() {
		return ac_id;
	}

	public void setAc_id(Integer ac_id) {
		this.ac_id = ac_id;
	}

	public Integer getS_id() {
		return s_id;
	}

	public void setS_id(Integer s_id) {
		this.s_id = s_id;
	}

	public String getAc_picture() {
		return ac_picture;
	}

	public void setAc_picture(String ac_picture) {
		this.ac_picture = ac_picture;
	}

	public String getAc_level() {
		return ac_level;
	}

	public void setAc_level(String ac_level) {
		this.ac_level = ac_level;
	}

}
