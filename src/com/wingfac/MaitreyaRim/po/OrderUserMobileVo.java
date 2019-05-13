package com.wingfac.MaitreyaRim.po;

import java.io.Serializable;

public class OrderUserMobileVo implements Serializable{

	private static final long serialVersionUID = -1L;
	private Integer o_id;
	private String o_number;
	private String s_name;
	private String c_name;
	private Integer ocl_num;
	private Double c_unit_price;
	private String c_first_figure;
	private Double amount_paid;
	private String o_time;

	public OrderUserMobileVo() {
		super();
	}

	public OrderUserMobileVo(Integer o_id, String o_number, String s_name,
			String c_name, Integer ocl_num, Double c_unit_price,
			String c_first_figure, Double amount_paid, String o_time) {
		super();
		this.o_id = o_id;
		this.o_number = o_number;
		this.s_name = s_name;
		this.c_name = c_name;
		this.ocl_num = ocl_num;
		this.c_unit_price = c_unit_price;
		this.c_first_figure = c_first_figure;
		this.amount_paid = amount_paid;
		this.o_time = o_time;
	}

	@Override
	public String toString() {
		return "OrderUserMobileVo [o_id=" + o_id + ", o_number=" + o_number
				+ ", s_name=" + s_name + ", c_name=" + c_name + ", ocl_num="
				+ ocl_num + ", c_unit_price=" + c_unit_price
				+ ", c_first_figure=" + c_first_figure + ", amount_paid="
				+ amount_paid + ", o_time=" + o_time + "]";
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

	public Double getAmount_paid() {
		return amount_paid;
	}

	public void setAmount_paid(Double amount_paid) {
		this.amount_paid = amount_paid;
	}

	public String getO_time() {
		return o_time;
	}

	public void setO_time(String o_time) {
		this.o_time = o_time;
	}

}
