package com.wingfac.MaitreyaRim.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.wingfac.MaitreyaRim.mapper.ComRecommendationMapper;
import com.wingfac.MaitreyaRim.po.ComRecommendation;

public class ComRecommendationService {

	@Autowired
	private ComRecommendationMapper comRecommendationMapper;

	public Integer insertCR(ComRecommendation comRecommendation) {
		return comRecommendationMapper.insertCR(comRecommendation);
	}

	public List<ComRecommendation> selectOneCR() {
		return comRecommendationMapper.selectOneCR();
	}

	public ComRecommendation selectByOneCR() {
		return comRecommendationMapper.selectByOneCR();
	}

	public List<ComRecommendation> selectTwoCR() {
		return comRecommendationMapper.selectTwoCR();
	}

	public ComRecommendation selectByTwoCR() {
		return comRecommendationMapper.selectByTwoCR();
	}

	public Integer delectCR(Integer cr_id) {
		return comRecommendationMapper.delectCR(cr_id);
	}
	
	public Integer batchDelete(Integer s_id) {
		return comRecommendationMapper.batchDelete(s_id);
	}

	public Integer deleCid(Integer c_id) {
		return comRecommendationMapper.deleCid(c_id);
	}
	
}
