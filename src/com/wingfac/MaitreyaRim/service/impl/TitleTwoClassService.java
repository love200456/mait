package com.wingfac.MaitreyaRim.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.wingfac.MaitreyaRim.mapper.TitleTwoClassMapper;
import com.wingfac.MaitreyaRim.po.TitleTwoClass;

public class TitleTwoClassService {

	@Autowired
	private TitleTwoClassMapper titleTwoClassMapper;

	public List<TitleTwoClass> selectTwo(Integer toc_id) {
		return titleTwoClassMapper.selectTwo(toc_id);
	}

	public List<TitleTwoClass> selectTwoAll() {
		return titleTwoClassMapper.selectTwoAll();
	}

}
