package com.wingfac.MaitreyaRim.po;

import java.io.Serializable;

public class TitleTwoClass implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 694558053143594973L;
	private Integer ttc_id;
	private Integer toc_id;
	private String ttc_name;

	public TitleTwoClass() {
		super();
	}

	public TitleTwoClass(Integer ttc_id, Integer toc_id, String ttc_name) {
		super();
		this.ttc_id = ttc_id;
		this.toc_id = toc_id;
		this.ttc_name = ttc_name;
	}

	@Override
	public String toString() {
		return "TitleTwoClass [ttc_id=" + ttc_id + ", toc_id=" + toc_id
				+ ", ttc_name=" + ttc_name + "]";
	}

	public Integer getTtc_id() {
		return ttc_id;
	}

	public void setTtc_id(Integer ttc_id) {
		this.ttc_id = ttc_id;
	}

	public Integer getToc_id() {
		return toc_id;
	}

	public void setToc_id(Integer toc_id) {
		this.toc_id = toc_id;
	}

	public String getTtc_name() {
		return ttc_name;
	}

	public void setTtc_name(String ttc_name) {
		this.ttc_name = ttc_name;
	}

}
