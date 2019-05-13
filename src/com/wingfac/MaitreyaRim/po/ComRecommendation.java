package com.wingfac.MaitreyaRim.po;

import java.io.Serializable;

public class ComRecommendation implements Serializable {

	private static final long serialVersionUID = -5454001646790377241L;
	private Integer cr_id;
	private Integer s_id;
	private Integer c_id;
	private String c_first_figure;
	private Double c_unit_price;
	private String c_name;
	private String cr_level;

	public ComRecommendation() {
		super();
	}

	public ComRecommendation(Integer cr_id, Integer s_id, Integer c_id,
			String c_first_figure, Double c_unit_price, String c_name,
			String cr_level) {
		super();
		this.cr_id = cr_id;
		this.s_id = s_id;
		this.c_id = c_id;
		this.c_first_figure = c_first_figure;
		this.c_unit_price = c_unit_price;
		this.c_name = c_name;
		this.cr_level = cr_level;
	}

	@Override
	public String toString() {
		return "ComRecommendation [cr_id=" + cr_id + ", s_id=" + s_id
				+ ", c_id=" + c_id + ", c_first_figure=" + c_first_figure
				+ ", c_unit_price=" + c_unit_price + ", c_name=" + c_name
				+ ", cr_level=" + cr_level + "]";
	}

	public Integer getCr_id() {
		return cr_id;
	}

	public void setCr_id(Integer cr_id) {
		this.cr_id = cr_id;
	}

	public Integer getS_id() {
		return s_id;
	}

	public void setS_id(Integer s_id) {
		this.s_id = s_id;
	}

	public Integer getC_id() {
		return c_id;
	}

	public void setC_id(Integer c_id) {
		this.c_id = c_id;
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

	public String getCr_level() {
		return cr_level;
	}

	public void setCr_level(String cr_level) {
		this.cr_level = cr_level;
	}

}
