package com.wingfac.MaitreyaRim.mapper;

import java.util.List;
import java.util.Map;

import com.wingfac.MaitreyaRim.po.Store;

public interface StoreMapper {

	public Integer insertStore(Map<String, Object> map);

	public List<Store> selectAll(Map<String, Object> map);

	public List<Store> selectAllBytocId(Map<String, Object> map);

	public List<Store> selectAllByttcId(Map<String, Object> map);

	public List<Store> selectAllLike(Map<String, Object> map);

	public List<Store> slesctAllPage();

	public List<Store> selectAllLikeTotal(String s_name);

	public Store selectByauId(Integer auId);

	public Store selectBysId(Integer s_id);

	public Integer updateInterBysId(Map<String, Object> map);

	public Integer updateBysId(Map<String, Object> map);

	public Store selectByauIdaddress(Integer auId);

	public Integer updateSaddress(Map<String, Object> map);

	public Store selectBysName(String s_name);

	public Integer delectBysId(Integer s_id);

	public Integer updStoreFire(Map<String, Object> map);

	public Integer updateuserMark(Map<String, Object> map);

	public Integer updateCode(Map<String, Object> map);

	public Integer modifyShopRemarks(Map<String, Object> map);
	
	public List<Store> selectAllStoreByTTC(Integer ttc_id);
	
	public List<Store> selectAllStoreByTOC(Integer ttc_id);
	

}
