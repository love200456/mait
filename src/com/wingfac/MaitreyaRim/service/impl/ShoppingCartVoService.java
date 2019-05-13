package com.wingfac.MaitreyaRim.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.wingfac.MaitreyaRim.mapper.ShoppingCartVoMapper;
import com.wingfac.MaitreyaRim.po.ShoppingCartVo;

public class ShoppingCartVoService {

	@Autowired
	private ShoppingCartVoMapper shoppingCartVoMapper;

	public List<ShoppingCartVo> selectAll(Map<String, Object> map) {
		return shoppingCartVoMapper.selectAll(map);
	}

	public Integer insertShopCar(ShoppingCartVo shoppingCartVo) {
		return shoppingCartVoMapper.insertShopCar(shoppingCartVo);
	}

	public Integer deleteShoppCart(Map<String, Object> map) {
		return shoppingCartVoMapper.deleteShoppCart(map);
	}

	public ShoppingCartVo selectPan(Map<String, Object> map) {
		return shoppingCartVoMapper.selectPan(map);
	}

	public Integer deletePan(Map<String, Object> map) {
		return shoppingCartVoMapper.deletePan(map);
	}

	public Integer delevtByauId(Integer auId) {
		return shoppingCartVoMapper.delevtByauId(auId);
	}

	public List<ShoppingCartVo> selByauId(Integer auId) {
		return shoppingCartVoMapper.selByauId(auId);
	}

}
