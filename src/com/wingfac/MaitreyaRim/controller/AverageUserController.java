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

import com.wingfac.MaitreyaRim.po.AverageUser;
import com.wingfac.MaitreyaRim.service.impl.AverageUserService;
import com.wingfac.MaitreyaRim.util.Constants;

@Controller
@RequestMapping("AverageUser")
public class AverageUserController {

	@Autowired
	private AverageUserService averageUserService;

	@RequestMapping("selectAll")
	public String selectAll(
			HttpServletRequest request,
			HttpServletResponse response,
			ModelMap modelMap,
			@RequestParam(value = "curPage", defaultValue = "0", required = false) Integer curPage) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Integer pstart = curPage * Constants.sun;
		map.put("psize", Constants.sun);
		map.put("pstart", pstart);
		List<AverageUser> selectAll = averageUserService.selectAll();
		if (selectAll.size() > 0) {
			List<AverageUser> selectAllFen = averageUserService.selectAllFen(map);
			if (selectAllFen.size() > 0) {
				Integer pages = selectAll.size() / Constants.sun;
				if (selectAll.size() % Constants.sun != 0) {
					pages = pages + 1;
				}
				modelMap.put("pages", pages);
				modelMap.put("list", selectAllFen);
				modelMap.put("curPage", curPage);
				modelMap.put("tiaojian", true);
			}
		}
		return "user/user";
	}

	@RequestMapping("selectLike")
	public String selectLike(
			HttpServletRequest request,
			HttpServletResponse response,
			ModelMap modelMap,
			@RequestParam("searchVal") String searchVal,
			@RequestParam(value = "curPage", defaultValue = "0", required = false) Integer curPage) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Integer pstart = curPage * Constants.sun;
		map.put("psize", Constants.sun);
		map.put("pstart", pstart);
		map.put("auMobile", searchVal);
		List<AverageUser> selectLike = averageUserService.selectLike(searchVal);
		if (selectLike.size() > 0) {
			List<AverageUser> selectLikeFen = averageUserService.selectLikeFen(map);
			if (selectLikeFen.size() > 0) {
				Integer pages = selectLike.size() / Constants.sun;
				if (selectLike.size() % Constants.sun != 0) {
					pages = pages + 1;
				}
				modelMap.put("pages", pages);
				modelMap.put("list", selectLikeFen);
				modelMap.put("curPage", curPage);
				modelMap.put("tiaojian", false);
				modelMap.put("searchVal", searchVal);
			}
		}
		return "user/user";
	}
	
	@ResponseBody
	@RequestMapping("updateauIdentity")
	public Map<String, Object> updateauIdentity(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("auId") String auId) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Integer updateauIdentity = averageUserService.updateauIdentity(Integer
				.parseInt(auId));
		if (updateauIdentity > 0) {
			map.put("msg", "1");
		} else {
			map.put("msg", "2");
		}
		return map;
	}

	@RequestMapping("selectauLP")
	public String selectauLP(
			HttpServletRequest request,
			HttpServletResponse response,
			ModelMap modelMap,
			@RequestParam(value = "curPage", defaultValue = "0", required = false) Integer curPage) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Integer pstart = curPage * Constants.sun;
		map.put("psize", Constants.sun);
		map.put("pstart", pstart);
		List<AverageUser> selectAll = averageUserService.selectAll();
		if (selectAll.size() > 0) {
			List<AverageUser> selectAllFen = averageUserService.selectAllFen(map);
			if (selectAllFen.size() > 0) {
				Integer pages = selectAll.size() / Constants.sun;
				if (selectAll.size() % Constants.sun != 0) {
					pages = pages + 1;
				}
				modelMap.put("pages", pages);
				modelMap.put("list", selectAllFen);
				modelMap.put("curPage", curPage);
				modelMap.put("tiaojian", true);
			}
		}
		return "integral/integral";
	}

	@RequestMapping("selectLikeLP")
	public String selectLikeLP(
			HttpServletRequest request,
			HttpServletResponse response,
			ModelMap modelMap,
			@RequestParam("searchVal") String searchVal,
			@RequestParam(value = "curPage", defaultValue = "0", required = false) Integer curPage) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Integer pstart = curPage * Constants.sun;
		map.put("psize", Constants.sun);
		map.put("pstart", pstart);
		map.put("auMobile", searchVal);
		List<AverageUser> selectLike = averageUserService.selectLike(searchVal);
		if (selectLike.size() > 0) {
			List<AverageUser> selectLikeFen = averageUserService.selectLikeFen(map);
			if (selectLikeFen.size() > 0) {
				Integer pages = selectLike.size() / Constants.sun;
				if (selectLike.size() % Constants.sun != 0) {
					pages = pages + 1;
				}
				modelMap.put("pages", pages);
				modelMap.put("list", selectLikeFen);
				modelMap.put("curPage", curPage);
				modelMap.put("tiaojian", false);
				modelMap.put("searchVal", searchVal);
			}
		}
		return "integral/integral";
	}
	
	@ResponseBody
	@RequestMapping("modifyRemarks")
	public Map<String, Object> modifyRemarks(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("auId") String auId,
			@RequestParam("resarks") String resarks) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Map<String, Object> m = new LinkedHashMap<String, Object>();
		m.put("auId", Long.parseLong(auId));
		m.put("remarks", resarks);
		Integer modifyRemarks = averageUserService.modifyRemarks(m);
		if (modifyRemarks > 0) {
			map.put("msg", "1");
		} else {
			map.put("msg", "2");
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping("modifyIntegralRemarks")
	public Map<String, Object> modifyIntegralRemarks(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("auId") String auId,
			@RequestParam("integralremarks") String integralremarks) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Map<String, Object> m = new LinkedHashMap<String, Object>();
		m.put("auId", Long.parseLong(auId));
		m.put("integral_remark", integralremarks);
		Integer modifyRemarks = averageUserService.modifyIntegralRemark(m);
		if (modifyRemarks > 0) {
			map.put("msg", "1");
		} else {
			map.put("msg", "2");
		}
		return map;
	}

}
