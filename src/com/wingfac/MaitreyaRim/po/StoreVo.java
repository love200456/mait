package com.wingfac.MaitreyaRim.po;

import java.io.Serializable;

public class StoreVo implements Serializable {

	private static final long serialVersionUID = -4787399064165113565L;
	private String s_id;
	private String s_address;
	private Double s_longitude;
	private Double s_latitude;

	public StoreVo() {
		super();
	}

	public StoreVo(String s_id, String s_address, Double s_longitude,
			Double s_latitude) {
		super();
		this.s_id = s_id;
		this.s_address = s_address;
		this.s_longitude = s_longitude;
		this.s_latitude = s_latitude;
	}

	@Override
	public String toString() {
		return "StoreVo [s_id=" + s_id + ", s_address=" + s_address
				+ ", s_longitude=" + s_longitude + ", s_latitude=" + s_latitude
				+ "]";
	}

	public String getS_id() {
		return s_id;
	}

	public void setS_id(String s_id) {
		this.s_id = s_id;
	}

	public String getS_address() {
		return s_address;
	}

	public void setS_address(String s_address) {
		this.s_address = s_address;
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

}
