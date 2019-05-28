package com.wingfac.MaitreyaRim.service.impl;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wingfac.MaitreyaRim.mapper.AverageUserMapper;
import com.wingfac.MaitreyaRim.mapper.FinanceStatisticsMapper;
import com.wingfac.MaitreyaRim.mapper.LimInteStatistiesMapper;
import com.wingfac.MaitreyaRim.mapper.OrderCommodityListMapper;
import com.wingfac.MaitreyaRim.mapper.OrderInformationMapper;
import com.wingfac.MaitreyaRim.mapper.PayMapper;
import com.wingfac.MaitreyaRim.mapper.PerInteStatisticsMapper;
import com.wingfac.MaitreyaRim.mapper.StoreMapper;
import com.wingfac.MaitreyaRim.po.AverageUser;
import com.wingfac.MaitreyaRim.po.FinanceStatistics;
import com.wingfac.MaitreyaRim.po.LimInteStatisties;
import com.wingfac.MaitreyaRim.po.OrderCommodityList;
import com.wingfac.MaitreyaRim.po.OrderInformation;
import com.wingfac.MaitreyaRim.po.PerInteStatistics;
import com.wingfac.MaitreyaRim.po.Store;
import com.wingfac.MaitreyaRim.po.WXNotifyParam;
import com.wingfac.MaitreyaRim.util.PushUtil;

@Service("payService")
public class PayService {

	@Autowired
	private PayMapper payMapper;
	@Autowired
	private LimInteStatistiesMapper limInteStatistiesMapper;
	@Autowired
	private FinanceStatisticsMapper financeStatisticsMapper;
	@Autowired
	private PerInteStatisticsMapper perInteStatisticsMapper;
	@Autowired
	private AverageUserMapper averageUserMapper;
	@Autowired
	private StoreMapper storeMapper;
	@Autowired
	private OrderInformationMapper orderInformationMapper;
	@Autowired
	private OrderCommodityListMapper orderCommodityListMapper;

	public Map<String, Object> getUserById(String uId) {
		// TODO Auto-generated method stub
		return payMapper.getUserById(uId);
	}

