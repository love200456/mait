package com.wingfac.MaitreyaRim.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
//import java.util.HashMap;
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

import com.wingfac.MaitreyaRim.po.AverageUser;
import com.wingfac.MaitreyaRim.po.LimInteStatisties;
import com.wingfac.MaitreyaRim.po.PerInteStatistics;
import com.wingfac.MaitreyaRim.service.impl.AverageUserService;
import com.wingfac.MaitreyaRim.service.impl.LimInteStatistiesService;
import com.wingfac.MaitreyaRim.service.impl.PerInteStatisticsService;
import com.wingfac.MaitreyaRim.util.ResponseStatus;

@Controller
@RequestMapping("PerInteStatistics")
public class PerInteStatisticsController {

	@Autowired
	private PerInteStatisticsService perInteStatisticsService;
	@Autowired
	private LimInteStatistiesService limInteStatistiesService;
	@Autowired
	private AverageUserService averageUserService;

	@ResponseBody
	@RequestMapping("selectByauIdPI")
	public Map<String, Object> selectByauIdPI(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("auId") String auId) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<PerInteStatistics> selectByauId = perInteStatisticsService
				.selectByauId(Integer.parseInt(auId));
		if (selectByauId.size() > 0) {
			map.put("ResponseStatus", ResponseStatus.QUERYWASSUCCESS);
			map.put("msg", ResponseStatus.QUERYWASSUCCESS_CN_MSG);
			map.put("obj", selectByauId);
		} else {
			map.put("ResponseStatus", ResponseStatus.STORENULL);
			map.put("msg", ResponseStatus.STORENULL_CN_MSG);
		}
		return map;
	}

	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param checkedId 用户IDS
	 * @param pis_get 0
	 * @param lis_get 积分金额
	 * @param lis_term 积分有效期
	 * @return
	 */
	
	@ResponseBody
	@RequestMapping("insertPL")
	public Map<String, Object> insertPL(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("checkedId") String checkedId,
			@RequestParam("pis_get") String pis_get,
			@RequestParam("lis_get") String lis_get,
			@RequestParam("lis_term") String lis_term) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Map<String, Object> ma = new LinkedHashMap<String, Object>();
		String[] auIds = checkedId.split(",");
		Integer auId[] = new Integer[auIds.length];
		for (int i = 0; i < auIds.length; i++) {
			auId[i] = Integer.parseInt(auIds[i]);
		}
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		String time = formater.format(new Date());
		Integer insertPI = 0;
		Integer insertLI = 0;
		for (int i = 0; i < auId.length; i++) {
			if (auId[i] != 0) {
				PerInteStatistics pis = new PerInteStatistics();
				pis.setAuId(auId[i]);
				pis.setS_name("无");
				pis.setC_name("无");
				pis.setPis_consumption(0d);
				pis.setPis_get(Double.valueOf(pis_get));
				pis.setPis_time(time);
				pis.setPis_category("+");
				insertPI += perInteStatisticsService.insertPI(pis);
				if (insertPI > 0) {
					LimInteStatisties lis = new LimInteStatisties();
					lis.setAuId(auId[i]);
					lis.setS_name("无");
					lis.setC_name("无");
					lis.setLis_consumption(0d);
					lis.setLis_get(Double.parseDouble(lis_get));
					lis.setLis_time(time);
					lis.setLis_category("+");
					//期限
					lis.setLis_term(lis_term);
					lis.setLis_state("1");
					insertLI += limInteStatistiesService.insertLI(lis);
					if (insertLI > 0) {
						AverageUser selectByauId = averageUserService.selectByauId(auId[i]);
						//下面两行可能写错了
						Double xLimit = selectByauId.getLimit_integral() + Integer.parseInt(pis_get);
						Double xPermanent = selectByauId.getPermanent_points() + Integer.parseInt(lis_get);
						ma.put("limit_integral", xLimit);
						ma.put("permanent_points", xPermanent);
						ma.put("auId", auId[i]);
						averageUserService.updateLisPis(ma);
					} else {
						map.put("msg", "4");
					}
				} else {
					map.put("msg", "3");
				}
			}
		}
		if (insertPI == auId.length && insertLI == auId.length) {
			map.put("msg", "1");
		} else {
			map.put("msg", "2");
		}
		return map;
	}
	
	
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param checkedId 用户IDS
	 * @param cash 现金积分金额
	 * @param expenses 生活费积分金额
	 * @return
	 */
	
	@ResponseBody
	@RequestMapping("insertPLN")
	public Map<String, Object> insertPLN(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("checkedId") String checkedId,
			@RequestParam("cash") Double cash,
			@RequestParam("expenses") Double expenses) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Map<String, Object> ma = new LinkedHashMap<String, Object>();
		String[] auIds = checkedId.split(",");
		Integer auId[] = new Integer[auIds.length];
		for (int i = 0; i < auIds.length; i++) {
			auId[i] = Integer.parseInt(auIds[i]);
		}
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		String time = formater.format(new Date());
		Integer insertPI = 0;
		Integer insertLI = 0;
		for (int i = 0; i < auId.length; i++) {
			if (auId[i] != 0) {
				PerInteStatistics pis = new PerInteStatistics();
				pis.setAuId(auId[i]);
				pis.setS_name("无");
				pis.setC_name("无");
				pis.setPis_consumption(0d);
				pis.setPis_get(cash);
				pis.setPis_time(time);
				pis.setPis_category("+");
				insertPI += perInteStatisticsService.insertPI(pis);
				if (insertPI > 0) {
					LimInteStatisties lis = new LimInteStatisties();
					lis.setAuId(auId[i]);
					lis.setS_name("无");
					lis.setC_name("无");
					lis.setLis_consumption(0d);
					lis.setLis_get(expenses);
					lis.setLis_time(time);
					lis.setLis_category("+");
					//期限 这个没有用上
					lis.setLis_term("0");
					lis.setLis_state("1");
					insertLI += limInteStatistiesService.insertLI(lis);
					if (insertLI > 0) {
						AverageUser selectByauId = averageUserService.selectByauId(auId[i]);
						Double xPermanent = selectByauId.getPermanent_points() + cash;
						Double xLimit = selectByauId.getLimit_integral() + expenses;
						ma.put("limit_integral", xLimit);
						ma.put("permanent_points", xPermanent);
						ma.put("auId", auId[i]);
						averageUserService.updateLisPis(ma);
					} else {
						map.put("msg", "4");
					}
				} else {
					map.put("msg", "3");
				}
			}
		}
		if (insertPI == auId.length && insertLI == auId.length) {
			map.put("msg", "1");
		} else {
			map.put("msg", "2");
		}
		return map;
	}
	

	@ResponseBody
	@RequestMapping("selectByauIdPIxin")
	public Map<String, Object> selectByauIdPIxin(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("auId") String auId) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<PerInteStatistics> selectByauId = perInteStatisticsService
				.selectByauId(Integer.parseInt(auId));
		if (selectByauId.size() > 0) {
			AverageUser byauId = averageUserService.selectByauId(Integer
					.parseInt(auId));
			map.put("limit", byauId.getLimit_integral());
			map.put("selectByauId", selectByauId);
			map.put("msg", "0");
		} else {
			map.put("msg", "1");
		}
		return map;
	}

	@ResponseBody
	@RequestMapping("selectIntByauId")
	public Map<String, Object> selectIntByauId(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("auId") String auId) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		AverageUser selectByauId = averageUserService.selectByauId(Integer
				.parseInt(auId));
		if (selectByauId != null) {
			map.put("ResponseStatus", ResponseStatus.QUERYWASSUCCESS);
			map.put("msg", ResponseStatus.QUERYWASSUCCESS_CN_MSG);
			map.put("obj", selectByauId);
		} else {
			map.put("ResponseStatus", ResponseStatus.STORENULL);
			map.put("msg", ResponseStatus.STORENULL_CN_MSG);
		}
		return map;
	}

}
