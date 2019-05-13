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

import com.wingfac.MaitreyaRim.po.ComRecommendation;
import com.wingfac.MaitreyaRim.service.impl.ComRecommendationService;
import com.wingfac.MaitreyaRim.util.ResponseStatus;

@Controller
@RequestMapping("ComRecommendationMobile")
public class ComRecommendationMobileController {

	@Autowired
	private ComRecommendationService comRecommendationService;

	@ResponseBody
	@RequestMapping("selectOneCR")
	public Map<String, Object> selectOneCR(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<ComRecommendation> selectOneCR = comRecommendationService
				.selectOneCR();
		if (selectOneCR.size() > 0) {
			map.put("ResponseStatus", ResponseStatus.QUERYWASSUCCESS);
			map.put("msg", ResponseStatus.QUERYWASSUCCESS_CN_MSG);
			map.put("obj", selectOneCR);
		} else {
			map.put("ResponseStatus", ResponseStatus.STORENULL);
			map.put("msg", ResponseStatus.STORENULL_CN_MSG);
		}
		return map;
	}
	
	
	/**推荐的二级商品
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("selectTwoCR")
	public Map<String, Object> selectTwoCR(HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<ComRecommendation> selectOneCR = comRecommendationService.selectTwoCR();
		if (selectOneCR.size() > 0) {
			map.put("ResponseStatus", ResponseStatus.QUERYWASSUCCESS);
			map.put("msg", ResponseStatus.QUERYWASSUCCESS_CN_MSG);
			map.put("obj", selectOneCR);
		} else {
			map.put("ResponseStatus", ResponseStatus.STORENULL);
			map.put("msg", ResponseStatus.STORENULL_CN_MSG);
		}
		return map;
	}

}
