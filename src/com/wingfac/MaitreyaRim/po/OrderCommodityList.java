package com.wingfac.MaitreyaRim.po;

import java.io.Serializable;

public class OrderCommodityList implements Serializable {

	private static final long serialVersionUID = -5606508761862389748L;
	private Integer ocl_id;
	private Integer o_id;
	private String c_name;
	private Integer ocl_num;
	private Double c_unit_price;
	private String c_introduce;
	private String c_first_figure;

	public OrderCommodityList() {
		super();
	}

	public OrderCommodityList(Integer ocl_id, Integer o_id, String c_name,
			Integer ocl_num, Double c_unit_price, String c_introduce,
			String c_first_figure) {
		super();
		this.ocl_id = ocl_id;
		this.o_id = o_id;
		this.c_name = c_name;
		this.ocl_num = ocl_num;
		this.c_unit_price = c_unit_price;
		this.c_introduce = c_introduce;
		this.c_first_figure = c_first_figure;
	}

	@Override
	public String toString() {
		return "OrderCommodityList [ocl_id=" + ocl_id + ", o_id=" + o_id
				+ ", c_name=" + c_name + ", ocl_num=" + ocl_num
				+ ", c_unit_price=" + c_unit_price + ", c_introduce="
				+ c_introduce + ", c_first_figure=" + c_first_figure + "]";
	}

	public Integer getOcl_id() {
		return ocl_id;
	}

	public void setOcl_id(Integer ocl_id) {
		this.ocl_id = ocl_id;
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

	public String getC_introduce() {
		return c_introduce;
	}

	public void setC_introduce(String c_introduce) {
		this.c_introduce = c_introduce;
	}

	public String getC_first_figure() {
		return c_first_figure;
	}

	public void setC_first_figure(String c_first_figure) {
		this.c_first_figure = c_first_figure;
	}

}
