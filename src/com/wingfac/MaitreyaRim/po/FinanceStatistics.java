package com.wingfac.MaitreyaRim.po;

import java.io.Serializable;

public class FinanceStatistics implements Serializable {

	private static final long serialVersionUID = -7753947483716095013L;
	private Integer fs_id;
	private Integer s_id;
	private String s_name;
	private String s_mobile;
	private String s_address;
	private Double fs_turnover;
	private String fs_time;
	private String o_number;
	private Double ermanent_integral_bonus;
	private Double time_limited_integration;
	private Double full_integral_purchase;
	private Integer ocl_num;
	private Double c_unit_price;
	private Double time_limited_sum;
	private Double ermanent_integral_sum;
	private Double price_sum;
	private Double fs_turnover_sum;

	public FinanceStatistics() {
		super();
	}

	public FinanceStatistics(Integer fs_id, Integer s_id, String s_name,
			String s_mobile, String s_address, Double fs_turnover,
			String fs_time, String o_number, Double ermanent_integral_bonus,
			Double time_limited_integration, Double full_integral_purchase,
			Integer ocl_num, Double c_unit_price, Double time_limited_sum,
			Double ermanent_integral_sum, Double price_sum,
			Double fs_turnover_sum) {
		super();
		this.fs_id = fs_id;
		this.s_id = s_id;
		this.s_name = s_name;
		this.s_mobile = s_mobile;
		this.s_address = s_address;
		this.fs_turnover = fs_turnover;
		this.fs_time = fs_time;
		this.o_number = o_number;
		this.ermanent_integral_bonus = ermanent_integral_bonus;
		this.time_limited_integration = time_limited_integration;
		this.full_integral_purchase = full_integral_purchase;
		this.ocl_num = ocl_num;
		this.c_unit_price = c_unit_price;
		this.time_limited_sum = time_limited_sum;
		this.ermanent_integral_sum = ermanent_integral_sum;
		this.price_sum = price_sum;
		this.fs_turnover_sum = fs_turnover_sum;
	}

	@Override
	public String toString() {
		return "FinanceStatistics [fs_id=" + fs_id + ", s_id=" + s_id
				+ ", s_name=" + s_name + ", s_mobile=" + s_mobile
				+ ", s_address=" + s_address + ", fs_turnover=" + fs_turnover
				+ ", fs_time=" + fs_time + ", o_number=" + o_number
				+ ", ermanent_integral_bonus=" + ermanent_integral_bonus
				+ ", time_limited_integration=" + time_limited_integration
				+ ", full_integral_purchase=" + full_integral_purchase
				+ ", ocl_num=" + ocl_num + ", c_unit_price=" + c_unit_price
				+ ", time_limited_sum=" + time_limited_sum
				+ ", ermanent_integral_sum=" + ermanent_integral_sum
				+ ", price_sum=" + price_sum + ", fs_turnover_sum="
				+ fs_turnover_sum + "]";
	}

	public Integer getFs_id() {
		return fs_id;
	}

	public void setFs_id(Integer fs_id) {
		this.fs_id = fs_id;
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

	public Double getFs_turnover() {
		return fs_turnover;
	}

	public void setFs_turnover(Double fs_turnover) {
		this.fs_turnover = fs_turnover;
	}

	public String getFs_time() {
		return fs_time;
	}

	public void setFs_time(String fs_time) {
		this.fs_time = fs_time;
	}

	public String getO_number() {
		return o_number;
	}

	public void setO_number(String o_number) {
		this.o_number = o_number;
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

	public Double getFull_integral_purchase() {
		return full_integral_purchase;
	}

	public void setFull_integral_purchase(Double full_integral_purchase) {
		this.full_integral_purchase = full_integral_purchase;
	}

	public Integer getOcl_num() {
		return ocl_num;
	}

	public void setOcl_num(Integer ocl_num) {
		this.ocl_num = ocl_num;
	}

	public Double getC_unit_price() {
		return c_unit_price;
	}

	public void setC_unit_price(Double c_unit_price) {
		this.c_unit_price = c_unit_price;
	}

	public Double getTime_limited_sum() {
		return time_limited_sum;
	}

	public void setTime_limited_sum(Double time_limited_sum) {
		this.time_limited_sum = time_limited_sum;
	}

	public Double getErmanent_integral_sum() {
		return ermanent_integral_sum;
	}

	public void setErmanent_integral_sum(Double ermanent_integral_sum) {
		this.ermanent_integral_sum = ermanent_integral_sum;
	}

	public Double getPrice_sum() {
		return price_sum;
	}

	public void setPrice_sum(Double price_sum) {
		this.price_sum = price_sum;
	}

	public Double getFs_turnover_sum() {
		return fs_turnover_sum;
	}

	public void setFs_turnover_sum(Double fs_turnover_sum) {
		this.fs_turnover_sum = fs_turnover_sum;
	}

}
