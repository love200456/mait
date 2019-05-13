package com.wingfac.MaitreyaRim.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.wingfac.MaitreyaRim.mapper.UserStoreFollowMapper;
import com.wingfac.MaitreyaRim.po.UserStoreFollow;

public class UserStoreFollowService {

	@Autowired
	private UserStoreFollowMapper userStoreFollowMapper;

	public Integer insertUSF(UserStoreFollow userStoreFollow) {
		return userStoreFollowMapper.insertUSF(userStoreFollow);
	}

	public Integer delectUSF(Map<String, Object> map) {
		return userStoreFollowMapper.delectUSF(map);
	}

	public List<UserStoreFollow> selectByauId(Map<String, Object> map) {
		return userStoreFollowMapper.selectByauId(map);
	}

	public UserStoreFollow selectByAuidSid(Map<String, Object> map) {
		return userStoreFollowMapper.selectByAuidSid(map);
	}

	public Integer delectByauId(Integer auId) {
		return userStoreFollowMapper.delectByauId(auId);
	}

	public List<UserStoreFollow> selByauId(Integer auId) {
		return userStoreFollowMapper.selByauId(auId);
	}

}
