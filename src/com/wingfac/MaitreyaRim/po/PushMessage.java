package com.wingfac.MaitreyaRim.po;

import java.io.Serializable;

public class PushMessage implements Serializable {

	private static final long serialVersionUID = 2791039934306421885L;
	private Integer pm_id;
	private String pm_time;
	private String pm_content;

	public PushMessage() {
		super();
	}

	public PushMessage(Integer pm_id, String pm_time, String pm_content) {
		super();
		this.pm_id = pm_id;
		this.pm_time = pm_time;
		this.pm_content = pm_content;
	}

	@Override
	public String toString() {
		return "PushMessage [pm_id=" + pm_id + ", pm_time=" + pm_time
				+ ", pm_content=" + pm_content + "]";
	}

	public Integer getPm_id() {
		return pm_id;
	}

	public void setPm_id(Integer pm_id) {
		this.pm_id = pm_id;
	}

	public String getPm_time() {
		return pm_time;
	}

	public void setPm_time(String pm_time) {
		this.pm_time = pm_time;
	}

	public String getPm_content() {
		return pm_content;
	}

	public void setPm_content(String pm_content) {
		this.pm_content = pm_content;
	}

}
