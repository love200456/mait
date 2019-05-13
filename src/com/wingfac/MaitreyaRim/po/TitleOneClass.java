package com.wingfac.MaitreyaRim.po;

import java.io.Serializable;

public class TitleOneClass implements Serializable {

	private static final long serialVersionUID = 6114559302959770735L;
	private Integer toc_id;
	private String toc_name;
	private String toc_havenohave;

	public TitleOneClass() {
		super();
	}

	public TitleOneClass(Integer toc_id, String toc_name, String toc_havenohave) {
		super();
		this.toc_id = toc_id;
		this.toc_name = toc_name;
		this.toc_havenohave = toc_havenohave;
	}

	@Override
	public String toString() {
		return "TitleOneClass [toc_id=" + toc_id + ", toc_id=" + toc_id
				+ ", toc_havenohave=" + toc_havenohave + "]";
	}

	public Integer getToc_id() {
		return toc_id;
	}

	public void setToc_id(Integer toc_id) {
		this.toc_id = toc_id;
	}

	public String getToc_name() {
		return toc_name;
	}

	public void setToc_name(String toc_name) {
		this.toc_name = toc_name;
	}

	public String getToc_havenohave() {
		return toc_havenohave;
	}

	public void setToc_havenohave(String toc_havenohave) {
		this.toc_havenohave = toc_havenohave;
	}

}
