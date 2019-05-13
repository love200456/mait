package com.wingfac.MaitreyaRim.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
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
@RequestMapping("LimInteStatisties")
public class LimInteStatistiesController {

	@Autowired
	private LimInteStatistiesService limInteStatistiesService;
	@Autowired
	private AverageUserService averageUserService;
	@Autowired
	private PerInteStatisticsService perInteStatisticsService;

	@ResponseBody
	@RequestMapping("selectByauIdLI")
	public Map<String, Object> selectByauIdLI(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("auId") String auId)
			throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		//Map<String, Object> m = new LinkedHashMap<String, Object>();
		List<LimInteStatisties> selectByauId = limInteStatistiesService.selectByauId(Integer.parseInt(auId));
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

	@ResponseBody
	@RequestMapping("selectByLI")
	public Map<String, Object> selectByLI(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("auId") String auId)
			throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		//Map<String, Object> m = new LinkedHashMap<String, Object>();
		List<LimInteStatisties> selectByauId = limInteStatistiesService.selectByauId(Integer.parseInt(auId));
		if (selectByauId.size() > 0) {
			AverageUser byauId = averageUserService.selectByauId(Integer.parseInt(auId));
			map.put("permanent", byauId.getPermanent_points());
			map.put("selectByauId", selectByauId);
			map.put("msg", "0");
			map.put("exist", "0");
		} else{
			List<PerInteStatistics> selectByauId2 = perInteStatisticsService.selectByauId(Integer.parseInt(auId));
			if(selectByauId2.size()>0){
				map.put("exist", "1");
				map.put("msg", "0");
			}else{
				map.put("msg", "1");
			}
		}
		return map;
	}

}