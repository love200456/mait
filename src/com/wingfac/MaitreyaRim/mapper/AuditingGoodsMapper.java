package com.wingfac.MaitreyaRim.mapper;

import java.util.List;
import java.util.Map;

import com.wingfac.MaitreyaRim.po.AuditingGoods;

public interface AuditingGoodsMapper {

	public Integer insertAg(AuditingGoods ad);

	public List<AuditingGoods> selectAllPage(Map<String, Object> map);

	public List<AuditingGoods> selectAllPageTotal();

	public List<AuditingGoods> selectByLisk(Map<String, Object> map);

	public List<AuditingGoods> selectLike(String ag_name);

	public Integer delectAg(String[] checkedId);

	public AuditingGoods selectByagId(Integer ag_id);

}
