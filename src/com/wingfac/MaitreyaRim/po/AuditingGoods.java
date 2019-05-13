package com.wingfac.MaitreyaRim.po;

import java.io.Serializable;

public class AuditingGoods implements Serializable {

	private static final long serialVersionUID = 3432888861117834570L;
	private Integer ag_id;
	private String first_figure;
	private Double unit_price;
	private String ag_name;
	private String ag_introduce;
	private String picture_one;
	private String picture_two;
	private String picture_three;
	private String picture_four;
	private String picture_five;
	private String picture_six;
	private String picture_seven;
	private String picture_eight;
	private String s_name;
	private String s_mobile;
	private Integer s_id;
	private String discount_type;

	public AuditingGoods() {
		super();
	}

	public AuditingGoods(Integer ag_id, String first_figure, Double unit_price,
			String ag_name, String ag_introduce, String picture_one,
			String picture_two, String picture_three, String picture_four,
			String picture_five, String picture_six, String picture_seven,
			String picture_eight, String s_name, String s_mobile, Integer s_id,
			String discount_type) {
		super();
		this.ag_id = ag_id;
		this.first_figure = first_figure;
		this.unit_price = unit_price;
		this.ag_name = ag_name;
		this.ag_introduce = ag_introduce;
		this.picture_one = picture_one;
		this.picture_two = picture_two;
		this.picture_three = picture_three;
		this.picture_four = picture_four;
		this.picture_five = picture_five;
		this.picture_six = picture_six;
		this.picture_seven = picture_seven;
		this.picture_eight = picture_eight;
		this.s_name = s_name;
		this.s_mobile = s_mobile;
		this.s_id = s_id;
		this.discount_type = discount_type;
	}

	@Override
	public String toString() {
		return "AuditingGoods [ag_id=" + ag_id + ", first_figure="
				+ first_figure + ", unit_price=" + unit_price + ", ag_name="
				+ ag_name + ", ag_introduce=" + ag_introduce + ", picture_one="
				+ picture_one + ", picture_two=" + picture_two
				+ ", picture_three=" + picture_three + ", picture_four="
				+ picture_four + ", picture_five=" + picture_five
				+ ", picture_six=" + picture_six + ", picture_seven="
				+ picture_seven + ", picture_eight=" + picture_eight
				+ ", s_name=" + s_name + ", s_mobile=" + s_mobile + ", s_id="
				+ s_id + ", discount_type=" + discount_type + "]";
	}

	public Integer getAg_id() {
		return ag_id;
	}

	public void setAg_id(Integer ag_id) {
		this.ag_id = ag_id;
	}

	public String getFirst_figure() {
		return first_figure;
	}

	public void setFirst_figure(String first_figure) {
		this.first_figure = first_figure;
	}

	public Double getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(Double unit_price) {
		this.unit_price = unit_price;
	}

	public String getAg_name() {
		return ag_name;
	}

	public void setAg_name(String ag_name) {
		this.ag_name = ag_name;
	}

	public String getAg_introduce() {
		return ag_introduce;
	}

	public void setAg_introduce(String ag_introduce) {
		this.ag_introduce = ag_introduce;
	}

	public String getPicture_one() {
		return picture_one;
	}

	public void setPicture_one(String picture_one) {
		this.picture_one = picture_one;
	}

	public String getPicture_two() {
		return picture_two;
	}

	public void setPicture_two(String picture_two) {
		this.picture_two = picture_two;
	}

	public String getPicture_three() {
		return picture_three;
	}

	public void setPicture_three(String picture_three) {
		this.picture_three = picture_three;
	}

	public String getPicture_four() {
		return picture_four;
	}

	public void setPicture_four(String picture_four) {
		this.picture_four = picture_four;
	}

	public String getPicture_five() {
		return picture_five;
	}

	public void setPicture_five(String picture_five) {
		this.picture_five = picture_five;
	}

	public String getPicture_six() {
		return picture_six;
	}

	public void setPicture_six(String picture_six) {
		this.picture_six = picture_six;
	}

	public String getPicture_seven() {
		return picture_seven;
	}

	public void setPicture_seven(String picture_seven) {
		this.picture_seven = picture_seven;
	}

	public String getPicture_eight() {
		return picture_eight;
	}

	public void setPicture_eight(String picture_eight) {
		this.picture_eight = picture_eight;
	}

	public String getS_name() {
		return s_name;
	}

	public void setS_name(String s_name) {
		this.s_name = s_name;
	}

	public String getS_mobile() {
		return s_mobile;
	}

	public void setS_mobile(String s_mobile) {
		this.s_mobile = s_mobile;
	}

	public Integer getS_id() {
		return s_id;
	}

	public void setS_id(Integer s_id) {
		this.s_id = s_id;
	}

	public String getDiscount_type() {
		return discount_type;
	}

	public void setDiscount_type(String discount_type) {
		this.discount_type = discount_type;
	}

}
