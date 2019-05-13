package com.wingfac.MaitreyaRim.mapper;

import java.util.List;

import com.wingfac.MaitreyaRim.po.TitleTwoClass;

public interface TitleTwoClassMapper {

	public List<TitleTwoClass> selectTwo(Integer toc_id);

	public List<TitleTwoClass> selectTwoAll();

}
