package com.wingfac.MaitreyaRim.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wingfac.MaitreyaRim.po.TitleOneClass;
import com.wingfac.MaitreyaRim.service.impl.TitleOneClassService;
import com.wingfac.MaitreyaRim.util.ResponseStatus;

@Controller
@RequestMapping("TitleOneClassMobile")
public class TitleOneClassControllerMobile {

	@Autowired
	private TitleOneClassService titleOneClassService;

	@ResponseBody
	@RequestMapping("selectOne")
	public Map<String, Object> selectOne(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<TitleOneClass> selectOne = titleOneClassService.selectOne();
		if (selectOne.size() > 0) {
			map.put("ResponseStatus", ResponseStatus.QUERYWASSUCCESS);
			map.put("msg", ResponseStatus.QUERYWASSUCCESS_CN_MSG);
			map.put("obj", selectOne);
		} else {
			map.put("ResponseStatus", ResponseStatus.TITLEONEFAIL);
			map.put("msg", ResponseStatus.TITLEONEFAIL_CN_MSG);
		}
		return map;
	}

	@RequestMapping("jump")
	public String jump(){
		return "classify/firstadv";
	}
	
}
