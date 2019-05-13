package com.wingfac.MaitreyaRim.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.wingfac.MaitreyaRim.mapper.TitleOneClassMapper;
import com.wingfac.MaitreyaRim.po.TitleOneClass;

public class TitleOneClassService {

	@Autowired
	private TitleOneClassMapper titleOneClassMapper;

	public List<TitleOneClass> selectOne() {
		return titleOneClassMapper.selectOne();
	}

}
