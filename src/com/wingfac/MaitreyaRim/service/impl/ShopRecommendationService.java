package com.wingfac.MaitreyaRim.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.wingfac.MaitreyaRim.mapper.ShopRecommendationMapper;
import com.wingfac.MaitreyaRim.po.ShopRecommendation;

public class ShopRecommendationService {

	@Autowired
	private ShopRecommendationMapper shopRecommendationMapper;

	public Integer insertSR(ShopRecommendation shopRecommendation) {
		return shopRecommendationMapper.insertSR(shopRecommendation);
	}

	public List<ShopRecommendation> selectOne() {
		return shopRecommendationMapper.selectOne();
	}

	public ShopRecommendation selectByOne() {
		return shopRecommendationMapper.selectByOne();
	}

	public ShopRecommendation selectByTwo() {
		return shopRecommendationMapper.selectByTwo();
	}

	public List<ShopRecommendation> selectTwo() {
		return shopRecommendationMapper.selectTwo();
	}

	public Integer delectSP(Integer spId) {
		return shopRecommendationMapper.delectSP(spId);
	}

}
