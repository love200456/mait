package com.wingfac.MaitreyaRim.po;

import java.io.Serializable;

public class SysAdmin implements Serializable {

	private static final long serialVersionUID = 683085656085880749L;
	private Integer sa_id;
	private String sa_mobile;
	private String sa_password;
	private String sa_role;

	public SysAdmin() {
		super();
	}

	public SysAdmin(Integer sa_id, String sa_mobile, String sa_password, String sa_role) {
		super();
		this.sa_id = sa_id;
		this.sa_mobile = sa_mobile;
		this.sa_password = sa_password;
		this.sa_role = sa_role;
	}

	@Override
	public String toString() {
		return "SysAdmin [sa_id=" + sa_id + ", sa_mobile=" + sa_mobile
				+ ", sa_password=" + sa_password + ", sa_role=" + sa_role + "]";
	}

	public Integer getSa_id() {
		return sa_id;
	}

	public void setSa_id(Integer sa_id) {
		this.sa_id = sa_id;
	}

	public String getSa_mobile() {
		return sa_mobile;
	}

	public void setSa_mobile(String sa_mobile) {
		this.sa_mobile = sa_mobile;
	}

	public String getSa_password() {
		return sa_password;
	}

	public void setSa_password(String sa_password) {
		this.sa_password = sa_password;
	}

	public String getSa_role() {
		return sa_role;
	}

	public void setSa_role(String sa_role) {
		this.sa_role = sa_role;
	}

}
