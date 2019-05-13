package com.wingfac.MaitreyaRim.po;

import java.io.Serializable;

public class CommodityVo implements Serializable {

	private static final long serialVersionUID = -4347790207043151684L;
	private Integer c_id;
	private Integer s_id;
	private String s_name;
	private String c_first_figure;
	private Double c_unit_price;
	private String c_name;

	public CommodityVo() {
		super();
	}

	public CommodityVo(Integer c_id, Integer s_id, String s_name,
			String c_first_figure, Double c_unit_price, String c_name) {
		super();
		this.c_id = c_id;
		this.s_id = s_id;
		this.s_name = s_name;
		this.c_first_figure = c_first_figure;
		this.c_unit_price = c_unit_price;
		this.c_name = c_name;
	}

	@Override
	public String toString() {
		return "CommodityVo [c_id=" + c_id + ", s_id=" + s_id + ", s_name="
				+ s_name + ", c_first_figure=" + c_first_figure
				+ ", c_unit_price=" + c_unit_price + ", c_unit_price="
				+ c_unit_price + ", c_name=" + c_name + "]";
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

	public String getS_name() {
		return s_name;
	}

	public void setS_name(String s_name) {
		this.s_name = s_name;
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

}
