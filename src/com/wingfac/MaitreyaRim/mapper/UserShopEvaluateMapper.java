package com.wingfac.MaitreyaRim.mapper;

import java.util.List;
import java.util.Map;

import com.wingfac.MaitreyaRim.po.UserShopEvaluate;

public interface UserShopEvaluateMapper {

	public Integer newScore(UserShopEvaluate userShopEvaluate);

	public List<UserShopEvaluate> viewScoreInformation(Integer s_id);
	
	public Integer updateShopEvaluate(Map<String,Object> param);

	public UserShopEvaluate selectByID(Long use_id);
	
}
