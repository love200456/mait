package com.wingfac.MaitreyaRim.mapper;

import java.util.List;

import com.wingfac.MaitreyaRim.po.ComRecommendation;

public interface ComRecommendationMapper {

	public Integer insertCR(ComRecommendation comRecommendation);

	public List<ComRecommendation> selectOneCR();

	public ComRecommendation selectByOneCR();

	public List<ComRecommendation> selectTwoCR();

	public ComRecommendation selectByTwoCR();

	public Integer delectCR(Integer cr_id);
	
	public Integer batchDelete(Integer s_id);
	
	public Integer deleCid(Integer c_id);

}
