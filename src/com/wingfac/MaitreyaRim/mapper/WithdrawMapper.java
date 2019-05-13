package com.wingfac.MaitreyaRim.mapper;

import java.util.List;
import java.util.Map;

import com.wingfac.MaitreyaRim.po.Withdraw;


public interface WithdrawMapper {
	
	public List<Withdraw> selectAll(Map<String,Object> param);
	
	public Integer getCount(Map<String,Object> param);
	
	public Integer insert(Withdraw withdraw);
	
}
