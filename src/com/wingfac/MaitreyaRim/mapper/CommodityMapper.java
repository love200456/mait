package com.wingfac.MaitreyaRim.mapper;

import java.util.List;
import java.util.Map;

import com.wingfac.MaitreyaRim.po.Commodity;

public interface CommodityMapper {

	public Integer insertCommodity(Commodity commodity);

	public List<Commodity> selectCoAll();

	public List<Commodity> selectCoLike(String cNmae);

	public List<Commodity> selectBySiD(Integer s_id);

	public Commodity selectDisBySiD(Integer s_id);
	
	public List<Commodity> selectDisBySiDChange(Integer s_id);

	public List<Commodity> selectAll(Map<String, Object> map);

	public List<Commodity> selectBySearchVal(Map<String, Object> map);

	public List<Commodity> selectBySearchMobile(Map<String, Object> map);

	public List<Commodity> selectAllComLike(String cName);

	public List<Commodity> selectBySearchValAll(Map<String, Object> map);

	public Commodity selectByCid(Integer c_id);

	public Integer delectCommodity(String[] checkedId);

	public Integer updateCommodity(Commodity commodity);

	public Integer updateCommodityDiscount(Commodity commodity);

	public Integer delectBysId(Integer s_id);
	
	public List<Commodity> selectByStoreID(Integer s_id);

}
