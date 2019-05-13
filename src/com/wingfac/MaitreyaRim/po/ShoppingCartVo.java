package com.wingfac.MaitreyaRim.po;

import java.io.Serializable;

public class ShoppingCartVo implements Serializable {

	private static final long serialVersionUID = -6591045059019109746L;
	private Integer sc_id;
	private Integer auId;
	private Integer c_id;
	private String c_first_figure;
	private Double c_unit_price;
	private String c_name;
	private String c_introduce;
	private String discount_type;

	public ShoppingCartVo() {
		super();
	}

	public ShoppingCartVo(Integer sc_id, Integer auId, Integer c_id,
			String c_first_figure, Double c_unit_price, String c_name,
			String c_introduce, String discount_type) {
		super();
		this.sc_id = sc_id;
		this.auId = auId;
		this.c_id = c_id;
		this.c_first_figure = c_first_figure;
		this.c_unit_price = c_unit_price;
		this.c_name = c_name;
		this.c_introduce = c_introduce;
		this.discount_type = discount_type;
	}

	@Override
	public String toString() {
		return "ShoppingCartVo [sc_id=" + sc_id + ", auId=" + auId + ", c_id="
				+ c_id + ", c_first_figure=" + c_first_figure
				+ ", c_unit_price=" + c_unit_price + ", c_name=" + c_name
				+ ", c_introduce=" + c_introduce + ", discount_type="
				+ discount_type + "]";
	}

	public Integer getSc_id() {
		return sc_id;
	}

	public void setSc_id(Integer sc_id) {
		this.sc_id = sc_id;
	}

	public Integer getAuId() {
		return auId;
	}

	public void setAuId(Integer auId) {
		this.auId = auId;
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

	public String getC_introduce() {
		return c_introduce;
	}

	public void setC_introduce(String c_introduce) {
		this.c_introduce = c_introduce;
	}

	public String getDiscount_type() {
		return discount_type;
	}

	public void setDiscount_type(String discount_type) {
		this.discount_type = discount_type;
	}

}
