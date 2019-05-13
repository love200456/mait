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

import com.wingfac.MaitreyaRim.po.Employ;
import com.wingfac.MaitreyaRim.service.impl.EmployService;
import com.wingfac.MaitreyaRim.util.Constants;

@Controller
@RequestMapping("EmployManagement")
public class EmployManagementController {

	@Autowired
	private EmployService employService;

	@RequestMapping("selectAll")
	public String selectAll(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap,
			@RequestParam(value = "curPage", defaultValue = "0", required = false) Integer curPage) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Integer pstart = curPage * Constants.sun;
		map.put("psize", Constants.sun);
		map.put("pstart", pstart);
		List<Employ> selectAll = employService.selectAll();
		if (selectAll.size() > 0) {
			List<Employ> selectAllFen = employService
					.selectAllFen(map);
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
		return "employ/employ";
	}

	@RequestMapping("selectLike")
	public String selectLike(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap,
			@RequestParam("searchVal") String searchVal,
			@RequestParam(value = "curPage", defaultValue = "0", required = false) Integer curPage) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Integer pstart = curPage * Constants.sun;
		map.put("psize", Constants.sun);
		map.put("pstart", pstart);
		map.put("auMobile", searchVal);
		List<Employ> selectLike = employService
				.selectLike(searchVal);
		if (selectLike.size() > 0) {
			List<Employ> selectLikeFen = employService
					.selectLikeFen(map);
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
		return "employ/employ";
	}

	@ResponseBody
	@RequestMapping("modifyEmpInfo")
	public Map<String, Object> modifyEmpInfo(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap,
			@RequestParam(value = "curPage", defaultValue = "0", required = false) Integer curPage,
			@RequestParam("emp_id") String empId,
			@RequestParam("position_describe") String positionDescribe) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		// 修改信息
		map.put("empId", empId);
		map.put("positionDescribe", positionDescribe);
		int modifyResult = employService.modifyEmpInfo(map);

		if (modifyResult > 0) {
			map.put("msg", "1");
		} else {
			map.put("msg", "2");
		}

		return map;
	}

	@ResponseBody
	@RequestMapping("auditEmpInfo")
	public Map<String, Object> auditEmpInfo(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("checkedId") String checkedId) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		String[] empIdStr = checkedId.split(",");
		int auditResult = 0;

		for (int i = 0; i < empIdStr.length; i++) {
			Integer empId = Integer.valueOf(empIdStr[i]);
			auditResult += employService.auditEmpInfo(empId);
		}

		if (auditResult > 0) {
			map.put("msg", "1");
		} else {
			map.put("msg", "2");
		}

		return map;
	}

	@ResponseBody
	@RequestMapping("deleteEmpInfo")
	public Map<String, Object> deleteEmpInfo(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("checkedId") String checkedId) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		String[] empIdStr = checkedId.split(",");
		int deleteResult = 0;

		for (int i = 0; i < empIdStr.length; i++) {
			Integer empId = Integer.valueOf(empIdStr[i]);
			deleteResult += employService.deleteEmpInfo(empId);
		}

		if (deleteResult > 0) {
			map.put("msg", "1");
		} else {
			map.put("msg", "2");
		}

		return map;
	}
}
