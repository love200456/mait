package com.wingfac.MaitreyaRim.po;

import java.io.Serializable;

public class UserMessage implements Serializable {

	private static final long serialVersionUID = 173132564756769085L;
	private Integer um_id;
	private Integer auId;
	private String pm_content;
	private String um_time;
	private String um_flag;

	public UserMessage() {
		super();
	}

	public UserMessage(Integer um_id, Integer auId, String pm_content,
			String um_time, String um_flag) {
		super();
		this.um_id = um_id;
		this.auId = auId;
		this.pm_content = pm_content;
		this.um_time = um_time;
		this.um_flag = um_flag;
	}

	@Override
	public String toString() {
		return "UserMessage [um_id=" + um_id + ", auId=" + auId
				+ ", pm_content=" + pm_content + ", um_time=" + um_time
				+ ", um_flag=" + um_flag + "]";
	}

	public Integer getUm_id() {
		return um_id;
	}

	public void setUm_id(Integer um_id) {
		this.um_id = um_id;
	}

	public Integer getAuId() {
		return auId;
	}

	public void setAuId(Integer auId) {
		this.auId = auId;
	}

	public String getPm_content() {
		return pm_content;
	}

	public void setPm_content(String pm_content) {
		this.pm_content = pm_content;
	}

	public String getUm_time() {
		return um_time;
	}

	public void setUm_time(String um_time) {
		this.um_time = um_time;
	}

	public String getUm_flag() {
		return um_flag;
	}

	public void setUm_flag(String um_flag) {
		this.um_flag = um_flag;
	}

}
