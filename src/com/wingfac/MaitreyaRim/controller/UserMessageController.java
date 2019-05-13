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
import com.wingfac.MaitreyaRim.po.PushMessage;
import com.wingfac.MaitreyaRim.po.UserMessage;
import com.wingfac.MaitreyaRim.service.impl.AverageUserService;
import com.wingfac.MaitreyaRim.service.impl.PushMessageService;
import com.wingfac.MaitreyaRim.service.impl.UserMessageService;
import com.wingfac.MaitreyaRim.util.PushUtil;
import com.wingfac.MaitreyaRim.util.ResponseStatus;

@Controller
@RequestMapping("UserMessage")
public class UserMessageController {

	@Autowired
	private UserMessageService userMessageService;
	@Autowired
	private PushMessageService pushMessageService;
	@Autowired
	private AverageUserService averageUserService;

	@ResponseBody
	@RequestMapping("insertAllUM")
	public Map<String, Object> insertAllUM(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("id") String id)
					throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		PushMessage selectId = pushMessageService
				.selectId(Integer.parseInt(id));
		if (selectId != null) {
			SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
			String time = formater.format(new Date());
			Integer insertUM = 0;
			List<AverageUser> selectAll = averageUserService.selectAll();
			String tui = "";
			if (selectAll.size() > 0) {
				for (int i = 0; i < selectAll.size(); i++) {
					UserMessage um = new UserMessage();
					um.setAuId(selectAll.get(i).getAuId());
					um.setPm_content(selectId.getPm_content());
					um.setUm_time(time);
					insertUM += userMessageService.insertUM(um);
					tui += selectAll.get(i).getAuMobile() + ",";
				}
				PushUtil.doPush(tui, selectId.getPm_content());
				if (insertUM > 0) {
					map.put("msg", "1");
				} else {
					map.put("msg", "2");
				}
			} else {
				map.put("msg", "3");
			}
		} else {
			map.put("msg", "4");
		}
		return map;
	}

	@ResponseBody
	@RequestMapping("selectByauId")
	public Map<String, Object> selectByauId(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("auId") String auId) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<UserMessage> selectByauId = userMessageService
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

	@ResponseBody
	@RequestMapping("deleteUserAnnouncementById")
	public Map<String, Object> deleteUserAnnouncementById(
			HttpServletRequest request, HttpServletResponse response,
			@RequestParam("umId") String umId) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Integer integer = userMessageService
				.deleteUserAnnouncement(Integer.parseInt(umId));
		if (integer > 0) {
			map.put("ResponseStatus", ResponseStatus.QUERYWASSUCCESS);// '0'
			map.put("msg", "删除成功");
		} else {
			map.put("ResponseStatus", ResponseStatus.STORENULL);// '1'
			map.put("msg", "删除失败");
		}
		return map;
	}

}
