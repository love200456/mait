package com.wingfac.MaitreyaRim.mapper;

import java.util.List;

import com.wingfac.MaitreyaRim.po.OrderCommodityList;

public interface OrderCommodityListMapper {

	public Integer insertOC(OrderCommodityList orderCommodityList);

	public List<OrderCommodityList> selectByOids(Integer oId);

	public Integer delectOC(Integer oId);

}
