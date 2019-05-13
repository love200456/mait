package com.wingfac.MaitreyaRim.controller;

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

import com.wingfac.MaitreyaRim.po.Store;
import com.wingfac.MaitreyaRim.po.UserStoreFollow;
import com.wingfac.MaitreyaRim.service.impl.StoreService;
import com.wingfac.MaitreyaRim.service.impl.UserStoreFollowService;
import com.wingfac.MaitreyaRim.util.ResponseStatus;

@Controller
@RequestMapping("UserStoreFollow")
public class UserStoreFollowController {

	@Autowired
	private UserStoreFollowService userStoreFollowService;
	@Autowired
	private StoreService storeService;

	@ResponseBody
	@RequestMapping("follow")
	public Map<String, Object> follow(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("auId") String auId,
			@RequestParam("s_id") String s_id) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		UserStoreFollow usf = new UserStoreFollow();
		Store selectBysId = storeService.selectBysId(Integer.parseInt(s_id));
		if (selectBysId != null) {
			usf.setAuId(Integer.parseInt(auId));
			usf.setS_id(selectBysId.getS_id());
			usf.setS_name(selectBysId.getS_name());
			usf.setS_logo(selectBysId.getS_logo());
			usf.setIntegral_setting(selectBysId.getIntegral_setting());
			usf.setS_longitude(selectBysId.getS_longitude());
			usf.setS_latitude(selectBysId.getS_latitude());
			Integer insertUSF = userStoreFollowService.insertUSF(usf);
			if (insertUSF > 0) {
				map.put("ResponseStatus", ResponseStatus.FOLLOWSUCCESS);
				map.put("msg", ResponseStatus.FOLLOWSUCCESS_CN_MSG);
			} else {
				map.put("ResponseStatus", ResponseStatus.FOLLOWFAIL);
				map.put("msg", ResponseStatus.FOLLOWFAIL_CN_MSG);
			}
		} else {
			map.put("ResponseStatus", ResponseStatus.AUDITINGGOODSFAIL4);
			map.put("msg", ResponseStatus.AUDITINGGOODSFAIL_CN_MSG4);
		}
		return map;
	}

	@ResponseBody
	@RequestMapping("takeOff")
	public Map<String, Object> takeOff(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("auId") String auId,
			@RequestParam("s_id") String s_id) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Map<String, Object> m = new LinkedHashMap<String, Object>();
		m.put("auId", Integer.parseInt(auId));
		m.put("s_id", Integer.parseInt(s_id));
		Integer delectUSF = userStoreFollowService.delectUSF(m);
		if (delectUSF > 0) {
			map.put("ResponseStatus", ResponseStatus.TAKEOFFSUCCESS);
			map.put("msg", ResponseStatus.TAKEOFFSUCCESS_CN_MSG);
		} else {
			map.put("ResponseStatus", ResponseStatus.TAKEOFFFAIL);
			map.put("msg", ResponseStatus.TAKEOFFFAIL_CN_MSG);
		}
		return map;
	}

	@ResponseBody
	@RequestMapping("selectAll")
	public Map<String, Object> selectAll(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("auId") String auId,
			@RequestParam("pstart") String pstart,
			@RequestParam("psize") String psize) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Map<String, Object> m = new LinkedHashMap<String, Object>();
		Integer pstarts = Integer.parseInt(pstart);
		Integer psizes = Integer.parseInt(psize);
		m.put("pstart", pstarts * psizes);
		m.put("psize", psizes);
		m.put("auId", Integer.parseInt(auId));
		List<UserStoreFollow> selectByauId = userStoreFollowService
				.selectByauId(m);
		if (selectByauId.size() > 0) {
			map.put("ResponseStatus", ResponseStatus.QUERYWASSUCCESS);
			map.put("msg", ResponseStatus.QUERYWASSUCCESS_CN_MSG);
			map.put("selectByauId", selectByauId);
		} else {
			map.put("ResponseStatus", ResponseStatus.USERFOTAFAIL);
			map.put("msg", ResponseStatus.USERFOTAFAIL_CN_MSG);
		}
		return map;
	}

	@ResponseBody
	@RequestMapping("followSid")
	public Map<String, Object> followSid(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("auId") String auId,
			@RequestParam("sId") String sId) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Map<String, Object> m = new LinkedHashMap<String, Object>();
		map.put("auId", Integer.parseInt(auId));
		map.put("s_id", Integer.parseInt(sId));
		UserStoreFollow selectByAuidSid = userStoreFollowService
				.selectByAuidSid(map);
		if (selectByAuidSid != null) {
			m.put("ResponseStatus", ResponseStatus.QUERYWASSUCCESS);
			m.put("msg", ResponseStatus.QUERYWASSUCCESS_CN_MSG);
		} else {
			m.put("ResponseStatus", ResponseStatus.STORENULL);
			m.put("msg", ResponseStatus.STORENULL_CN_MSG);
		}
		return m;
	}

}
