package com.wingfac.MaitreyaRim.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wingfac.MaitreyaRim.po.TitleTwoClass;
import com.wingfac.MaitreyaRim.service.impl.TitleTwoClassService;
import com.wingfac.MaitreyaRim.util.ResponseStatus;

@Controller
@RequestMapping("TitleTwoClassMobile")
public class TitleTwoClassControllerMobile {

	@Autowired
	private TitleTwoClassService titleTwoClassService;

	@ResponseBody
	@RequestMapping("selectTwoById")
	public Map<String, Object> selectTwoById(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("tocId") String tocId) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<TitleTwoClass> selectTwo = titleTwoClassService.selectTwo(Integer
				.parseInt(tocId));
		if (selectTwo.size() > 0) {
			map.put("ResponseStatus", ResponseStatus.QUERYWASSUCCESS);
			map.put("msg", ResponseStatus.QUERYWASSUCCESS_CN_MSG);
			map.put("obj", selectTwo);
		} else {
			map.put("ResponseStatus", ResponseStatus.STORENULL);
			map.put("mag", ResponseStatus.STORENULL_CN_MSG);
		}
		return map;
	}

	@ResponseBody
	@RequestMapping("selectTwoAll")
	public Map<String, Object> selectTwoAll(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<TitleTwoClass> selectTwoAll = titleTwoClassService.selectTwoAll();
		if (selectTwoAll.size() > 0) {
			map.put("ResponseStatus", ResponseStatus.QUERYWASSUCCESS);
			map.put("msg", ResponseStatus.QUERYWASSUCCESS_CN_MSG);
			map.put("obj", selectTwoAll);
		} else {
			map.put("ResponseStatus", ResponseStatus.STORENULL);
			map.put("mag", ResponseStatus.STORENULL_CN_MSG);
		}
		return map;
	}

}
