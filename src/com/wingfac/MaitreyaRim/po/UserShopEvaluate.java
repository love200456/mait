package com.wingfac.MaitreyaRim.po;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserShopEvaluate implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long use_id;
	private Integer auId;
	private Integer s_id;
	private Integer user_mark;
	private String content;
	private String resetContent;
	private String picA;
	private String picB;
	private String picC;
	private String picD;
	private Integer picNum;
	private String auAvatar;
	private String auBuyerNick;
	private Date createTime;
	private Long order_id;
	public UserShopEvaluate() {
		super();
	}

	public UserShopEvaluate(Long use_id, Integer auId, Integer s_id,
			Integer user_mark) {
		super();
		this.use_id = use_id;
		this.auId = auId;
		this.s_id = s_id;
		this.user_mark = user_mark;
	}

	@Override
	public String toString() {
		return "UserShopEvaluate [use_id=" + use_id + ", auId=" + auId
				+ ", s_id=" + s_id + ", user_mark=" + user_mark + "]";
	}

	public Long getUse_id() {
		return use_id;
	}

	public void setUse_id(Long use_id) {
		this.use_id = use_id;
	}

	public Integer getAuId() {
		return auId;
	}

	public void setAuId(Integer auId) {
		this.auId = auId;
	}

	public Integer getS_id() {
		return s_id;
	}

	public void setS_id(Integer s_id) {
		this.s_id = s_id;
	}

	public Integer getUser_mark() {
		return user_mark;
	}

	public void setUser_mark(Integer user_mark) {
		this.user_mark = user_mark;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getResetContent() {
		return resetContent;
	}

	public void setResetContent(String resetContent) {
		this.resetContent = resetContent;
	}

	public String getPicA() {
		return picA;
	}

	public void setPicA(String picA) {
		this.picA = picA;
	}

	public String getPicB() {
		return picB;
	}

	public void setPicB(String picB) {
		this.picB = picB;
	}

	public String getPicC() {
		return picC;
	}

	public void setPicC(String picC) {
		this.picC = picC;
	}

	public String getPicD() {
		return picD;
	}

	public void setPicD(String picD) {
		this.picD = picD;
	}

	public Integer getPicNum() {
		return picNum;
	}

	public void setPicNum(Integer picNum) {
		this.picNum = picNum;
	}

	public String getAuAvatar() {
		return auAvatar;
	}

	public void setAuAvatar(String auAvatar) {
		this.auAvatar = auAvatar;
	}

	public String getAuBuyerNick() {
		return auBuyerNick;
	}

	public void setAuBuyerNick(String auBuyerNick) {
		this.auBuyerNick = auBuyerNick;
	}

	
	public Long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	public String getCreateTimeStr() {
		String str="";
		if(this.createTime!=null){
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			str=sdf.format(this.createTime);
		}
		return str;
	}
}
