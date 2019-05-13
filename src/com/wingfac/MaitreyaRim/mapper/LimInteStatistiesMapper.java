package com.wingfac.MaitreyaRim.mapper;

import java.util.List;

import com.wingfac.MaitreyaRim.po.LimInteStatisties;

public interface LimInteStatistiesMapper {

	public Integer insertLI(LimInteStatisties limInteStatisties);

	public List<LimInteStatisties> selectByauId(Integer auId);

	public Integer updateLisstate(Integer auId);

	public Integer delectById(Integer lis_id);

	public Integer delectByauId(Integer auId);

}
