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

import com.wingfac.MaitreyaRim.po.Withdraw;
import com.wingfac.MaitreyaRim.service.impl.WithdrawService;
import com.wingfac.MaitreyaRim.util.Constants;

@Controller
@RequestMapping("Withdraw")
public class WithdrawController {

	@Autowired
	private WithdrawService withdrawService;

	@RequestMapping("selectAll")
	public String selectAll(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap,
			@RequestParam(value = "curPage", defaultValue = "1", required = false) Integer curPage) {
		Map<String, Object> param = new LinkedHashMap<String, Object>();
		Integer pstart = (curPage-1) * Constants.sun;
		Integer count = withdrawService.getCount(param);
		Integer pages=(count+Constants.sun-1)/Constants.sun;
		param.put("psize", Constants.sun);
		param.put("pstart", pstart);
		List<Withdraw> list=withdrawService.selectAll(param);
		modelMap.put("pages", pages);
		modelMap.put("list",list);
		modelMap.put("curPage", curPage);
		return "withdraw/withdraw";
	}

}
