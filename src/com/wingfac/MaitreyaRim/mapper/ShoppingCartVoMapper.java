package com.wingfac.MaitreyaRim.mapper;

import java.util.List;
import java.util.Map;

import com.wingfac.MaitreyaRim.po.ShoppingCartVo;

public interface ShoppingCartVoMapper {

	public List<ShoppingCartVo> selectAll(Map<String, Object> map);

	public Integer insertShopCar(ShoppingCartVo shoppingCartVo);

	public Integer deleteShoppCart(Map<String, Object> map);

	public ShoppingCartVo selectPan(Map<String, Object> map);

	public Integer deletePan(Map<String, Object> map);

	public Integer delevtByauId(Integer auId);

	public List<ShoppingCartVo> selByauId(Integer auId);

}
