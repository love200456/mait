package com.wingfac.MaitreyaRim.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.wingfac.MaitreyaRim.mapper.WithdrawMapper;
import com.wingfac.MaitreyaRim.po.Withdraw;

public class WithdrawService {

	@Autowired
	private WithdrawMapper withdrawMapper;
	
	public List<Withdraw> selectAll(Map<String,Object> param){
		return withdrawMapper.selectAll(param);
	}
	
	public Integer getCount(Map<String,Object> param){
		return withdrawMapper.getCount(param);
	}
	
	public Integer insert(Withdraw withdraw){
		return withdrawMapper.insert(withdraw);
	}
}
