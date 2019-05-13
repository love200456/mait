package com.wingfac.MaitreyaRim.po;

import java.io.Serializable;

public class LimInteStatisties implements Serializable {

	private static final long serialVersionUID = 311447793696707706L;
	private Integer lis_id;
	private Integer auId;
	private String s_name;
	private String c_name;
	private Integer lis_consumption;
	private Integer lis_get;
	private String lis_time;
	private String lis_category;
	private String lis_term;
	private String lis_state;

	public LimInteStatisties() {
		super();
	}

	public LimInteStatisties(Integer lis_id, Integer auId, String s_name,
			String c_name, Integer lis_consumption, Integer lis_get,
			String lis_time, String lis_category, String lis_term,
			String lis_state) {
		super();
		this.lis_id = lis_id;
		this.auId = auId;
		this.s_name = s_name;
		this.c_name = c_name;
		this.lis_consumption = lis_consumption;
		this.lis_get = lis_get;
		this.lis_time = lis_time;
		this.lis_category = lis_category;
		this.lis_term = lis_term;
		this.lis_state = lis_state;
	}

	@Override
	public String toString() {
		return "LimInteStatisties [lis_id=" + lis_id + ", auId=" + auId
				+ ", s_name=" + s_name + ", c_name=" + c_name
				+ ", lis_consumption=" + lis_consumption + ", lis_get="
				+ lis_get + ", lis_time=" + lis_time + ", lis_category="
				+ lis_category + ", lis_term=" + lis_term + ", lis_state="
				+ lis_state + "]";
	}

	public Integer getLis_id() {
		return lis_id;
	}

	public void setLis_id(Integer lis_id) {
		this.lis_id = lis_id;
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

	public Integer getLis_consumption() {
		return lis_consumption;
	}

	public void setLis_consumption(Integer lis_consumption) {
		this.lis_consumption = lis_consumption;
	}

	public Integer getLis_get() {
		return lis_get;
	}

	public void setLis_get(Integer lis_get) {
		this.lis_get = lis_get;
	}

	public String getLis_time() {
		return lis_time;
	}

	public void setLis_time(String lis_time) {
		this.lis_time = lis_time;
	}

	public String getLis_category() {
		return lis_category;
	}

	public void setLis_category(String lis_category) {
		this.lis_category = lis_category;
	}

	public String getLis_term() {
		return lis_term;
	}

	public void setLis_term(String lis_term) {
		this.lis_term = lis_term;
	}

	public String getLis_state() {
		return lis_state;
	}

	public void setLis_state(String lis_state) {
		this.lis_state = lis_state;
	}

}
