package com.wingfac.MaitreyaRim.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wingfac.MaitreyaRim.po.Commodity;
import com.wingfac.MaitreyaRim.po.ShoppingCartVo;
import com.wingfac.MaitreyaRim.service.impl.CommodityService;
import com.wingfac.MaitreyaRim.service.impl.ShoppingCartVoService;
import com.wingfac.MaitreyaRim.util.ResponseStatus;

@Controller
@RequestMapping("ShoppingCartVo")
public class ShoppingCartVoController {

	@Autowired
	private ShoppingCartVoService shoppingCartVoService;
	@Autowired
	private CommodityService commodityService;

	@ResponseBody
	@RequestMapping("selectAllFen")
	public Map<String, Object> selectAllFen(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("auId") String auId,
			@RequestParam("pstart") String pstart,
			@RequestParam("psize") String psize) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Map<String, Object> m = new LinkedHashMap<String, Object>();
		Integer pstarts = Integer.parseInt(pstart);
		Integer psizes = Integer.parseInt(psize);
		map.put("pstart", pstarts * psizes);
		map.put("psize", psizes);
		map.put("auId", Integer.parseInt(auId));
		List<ShoppingCartVo> selectAll = shoppingCartVoService.selectAll(map);
		if (selectAll.size() > 0) {
			m.put("ResponseStatus", ResponseStatus.QUERYWASSUCCESS);
			m.put("msg", ResponseStatus.QUERYWASSUCCESS_CN_MSG);
			m.put("obj", selectAll);
		} else {
			m.put("ResponseStatus", ResponseStatus.SHOPPCARTNULL);
			m.put("msg", ResponseStatus.SHOPPCARTNULL_CN_MSG);
		}
		return m;
	}

	@ResponseBody
	@RequestMapping("insertSc")
	public Map<String, Object> insertSc(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("auId") String auId,
			@RequestParam("c_id") String c_id) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Map<String, Object> m = new LinkedHashMap<String, Object>();
		Commodity selectByCid = commodityService.selectByCid(Integer.parseInt(c_id));
		if (selectByCid != null) {
			m.put("auId", Integer.parseInt(auId));
			m.put("c_id", Integer.parseInt(c_id));
			ShoppingCartVo selectPan = shoppingCartVoService.selectPan(m);
			if (selectPan != null) {
				shoppingCartVoService.deletePan(m);
			}
			ShoppingCartVo acv = new ShoppingCartVo();
			acv.setAuId(Integer.parseInt(auId));
			acv.setC_id(selectByCid.getC_id());
			acv.setC_first_figure(selectByCid.getC_first_figure());
			acv.setC_unit_price(selectByCid.getC_unit_price());
			acv.setC_introduce(selectByCid.getC_introduce());
			acv.setC_name(selectByCid.getC_name());
			Integer insertShopCar = shoppingCartVoService.insertShopCar(acv);
			if (insertShopCar > 0) {
				map.put("ResponseStatus", ResponseStatus.SHOPPINGCARTSUCCESS);
				map.put("msg", ResponseStatus.SHOPPINGCARTSUCCESS_CN_MSG);
			} else {
				map.put("ResponseStatus", ResponseStatus.SHOPPINGCARTFAIL);
				map.put("msg", ResponseStatus.SHOPPINGCARTFAIL_CN_MSG);
			}
		} else {
			map.put("ResponseStatus", ResponseStatus.COMMODITYFAIL2);
			map.put("msg", ResponseStatus.COMMODITYFAIL_CN_MSG2);
		}
		return map;
	}

	@ResponseBody
	@RequestMapping("deleteSc")
	public Map<String, Object> deleteSc(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("auId") String auId,
			String scId) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Map<String, Object> m = new LinkedHashMap<String, Object>();
		String[] scIds = scId.split(",");
		Integer sc_id[] = new Integer[scIds.length];
		for (int i = 0; i < scIds.length; i++) {
			sc_id[i] = Integer.parseInt(scIds[i]);
		}
		Integer deleteShoppCart = 0;
		for (int i = 0; i < sc_id.length; i++) {
			m.put("auId", Integer.parseInt(auId));
			m.put("sc_id", sc_id[i]);
			deleteShoppCart += shoppingCartVoService.deleteShoppCart(m);
		}
		if (deleteShoppCart == sc_id.length) {
			map.put("ResponseStatus", ResponseStatus.SHOPPINGCARTGOODSSUCCESS);
			map.put("msg", ResponseStatus.SHOPPINGCARTGOODSSUCCESS_CN_MSG);
		} else {
			map.put("ResponseStatus", ResponseStatus.SHOPPINGCARTGOODSFAIL);
			map.put("msg", ResponseStatus.SHOPPINGCARTGOODSFAIL_CN_MSG);
		}
		return map;
	}

}
