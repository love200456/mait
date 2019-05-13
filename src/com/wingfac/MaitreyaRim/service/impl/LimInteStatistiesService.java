package com.wingfac.MaitreyaRim.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.wingfac.MaitreyaRim.mapper.LimInteStatistiesMapper;
import com.wingfac.MaitreyaRim.po.LimInteStatisties;

public class LimInteStatistiesService {

	@Autowired
	private LimInteStatistiesMapper limInteStatistiesMapper;

	public Integer insertLI(LimInteStatisties limInteStatisties) {
		return limInteStatistiesMapper.insertLI(limInteStatisties);
	}

	public List<LimInteStatisties> selectByauId(Integer auId) {
		return limInteStatistiesMapper.selectByauId(auId);
	}

	public Integer updateLisstate(Integer auId) {
		return limInteStatistiesMapper.updateLisstate(auId);
	}

	public Integer delectById(Integer lis_id) {
		return limInteStatistiesMapper.delectById(lis_id);
	}

	public Integer delectByauId(Integer auId) {
		return limInteStatistiesMapper.delectByauId(auId);
	}

}
