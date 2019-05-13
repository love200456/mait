package com.wingfac.MaitreyaRim.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.wingfac.MaitreyaRim.mapper.PerInteStatisticsMapper;
import com.wingfac.MaitreyaRim.po.PerInteStatistics;

public class PerInteStatisticsService {

	@Autowired
	private PerInteStatisticsMapper perInteStatisticsMapper;

	public Integer insertPI(PerInteStatistics perInteStatistics) {
		return perInteStatisticsMapper.insertPI(perInteStatistics);
	}

	public List<PerInteStatistics> selectByauId(Integer auId) {
		return perInteStatisticsMapper.selectByauId(auId);
	}
	
	public Integer delectInte(Integer auId){
		return perInteStatisticsMapper.delectInte(auId);
	}

}
