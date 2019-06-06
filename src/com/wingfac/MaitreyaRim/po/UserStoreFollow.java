package com.wingfac.MaitreyaRim.po;

import java.io.Serializable;

public class UserStoreFollow implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2203381484841126332L;
	private Integer usf_id;
	private Integer auId;
	private Integer s_id;
	private String s_name;
	private String s_logo;
	private Double integral_setting;
	private Double s_longitude;
	private Double s_latitude;
	private String working_hours;
	
	private String describe;
	private Double deductible_percentage;

	public UserStoreFollow() {
		super();
	}

	public UserStoreFollow(Integer usf_id, Integer auId, Integer s_id,
			String s_name, String s_logo, Double integral_setting,
			Double s_longitude, Double s_latitude) {
		super();
		this.usf_id = usf_id;
		this.auId = auId;
		this.s_id = s_id;
		this.s_name = s_name;
		this.s_logo = s_logo;
		this.integral_setting = integral_setting;
		this.s_longitude = s_longitude;
		this.s_latitude = s_latitude;
	}

	@Override
	public String toString() {
		return "UserStoreFollow [usf_id=" + usf_id + ", auId=" + auId
				+ ", s_id=" + s_id + ", s_name=" + s_name + ", s_logo="
				+ s_logo + ", integral_setting=" + integral_setting
				+ ", s_longitude=" + s_longitude + ", s_latitude=" + s_latitude
				+ "]";
	}

	public Integer getUsf_id() {
		return usf_id;
	}

	public void setUsf_id(Integer usf_id) {
		this.usf_id = usf_id;
	}

	public Integer getAuId() {
		return auId;
	}

	public void setAuId(Integer auId) {
		this.auId = auId;
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

	public String getS_logo() {
		return s_logo;
	}

	public void setS_logo(String s_logo) {
		this.s_logo = s_logo;
	}

	public Double getIntegral_setting() {
		return integral_setting;
	}

	public void setIntegral_setting(Double integral_setting) {
		this.integral_setting = integral_setting;
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

	public String getWorking_hours() {
		return working_hours;
	}

	public void setWorking_hours(String working_hours) {
		this.working_hours = working_hours;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public Double getDeductible_percentage() {
		return deductible_percentage;
	}

	public void setDeductible_percentage(Double deductible_percentage) {
		this.deductible_percentage = deductible_percentage;
	}

	
}
