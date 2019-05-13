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

@Controller
@RequestMapping("TitleTwoClassMobile")
public class TitleTwoClassController {

	@Autowired
	private TitleTwoClassService titleTwoClassService;
	
	@ResponseBody
	@RequestMapping("selectByToid")
	public Map<String, Object> selectByToid(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("tocId") String tocId){
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<TitleTwoClass> selectTwo = titleTwoClassService.selectTwo(Integer.parseInt(tocId));
		if(selectTwo.size()>0){
			map.put("selectTwo", selectTwo);
		}else{
			map.put("selectTwo", "无");
		}
		return map;
	}
	
}
