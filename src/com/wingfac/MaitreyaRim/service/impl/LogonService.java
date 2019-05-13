package com.wingfac.MaitreyaRim.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.aliyuncs.exceptions.ClientException;
import com.wingfac.MaitreyaRim.mapper.LogonMapper;
import com.wingfac.MaitreyaRim.po.AverageUserVo;
import com.wingfac.MaitreyaRim.util.Constants;
import com.wingfac.MaitreyaRim.util.SmsUtil;

public class LogonService {

	@Autowired
	private LogonMapper logonMapper;

	public AverageUserVo logon(Map<String, Object> map) {
		return logonMapper.logon(map);
	}
	
	public AverageUserVo jude(String auMobile) {
		return logonMapper.jude(auMobile);
	}

	public Map<String, Object> sendOutYZM(HttpServletRequest request,
			String phone) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String UUID = Constants.getRandom4();
		String strDate = Constants.getSystemTime();
		ServletContext sc = request.getServletContext();
		sc.setAttribute("strDate", strDate);
		sc.setAttribute("phone", phone);
		sc.setAttribute("generateYZM", UUID);
		SmsUtil.sendSms(phone, UUID);
		map.put("msg", "发送成功");
		map.put("code", UUID);
		map.put("phone", phone);
		return map;
	}

}
