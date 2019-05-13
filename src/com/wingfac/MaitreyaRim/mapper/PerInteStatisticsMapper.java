package com.wingfac.MaitreyaRim.mapper;

import java.util.List;

import com.wingfac.MaitreyaRim.po.PerInteStatistics;

public interface PerInteStatisticsMapper {

	public Integer insertPI(PerInteStatistics perInteStatistics);

	public List<PerInteStatistics> selectByauId(Integer auId);

	public Integer delectInte(Integer auId);

}
