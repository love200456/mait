package com.wingfac.MaitreyaRim.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.wingfac.MaitreyaRim.mapper.AverageUserMapper;
import com.wingfac.MaitreyaRim.po.AverageUser;

public class AverageUserService {

	@Autowired
	private AverageUserMapper averageUserMapper;

	public List<AverageUser> selectAll() {
		return averageUserMapper.selectAll();
	}

	public List<AverageUser> selectAllFen(Map<String, Object> map) {
		return averageUserMapper.selectAllFen(map);
	}

	public List<AverageUser> selectLike(String auMobile) {
		return averageUserMapper.selectLike(auMobile);
	}

	public List<AverageUser> selectLikeFen(Map<String, Object> map) {
		return averageUserMapper.selectLikeFen(map);
	}

	public AverageUser selectByauId(Integer auId) {
		return averageUserMapper.selectByauId(auId);
	}

	public Integer updateauIdentity(Integer auId) {
		return averageUserMapper.updateauIdentity(auId);
	}

	public Integer updateauIntegral(Map<String, Object> map) {
		return averageUserMapper.updateauIntegral(map);
	}

	public Integer updateLisstate(Map<String, Object> map) {
		return averageUserMapper.updateLisstate(map);
	}

	public Integer updateLisPis(Map<String, Object> map) {
		return averageUserMapper.updateLisPis(map);
	}

	public Integer deleteUaer(Integer auId) {
		return averageUserMapper.deleteUaer(auId);
	}
	
	public Integer modifyRemarks(Map<String, Object> map) {
		return averageUserMapper.modifyRemarks(map);
	}
	
	public Integer modifyIntegralRemark(Map<String, Object> map) {
		return averageUserMapper.modifyIntegralRemark(map);
	}

	public Integer updateMoney(Map<String,Object> map){
		return averageUserMapper.updateMoney(map);
	}
	
}
