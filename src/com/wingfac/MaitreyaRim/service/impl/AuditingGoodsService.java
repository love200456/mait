package com.wingfac.MaitreyaRim.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.wingfac.MaitreyaRim.mapper.AuditingGoodsMapper;
import com.wingfac.MaitreyaRim.po.AuditingGoods;

public class AuditingGoodsService {

	@Autowired
	private AuditingGoodsMapper auditingGoodsMapper;

	public Integer insertAg(AuditingGoods ad) {
		return auditingGoodsMapper.insertAg(ad);
	}

	public List<AuditingGoods> selectAllPage(Map<String, Object> map) {
		return auditingGoodsMapper.selectAllPage(map);
	}

	public List<AuditingGoods> selectAllPageTotal() {
		return auditingGoodsMapper.selectAllPageTotal();
	}

	public List<AuditingGoods> selectByLisk(Map<String, Object> map) {
		return auditingGoodsMapper.selectByLisk(map);
	}

	public List<AuditingGoods> selectLike(String ag_name) {
		return auditingGoodsMapper.selectLike(ag_name);
	}

	public Integer delectAg(String checkedId) {
		return auditingGoodsMapper.delectAg(checkedId.split(","));
	}
	
	public AuditingGoods selectByagId(Integer ag_id) {
		return auditingGoodsMapper.selectByagId(ag_id);
	}

}
