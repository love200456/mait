package com.wingfac.MaitreyaRim.po;

import java.io.Serializable;

public class Commodity implements Serializable {

	private static final long serialVersionUID = 2513530431525629451L;
	private Integer c_id;
	private Integer s_id;
	private String c_first_figure;
	private Double c_unit_price;
	private String c_name;
	private String c_introduce;
	private String c_picture_one;
	private String c_picture_two;
	private String c_picture_three;
	private String c_picture_four;
	private String c_picture_five;
	private String c_picture_six;
	private String c_picture_seven;
	private String c_picture_eight;
	private String discount_type;

	public Commodity() {
		super();
	}

	public Commodity(Integer c_id, Integer s_id, String c_first_figure,
			Double c_unit_price, String c_name, String c_introduce,
			String c_picture_one, String c_picture_two, String c_picture_three,
			String c_picture_four, String c_picture_five, String c_picture_six,
			String c_picture_seven, String c_picture_eight,
			String discount_type) {
		super();
		this.c_id = c_id;
		this.s_id = s_id;
		this.c_first_figure = c_first_figure;
		this.c_unit_price = c_unit_price;
		this.c_name = c_name;
		this.c_introduce = c_introduce;
		this.c_picture_one = c_picture_one;
		this.c_picture_two = c_picture_two;
		this.c_picture_three = c_picture_three;
		this.c_picture_four = c_picture_four;
		this.c_picture_five = c_picture_five;
		this.c_picture_six = c_picture_six;
		this.c_picture_seven = c_picture_seven;
		this.c_picture_eight = c_picture_eight;
		this.discount_type = discount_type;
	}

	@Override
	public String toString() {
		return "Commodity [c_id=" + c_id + ", s_id=" + s_id
				+ ", c_first_figure=" + c_first_figure + ", c_unit_price="
				+ c_unit_price + ", c_name=" + c_name + ", c_introduce="
				+ c_introduce + ", c_picture_one=" + c_picture_one
				+ ", c_picture_two=" + c_picture_two + ", c_picture_three="
				+ c_picture_three + ", c_picture_four=" + c_picture_four
				+ ", c_picture_five=" + c_picture_five + ", c_picture_six="
				+ c_picture_six + ", c_picture_seven=" + c_picture_seven
				+ ", c_picture_eight=" + c_picture_eight + ", discount_type="
				+ discount_type + "]";
	}

	public Integer getC_id() {
		return c_id;
	}

	public void setC_id(Integer c_id) {
		this.c_id = c_id;
	}

	public Integer getS_id() {
		return s_id;
	}

	public void setS_id(Integer s_id) {
		this.s_id = s_id;
	}

	public String getC_first_figure() {
		return c_first_figure;
	}

	public void setC_first_figure(String c_first_figure) {
		this.c_first_figure = c_first_figure;
	}

	public Double getC_unit_price() {
		return c_unit_price;
	}

	public void setC_unit_price(Double c_unit_price) {
		this.c_unit_price = c_unit_price;
	}

	public String getC_name() {
		return c_name;
	}

	public void setC_name(String c_name) {
		this.c_name = c_name;
	}

	public String getC_introduce() {
		return c_introduce;
	}

	public void setC_introduce(String c_introduce) {
		this.c_introduce = c_introduce;
	}

	public String getC_picture_one() {
		return c_picture_one;
	}

	public void setC_picture_one(String c_picture_one) {
		this.c_picture_one = c_picture_one;
	}

	public String getC_picture_two() {
		return c_picture_two;
	}

	public void setC_picture_two(String c_picture_two) {
		this.c_picture_two = c_picture_two;
	}

	public String getC_picture_three() {
		return c_picture_three;
	}

	public void setC_picture_three(String c_picture_three) {
		this.c_picture_three = c_picture_three;
	}

	public String getC_picture_four() {
		return c_picture_four;
	}

	public void setC_picture_four(String c_picture_four) {
		this.c_picture_four = c_picture_four;
	}

	public String getC_picture_five() {
		return c_picture_five;
	}

	public void setC_picture_five(String c_picture_five) {
		this.c_picture_five = c_picture_five;
	}

	public String getC_picture_six() {
		return c_picture_six;
	}

	public void setC_picture_six(String c_picture_six) {
		this.c_picture_six = c_picture_six;
	}

	public String getC_picture_seven() {
		return c_picture_seven;
	}

	public void setC_picture_seven(String c_picture_seven) {
		this.c_picture_seven = c_picture_seven;
	}

	public String getC_picture_eight() {
		return c_picture_eight;
	}

	public void setC_picture_eight(String c_picture_eight) {
		this.c_picture_eight = c_picture_eight;
	}

	public String getDiscount_type() {
		return discount_type;
	}

	public void setDiscount_type(String discount_type) {
		this.discount_type = discount_type;
	}

}
