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

import com.wingfac.MaitreyaRim.po.ShopRecommendation;
import com.wingfac.MaitreyaRim.po.Store;
import com.wingfac.MaitreyaRim.service.impl.ShopRecommendationService;
import com.wingfac.MaitreyaRim.service.impl.StoreService;

@Controller
@RequestMapping("ShopRecommendation")
public class ShopRecommendationController {

	@Autowired
	private ShopRecommendationService shopRecommendationService;
	@Autowired
	private StoreService storeService;

	@ResponseBody
	@RequestMapping("insertOneSR")
	public Map<String, Object> insertOneSR(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("sp_level") String sp_level, String sId) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		String[] sIds = sId.split(",");
		Integer s_id[] = new Integer[sIds.length];
		for (int i = 0; i < sIds.length; i++) {
			s_id[i] = Integer.parseInt(sIds[i]);
		}
		Integer insertSR = 0;
		for (int i = 0; i < s_id.length; i++) {
			List<ShopRecommendation> selectOne = shopRecommendationService.selectOne();
			if (selectOne.size() < 10) {
				Store selectBysId = storeService.selectBysId(s_id[i]);
				if (selectBysId != null) {
					ShopRecommendation sr = new ShopRecommendation();
					sr.setS_id(selectBysId.getS_id());
					sr.setS_name(selectBysId.getS_name());
					sr.setPicture(selectBysId.getPicture());
					sr.setDescribe(selectBysId.getDescribe());
					sr.setS_longitude(selectBysId.getS_longitude());
					sr.setS_latitude(selectBysId.getS_latitude());
					sr.setSp_level(sp_level);
					insertSR += shopRecommendationService.insertSR(sr);
				} else {
					map.put("msg", "3");
				}
			} else {
				ShopRecommendation selectByOne = shopRecommendationService.selectByOne();
				Integer delectSP = shopRecommendationService.delectSP(selectByOne.getSp_id());
				if (delectSP > 0) {
					Store selectBysId = storeService.selectBysId(s_id[i]);
					if (selectBysId != null) {
						ShopRecommendation sr = new ShopRecommendation();
						sr.setS_id(selectBysId.getS_id());
						sr.setS_name(selectBysId.getS_name());
						sr.setPicture(selectBysId.getPicture());
						sr.setDescribe(selectBysId.getDescribe());
						sr.setS_longitude(selectBysId.getS_longitude());
						sr.setS_latitude(selectBysId.getS_latitude());
						sr.setSp_level(sp_level);
						insertSR += shopRecommendationService.insertSR(sr);
					} else {
						map.put("msg", "3");
					}
				} else {
					map.put("msg", "4");
				}
			}
		}
		if (insertSR == s_id.length) {
			map.put("msg", "1");
		} else {
			map.put("msg", "2");
		}
		return map;
	}

	@ResponseBody
	@RequestMapping("insertTwoSR")
	public Map<String, Object> insertTwoSR(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("sp_level") String sp_level, String sId) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		String[] sIds = sId.split(",");
		Integer s_id[] = new Integer[sIds.length];
		for (int i = 0; i < sIds.length; i++) {
			s_id[i] = Integer.parseInt(sIds[i]);
		}
		Integer insertSR = 0;
		for (int i = 0; i < s_id.length; i++) {
			List<ShopRecommendation> selectTwo = shopRecommendationService
					.selectTwo();
			if (selectTwo.size() < 10) {
				Store selectBysId = storeService.selectBysId(s_id[i]);
				if (selectBysId != null) {
					ShopRecommendation sr = new ShopRecommendation();
					sr.setS_id(selectBysId.getS_id());
					sr.setS_name(selectBysId.getS_name());
					sr.setPicture(selectBysId.getPicture());
					sr.setDescribe(selectBysId.getDescribe());
					sr.setS_longitude(selectBysId.getS_longitude());
					sr.setS_latitude(selectBysId.getS_latitude());
					sr.setSp_level(sp_level);
					insertSR += shopRecommendationService.insertSR(sr);
				} else {
					map.put("msg", "3");
				}
			} else {
				ShopRecommendation selectByTwo = shopRecommendationService
						.selectByTwo();
				Integer delectSP = shopRecommendationService
						.delectSP(selectByTwo.getSp_id());
				if (delectSP > 0) {
					Store selectBysId = storeService.selectBysId(s_id[i]);
					if (selectBysId != null) {
						ShopRecommendation sr = new ShopRecommendation();
						sr.setS_id(selectBysId.getS_id());
						sr.setS_name(selectBysId.getS_name());
						sr.setPicture(selectBysId.getPicture());
						sr.setDescribe(selectBysId.getDescribe());
						sr.setS_longitude(selectBysId.getS_longitude());
						sr.setS_latitude(selectBysId.getS_latitude());
						sr.setSp_level(sp_level);
						insertSR += shopRecommendationService.insertSR(sr);
					} else {
						map.put("msg", "3");
					}
				} else {
					map.put("msg", "4");
				}
			}
		}
		if (insertSR == s_id.length) {
			map.put("msg", "1");
		} else {
			map.put("msg", "2");
		}
		return map;
	}

}
