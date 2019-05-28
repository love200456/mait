package com.wingfac.MaitreyaRim.po;

import java.io.Serializable;

public class OrderInformationVo implements Serializable {

	private static final long serialVersionUID = -6051364209490020166L;
	private Integer o_id;
	private String c_name;
	private String auBuyerNick;
	private String o_time;
	private Double o_amount;
	private Double use_limit_integral;
	private Double use_permanent_points;
	private Double full_integral_purchase;

	public OrderInformationVo() {
		super();
	}

	public OrderInformationVo(Integer o_id, String c_name, String auBuyerNick,
			String o_time, Double o_amount, Double use_limit_integral,
			Double use_permanent_points, Double full_integral_purchase) {
		super();
		this.o_id = o_id;
		this.c_name = c_name;
		this.auBuyerNick = auBuyerNick;
		this.o_time = o_time;
		this.o_amount = o_amount;
		this.use_limit_integral = use_limit_integral;
		this.use_permanent_points = use_permanent_points;
		this.full_integral_purchase = full_integral_purchase;
	}

	@Override
	public String toString() {
		return "OrderInformationVo [o_id=" + o_id + ", c_name=" + c_name
				+ ", auBuyerNick=" + auBuyerNick + ", o_time=" + o_time
				+ ", o_amount=" + o_amount + ", use_limit_integral="
				+ use_limit_integral + ", use_permanent_points="
				+ use_permanent_points + ", full_integral_purchase="
				+ full_integral_purchase + "]";
	}

	public Integer getO_id() {
		return o_id;
	}

	public void setO_id(Integer o_id) {
		this.o_id = o_id;
	}

	public String getC_name() {
		return c_name;
	}

	public void setC_name(String c_name) {
		this.c_name = c_name;
	}

	public String getAuBuyerNick() {
		return auBuyerNick;
	}

	public void setAuBuyerNick(String auBuyerNick) {
		this.auBuyerNick = auBuyerNick;
	}

	public String getO_time() {
		return o_time;
	}

	public void setO_time(String o_time) {
		this.o_time = o_time;
	}

	public Double getO_amount() {
		return o_amount;
	}

	public void setO_amount(Double o_amount) {
		this.o_amount = o_amount;
	}

	public Double getUse_limit_integral() {
		return use_limit_integral;
	}

	public void setUse_limit_integral(Double use_limit_integral) {
		this.use_limit_integral = use_limit_integral;
	}

	public Double getUse_permanent_points() {
		return use_permanent_points;
	}

	public void setUse_permanent_points(Double use_permanent_points) {
		this.use_permanent_points = use_permanent_points;
	}

	public Double getFull_integral_purchase() {
		return full_integral_purchase;
	}

	public void setFull_integral_purchase(Double full_integral_purchase) {
		this.full_integral_purchase = full_integral_purchase;
	}

}
