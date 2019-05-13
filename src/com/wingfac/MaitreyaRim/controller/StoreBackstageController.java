package com.wingfac.MaitreyaRim.controller;

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

import com.wingfac.MaitreyaRim.po.Store;
import com.wingfac.MaitreyaRim.po.TitleOneClass;
import com.wingfac.MaitreyaRim.po.TitleTwoClass;
import com.wingfac.MaitreyaRim.service.impl.StoreService;
import com.wingfac.MaitreyaRim.service.impl.TitleOneClassService;
import com.wingfac.MaitreyaRim.service.impl.TitleTwoClassService;
import com.wingfac.MaitreyaRim.util.Constants;

@Controller
@RequestMapping("StoreBackstage")
public class StoreBackstageController {

	@Autowired
	private StoreService storeService;
	@Autowired
	private TitleOneClassService titleOneClassService;
	@Autowired
	private TitleTwoClassService titleTwoClassService;

	@RequestMapping("selectAll")
	public String selectAll(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) {
		List<Store> slesctAllPage = storeService.slesctAllPage();
		if (slesctAllPage.size() > 0) {
			modelMap.put("slesctAllPage", slesctAllPage);
		}
		return "classify/classify";
	}

	@RequestMapping("selectAllStoreXian")
	public String selectAllStoreXian(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap,
			@RequestParam("level") String level) {
		List<Store> slesctAllPage = storeService.slesctAllPage();
		if (slesctAllPage.size() > 0) {
			modelMap.put("list", slesctAllPage);
			modelMap.put("tiaojian", true);
		}
		if (level.equals("1")) {
			return "classify/firststore";
		} else {
			return "classify/secondstore";
		}
	}

	@RequestMapping("selectLikeLevelXian")
	public String selectLikeLevelXian(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap,
			@RequestParam("levels") String levels,
			@RequestParam("searchVal") String searchVal) {
		List<Store> allLikeTotal = storeService.selectAllLikeTotal(searchVal);
		if (allLikeTotal.size() > 0) {
			modelMap.put("list", allLikeTotal);
			modelMap.put("searchVal", searchVal);
			modelMap.put("tiaojian", false);
		}
		if (levels.equals("1")) {
			return "classify/firststore";
		} else {
			return "classify/secondstore";
		}
	}

