package com.wingfac.MaitreyaRim.mapper;

import java.util.List;

import com.wingfac.MaitreyaRim.po.ShopRecommendation;

public interface ShopRecommendationMapper {

	public Integer insertSR(ShopRecommendation shopRecommendation);

	public List<ShopRecommendation> selectOne();

	public ShopRecommendation selectByOne();

	public List<ShopRecommendation> selectTwo();

	public ShopRecommendation selectByTwo();

	public Integer delectSP(Integer spId);

}
