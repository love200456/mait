package com.wingfac.MaitreyaRim.po;

import java.io.Serializable;

public class PerInteStatistics implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3220257470966694270L;
	private Integer pis_id;
	private Integer auId;
	private String s_name;
	private String c_name;
	private Double pis_consumption;
	private Double pis_get;
	private String pis_time;
	private String pis_category;

	public PerInteStatistics() {
		super();
	}

	public PerInteStatistics(Integer pis_id, Integer auId, String s_name,
			String c_name, Double pis_consumption, Double pis_get,
			String pis_time, String pis_category) {
		super();
		this.pis_id = pis_id;
		this.auId = auId;
		this.s_name = s_name;
		this.c_name = c_name;
		this.pis_consumption = pis_consumption;
		this.pis_get = pis_get;
		this.pis_time = pis_time;
		this.pis_category = pis_category;
	}

	@Override
	public String toString() {
		return "PerInteStatistics [pis_id=" + pis_id + ", auId=" + auId
				+ ", s_name=" + s_name + ", c_name=" + c_name
				+ ", pis_consumption=" + pis_consumption + ", pis_get="
				+ pis_get + ", pis_time=" + pis_time + ", pis_category="
				+ pis_category + "]";
	}

	public Integer getPis_id() {
		return pis_id;
	}

	public void setPis_id(Integer pis_id) {
		this.pis_id = pis_id;
	}

	public Integer getAuId() {
		return auId;
	}

	public void setAuId(Integer auId) {
		this.auId = auId;
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

	public Double getPis_consumption() {
		return pis_consumption;
	}

	public void setPis_consumption(Double pis_consumption) {
		this.pis_consumption = pis_consumption;
	}

	public Double getPis_get() {
		return pis_get;
	}

	public void setPis_get(Double pis_get) {
		this.pis_get = pis_get;
	}

	public String getPis_time() {
		return pis_time;
	}

	public void setPis_time(String pis_time) {
		this.pis_time = pis_time;
	}

	public String getPis_category() {
		return pis_category;
	}

	public void setPis_category(String pis_category) {
		this.pis_category = pis_category;
	}

}