	@RequestMapping("selectAllPage")
	public String selectAllPage(
			HttpServletRequest request,
			HttpServletResponse response,
			ModelMap modelMap,
			@RequestParam(value = "curPage", defaultValue = "0", required = false) Integer curPage) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Integer pstart = curPage * Constants.sun;
		map.put("psize", Constants.sun);
		map.put("pstart", pstart);
		List<Store> allPage = storeService.slesctAllPage();
		if (allPage.size() > 0) {
			List<Store> list = storeService.selectAll(map);
			if (list.size() > 0) {
				Integer pages = allPage.size() / Constants.sun;
				if (allPage.size() % Constants.sun != 0) {
					pages = pages + 1;
				}
				modelMap.put("pages", pages);
				modelMap.put("list", list);
				modelMap.put("curPage", curPage);
				modelMap.put("tiaojian", true);
			}
		}
		return "shop/shop";
	}

	@RequestMapping("selectStoreLike")
	public String selectStoreLike(
			HttpServletRequest request,
			HttpServletResponse response,
			ModelMap modelMap,
			@RequestParam("searchVal") String searchVal,
			@RequestParam(value = "curPage", defaultValue = "0", required = false) Integer curPage) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Integer pstart = curPage * Constants.sun;
		map.put("s_name", searchVal);
		map.put("psize", Constants.sun);
		map.put("pstart", pstart);
		List<Store> allLikeTotal = storeService.selectAllLikeTotal(searchVal);
		if (allLikeTotal.size() > 0) {
			List<Store> selectAllLike = storeService.selectAllLike(map);
			if (selectAllLike.size() > 0) {
				Integer pages = allLikeTotal.size() / Constants.sun;
				if (allLikeTotal.size() % Constants.sun != 0) {
					pages = pages + 1;
				}
				modelMap.put("pages", pages);
				modelMap.put("list", selectAllLike);
				modelMap.put("searchVal", searchVal);
				modelMap.put("curPage", curPage);
				modelMap.put("tiaojian", false);
			}
		}
		return "shop/shop";
	}

	@RequestMapping("selectBysIdPage")
	public String selectBysIdPage(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("s_id") String s_id,
			ModelMap modelMap) {
		if (!"".equals(s_id)) {
			Store bysId = storeService.selectBysId(Integer.parseInt(s_id));
			List<TitleOneClass> selectOne = titleOneClassService.selectOne();
			List<TitleTwoClass> selectTwo = titleTwoClassService.selectTwo(bysId.getToc_id());
			if (bysId != null) {
				modelMap.put("bysId", bysId);
				modelMap.put("selectOne", selectOne);
				modelMap.put("selectTwo", selectTwo);
			}
		}
		return "shop/shopmessage";
	}

	@RequestMapping("jumpShopdetails")
	public String jumpShopdetails(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("s_id") String s_id,
			ModelMap modelMap) {
		if (s_id != null || s_id != "") {
			modelMap.put("s_id", s_id);
		}
		return "shop/shopdetails";
	}

	@RequestMapping("selectOR")
	public String selectOR(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("s_id") String s_id,
			ModelMap modelMap) {
		Store selectBysId = storeService.selectBysId(Integer.parseInt(s_id));
		if (selectBysId != null) {
			modelMap.put("qrcode", selectBysId.getPayment_code());
		} else {
			modelMap.put("qrcode", " ");
		}
		return "shop/erweima";
	}

	@ResponseBody
	@RequestMapping("selectBysName")
	public Map<String, Object> selectBysName(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("sName") String sName) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Store selectBysName = storeService.selectBysName(sName);
		if (selectBysName != null) {
			map.put("csId", selectBysName.getS_id());
			map.put("msg", "1");
		} else {
			map.put("msg", "2");
		}
		return map;
	}

	@ResponseBody
	@RequestMapping("updayeStoreIntegral")
	public Map<String, Object> updayeStoreIntegral(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("s_id") String s_id,
			@RequestParam("integral_setting") String integral_setting,
			@RequestParam("offset_setting") String offset_setting,
			@RequestParam("toc_id") String toc_id,
			@RequestParam("ttc_id") String ttc_id,
			@RequestParam("deductible_percentage") String deductible_percentage) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Map<String, Object> m = new LinkedHashMap<String, Object>();
		m.put("s_id", Integer.parseInt(s_id));
		m.put("integral_setting", Double.parseDouble(integral_setting));
		m.put("offset_setting", Double.parseDouble(offset_setting));
		m.put("toc_id", Integer.parseInt(toc_id));
		m.put("ttc_id", Integer.parseInt(ttc_id));
		m.put("deductible_percentage", Double.parseDouble(deductible_percentage));
		Integer interBysId = storeService.updateInterBysId(m);
		map.put("s_id", Integer.parseInt(s_id));
		if (interBysId > 0) {
			map.put("msg", "1");
		} else {
			map.put("msg", "2");
		}
		return map;
	}

	@ResponseBody
	@RequestMapping("modifyShopRemarksById")
	public Map<String, Object> modifyShopRemarksById(
			HttpServletRequest request, HttpServletResponse response,
			@RequestParam("s_id") String s_id,
			@RequestParam("shop_note") String shop_note) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Map<String, Object> m = new LinkedHashMap<String, Object>();
		m.put("s_id", Long.parseLong(s_id));
		m.put("shop_note", shop_note);
		Integer integer = storeService.modifyShopRemarks(m);
		if (integer > 0) {
			map.put("msg", "1");
		} else {
			map.put("msg", "2");
		}
		return map;
	}
	
}
