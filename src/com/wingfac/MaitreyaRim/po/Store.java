package com.wingfac.MaitreyaRim.po;

import java.io.Serializable;

public class Store implements Serializable {

	private static final long serialVersionUID = -4787399064165113565L;
	private Integer s_id;
	private Integer auId;
	private Integer toc_id;
	private Integer ttc_id;
	private String first_picture;
	private String picture;
	private String s_logo;
	private Double user_mark;
	private String s_name;
	private String open_time;
	private String s_mobile;
	private String s_address;
	private String describe;
	private String s_physical;
	private Double integral_setting;
	private Double s_longitude;
	private Double s_latitude;
	private String payment_code;
	private Double offset_setting;
	private Double deductible_percentage;
	private String shop_note;
	private String is_open;
	private String working_hours;
	

	public Store() {
		super();
	}

	public Store(Integer s_id, Integer auId, Integer toc_id, Integer ttc_id,
			String first_picture, String picture, String s_logo,
			Double user_mark, String s_name, String open_time, String s_mobile,
			String s_address, String describe, String s_physical,
			Double integral_setting, Double s_longitude, Double s_latitude,
			String payment_code, Double offset_setting,
			Double deductible_percentage, String shop_note, String is_open) {
		super();
		this.s_id = s_id;
		this.auId = auId;
		this.toc_id = toc_id;
		this.ttc_id = ttc_id;
		this.first_picture = first_picture;
		this.picture = picture;
		this.s_logo = s_logo;
		this.user_mark = user_mark;
		this.s_name = s_name;
		this.open_time = open_time;
		this.s_mobile = s_mobile;
		this.s_address = s_address;
		this.describe = describe;
		this.s_physical = s_physical;
		this.integral_setting = integral_setting;
		this.s_longitude = s_longitude;
		this.s_latitude = s_latitude;
		this.payment_code = payment_code;
		this.offset_setting = offset_setting;
		this.deductible_percentage = deductible_percentage;
		this.shop_note = shop_note;
		this.is_open = is_open;
	}

	@Override
	public String toString() {
		return "Store [s_id=" + s_id + ", auId=" + auId + ", toc_id=" + toc_id
				+ ", ttc_id=" + ttc_id + ", first_picture=" + first_picture
				+ ", picture=" + picture + ", s_logo=" + s_logo + ", user_mark="
				+ user_mark + ", s_name=" + s_name + ", open_time=" + open_time
				+ ", s_mobile=" + s_mobile + ", s_address=" + s_address
				+ ", describe=" + describe + ", s_physical=" + s_physical
				+ ", integral_setting=" + integral_setting + ", s_longitude="
				+ s_longitude + ", s_latitude=" + s_latitude + ", payment_code="
				+ payment_code + ", offset_setting=" + offset_setting
				+ ", deductible_percentage=" + deductible_percentage
				+ ", shop_note=" + shop_note + ", is_open=" + is_open + "]";
	}

	public Integer getS_id() {
		return s_id;
	}

	public void setS_id(Integer s_id) {
		this.s_id = s_id;
	}

	public Integer getAuId() {
		return auId;
	}

	public void setAuId(Integer auId) {
		this.auId = auId;
	}

	public Integer getToc_id() {
		return toc_id;
	}

	public void setToc_id(Integer toc_id) {
		this.toc_id = toc_id;
	}

	public Integer getTtc_id() {
		return ttc_id;
	}

	public void setTtc_id(Integer ttc_id) {
		this.ttc_id = ttc_id;
	}

	public String getFirst_picture() {
		return first_picture;
	}

	public void setFirst_picture(String first_picture) {
		this.first_picture = first_picture;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getS_logo() {
		return s_logo;
	}

	public void setS_logo(String s_logo) {
		this.s_logo = s_logo;
	}

	public Double getUser_mark() {
		return user_mark;
	}

	public void setUser_mark(Double user_mark) {
		this.user_mark = user_mark;
	}

	public String getS_name() {
		return s_name;
	}

	public void setS_name(String s_name) {
		this.s_name = s_name;
	}

	public String getOpen_time() {
		return open_time;
	}

	public void setOpen_time(String open_time) {
		this.open_time = open_time;
	}

	public String getS_mobile() {
		return s_mobile;
	}

	public void setS_mobile(String s_mobile) {
		this.s_mobile = s_mobile;
	}

	public String getS_address() {
		return s_address;
	}

	public void setS_address(String s_address) {
		this.s_address = s_address;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getS_physical() {
		return s_physical;
	}

	public void setS_physical(String s_physical) {
		this.s_physical = s_physical;
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

	public String getPayment_code() {
		return payment_code;
	}

	public void setPayment_code(String payment_code) {
		this.payment_code = payment_code;
	}

	public Double getOffset_setting() {
		return offset_setting;
	}

	public void setOffset_setting(Double offset_setting) {
		this.offset_setting = offset_setting;
	}

	public Double getDeductible_percentage() {
		return deductible_percentage;
	}

	public void setDeductible_percentage(Double deductible_percentage) {
		this.deductible_percentage = deductible_percentage;
	}

	public String getShop_note() {
		return shop_note;
	}

	public void setShop_note(String shop_note) {
		this.shop_note = shop_note;
	}

	public String getIs_open() {
		return is_open;
	}

	public void setIs_open(String is_open) {
		this.is_open = is_open;
	}

	public String getWorking_hours() {
		return working_hours;
	}

	public void setWorking_hours(String working_hours) {
		this.working_hours = working_hours;
	}
	
	

}
