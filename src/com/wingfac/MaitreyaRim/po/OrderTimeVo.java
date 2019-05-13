package com.wingfac.MaitreyaRim.po;

import java.io.Serializable;

public class OrderTimeVo implements Serializable {

	private static final long serialVersionUID = 7034888158598184039L;
	private Integer o_id;
	private String o_number;
	private String auBuyerNick;
	private String auMobile;
	private Integer use_limit_integral;
	private Integer use_permanent_points;
	private Double ermanent_integral_bonus;
	private Double time_limited_integration;
	private Double o_amount;
	private Double amount_paid;
	private Integer full_integral_purchase;
	private String payment_time;
	private String o_state;
	private String store_consume_state;

	public OrderTimeVo() {
		super();
	}

	public OrderTimeVo(Integer o_id, String o_number, String auBuyerNick,
			String auMobile, Integer use_limit_integral,
			Integer use_permanent_points, Double ermanent_integral_bonus,
			Double time_limited_integration, Double o_amount,
			Double amount_paid, Integer full_integral_purchase,
			String payment_time, String o_state, String store_consume_state) {
		super();
		this.o_id = o_id;
		this.o_number = o_number;
		this.auBuyerNick = auBuyerNick;
		this.auMobile = auMobile;
		this.use_limit_integral = use_limit_integral;
		this.use_permanent_points = use_permanent_points;
		this.ermanent_integral_bonus = ermanent_integral_bonus;
		this.time_limited_integration = time_limited_integration;
		this.o_amount = o_amount;
		this.amount_paid = amount_paid;
		this.full_integral_purchase = full_integral_purchase;
		this.payment_time = payment_time;
		this.o_state = o_state;
		this.store_consume_state = store_consume_state;
	}

	@Override
	public String toString() {
		return "OrderTimeVo [o_id=" + o_id + ", o_number=" + o_number
				+ ", auBuyerNick=" + auBuyerNick + ", auMobile=" + auMobile
				+ ", use_limit_integral=" + use_limit_integral
				+ ", use_permanent_points=" + use_permanent_points
				+ ", ermanent_integral_bonus=" + ermanent_integral_bonus
				+ ", time_limited_integration=" + time_limited_integration
				+ ", o_amount=" + o_amount + ", amount_paid=" + amount_paid
				+ ", full_integral_purchase=" + full_integral_purchase
				+ ", payment_time=" + payment_time + ", o_state=" + o_state
				+ ", store_consume_state=" + store_consume_state + "]";
	}

	public Integer getO_id() {
		return o_id;
	}

	public void setO_id(Integer o_id) {
		this.o_id = o_id;
	}

	public String getO_number() {
		return o_number;
	}

	public void setO_number(String o_number) {
		this.o_number = o_number;
	}

	public String getAuBuyerNick() {
		return auBuyerNick;
	}

	public void setAuBuyerNick(String auBuyerNick) {
		this.auBuyerNick = auBuyerNick;
	}

	public String getAuMobile() {
		return auMobile;
	}

	public void setAuMobile(String auMobile) {
		this.auMobile = auMobile;
	}

	public Integer getUse_limit_integral() {
		return use_limit_integral;
	}

	public void setUse_limit_integral(Integer use_limit_integral) {
		this.use_limit_integral = use_limit_integral;
	}

	public Integer getUse_permanent_points() {
		return use_permanent_points;
	}

	public void setUse_permanent_points(Integer use_permanent_points) {
		this.use_permanent_points = use_permanent_points;
	}

	public Double getErmanent_integral_bonus() {
		return ermanent_integral_bonus;
	}

	public void setErmanent_integral_bonus(Double ermanent_integral_bonus) {
		this.ermanent_integral_bonus = ermanent_integral_bonus;
	}

	public Double getTime_limited_integration() {
		return time_limited_integration;
	}

	public void setTime_limited_integration(Double time_limited_integration) {
		this.time_limited_integration = time_limited_integration;
	}

	public Double getO_amount() {
		return o_amount;
	}

	public void setO_amount(Double o_amount) {
		this.o_amount = o_amount;
	}

	public Double getAmount_paid() {
		return amount_paid;
	}

	public void setAmount_paid(Double amount_paid) {
		this.amount_paid = amount_paid;
	}

	public Integer getFull_integral_purchase() {
		return full_integral_purchase;
	}

	public void setFull_integral_purchase(Integer full_integral_purchase) {
		this.full_integral_purchase = full_integral_purchase;
	}

	public String getPayment_time() {
		return payment_time;
	}

	public void setPayment_time(String payment_time) {
		this.payment_time = payment_time;
	}

	public String getO_state() {
		return o_state;
	}

	public void setO_state(String o_state) {
		this.o_state = o_state;
	}

	public String getStore_consume_state() {
		return store_consume_state;
	}

	public void setStore_consume_state(String store_consume_state) {
		this.store_consume_state = store_consume_state;
	}

}
