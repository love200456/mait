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

import com.wingfac.MaitreyaRim.po.FinanceStatistics;
import com.wingfac.MaitreyaRim.service.impl.FinanceStatisticsService;
import com.wingfac.MaitreyaRim.util.Constants;

@Controller
@RequestMapping("FinanceStatistics")
public class FinanceStatisticsController {

	@Autowired
	private FinanceStatisticsService financeStatisticsService;

	@RequestMapping("selectPage")
	public String selectPage(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap,
			@RequestParam(value = "curPage", defaultValue = "0", required = false) Integer curPage) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<FinanceStatistics> selectAll = financeStatisticsService
				.selectAll();
		if (selectAll.size() > 0) {
			Integer pstart = curPage * Constants.sun;
			map.put("psize", Constants.sun);
			map.put("pstart", pstart);
			List<FinanceStatistics> selectPage = financeStatisticsService
					.selectPage(map);
			if (selectPage.size() > 0) {
				Integer pages = selectAll.size() / Constants.sun;
				if (selectAll.size() % Constants.sun != 0) {
					pages = pages + 1;
				}
				modelMap.put("pages", pages);
				modelMap.put("list", selectPage);
				modelMap.put("curPage", curPage);
				modelMap.put("tiaojian", "1");
				// 新增求和
				FinanceStatistics selectAllSumObj = financeStatisticsService
						.selectAllSum().get(0);
				modelMap.put("time_limited_sum",
						selectAllSumObj.getTime_limited_sum());
				modelMap.put("ermanent_integral_sum",
						selectAllSumObj.getErmanent_integral_sum());
				modelMap.put("price_sum", selectAllSumObj.getPrice_sum());
				modelMap.put("fs_turnover_sum",
						selectAllSumObj.getFs_turnover_sum());
			}
		}
		return "money/money";
	}

	@RequestMapping("selectLikePage")
	public String selectLikePage(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap,
			@RequestParam("searchVal") String searchVal,
			@RequestParam(value = "curPage", defaultValue = "0", required = false) Integer curPage) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<FinanceStatistics> selectLikeAll = financeStatisticsService
				.selectLikeAll(searchVal);
		if (selectLikeAll.size() > 0) {
			Integer pstart = curPage * Constants.sun;
			map.put("psize", Constants.sun);
			map.put("pstart", pstart);
			map.put("s_name", searchVal);
			List<FinanceStatistics> selectLike = financeStatisticsService
					.selectLike(map);
			if (selectLike.size() > 0) {
				Integer pages = selectLikeAll.size() / Constants.sun;
				if (selectLikeAll.size() % Constants.sun != 0) {
					pages = pages + 1;
				}
				modelMap.put("pages", pages);
				modelMap.put("list", selectLike);
				modelMap.put("searchVal", searchVal);
				modelMap.put("curPage", curPage);
				modelMap.put("tiaojian", "2");
				// 新增求和
				FinanceStatistics selectLikeAllSumObj = financeStatisticsService
						.selectLikeAllSum(searchVal).get(0);
				modelMap.put("time_limited_sum",
						selectLikeAllSumObj.getTime_limited_sum());
				modelMap.put("ermanent_integral_sum",
						selectLikeAllSumObj.getErmanent_integral_sum());
				modelMap.put("price_sum", selectLikeAllSumObj.getPrice_sum());
				modelMap.put("fs_turnover_sum",
						selectLikeAllSumObj.getFs_turnover_sum());
			}
		}
		return "money/money";
	}

	@RequestMapping("selectTime")
	public String selectTime(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap,
			@RequestParam("strtime") String strtime,
			@RequestParam("endtime") String endtime,
			@RequestParam(value = "curPage", defaultValue = "0", required = false) Integer curPage)
					throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Map<String, Object> m = new LinkedHashMap<String, Object>();
		map.put("strtime", strtime);
		map.put("endtime", endtime);
		Integer total = financeStatisticsService.selectTimeTotal(map);
		if (total > 0) {
			Integer pstart = curPage * Constants.sun;
			m.put("psize", Constants.sun);
			m.put("pstart", pstart);
			m.put("strtime", strtime);
			m.put("endtime", endtime);
			List<FinanceStatistics> timePage = financeStatisticsService
					.selectTimePage(m);
			if (timePage.size() > 0) {
				Integer pages = total / Constants.sun12;
				if (total % Constants.sun12 != 0) {
					pages = pages + 1;
				}
				modelMap.put("pages", pages);
				modelMap.put("list", timePage);
				modelMap.put("strtime", strtime);
				modelMap.put("endtime", endtime);
				modelMap.put("curPage", curPage);
				modelMap.put("tiaojian", "3");
				// 新增求和
				FinanceStatistics selectTimeTotalSumObj = financeStatisticsService
						.selectTimeTotalSum(map).get(0);
				modelMap.put("time_limited_sum",
						selectTimeTotalSumObj.getTime_limited_sum());
				modelMap.put("ermanent_integral_sum",
						selectTimeTotalSumObj.getErmanent_integral_sum());
				modelMap.put("price_sum", selectTimeTotalSumObj.getPrice_sum());
				modelMap.put("fs_turnover_sum",
						selectTimeTotalSumObj.getFs_turnover_sum());
			}
		}
		return "money/money";
	}

	@ResponseBody
	@RequestMapping("delectFS")
	public Map<String, Object> delectFS(String checkedId) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Integer delectFS = financeStatisticsService.delectFS(checkedId);
		if (delectFS > 0) {
			map.put("msg", "1");
		} else {
			map.put("msg", "2");
		}
		return map;
	}

}
