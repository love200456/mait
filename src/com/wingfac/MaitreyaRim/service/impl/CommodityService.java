package com.wingfac.MaitreyaRim.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.wingfac.MaitreyaRim.mapper.CommodityMapper;
import com.wingfac.MaitreyaRim.po.Commodity;

public class CommodityService {

	@Autowired
	private CommodityMapper commodityMapper;

	public Integer insertCommodity(Commodity commodity) {
		return commodityMapper.insertCommodity(commodity);
	}

	public List<Commodity> selectCoAll() {
		return commodityMapper.selectCoAll();
	}

	public List<Commodity> selectCoLike(String cNmae) {
		return commodityMapper.selectAllComLike(cNmae);
	}

	public List<Commodity> selectBySiD(Integer s_id) {
		return commodityMapper.selectBySiD(s_id);
	}

	public Commodity selectDisBySiD(Integer s_id) {
		return commodityMapper.selectDisBySiD(s_id);
	}
	
	public List<Commodity> selectDisBySiDChange(Integer s_id) {
		return commodityMapper.selectDisBySiDChange(s_id);
	}

	public List<Commodity> selectAll(Map<String, Object> map) {
		return commodityMapper.selectAll(map);
	}

	public List<Commodity> selectBySearchVal(Map<String, Object> map) {
		return commodityMapper.selectBySearchVal(map);
	}

	public List<Commodity> selectBySearchMobile(Map<String, Object> map) {
		return commodityMapper.selectBySearchMobile(map);
	}

	public List<Commodity> selectAllComLike(String cName) {
		return commodityMapper.selectAllComLike(cName);
	}

	public List<Commodity> selectBySearchValAll(Map<String, Object> map) {
		return commodityMapper.selectBySearchValAll(map);
	}

	public Commodity selectByCid(Integer c_id) {
		return commodityMapper.selectByCid(c_id);
	}

	public Integer delectCommodity(String checkedId) {
		return commodityMapper.delectCommodity(checkedId.split(","));
	}

	public Integer updateCommodity(Commodity commodity) {
		return commodityMapper.updateCommodity(commodity);
	}

	public Integer updateCommodityDiscount(Commodity commodity) {
		return commodityMapper.updateCommodityDiscount(commodity);
	}

	public Integer delectBysId(Integer s_id) {
		return commodityMapper.delectBysId(s_id);
	}

	public List<Commodity> selectByStoreID(Integer s_id){
		return commodityMapper.selectByStoreID(s_id);
	}
}
