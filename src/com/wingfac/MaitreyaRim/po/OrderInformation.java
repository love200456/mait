package com.wingfac.MaitreyaRim.po;

import java.io.Serializable;

public class OrderInformation implements Serializable {

	private static final long serialVersionUID = 7058623967689309447L;
	private Integer o_id;
	private Integer s_id;
	private Integer auId;
	private String o_number;
	private String s_name;
	private String s_mobile;
	private String auBuyerNick;
	private String auMobile;
	private String auAddress;
	private String o_time;
	private String payment_time;
	private Double o_amount;
	private Double amount_paid;
	private Integer use_limit_integral;
	private Integer use_permanent_points;
	private Double ermanent_integral_bonus;
	private Double time_limited_integration;
	private Integer full_integral_purchase;
	//支付中:2 新加的支付状态 
	private String o_state;
	private String store_consume_state;
	//未进店具体状态（0待发货，1已发货）
	private String store_unconsume_state;
	private Integer pay_state;

	public OrderInformation() {
		super();
	}

	public OrderInformation(Integer o_id, Integer s_id, Integer auId,
			String o_number, String s_name, String s_mobile,
			String auBuyerNick, String auMobile, String auAddress,
			String o_time, String payment_time, Double o_amount,
			Double amount_paid, Integer use_limit_integral,
			Integer use_permanent_points, Double ermanent_integral_bonus,
			Double time_limited_integration, Integer full_integral_purchase,
			String o_state, String store_consume_state, Integer pay_state) {
		super();
		this.o_id = o_id;
		this.s_id = s_id;
		this.auId = auId;
		this.o_number = o_number;
		this.s_name = s_name;
		this.s_mobile = s_mobile;
		this.auBuyerNick = auBuyerNick;
		this.auMobile = auMobile;
		this.auAddress = auAddress;
		this.o_time = o_time;
		this.payment_time = payment_time;
		this.o_amount = o_amount;
		this.amount_paid = amount_paid;
		this.use_limit_integral = use_limit_integral;
		this.use_permanent_points = use_permanent_points;
		this.ermanent_integral_bonus = ermanent_integral_bonus;
		this.time_limited_integration = time_limited_integration;
		this.full_integral_purchase = full_integral_purchase;
		this.o_state = o_state;
		this.store_consume_state = store_consume_state;
		this.pay_state = pay_state;
	}

	@Override
	public String toString() {
		return "OrderInformation [o_id=" + o_id + ", s_id=" + s_id + ", auId="
				+ auId + ", o_number=" + o_number + ", s_name=" + s_name
				+ ", s_mobile=" + s_mobile + ", auBuyerNick=" + auBuyerNick
				+ ", auMobile=" + auMobile + ", auAddress=" + auAddress
				+ ", o_time=" + o_time + ", payment_time=" + payment_time
				+ ", o_amount=" + o_amount + ", amount_paid=" + amount_paid
				+ ", use_limit_integral=" + use_limit_integral
				+ ", use_permanent_points=" + use_permanent_points
				+ ", ermanent_integral_bonus=" + ermanent_integral_bonus
				+ ", time_limited_integration=" + time_limited_integration
				+ ", full_integral_purchase=" + full_integral_purchase
				+ ", o_state=" + o_state + ", store_consume_state="
				+ store_consume_state + ", pay_state=" + pay_state + "]";
	}

	public Integer getO_id() {
		return o_id;
	}

	public void setO_id(Integer o_id) {
		this.o_id = o_id;
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

	public String getO_number() {
		return o_number;
	}

	public void setO_number(String o_number) {
		this.o_number = o_number;
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

	public String getAuAddress() {
		return auAddress;
	}

	public void setAuAddress(String auAddress) {
		this.auAddress = auAddress;
	}

	public String getO_time() {
		return o_time;
	}

	public void setO_time(String o_time) {
		this.o_time = o_time;
	}

	public String getPayment_time() {
		return payment_time;
	}

	public void setPayment_time(String payment_time) {
		this.payment_time = payment_time;
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

	public Integer getFull_integral_purchase() {
		return full_integral_purchase;
	}

	public void setFull_integral_purchase(Integer full_integral_purchase) {
		this.full_integral_purchase = full_integral_purchase;
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

	public Integer getPay_state() {
		return pay_state;
	}

	public void setPay_state(Integer pay_state) {
		this.pay_state = pay_state;
	}

	public String getStore_unconsume_state() {
		return store_unconsume_state;
	}

	public void setStore_unconsume_state(String store_unconsume_state) {
		this.store_unconsume_state = store_unconsume_state;
	}

	
}
