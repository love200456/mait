package com.wingfac.MaitreyaRim.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.wingfac.MaitreyaRim.mapper.OrderCommodityListMapper;
import com.wingfac.MaitreyaRim.po.OrderCommodityList;

public class OrderCommodityListService {

	@Autowired
	private OrderCommodityListMapper orderCommodityListMapper;

	public Integer insertOC(OrderCommodityList orderCommodityList) {
		return orderCommodityListMapper.insertOC(orderCommodityList);
	}

	public List<OrderCommodityList> selectByOid(Integer oId) {
		return orderCommodityListMapper.selectByOids(oId);
	}

	public Integer delectOC(Integer oId) {
		return orderCommodityListMapper.delectOC(oId);
	}

}
