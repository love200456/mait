package com.wingfac.MaitreyaRim.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wingfac.MaitreyaRim.po.AverageUserVo;
import com.wingfac.MaitreyaRim.po.Store;
import com.wingfac.MaitreyaRim.po.StoreVo;
import com.wingfac.MaitreyaRim.service.impl.LogonService;
import com.wingfac.MaitreyaRim.service.impl.StoreService;
import com.wingfac.MaitreyaRim.util.ResponseStatus;

@Controller
@RequestMapping("logon")
public class LogonController {

	@Autowired
	private LogonService logonService;
	@Autowired
	private StoreService storeService;

	@ResponseBody
	@RequestMapping("logonJudge")
	public Map<String, Object> logonJudge(HttpServletRequest request) {
		String auMobile = request.getParameter("auMobile");
		String auPassword = request.getParameter("auPassword");
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> m = new HashMap<String, Object>();
		if (!"".equals(auMobile) && !"".equals(auPassword)) {
			m.put("auMobile", auMobile);
			m.put("auPassword", auPassword);
			AverageUserVo aUser = logonService.logon(m);
			if (aUser != null) {
				if (aUser.getAuIdentity().equals("2")) {
					Store selectByauIdaddress = storeService.selectByauIdaddress(Integer.parseInt(aUser.getAuId()));
					if (selectByauIdaddress != null) {
						StoreVo sv = new StoreVo();
						sv.setS_id(selectByauIdaddress.getS_id() + "");
						sv.setS_address(selectByauIdaddress.getS_address());
						sv.setS_longitude(selectByauIdaddress.getS_longitude());
						sv.setS_latitude(selectByauIdaddress.getS_latitude());
						map.put("obj1", sv);
						map.put("obj", aUser);
						map.put("msg", ResponseStatus.LOGINSUCCESS_CN_MSG);
						map.put("ResponseStatus", ResponseStatus.LOGINSUCCESS);
					} else {
						map.put("msg", ResponseStatus.AUDITINGGOODSFAIL_CN_MSG3);
						map.put("ResponseStatus", ResponseStatus.LOGINSUCCESS);
						map.put("obj", aUser);
					}
				} else {
					map.put("obj", aUser);
					map.put("msg", ResponseStatus.LOGINSUCCESS_CN_MSG);
					map.put("ResponseStatus", ResponseStatus.LOGINSUCCESS);
				}
			} else {
				map.put("msg", ResponseStatus.LOGINNULL_CN_MSG);
				map.put("ResponseStatus", ResponseStatus.LOGINNULL);
			}
		} else {
			map.put("msg", ResponseStatus.LOGINFAIL_CN_MSG);
			map.put("ResponseStatus", ResponseStatus.LOGINFAIL);
		}
		return map;
	}

	@ResponseBody
	@RequestMapping("sendAuthenticationcode")
	public Map<String, Object> sendAuthenticationcode(
			HttpServletRequest request, @RequestParam("phone") String phone,
			@RequestParam("judge") String judge) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		AverageUserVo vo = logonService.jude(phone);
		if (judge.equals("1")) {
			if (vo != null) {
				map.put("msg", "该手机号已被注册");
				map.put("ResponseStatus", "1");
			} else {
				try {
					map = logonService.sendOutYZM(request, phone);
					map.put("ResponseStatus", "0");
					map.put("msg", "验证码发送成功");
				} catch (Exception e) {
					map.put("ResponseStatus", "1");
					map.put("msg", "验证码发送失败,请重新发送");
					e.printStackTrace();
				}
			}
		} else {
			if (vo != null) {
				try {
					map = logonService.sendOutYZM(request, phone);
					map.put("ResponseStatus", "0");
					map.put("msg", "验证码发送成功");
				} catch (Exception e) {
					map.put("ResponseStatus", "1");
					map.put("msg", "验证码发送失败,请重新发送");
					e.printStackTrace();
				}
			} else {
				map.put("responseStatus", "1");
				map.put("msg", "请确认该手机号是否正确!");
			}
		}
		return map;
	}

}