	public Map<String, Object> updateOrderStateById(Map<String, Object> user,
			String oId, String orderState, String ermanent_integral_bonus,
			String time_limited_integration, int payState,
			int store_consume_state, boolean go) {
		Map<String, Object> order = payMapper.getOrderById(oId);
		Map<String, Object> store = payMapper
				.getStoreById(order.get("s_id") + "");

		if (ermanent_integral_bonus == null
				|| "".equals(ermanent_integral_bonus))
			ermanent_integral_bonus = "0";
		if (time_limited_integration == null
				|| "".equals(time_limited_integration))
			time_limited_integration = "0";

		double offset_setting = (Double) store.get("offset_setting");
		BigDecimal total_amount = new BigDecimal(
				(Double) order.get("o_amount"));
		BigDecimal e = new BigDecimal(ermanent_integral_bonus);
		BigDecimal t = new BigDecimal(time_limited_integration);

		order.put("amount_paid", total_amount.subtract(e.add(t)).doubleValue());
		order.put("pay_state", 1);
		order.put("ermanent_integral_bonus",
				(double) Double.valueOf(ermanent_integral_bonus));
		order.put("time_limited_integration",
				(double) Double.valueOf(time_limited_integration));

		order.put("store_consume_state", store_consume_state);
		payMapper.updateOrderConsumeState(oId, store_consume_state, payState,
				(double) Double.valueOf(ermanent_integral_bonus),
				(double) Double.valueOf(time_limited_integration),
				total_amount.subtract(e.add(t)).doubleValue());

		OrderInformation saoma = orderInformationMapper
				.selectByOid(Integer.parseInt(oId));
		if (saoma.getO_state().equals("2")) {
			if (ermanent_integral_bonus == null
					|| "".equals(ermanent_integral_bonus)) {
				if (time_limited_integration == null
						|| "".equals(time_limited_integration)) {
					orderInformationMapper.updateSaoma(saoma.getO_id());
				}
			}
		}

		if (go) {
			// payMapper.updateUserIntegration((Integer) user.get("auId") + "",
			// -(Math.floor((double) Double.valueOf(time_limited_integration) %
			// offset_setting > 0 ? (double)
			// Double.valueOf(time_limited_integration) / offset_setting + 1 :
			// (double) Double.valueOf(time_limited_integration) /
			// offset_setting)), -(Math.floor((double)
			// Double.valueOf(ermanent_integral_bonus) % offset_setting > 0 ?
			// (double) Double.valueOf(ermanent_integral_bonus) / offset_setting
			// + 1 : (double) Double.valueOf(ermanent_integral_bonus) /
			// offset_setting)));

			if (orderState.equals("1")) {
				orderInformationMapper.updateOstate(Integer.parseInt(oId));
			}

			OrderInformation selectByOidd = orderInformationMapper
					.selectByOid(Integer.parseInt(oId));
			Map<String, Object> of = new LinkedHashMap<String, Object>();
			of.put("o_id", Long.parseLong(oId));
			of.put("full_integral_purchase",
					selectByOidd.getFull_integral_purchase());
			orderInformationMapper.modifyOrderAllPurchase(of);
			OrderInformation orderInformation = orderInformationMapper
					.selectByOid(Integer.parseInt(oId));

			List<OrderCommodityList> selectByOids = orderCommodityListMapper
					.selectByOids(Integer.parseInt(oId));
			Store s = storeMapper.selectBysId(orderInformation.getS_id());
			FinanceStatistics statistics = financeStatisticsMapper
					.viewFinancialinformation(orderInformation.getO_number());
			if (statistics == null) {
				FinanceStatistics f = new FinanceStatistics();
				f.setS_id(s.getS_id());
				f.setS_name(s.getS_name());
				f.setS_mobile(s.getS_mobile());
				f.setS_address(s.getS_address());
				f.setFs_turnover(orderInformation.getAmount_paid());
				f.setFs_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.format(new Date()));
				f.setO_number(orderInformation.getO_number());
				f.setErmanent_integral_bonus(0.0);
				f.setTime_limited_integration(0.0);
				f.setFull_integral_purchase(orderInformation.getFull_integral_purchase());
				f.setOcl_num(selectByOids.get(0).getOcl_num());
				f.setC_unit_price(selectByOids.get(0).getC_unit_price());
				financeStatisticsMapper.insertFS(f);
			}
			Map<String, Object> ot = new HashMap<String, Object>();
			ot.put("o_id", orderInformation.getO_id());
			ot.put("payment_time",
					new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
			orderInformationMapper.updateOrderTime(ot);

			Double era = Double.parseDouble(ermanent_integral_bonus);
			OrderInformation selectByOid = orderInformationMapper
					.selectByOid(Integer.parseInt(oId));
			List<OrderCommodityList> byOid = orderCommodityListMapper
					.selectByOids(selectByOid.getO_id());

			// Integer shiyong = (int) Math.floor((double)
			// Double.valueOf(ermanent_integral_bonus) % offset_setting > 0 ?
			// (double) Double.valueOf(ermanent_integral_bonus) / offset_setting
			// + 1 : (double) Double.valueOf(ermanent_integral_bonus) /
			// offset_setting);// 此为使用积分数

			PerInteStatistics p = new PerInteStatistics();
			p.setAuId(selectByOid.getAuId());
			p.setS_name(selectByOid.getS_name());
			p.setC_name(byOid.get(0).getC_name());
			p.setPis_category("-");
			p.setPis_consumption(orderInformation.getFull_integral_purchase());
			p.setPis_get(0d);
			p.setPis_time(
					new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			perInteStatisticsMapper.insertPI(p);
			AverageUser selectByauId = averageUserMapper
					.selectByauId(selectByOid.getAuId());
			Double surplus = selectByauId.getLimit_integral() - orderInformation.getFull_integral_purchase();
			Map<String, Object> umap = new LinkedHashMap<String, Object>();
			umap.put("auId", selectByauId.getAuId());
			umap.put("limit_integral", surplus);
			averageUserMapper.updateauIntegral(umap);

			Store store2 = storeMapper.selectBysId(orderInformation.getS_id());
			if (store2 != null) {
				try {
					PushUtil.doPushBus(store2.getS_mobile(), "有新订单消息,请进行查看!!");
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		}

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("tr_id", UUID.randomUUID().toString().replaceAll("-", ""));
		param.put("total_money", 0);
		param.put("u_id", user.get("auId") + "");
		param.put("o_id", order.get("o_id") + "");
		payMapper.addTransactionRecord(param);
		return order;
	}

	public void WXPaySuccess(WXNotifyParam wxp) {
		String oId = wxp.getOut_trade_no();

		OrderInformation selectByOid = orderInformationMapper
				.selectByOid(Integer.parseInt(oId));
		List<OrderCommodityList> selectByOids = orderCommodityListMapper
				.selectByOids(Integer.parseInt(oId));
		Map<String, Object> ot = new HashMap<String, Object>();
		ot.put("o_id", selectByOid.getO_id());
		ot.put("payment_time",
				new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		orderInformationMapper.updateOrderTime(ot);
		Store s = storeMapper.selectBysId(selectByOid.getS_id());
		AverageUser user = averageUserMapper
				.selectByauId(selectByOid.getAuId());
		FinanceStatistics statistics = financeStatisticsMapper
				.viewFinancialinformation(selectByOid.getO_number());
		if (statistics == null) {
			FinanceStatistics f = new FinanceStatistics();
			f.setS_id(s.getS_id());
			f.setS_name(s.getS_name());
			f.setS_mobile(s.getS_mobile());
			f.setS_address(s.getS_address());
			f.setFs_turnover(selectByOid.getAmount_paid());
			f.setFs_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.format(new Date()));
			f.setO_number(selectByOid.getO_number());
			f.setErmanent_integral_bonus(
					selectByOid.getErmanent_integral_bonus());
			f.setTime_limited_integration(
					selectByOid.getTime_limited_integration());
			f.setFull_integral_purchase(
					selectByOid.getFull_integral_purchase());
			f.setOcl_num(selectByOids.get(0).getOcl_num());
			f.setC_unit_price(selectByOids.get(0).getC_unit_price());
			financeStatisticsMapper.insertFS(f);
		}
		Double integration = selectByOid.getAmount_paid() * s.getIntegral_setting();
		Double consumer_points = 0d;
		String lim_type = "";
		if ((selectByOid.getUse_limit_integral() == 0)
				&& (selectByOid.getUse_permanent_points() == 0)) {
			consumer_points = 0d;
			lim_type = "+";
		} else if ((selectByOid.getUse_limit_integral() != 0)
				&& (selectByOid.getUse_permanent_points() == 0)) {
			consumer_points = selectByOid.getUse_limit_integral();
			lim_type = "-";
		} else if ((selectByOid.getUse_limit_integral() == 0)
				&& (selectByOid.getUse_permanent_points() != 0)) {

			LimInteStatisties l = new LimInteStatisties();
			l.setAuId(user.getAuId());
			l.setS_name(s.getS_name());
			l.setC_name(selectByOids.get(0).getC_name());
			l.setLis_consumption(consumer_points);
			l.setLis_get(0d);
			l.setLis_time(
					new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			l.setLis_category("-");
			l.setLis_term(" ");
			l.setLis_state("2");
			limInteStatistiesMapper.insertLI(l);
		} else {
			consumer_points = selectByOid.getUse_limit_integral();
			lim_type = "-";
			LimInteStatisties l = new LimInteStatisties();
			l.setAuId(user.getAuId());
			l.setS_name(s.getS_name());
			l.setC_name(selectByOids.get(0).getC_name());
			l.setLis_consumption(consumer_points);
			l.setLis_get(0d);
			l.setLis_time(
					new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			l.setLis_category("-");
			l.setLis_term(" ");
			l.setLis_state("2");
			limInteStatistiesMapper.insertLI(l);
		}

		PerInteStatistics p = new PerInteStatistics();
		p.setAuId(user.getAuId());
		p.setS_name(s.getS_name());
		p.setC_name(selectByOids.get(0).getC_name());
		p.setPis_consumption(consumer_points);
		p.setPis_get(integration);
		p.setPis_time(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		p.setPis_category(lim_type);
		perInteStatisticsMapper.insertPI(p);

		try {
			PushUtil.doPushBus(s.getS_mobile(), "有新订单消息,请进行查看!!");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Map<String, Object> order = payMapper.getOrderById(oId);
		Map<String, Object> store = payMapper
				.getStoreById(order.get("s_id") + "");
		double ermanent_integral_bonus = (Double) order
				.get("ermanent_integral_bonus");
		double time_limited_integration = (Double) order
				.get("time_limited_integration");

		double offset_setting = (Double) store.get("offset_setting");
		payMapper.updateUserIntegration(order.get("auId").toString(),
				-(Math.floor((double) Double.valueOf(time_limited_integration)
						% offset_setting > 0
								? (double) Double
										.valueOf(time_limited_integration)
										/ offset_setting + 1
								: (double) Double
										.valueOf(time_limited_integration)
										/ offset_setting)),
				-(Math.floor((double) Double.valueOf(ermanent_integral_bonus)
						% offset_setting > 0
								? (double) Double
										.valueOf(ermanent_integral_bonus)
										/ offset_setting + 1
								: (double) Double
										.valueOf(ermanent_integral_bonus)
										/ offset_setting)));

		if ("1".equals(order.get("store_consume_state"))) {

			System.out.println("订单已成功处理完成");
		} else {

			String orderState = order.get("o_state").toString();
			if ("0".equals(orderState)) {

				payMapper.updateOrderPayAndState(oId, 1, 1);
			} else {

				payMapper.updateOrderPayAndState(oId, 0, 1);
			}
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("tr_id",
					UUID.randomUUID().toString().replaceAll("-", ""));
			param.put("total_money", order.get("amount_paid"));
			param.put("u_id", order.get("auId"));
			param.put("o_id", order.get("o_id"));
			payMapper.addTransactionRecord(param);

		}
	}

	public void aLiPaySuccess(String out_trade_no) {

		String oId = out_trade_no;
		OrderInformation selectByOid = orderInformationMapper
				.selectByOid(Integer.parseInt(oId));
		List<OrderCommodityList> selectByOids = orderCommodityListMapper
				.selectByOids(Integer.parseInt(oId));
		Map<String, Object> ot = new HashMap<String, Object>();
		ot.put("o_id", selectByOid.getO_id());
		ot.put("payment_time",
				new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		orderInformationMapper.updateOrderTime(ot);
		Store s = storeMapper.selectBysId(selectByOid.getS_id());
		AverageUser user = averageUserMapper
				.selectByauId(selectByOid.getAuId());
		FinanceStatistics statistics = financeStatisticsMapper
				.viewFinancialinformation(selectByOid.getO_number());
		if (statistics == null) {
			FinanceStatistics f = new FinanceStatistics();
			f.setS_id(s.getS_id());
			f.setS_name(s.getS_name());
			f.setS_mobile(s.getS_mobile());
			f.setS_address(s.getS_address());
			f.setFs_turnover(selectByOid.getAmount_paid());
			f.setFs_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.format(new Date()));
			f.setO_number(selectByOid.getO_number());
			f.setErmanent_integral_bonus(
					selectByOid.getErmanent_integral_bonus());
			f.setTime_limited_integration(
					selectByOid.getTime_limited_integration());
			f.setFull_integral_purchase(
					selectByOid.getFull_integral_purchase());
			f.setOcl_num(selectByOids.get(0).getOcl_num());
			f.setC_unit_price(selectByOids.get(0).getC_unit_price());
			financeStatisticsMapper.insertFS(f);
		}
		Double integration = selectByOid.getAmount_paid() * s.getIntegral_setting();
		Double consumer_points = 0d;
		String lim_type = "";
		if ((selectByOid.getUse_limit_integral() == 0)
				&& (selectByOid.getUse_permanent_points() == 0)) {
			consumer_points = 0d;
			lim_type = "+";
		} else if ((selectByOid.getUse_limit_integral() != 0)
				&& (selectByOid.getUse_permanent_points() == 0)) {
			consumer_points = selectByOid.getUse_limit_integral();
			lim_type = "-";
		} else if ((selectByOid.getUse_limit_integral() == 0)
				&& (selectByOid.getUse_permanent_points() != 0)) {

			LimInteStatisties l = new LimInteStatisties();
			l.setAuId(user.getAuId());
			l.setS_name(s.getS_name());
			l.setC_name(selectByOids.get(0).getC_name());
			l.setLis_consumption(consumer_points);
			l.setLis_get(0d);
			l.setLis_time(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			l.setLis_category("-");
			l.setLis_term(" ");
			l.setLis_state("2");
			limInteStatistiesMapper.insertLI(l);
		} else {
			consumer_points = selectByOid.getUse_limit_integral();
			lim_type = "-";
			LimInteStatisties l = new LimInteStatisties();
			l.setAuId(user.getAuId());
			l.setS_name(s.getS_name());
			l.setC_name(selectByOids.get(0).getC_name());
			l.setLis_consumption(consumer_points);
			l.setLis_get(0d);
			l.setLis_time(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			l.setLis_category("-");
			l.setLis_term(" ");
			l.setLis_state("2");
			limInteStatistiesMapper.insertLI(l);
		}

		PerInteStatistics p = new PerInteStatistics();
		p.setAuId(user.getAuId());
		p.setS_name(s.getS_name());
		p.setC_name(selectByOids.get(0).getC_name());
		p.setPis_consumption(consumer_points);
		p.setPis_get(integration);
		p.setPis_time(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		p.setPis_category(lim_type);
		perInteStatisticsMapper.insertPI(p);

		try {
			PushUtil.doPushBus(s.getS_mobile(), "有新订单消息,请进行查看!!");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Map<String, Object> order = payMapper.getOrderById(out_trade_no);
		Map<String, Object> store = payMapper
				.getStoreById(order.get("s_id") + "");
		double ermanent_integral_bonus = (Double) order
				.get("ermanent_integral_bonus");
		double time_limited_integration = (Double) order
				.get("time_limited_integration");

		double offset_setting = (Double) store.get("offset_setting");
		payMapper.updateUserIntegration(order.get("auId").toString(),
				-(Math.floor((double) Double.valueOf(time_limited_integration)
						% offset_setting > 0
								? (double) Double
										.valueOf(time_limited_integration)
										/ offset_setting + 1
								: (double) Double
										.valueOf(time_limited_integration)
										/ offset_setting)),
				-(Math.floor((double) Double.valueOf(ermanent_integral_bonus)
						% offset_setting > 0
								? (double) Double
										.valueOf(ermanent_integral_bonus)
										/ offset_setting + 1
								: (double) Double
										.valueOf(ermanent_integral_bonus)
										/ offset_setting)));
		if ("1".equals(order.get("store_consume_state"))) {

			System.out.println("订单已成功处理完成");
		} else {
			String orderState = order.get("o_state").toString();
			if ("0".equals(orderState)) {

				payMapper.updateOrderPayAndState(out_trade_no, 1, 1);
			} else {

				payMapper.updateOrderPayAndState(out_trade_no, 0, 1);
			}
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("tr_id",
					UUID.randomUUID().toString().replaceAll("-", ""));
			param.put("total_money", order.get("amount_paid"));
			param.put("u_id", order.get("auId"));
			param.put("o_id", order.get("o_id"));
			payMapper.addTransactionRecord(param);
		}
	}

}
