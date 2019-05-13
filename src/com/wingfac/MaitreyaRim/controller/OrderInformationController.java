package com.wingfac.MaitreyaRim.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wingfac.MaitreyaRim.po.OrderCommodityList;
import com.wingfac.MaitreyaRim.po.OrderInformation;
import com.wingfac.MaitreyaRim.po.OrderInformationVo;
import com.wingfac.MaitreyaRim.service.impl.OrderCommodityListService;
import com.wingfac.MaitreyaRim.service.impl.OrderInformationService;
import com.wingfac.MaitreyaRim.util.Constants;

@Controller
@RequestMapping("OrderInformation")
public class OrderInformationController {

	@Autowired
	private OrderInformationService orderInformationService;
	@Autowired
	private OrderCommodityListService orderCommodityListService;

	@RequestMapping("selectBysId")
	public String selectBysId(
			HttpServletRequest request,
			HttpServletResponse response,
			ModelMap modelMap,
			@RequestParam("s_id") String s_id,
			@RequestParam(value = "curPage", defaultValue = "0", required = false) Integer curPage)
			throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Integer pstart = curPage * Constants.sun;
		map.put("psize", Constants.sun);
		map.put("pstart", pstart);
		map.put("s_id", s_id);
		List<OrderInformation> list = orderInformationService.viewAllorder();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getPay_state() == 0) {
				orderInformationService.delectBtoId(list.get(i).getO_id());
				orderCommodityListService.delectOC(list.get(i).getO_id());
			}
		}
		List<OrderInformation> selectAllOrdeInfor = orderInformationService.selectAllOrdeInfor(Integer.parseInt(s_id));
		if (selectAllOrdeInfor.size() > 0) {
			List<OrderInformation> selectAllOrdInforFen = orderInformationService.selectAllOrdInforFen(map);
			if (selectAllOrdInforFen.size() > 0) {
				List<OrderInformationVo> oil = new ArrayList<OrderInformationVo>();
				List<OrderCommodityList> selectByOid = null;
				for (int i = 0; i < selectAllOrdInforFen.size(); i++) {
					OrderInformationVo oiv = new OrderInformationVo();
					selectByOid = orderCommodityListService.selectByOid(selectAllOrdInforFen.get(i).getO_id());
					if (selectByOid.size() > 0) {
						oiv.setO_id(selectAllOrdInforFen.get(i).getO_id());
						oiv.setC_name(selectByOid.get(0).getC_name());
						oiv.setAuBuyerNick(selectAllOrdInforFen.get(i).getAuBuyerNick());
						oiv.setO_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new SimpleDateFormat("yyyyMMddHHmmss").parse(selectAllOrdInforFen.get(i).getO_time())));
						oiv.setO_amount(selectAllOrdInforFen.get(i).getO_amount());
						oiv.setUse_limit_integral((int)(selectAllOrdInforFen.get(i).getErmanent_integral_bonus()).intValue());
						oiv.setUse_permanent_points((int)(selectAllOrdInforFen.get(i).getTime_limited_integration()).intValue());
						oiv.setFull_integral_purchase(selectAllOrdInforFen.get(i).getFull_integral_purchase());
						oil.add(oiv);
					}
				}
				Integer pages = selectAllOrdeInfor.size() / Constants.sun;
				if (selectAllOrdeInfor.size() % Constants.sun != 0) {
					pages = pages + 1;
				}
				modelMap.put("pages", pages);
				modelMap.put("list", oil);
				modelMap.put("curPage", curPage);
				modelMap.put("tiaojian", true);
			}
		}
		modelMap.put("s_id", s_id);
		return "shop/orderlist";
	}

	@RequestMapping("selectByoId")
	public String selectByoId(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap,
			@RequestParam("o_id") String o_id) throws Exception {
		OrderInformation selectByOid = orderInformationService.selectByOid(Integer.parseInt(o_id));
		if (selectByOid != null) {
			List<OrderCommodityList> oidCom = orderCommodityListService.selectByOid(selectByOid.getO_id());
			if (oidCom.size() > 0) {
				selectByOid.setO_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new SimpleDateFormat("yyyyMMddHHmmss").parse(selectByOid.getO_time())));
				modelMap.put("selectByOid", selectByOid);
				modelMap.put("oidCom", oidCom);
			}
		}
		modelMap.put("s_id", selectByOid.getS_id());
		return "shop/orderdetails";
	}

	@ResponseBody
	@RequestMapping("delectOiCom")
	public Map<String, Object> delectOiCom(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("checkedId") String checkedId) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		String[] oIds = checkedId.split(",");
		Integer oId[] = new Integer[oIds.length];
		for (int i = 0; i < oIds.length; i++) {
			oId[i] = Integer.parseInt(oIds[i]);
		}
		Integer delectOC = 0;
		for (int i = 0; i < oId.length; i++) {
			delectOC += orderCommodityListService.delectOC(oId[i]);
		}
		if (delectOC > 0) {
			Integer delectOrdeInfor = orderInformationService.delectOrdeInfor(checkedId);
			if (delectOrdeInfor > 0) {
				map.put("msg", "1");
			} else {
				map.put("msg", "2");
			}
		} else {
			map.put("msg", "2");
		}
		return map;
	}

	@RequestMapping("selectByTime")
	public String selectByTime(
			HttpServletRequest request,
			HttpServletResponse response,
			ModelMap modelMap,
			@RequestParam("strtime") String strtime,
			@RequestParam("endtime") String endtime,
			@RequestParam("sId") String sId,
			@RequestParam(value = "curPage", defaultValue = "0", required = false) Integer curPage)
			throws Exception {
		List<OrderInformation> selectAllOrdeInfor = orderInformationService.selectAllOrdeInfor(Integer.parseInt(sId));
		List<OrderInformationVo> oiv = new ArrayList<OrderInformationVo>();
		if (selectAllOrdeInfor.size() > 0) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat dateFormats = new SimpleDateFormat("yyyyMMddHHmmss");
			long fTime = dateFormat.parse(strtime).getTime();
			long tTime = dateFormat.parse(endtime).getTime();
			List<OrderCommodityList> selectByOid = null;
			for (int i = 0; i < selectAllOrdeInfor.size(); i++) {
				long oTime = dateFormats.parse(selectAllOrdeInfor.get(i).getO_time()).getTime();
				if (fTime <= oTime && oTime <= tTime) {
					OrderInformationVo oi = new OrderInformationVo();
					selectByOid = orderCommodityListService.selectByOid(selectAllOrdeInfor.get(i).getO_id());
					if (selectByOid.size() > 0) {
						oi.setO_id(selectAllOrdeInfor.get(i).getO_id());
						oi.setC_name(selectByOid.get(0).getC_name());
						oi.setAuBuyerNick(selectAllOrdeInfor.get(i).getAuBuyerNick());
						oi.setO_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new SimpleDateFormat("yyyyMMddHHmmss").parse(selectAllOrdeInfor.get(i).getO_time())));
						oi.setO_amount(selectAllOrdeInfor.get(i).getO_amount());
						oi.setUse_limit_integral(selectAllOrdeInfor.get(i).getUse_limit_integral());
						oi.setUse_permanent_points(selectAllOrdeInfor.get(i).getUse_permanent_points());
						oiv.add(oi);
					}
				}
			}
			Integer pages = oiv.size() / Constants.sun;
			if (oiv.size() % Constants.sun != 0) {
				pages = pages + 1;
			}
			List<OrderInformationVo> oivFen = new ArrayList<OrderInformationVo>();
			Integer start = curPage * Constants.sun;
			Integer end = (curPage + 1) * Constants.sun;
			Integer endZong = 0;
			if (end > oiv.size()) {
				endZong = oiv.size();
			} else {
				endZong = end;
			}
			for (int i = start; i < endZong; i++) {
				OrderInformationVo oi = new OrderInformationVo();
				oi.setO_id(oiv.get(i).getO_id());
				oi.setC_name(oiv.get(i).getC_name());
				oi.setAuBuyerNick(oiv.get(i).getAuBuyerNick());
				oi.setO_time(oiv.get(i).getO_time());
				oi.setO_amount(oiv.get(i).getO_amount());
				oi.setUse_limit_integral(oiv.get(i).getUse_limit_integral());
				oi.setUse_permanent_points(oiv.get(i).getUse_permanent_points());
				oivFen.add(oi);
			}
			modelMap.put("pages", pages);
			modelMap.put("list", oivFen);
			modelMap.put("curPage", curPage);
			modelMap.put("tiaojian", false);
			request.setAttribute("strtime", strtime);
			request.setAttribute("endtime", endtime);
		}
		modelMap.put("s_id", sId);
		return "shop/orderlist";
	}

}
