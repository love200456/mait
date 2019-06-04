package com.wingfac.MaitreyaRim.controller;

import java.util.ArrayList;
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
import com.wingfac.MaitreyaRim.po.Store;
import com.wingfac.MaitreyaRim.service.impl.ShopRecommendationService;
import com.wingfac.MaitreyaRim.service.impl.StoreService;
import com.wingfac.MaitreyaRim.util.ResponseStatus;

@Controller
@RequestMapping("ShopRecommendationMobile")
public class ShopRecommendationMobileController {

	@Autowired
	private ShopRecommendationService shopRecommendationService;

	@Autowired
	private StoreService storeService;
	
	@ResponseBody
	@RequestMapping("selectOneSR")
	public Map<String, Object> selectOneSR(HttpServletRequest request,
			HttpServletResponse response,Integer toc_id) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<ShopRecommendation> resultList=new ArrayList<ShopRecommendation>();
		List<ShopRecommendation> selectOne = shopRecommendationService.selectOne();
		for(ShopRecommendation ele:selectOne){
			Integer s_id=ele.getS_id();
			Store store=storeService.selectBysId(s_id);
			if(store!=null && store.getToc_id()==toc_id){
				resultList.add(ele);
			}
		}
		
		if (selectOne.size() > 0) {
			map.put("ResponseStatus", ResponseStatus.QUERYWASSUCCESS);
			map.put("msg", ResponseStatus.QUERYWASSUCCESS_CN_MSG);
			map.put("obj", resultList);
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
