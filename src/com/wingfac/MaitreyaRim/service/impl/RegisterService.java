package com.wingfac.MaitreyaRim.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.wingfac.MaitreyaRim.mapper.RegisterMapper;
import com.wingfac.MaitreyaRim.po.AverageUser;
import com.wingfac.MaitreyaRim.po.AverageUserVo;

public class RegisterService {

	@Autowired
	private RegisterMapper registerMapper;

	public Integer insertAverUser(AverageUser averageUser) {
		return registerMapper.insertAverUser(averageUser);
	}

	public AverageUser selectByMobile(String auMobile) {
		return registerMapper.selectByMobile(auMobile);
	}

	public Integer updateauAvatar(Map<String, Object> map) {
		return registerMapper.updateauAvatar(map);
	}

	public AverageUserVo selectByauId(Integer auId) {
		return registerMapper.selectByauId(auId);
	}

	public Integer updateauPasswordByauMoblie(Map<String, Object> map) {
		return registerMapper.updateauPasswordByauMoblie(map);
	}

	public Integer updateauPasswordByauId(Map<String, Object> map) {
		return registerMapper.updateauPasswordByauId(map);
	}

	public Integer updateauAddress(Map<String, Object> map) {
		return registerMapper.updateauAddress(map);
	}

	public Integer updateauNick(Map<String, Object> map) {
		return registerMapper.updateauNick(map);
	}

}
