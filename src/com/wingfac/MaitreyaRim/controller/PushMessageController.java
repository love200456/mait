package com.wingfac.MaitreyaRim.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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

import com.wingfac.MaitreyaRim.po.PushMessage;
import com.wingfac.MaitreyaRim.service.impl.PushMessageService;
import com.wingfac.MaitreyaRim.util.Constants;

@Controller
@RequestMapping("PushMessage")
public class PushMessageController {

	@Autowired
	private PushMessageService pushMessageService;

	@ResponseBody
	@RequestMapping("insertPM")
	public Map<String, Object> insertPM(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("pm_content") String pm_content) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Map<String, Object> m = new LinkedHashMap<String, Object>();
		Date date = new Date();
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		String time = formater.format(date);
		map.put("pm_content", pm_content);
		map.put("pm_time", time);
		Integer insertPM = pushMessageService.insertPM(map);
		if (insertPM > 0) {
			m.put("msg", "1");
		} else {
			m.put("msg", "2");
		}
		return m;
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
		List<PushMessage> selectAll = pushMessageService.selectAll();
		if (selectAll.size() > 0) {
			List<PushMessage> selectAllPage = pushMessageService.selectAllPage(map);
			if (selectAllPage.size() > 0) {
				Integer pages = selectAll.size() / Constants.sun;
				if (selectAll.size() % Constants.sun != 0) {
					pages = pages + 1;
				}
				modelMap.put("pages", pages);
				modelMap.put("list", selectAllPage);
				modelMap.put("curPage", curPage);
			}
		}
		return "message/message";
	}

	@ResponseBody
	@RequestMapping("updatePM")
	public Map<String, Object> updatePM(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("pm_id") String pm_id,
			@RequestParam("pm_content") String pm_content) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Map<String, Object> m = new LinkedHashMap<String, Object>();
		Date date = new Date();
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		String time = formater.format(date);
		map.put("pm_id", Integer.parseInt(pm_id));
		map.put("pm_content", pm_content);
		map.put("pm_time", time);
		Integer updatePM = pushMessageService.updatePM(map);
		if (updatePM > 0) {
			m.put("msg", "1");
		} else {
			m.put("msg", "2");
		}
		return m;
	}

	@ResponseBody
	@RequestMapping("doFalseDelete")
	public Map<String, Object> doFalseDelete(String checkedId) {
		Map<String, Object> map = new HashMap<String, Object>();
		int delectPM = pushMessageService.delectPM(checkedId);
		if (delectPM > 0) {
			map.put("msg", "1");
		} else {
			map.put("msg", "2");
		}
		return map;
	}

}
