package com.wingfac.MaitreyaRim.po;

import java.io.Serializable;

public class Employ implements Serializable {

	private static final long serialVersionUID = 5073184465880762453L;

	private Integer emp_id;
	private Integer auId;
	private String s_name;
	private String s_address;
	private String position;
	private String position_describe;
	private String salary;
	private String work_nature;
	private Integer emp_num;
	private String release_date;
	private String contacts;
	private String contacts_tel;
	private String emp_flag;
	private String update_time;
	private String create_time;
	//招聘类型(普通招聘 putong,悬赏招聘 xuanshang)
	private String employType;

	public Employ() {
		super();
	}

	public Employ(Integer emp_id, Integer auId, String s_name, String s_address,
			String position, String position_describe, String salary,
			String work_nature, Integer emp_num, String release_date,
			String contacts, String contacts_tel, String emp_flag,
			String update_time, String create_time) {
		super();
		this.emp_id = emp_id;
		this.auId = auId;
		this.s_name = s_name;
		this.s_address = s_address;
		this.position = position;
		this.position_describe = position_describe;
		this.salary = salary;
		this.work_nature = work_nature;
		this.emp_num = emp_num;
		this.release_date = release_date;
		this.contacts = contacts;
		this.contacts_tel = contacts_tel;
		this.emp_flag = emp_flag;
		this.update_time = update_time;
		this.create_time = create_time;
	}

	@Override
	public String toString() {
		return "Employ [emp_id=" + emp_id + ", auId=" + auId + ", s_name="
				+ s_name + ", s_address=" + s_address + ", position=" + position
				+ ", position_describe=" + position_describe + ", salary="
				+ salary + ", work_nature=" + work_nature + ", emp_num="
				+ emp_num + ", release_date=" + release_date + ", contacts="
				+ contacts + ", contacts_tel=" + contacts_tel + ", emp_flag="
				+ emp_flag + ", update_time=" + update_time + ", create_time="
				+ create_time + "]";
	}

	public Integer getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(Integer emp_id) {
		this.emp_id = emp_id;
	}

	public Integer getAuId() {
		return auId;
	}

	public void setAuId(Integer auId) {
		this.auId = auId;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPosition_describe() {
		return position_describe;
	}

	public void setPosition_describe(String position_describe) {
		this.position_describe = position_describe;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getWork_nature() {
		return work_nature;
	}

	public void setWork_nature(String work_nature) {
		this.work_nature = work_nature;
	}

	public Integer getEmp_num() {
		return emp_num;
	}

	public void setEmp_num(Integer emp_num) {
		this.emp_num = emp_num;
	}

	public String getRelease_date() {
		return release_date;
	}

	public void setRelease_date(String release_date) {
		this.release_date = release_date;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getContacts_tel() {
		return contacts_tel;
	}

	public void setContacts_tel(String contacts_tel) {
		this.contacts_tel = contacts_tel;
	}

	public String getEmp_flag() {
		return emp_flag;
	}

	public void setEmp_flag(String emp_flag) {
		this.emp_flag = emp_flag;
	}

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getS_name() {
		return s_name;
	}

	public void setS_name(String s_name) {
		this.s_name = s_name;
	}

	public String getS_address() {
		return s_address;
	}

	public void setS_address(String s_address) {
		this.s_address = s_address;
	}

	public String getEmployType() {
		return employType;
	}

	public void setEmployType(String employType) {
		this.employType = employType;
	}

	
}
