package com.wingfac.MaitreyaRim.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wingfac.MaitreyaRim.po.ComRecommendation;
import com.wingfac.MaitreyaRim.po.Commodity;
import com.wingfac.MaitreyaRim.po.CommodityVo;
import com.wingfac.MaitreyaRim.po.Store;
import com.wingfac.MaitreyaRim.service.impl.ComRecommendationService;
import com.wingfac.MaitreyaRim.service.impl.CommodityService;
import com.wingfac.MaitreyaRim.service.impl.StoreService;

@Controller
@RequestMapping("ComRecommendation")
public class ComRecommendationController {

	@Autowired
	private ComRecommendationService comRecommendationService;
	@Autowired
	private CommodityService commodityService;
	@Autowired
	private StoreService storeService;

	@RequestMapping("selectComAll")
	public String selectComAll(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) {
		List<Commodity> selectCoAll = commodityService.selectCoAll();
		List<CommodityVo> co = new ArrayList<CommodityVo>();
		CommodityVo cos;
		Store bysId = null;
		if (selectCoAll.size() > 0) {
			for (Commodity commodity : selectCoAll) {
				bysId = storeService.selectBysId(commodity.getS_id());
				if (bysId != null) {
					cos = new CommodityVo();
					cos.setC_id(commodity.getC_id());
					cos.setS_id(commodity.getS_id());
					cos.setC_name(commodity.getC_name());
					cos.setS_name(bysId.getS_name());
					cos.setC_first_figure(commodity.getC_first_figure());
					cos.setC_unit_price(commodity.getC_unit_price());
					co.add(cos);
				}
			}
			modelMap.put("list", co);
		}
		return "classify/firstgoods";
	}

	@RequestMapping("selectCoLike")
	public String selectCoLike(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap,
			@RequestParam("searchVal") String searchVal) {
		List<Commodity> selectCoLike = commodityService.selectCoLike(searchVal);
		List<CommodityVo> co = new ArrayList<CommodityVo>();
		CommodityVo cos;
		Store bysId = null;
		if (selectCoLike.size() > 0) {
			for (Commodity commodity : selectCoLike) {
				bysId = storeService.selectBysId(commodity.getS_id());
				if (bysId != null) {
					cos = new CommodityVo();
					cos.setC_id(commodity.getC_id());
					cos.setS_id(commodity.getS_id());
					cos.setC_name(commodity.getC_name());
					cos.setS_name(bysId.getS_name());
					cos.setC_first_figure(commodity.getC_first_figure());
					cos.setC_unit_price(commodity.getC_unit_price());
					co.add(cos);
				}
			}
			modelMap.put("list", co);
		}
		return "classify/firstgoods";
	}

	@RequestMapping("selectBysId")
	public String selectBysId(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap,
			@RequestParam("sId") String sId) {
		List<Commodity> selectBySiD = commodityService.selectBySiD(Integer.parseInt(sId));
		List<CommodityVo> co = new ArrayList<CommodityVo>();
		CommodityVo cos;
		Store bysId = null;
		if (selectBySiD.size() > 0) {
			for (Commodity commodity : selectBySiD) {
				cos = new CommodityVo();
				cos.setC_id(commodity.getC_id());
				cos.setS_id(commodity.getS_id());
				cos.setC_name(commodity.getC_name());
				bysId = storeService.selectBysId(commodity.getS_id());
				cos.setS_name(bysId.getS_name());
				cos.setC_first_figure(commodity.getC_first_figure());
				cos.setC_unit_price(commodity.getC_unit_price());
				co.add(cos);
			}
			modelMap.put("list", co);
		}
		return "classify/firstgoods";
	}

	@ResponseBody
	@RequestMapping("insertOneCR")
	public Map<String, Object> insertOneCR(HttpServletRequest request,
			HttpServletResponse response, String cId) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		String[] cIds = cId.split(",");
		Integer c_id[] = new Integer[cIds.length];
		for (int i = 0; i < cIds.length; i++) {
			c_id[i] = Integer.parseInt(cIds[i]);
		}
		Integer insertCR = 0;
		for (int i = 0; i < c_id.length; i++) {
			List<ComRecommendation> selectOneCR = comRecommendationService.selectOneCR();
			if (selectOneCR.size() < 6) {
				Commodity selectByCid = commodityService.selectByCid(c_id[i]);
				if (selectByCid != null) {
					ComRecommendation cr = new ComRecommendation();
					cr.setS_id(selectByCid.getS_id());
					cr.setC_id(selectByCid.getC_id());
					cr.setC_first_figure(selectByCid.getC_first_figure());
					cr.setC_name(selectByCid.getC_name());
					cr.setC_unit_price(selectByCid.getC_unit_price());
					cr.setCr_level("1");
					insertCR += comRecommendationService.insertCR(cr);
				} else {
					map.put("msg", "3");
				}
			} else {
				ComRecommendation selectByOneCR = comRecommendationService.selectByOneCR();
				Integer delectCR = comRecommendationService.delectCR(selectByOneCR.getCr_id());
				if (delectCR > 0) {
					Commodity selectByCid = commodityService.selectByCid(c_id[i]);
					if (selectByCid != null) {
						ComRecommendation cr = new ComRecommendation();
						cr.setS_id(selectByCid.getS_id());
						cr.setC_id(selectByCid.getC_id());
						cr.setC_first_figure(selectByCid.getC_first_figure());
						cr.setC_name(selectByCid.getC_name());
						cr.setC_unit_price(selectByCid.getC_unit_price());
						cr.setCr_level("1");
						insertCR += comRecommendationService.insertCR(cr);
					} else {
						map.put("msg", "3");
					}
				} else {
					map.put("msg", "4");
				}
			}
		}
		if (insertCR == c_id.length) {
			map.put("msg", "1");
		} else {
			map.put("msg", "2");
		}
		return map;
	}

}
