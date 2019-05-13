package com.wingfac.MaitreyaRim.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.wingfac.MaitreyaRim.mapper.AdvertisingCategoryMapper;
import com.wingfac.MaitreyaRim.po.AdvertisingCategory;

public class AdvertisingCategoryService {

	@Autowired
	private AdvertisingCategoryMapper advertisingCategoryMapper;

	public List<AdvertisingCategory> selectOne() {
		return advertisingCategoryMapper.selectOne();
	}

	public List<AdvertisingCategory> selectTwo() {
		return advertisingCategoryMapper.selectTwo();
	}

	public Integer insertAC(Map<String, Object> map) {
		return advertisingCategoryMapper.insertAC(map);
	}

	public Integer updateAC(Map<String, Object> map) {
		return advertisingCategoryMapper.updateAC(map);
	}

	public AdvertisingCategory selectById(Integer ac_id) {
		return advertisingCategoryMapper.selectById(ac_id);
	}
	
	public Integer batchDelete(Integer s_id) {
		return advertisingCategoryMapper.batchDelete(s_id);
	}

}
