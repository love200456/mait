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

import com.wingfac.MaitreyaRim.po.ShopRecommendation;
import com.wingfac.MaitreyaRim.service.impl.ShopRecommendationService;
import com.wingfac.MaitreyaRim.util.ResponseStatus;

@Controller
@RequestMapping("ShopRecommendationMobile")
public class ShopRecommendationMobileController {

	@Autowired
	private ShopRecommendationService shopRecommendationService;

	@ResponseBody
	@RequestMapping("selectOneSR")
	public Map<String, Object> selectOneSR(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<ShopRecommendation> selectOne = shopRecommendationService
				.selectOne();
		if (selectOne.size() > 0) {
			map.put("ResponseStatus", ResponseStatus.QUERYWASSUCCESS);
			map.put("msg", ResponseStatus.QUERYWASSUCCESS_CN_MSG);
			map.put("obj", selectOne);
		} else {
			map.put("ResponseStatus", ResponseStatus.STORENULL);
			map.put("msg", ResponseStatus.STORENULL_CN_MSG);
		}
		return map;
	}

	@ResponseBody
	@RequestMapping("selectTwoSR")
	public Map<String, Object> selectTwoSR(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<ShopRecommendation> selectTwo = shopRecommendationService
				.selectTwo();
		if (selectTwo.size() > 0) {
			map.put("ResponseStatus", ResponseStatus.QUERYWASSUCCESS);
			map.put("msg", ResponseStatus.QUERYWASSUCCESS_CN_MSG);
			map.put("obj", selectTwo);
		} else {
			map.put("ResponseStatus", ResponseStatus.STORENULL);
			map.put("msg", ResponseStatus.STORENULL_CN_MSG);
		}
		return map;
	}

}
