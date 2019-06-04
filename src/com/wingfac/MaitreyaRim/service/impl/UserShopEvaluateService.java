package com.wingfac.MaitreyaRim.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.wingfac.MaitreyaRim.mapper.UserShopEvaluateMapper;
import com.wingfac.MaitreyaRim.po.UserShopEvaluate;


public class UserShopEvaluateService {

	@Autowired
	private UserShopEvaluateMapper userShopEvaluateMapper;

	public Integer newScore(UserShopEvaluate userShopEvaluate) {
		return userShopEvaluateMapper.newScore(userShopEvaluate);
	}

	public List<UserShopEvaluate> viewScoreInformation(Integer s_id) {
		return userShopEvaluateMapper.viewScoreInformation(s_id);
	}
	
	public List<UserShopEvaluate> viewScoreInformationPage(Map<String,Object> param){
		return userShopEvaluateMapper.viewScoreInformationPage(param);
	}

	public Integer updateShopEvaluate(Map<String,Object> param){
		return userShopEvaluateMapper.updateShopEvaluate(param);
	}
	
	public UserShopEvaluate selectByID(Long use_id){
		return userShopEvaluateMapper.selectByID(use_id);
	}
	
	public List<UserShopEvaluate> selectByOrderID(Long order_id){
		return userShopEvaluateMapper.selectByOrderID(order_id);
	}
}
