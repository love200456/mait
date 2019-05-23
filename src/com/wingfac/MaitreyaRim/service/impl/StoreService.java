package com.wingfac.MaitreyaRim.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.wingfac.MaitreyaRim.mapper.StoreMapper;
import com.wingfac.MaitreyaRim.po.Store;

public class StoreService {

	@Autowired
	private StoreMapper storeMapper;

	public Integer insertStore(Map<String, Object> map) {
		return storeMapper.insertStore(map);
	}

	public List<Store> selectAll(Map<String, Object> map) {
		return storeMapper.selectAll(map);
	}

	public List<Store> selectAllBytocId(Map<String, Object> map) {
		return storeMapper.selectAllBytocId(map);
	}

	public List<Store> selectAllByttcId(Map<String, Object> map) {
		return storeMapper.selectAllByttcId(map);
	}

	public List<Store> selectAllLike(Map<String, Object> map) {
		return storeMapper.selectAllLike(map);
	}

	public List<Store> slesctAllPage() {
		return storeMapper.slesctAllPage();
	}

	public List<Store> selectAllLikeTotal(String s_name) {
		return storeMapper.selectAllLikeTotal(s_name);
	}

	public Store selectByauId(Integer auId) {
		return storeMapper.selectByauId(auId);
	}

	public Store selectBysId(Integer s_id) {
		return storeMapper.selectBysId(s_id);
	}

	public Integer updateInterBysId(Map<String, Object> map) {
		return storeMapper.updateInterBysId(map);
	}

	public Integer updateBysId(Map<String, Object> map) {
		return storeMapper.updateBysId(map);
	}

	public Store selectByauIdaddress(Integer auId) {
		return storeMapper.selectByauIdaddress(auId);
	}

	public Integer updateSaddress(Map<String, Object> map) {
		return storeMapper.updateSaddress(map);
	}

	public Store selectBysName(String s_name) {
		return storeMapper.selectBysName(s_name);
	}

	public Integer delectBysId(Integer s_id) {
		return storeMapper.delectBysId(s_id);
	}

	public Integer updStoreFire(Map<String, Object> map) {
		return storeMapper.updStoreFire(map);
	}

	public Integer updateuserMark(Map<String, Object> map) {
		return storeMapper.updateuserMark(map);
	}

	public Integer updateCode(Map<String, Object> map) {
		return storeMapper.updateCode(map);
	}
	
	public Integer modifyShopRemarks(Map<String, Object> map) {
		return storeMapper.modifyShopRemarks(map);
	}

	public List<Store> selectAllStoreByTTC(Integer ttc_id){
		return storeMapper.selectAllStoreByTTC(ttc_id);
	}
	
	public List<Store> selectAllStoreByTOC(Integer ttc_id){
		return storeMapper.selectAllStoreByTOC(ttc_id);
	}
}
