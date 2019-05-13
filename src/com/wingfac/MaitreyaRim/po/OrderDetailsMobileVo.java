package com.wingfac.MaitreyaRim.po;

import java.io.Serializable;

public class OrderDetailsMobileVo implements Serializable {

	private static final long serialVersionUID = 464048771582045599L;
	private Integer o_id;
	private String o_number;
	private String s_name;
	private String c_name;
	private Integer ocl_num;
	private Double c_unit_price;
	private String c_first_figure;
	private String o_time;
	private Integer use_limit_integral;
	private Integer use_permanent_points;
	private Double ermanent_integral_bonus;
	private Double time_limited_integration;
	private Integer commodity_integral;
	private Integer integral_available;
	private String auAddress;

	public OrderDetailsMobileVo() {
		super();
	}

	public OrderDetailsMobileVo(Integer o_id, String o_number, String s_name,
			String c_name, Integer ocl_num, Double c_unit_price,
			String c_first_figure, String o_time, Integer use_limit_integral,
			Integer use_permanent_points, Double ermanent_integral_bonus,
			Double time_limited_integration, Integer commodity_integral,
			Integer integral_available, String auAddress) {
		super();
		this.o_id = o_id;
		this.o_number = o_number;
		this.s_name = s_name;
		this.c_name = c_name;
		this.ocl_num = ocl_num;
		this.c_unit_price = c_unit_price;
		this.c_first_figure = c_first_figure;
		this.o_time = o_time;
		this.use_limit_integral = use_limit_integral;
		this.use_permanent_points = use_permanent_points;
		this.ermanent_integral_bonus = ermanent_integral_bonus;
		this.time_limited_integration = time_limited_integration;
		this.commodity_integral = commodity_integral;
		this.integral_available = integral_available;
		this.auAddress = auAddress;
	}

	@Override
	public String toString() {
		return "OrderDetailsMobileVo [o_id=" + o_id + ", o_number=" + o_number
				+ ", s_name=" + s_name + ", c_name=" + c_name + ", ocl_num="
				+ ocl_num + ", c_unit_price=" + c_unit_price
				+ ", c_first_figure=" + c_first_figure + ", o_time=" + o_time
				+ ", use_limit_integral=" + use_limit_integral
				+ ", use_permanent_points=" + use_permanent_points
				+ ", ermanent_integral_bonus=" + ermanent_integral_bonus
				+ ", time_limited_integration=" + time_limited_integration
				+ ", auAddress=" + auAddress + ", commodity_integral="
				+ commodity_integral + ", integral_available="
				+ integral_available + "]";
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

	public String getS_name() {
		return s_name;
	}

	public void setS_name(String s_name) {
		this.s_name = s_name;
	}

	public String getC_name() {
		return c_name;
	}

	public void setC_name(String c_name) {
		this.c_name = c_name;
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

	public String getC_first_figure() {
		return c_first_figure;
	}

	public void setC_first_figure(String c_first_figure) {
		this.c_first_figure = c_first_figure;
	}

	public String getO_time() {
		return o_time;
	}

	public void setO_time(String o_time) {
		this.o_time = o_time;
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

	public Integer getCommodity_integral() {
		return commodity_integral;
	}

	public void setCommodity_integral(Integer commodity_integral) {
		this.commodity_integral = commodity_integral;
	}

	public String getAuAddress() {
		return auAddress;
	}

	public void setAuAddress(String auAddress) {
		this.auAddress = auAddress;
	}

	public Integer getIntegral_available() {
		return integral_available;
	}

	public void setIntegral_available(Integer integral_available) {
		this.integral_available = integral_available;
	}

}
