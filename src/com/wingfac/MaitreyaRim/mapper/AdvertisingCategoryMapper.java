package com.wingfac.MaitreyaRim.mapper;

import java.util.List;
import java.util.Map;

import com.wingfac.MaitreyaRim.po.AdvertisingCategory;

public interface AdvertisingCategoryMapper {

	public List<AdvertisingCategory> selectOne();

	public List<AdvertisingCategory> selectTwo();

	public Integer insertAC(Map<String, Object> map);

	public Integer updateAC(Map<String, Object> map);

	public AdvertisingCategory selectById(Integer ac_id);
	
	public Integer batchDelete(Integer s_id);

}
