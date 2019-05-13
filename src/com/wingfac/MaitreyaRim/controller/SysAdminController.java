package com.wingfac.MaitreyaRim.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wingfac.MaitreyaRim.po.SysAdmin;
import com.wingfac.MaitreyaRim.service.impl.SysAdminService;

@Controller
@RequestMapping("SysAdmin")
public class SysAdminController {

	@Autowired
	private SysAdminService sysAdminService;

	@ResponseBody
	@RequestMapping("loginJudgment")
	public Map<String, Object> loginJudgment(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			@RequestParam("saMobile") String saMobile,
			@RequestParam("saPassword") String saPassword) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Map<String, Object> m = new LinkedHashMap<String, Object>();
		map.put("sa_mobile", saMobile);
		map.put("sa_password", saPassword);
		SysAdmin selectSys = sysAdminService.selectSys(map);
		if (selectSys != null) {
			// 新增存session信息
			session.setAttribute("saMobile", selectSys.getSa_mobile());
			session.setAttribute("saRole", selectSys.getSa_role());

			m.put("msg", "1");
		} else {
			m.put("msg", "2");
		}
		return m;
	}

	@RequestMapping("jumpLogin")
	public String jumpLogin() {
		return "login";
	}

	@RequestMapping("jumpIndex")
	public String jumpIndex() {
		return "index";
	}

	@RequestMapping("exitSystem")
	public String exitSystem(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		// 新增删session信息
		session.removeAttribute("saMobile");
		session.removeAttribute("saRole");

		return "login";
	}

}
