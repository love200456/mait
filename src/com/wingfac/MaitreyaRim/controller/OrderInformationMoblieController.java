package com.wingfac.MaitreyaRim.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConfig;
import com.wingfac.MaitreyaRim.po.AverageUser;
import com.wingfac.MaitreyaRim.po.Commodity;
import com.wingfac.MaitreyaRim.po.LimInteStatisties;
import com.wingfac.MaitreyaRim.po.OrderCommodityList;
import com.wingfac.MaitreyaRim.po.OrderDetailsMobileVo;
import com.wingfac.MaitreyaRim.po.OrderInformation;
import com.wingfac.MaitreyaRim.po.OrderTimeVo;
import com.wingfac.MaitreyaRim.po.OrderUserMobileVo;
import com.wingfac.MaitreyaRim.po.PerInteStatistics;
import com.wingfac.MaitreyaRim.po.Store;
import com.wingfac.MaitreyaRim.po.UserShopEvaluate;
import com.wingfac.MaitreyaRim.po.WXNotifyParam;
import com.wingfac.MaitreyaRim.po.WXPayResult;
import com.wingfac.MaitreyaRim.service.impl.AverageUserService;
import com.wingfac.MaitreyaRim.service.impl.CommodityService;
import com.wingfac.MaitreyaRim.service.impl.LimInteStatistiesService;
import com.wingfac.MaitreyaRim.service.impl.OrderCommodityListService;
import com.wingfac.MaitreyaRim.service.impl.OrderInformationService;
import com.wingfac.MaitreyaRim.service.impl.PayService;
import com.wingfac.MaitreyaRim.service.impl.PerInteStatisticsService;
import com.wingfac.MaitreyaRim.service.impl.StoreService;
import com.wingfac.MaitreyaRim.service.impl.UserShopEvaluateService;
import com.wingfac.MaitreyaRim.util.Constants;
import com.wingfac.MaitreyaRim.util.DirectPayConfig;
import com.wingfac.MaitreyaRim.util.ResponseStatus;
import com.wingfac.MaitreyaRim.util.WXPayConfigImpl;
import com.wingfac.MaitreyaRim.util.WXPayUtil;

@Controller
@RequestMapping("OrderInformationMoblie")
public class OrderInformationMoblieController {
	Log log = LogFactory.getLog(OrderInformationMoblieController.class);
	@Autowired
	private OrderInformationService orderInformationService;
	@Autowired
	private OrderCommodityListService orderCommodityListService;
	@Autowired
	private AverageUserService averageUserService;
	@Autowired
	private StoreService storeService;
	@Autowired
	private CommodityService commodityService;
	@Autowired
	private LimInteStatistiesService limInteStatistiesService;
	@Autowired
	private PerInteStatisticsService perInteStatisticsService;
	
	@Autowired
	private UserShopEvaluateService userShopEvaluateService;
	
	@Resource
	private PayService payService;

	@RequestMapping("payOrderOld")
	@ResponseBody
	public Map<String,Object> payOrderOld(HttpServletRequest request,String auId, String oId,String payType, String orderState, 
			String ermanent_integral_bonus, String time_limited_integration){
		Map<String, Object> data = new HashMap<String,Object>();
		Map<String, Object> user = payService.getUserById(auId);
		switch(Integer.parseInt(payType)){
		case 0:
			
			int store_consume_state = 0; 
			if("0".equals(orderState)){
				store_consume_state = 1;
			}
			try{
				Map<String, Object> order = payService.updateOrderStateById(user, oId, orderState, ermanent_integral_bonus, time_limited_integration, 1,store_consume_state, true);
				
				data.put("data", order);
				data.put("ResponseStatus", "0");
				data.put("msg",ResponseStatus.ORDERPAYSUCCESS_CN_MSG );
			} catch(Exception e){
				System.out.println(e.getMessage());
				data.put("ResponseStatus", "1");
				data.put("msg",ResponseStatus.ORDERPAYFAIL_CN_MSG );
			}
			
			break;
		case 1:
			Map<String, Object> ALOrder = payService.updateOrderStateById(user, oId, orderState, ermanent_integral_bonus, time_limited_integration, 0,0, false);
			AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do", DirectPayConfig.ALPayAppId, DirectPayConfig.ALAppPrivateKey, "json", "UTF-8", DirectPayConfig.ALPublicKey, "RSA2");
			AlipayTradeAppPayRequest req = new AlipayTradeAppPayRequest();
			AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
			model.setBody("弥勒缘订单");
			model.setSubject("订单支付");
			model.setOutTradeNo(oId);
			model.setTimeoutExpress("30m");
			model.setTotalAmount(((Double)ALOrder.get("amount_paid")).toString());
			//model.setTotalAmount("0.01");
			model.setProductCode("QUICK_MSECURITY_PAY");
			req.setBizModel(model);
			req.setNotifyUrl(DirectPayConfig.ALNotifyUrl); 
			try {
		        AlipayTradeAppPayResponse resp = alipayClient.sdkExecute(req);
		        data.put("ResponseStatus", ResponseStatus.ALRePayFail);
		        data.put("msg", ResponseStatus.ALRePaySuccess_CN_MSG);
		        Map m = new HashMap<String,Object>();
		        m.put("alSign", resp.getBody());
		        data.put("data", m);
		    } catch (AlipayApiException e) {
		        System.out.println(e.getMessage());
		        data.put("ResponseStatus", ResponseStatus.ALRePaySuccess);
		        data.put("msg", ResponseStatus.ALRePayFail_CN_MSG);
		        data.put("oId", "");
		        data.put("data", "");
		    }
			break;
		case 2:
			Map<String, Object> order = payService.updateOrderStateById(user, oId, orderState, ermanent_integral_bonus, time_limited_integration, 0,0,false);
			HashMap<String, String> payParam = new HashMap<String, String>();
			payParam.put("body", "弥勒缘-订单支付");
			payParam.put("out_trade_no", oId);
			payParam.put("total_fee", Integer.toString((int)((double)order.get("amount_paid") * 100)));
			//payParam.put("total_fee", "2000");
			payParam.put("spbill_create_ip", WXPayUtil.getIpAddress(request));
			payParam.put("notify_url", Constants.WXNotifyUrl);
			payParam.put("trade_type", "APP");
			Map<String, String> r;
			try {
				WXPayConfig config = WXPayConfigImpl.getInstance();
				WXPay wxPay = new WXPay(config);
				r = wxPay.unifiedOrder(payParam);
				Map<String, String> appResult = new HashMap<String,String>();
				appResult.put("appid", Constants.WXAppid);
				appResult.put("partnerid", Constants.WXMchid);
				appResult.put("prepayid", r.get("prepay_id"));
				appResult.put("package", "Sign=WXPay");
				appResult.put("noncestr", WXPayUtil.generateNonceStr());
				appResult.put("timestamp", System.currentTimeMillis()/1000+"");
				String sign = WXPayUtil.generateSignature(appResult, Constants.WXApiKey);
				appResult.put("sign", sign);
				data.put("data", appResult);
				data.put("ResponseStatus", ResponseStatus.WXRePayFail);
				data.put("msg",ResponseStatus.WXRePaySuccess_CN_MSG );
			} catch (Exception e) {
				System.out.println(e.getMessage());
				data.put("ResponseStatus", ResponseStatus.WXRePaySuccess);
				data.put("msg",ResponseStatus.WXRePayFail_CN_MSG );
			}
			break;
		}
		return data;
		
	}
	
	
	@RequestMapping("payOrder")
	@ResponseBody
	public Map<String,Object> payOrder(HttpServletRequest request,String auId, String oId,String payType, String orderState, String ermanent_integral_bonus, String time_limited_integration){
		Map<String, Object> data = new HashMap<String,Object>();
		Map<String, Object> user = payService.getUserById(auId);
		switch(Integer.parseInt(payType)){
		case 0:
			int store_consume_state = 0; 
			if("0".equals(orderState)){
				store_consume_state = 1;
			}
			try{
				Map<String, Object> order = payService.updateOrderStateById(user, oId, orderState, ermanent_integral_bonus, time_limited_integration, 1,store_consume_state, true);
				
				data.put("data", order);
				data.put("ResponseStatus", "0");
				data.put("msg",ResponseStatus.ORDERPAYSUCCESS_CN_MSG );
			} catch(Exception e){
				System.out.println(e.getMessage());
				data.put("ResponseStatus", "1");
				data.put("msg",ResponseStatus.ORDERPAYFAIL_CN_MSG );
			}
			
			break;
		case 1:
			Map<String, Object> ALOrder = payService.updateOrderStateById(user, oId, orderState, ermanent_integral_bonus, time_limited_integration, 0,0, false);
			AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do", DirectPayConfig.ALPayAppId, DirectPayConfig.ALAppPrivateKey, "json", "UTF-8", DirectPayConfig.ALPublicKey, "RSA2");
			AlipayTradeAppPayRequest req = new AlipayTradeAppPayRequest();
			AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
			model.setBody("弥勒缘订单");
			model.setSubject("订单支付");
			model.setOutTradeNo(oId);
			model.setTimeoutExpress("30m");
			model.setTotalAmount(((Double)ALOrder.get("amount_paid")).toString());
			//model.setTotalAmount("0.01");
			model.setProductCode("QUICK_MSECURITY_PAY");
			req.setBizModel(model);
			req.setNotifyUrl(DirectPayConfig.ALNotifyUrl); 
			try {
		        AlipayTradeAppPayResponse resp = alipayClient.sdkExecute(req);
		        data.put("ResponseStatus", ResponseStatus.ALRePayFail);
		        data.put("msg", ResponseStatus.ALRePaySuccess_CN_MSG);
		        Map m = new HashMap<String,Object>();
		        m.put("alSign", resp.getBody());
		        data.put("data", m);
		    } catch (AlipayApiException e) {
		        System.out.println(e.getMessage());
		        data.put("ResponseStatus", ResponseStatus.ALRePaySuccess);
		        data.put("msg", ResponseStatus.ALRePayFail_CN_MSG);
		        data.put("oId", "");
		        data.put("data", "");
		    }
			break;
		case 2:
			Map<String, Object> order = payService.updateOrderStateById(user, oId, orderState, ermanent_integral_bonus, time_limited_integration, 0,0,false);
			HashMap<String, String> payParam = new HashMap<String, String>();
			payParam.put("body", "弥勒缘-订单支付");
			payParam.put("out_trade_no", oId);
			payParam.put("total_fee", Integer.toString((int)((double)order.get("amount_paid") * 100)));
			//payParam.put("total_fee", "2000");
			payParam.put("spbill_create_ip", WXPayUtil.getIpAddress(request));
			payParam.put("notify_url", Constants.WXNotifyUrl);
			payParam.put("trade_type", "APP");
			Map<String, String> r;
			try {
				WXPayConfig config = WXPayConfigImpl.getInstance();
				WXPay wxPay = new WXPay(config);
				r = wxPay.unifiedOrder(payParam);
				Map<String, String> appResult = new HashMap<String,String>();
				appResult.put("appid", Constants.WXAppid);
				appResult.put("partnerid", Constants.WXMchid);
				appResult.put("prepayid", r.get("prepay_id"));
				appResult.put("package", "Sign=WXPay");
				appResult.put("noncestr", WXPayUtil.generateNonceStr());
				appResult.put("timestamp", System.currentTimeMillis()/1000+"");
				String sign = WXPayUtil.generateSignature(appResult, Constants.WXApiKey);
				appResult.put("sign", sign);
				data.put("data", appResult);
				data.put("ResponseStatus", ResponseStatus.WXRePayFail);
				data.put("msg",ResponseStatus.WXRePaySuccess_CN_MSG );
			} catch (Exception e) {
				System.out.println(e.getMessage());
				data.put("ResponseStatus", ResponseStatus.WXRePaySuccess);
				data.put("msg",ResponseStatus.WXRePayFail_CN_MSG );
			}
			break;
		}
		return data;
		
	}

	/**
	 * 
	 * @param request
	 * @param o_id
	 * @param optionFlag
	 * @return
	 */

	@RequestMapping("getPayOrderInfo")
	@ResponseBody
	public Map<String,Object> getPayOrderInfo(HttpServletRequest request,Integer o_id,String optionFlag){
		//optionFlag 0两者都不选1生活费支付，2现金支付，3两者都选
		Map<String,Object> result=new HashMap<String,Object>();
		OrderInformation orderInformation=orderInformationService.selectByOid(o_id);
		Double amount=orderInformation.getO_amount();
		Integer s_id=orderInformation.getS_id();
		Integer auId=orderInformation.getAuId();
		AverageUser user=averageUserService.selectByauId(auId);
		//用户的现金积分
		Double permanent_points=user.getPermanent_points();
		//用户的生活费积分
		Double limit_integral=user.getLimit_integral();
		Store store=storeService.selectBysId(s_id);
		//商家的抵扣设置
		Double percentage=store.getDeductible_percentage();
		//需要用扣的积分现金
		Double cash=0d;
		//需要扣的积分生活费
		Double expenses=0d;
		//实际支付金额
		Double amount_paid=0d;
		//计算扣的现金积分，生活费，和支付的实际金额
		
		if(optionFlag.equals("1")){
			if(amount*percentage>limit_integral){
				result.put("ResponseStatus","1");
				result.put("msg","订单更新出错!");
				return result;
			}
			expenses=amount*percentage;
			amount_paid=amount-expenses;
		}else if(optionFlag.equals("2")){
			if(permanent_points<=amount){
				cash=permanent_points;
				amount_paid=amount-cash;
			}else{
				cash=amount;
				amount_paid=0d;
			}
		}else if(optionFlag.equals("3")){
			if(amount*percentage<=limit_integral){
				expenses=amount*percentage;
			}else{
				expenses=limit_integral;
			}
			if((amount-expenses)>=permanent_points){
				cash=permanent_points;
				amount_paid=amount-expenses-cash;
			}else{
				cash=amount-expenses;
				amount_paid=0d;
			}
		}else if(optionFlag.equals("0")){
			amount_paid=amount;
		}
		
		System.out.println("生活费积分折扣为："+percentage);
		System.out.println("可提取的现金积分为："+permanent_points);
		System.out.println("可提取的生活费积分为："+limit_integral);
		System.out.println("实际扣的生活费积分："+expenses);
		System.out.println("实际扣的现金积分："+cash);
		System.out.println("原订单的金额："+amount);
		System.out.println("最后实际的金额："+amount_paid);
		result.put("percentage",percentage);
		result.put("permanent_points",permanent_points);
		result.put("limit_integral",limit_integral);
		result.put("expenses",expenses);
		result.put("cash",cash);
		result.put("amount_paid",amount_paid);
		result.put("ResponseStatus","0");
		result.put("msg","操作完成!");
		return result;
	}
	
	
	
	/**
	 * 
	 * @param request
	 * @param o_id 订单ID
	 * @param payType 支付类型
	 * @return
	 */
	/*@RequestMapping("payOrder")
	@ResponseBody
	public Map<String,Object> payOrder(HttpServletRequest request,Integer o_id,String payType,String optionFlag){
		//optionFlag 0两者都不选1生活费支付，2现金支付，3两者都选
		Map<String,Object> result=new HashMap<String,Object>();
		Map<String, Object> data = new HashMap<String,Object>();
		OrderInformation orderInformation=orderInformationService.selectByOid(o_id);
		Double amount=orderInformation.getO_amount();
		Integer s_id=orderInformation.getS_id();
		Integer auId=orderInformation.getAuId();
		AverageUser user=averageUserService.selectByauId(auId);
		//用户的现金积分
		Double permanent_points=user.getPermanent_points();
		//用户的生活费积分
		Double limit_integral=user.getLimit_integral();
		Store store=storeService.selectBysId(s_id);
		//商家的抵扣设置
		Double percentage=store.getDeductible_percentage();
		//需要用扣的积分现金
		Double cash=0d;
		//需要扣的积分生活费
		Double expenses=0d;
		//实际支付金额
		Double amount_paid=0d;
		//计算扣的现金积分，生活费，和支付的实际金额
		
		if(optionFlag.equals("1")){
			if(amount*percentage>limit_integral){
				result.put("ResponseStatus","1");
				result.put("msg","订单更新出错!");
				return result;
			}
			expenses=amount*percentage;
			amount_paid=amount-expenses;
		}else if(optionFlag.equals("2")){
			if(permanent_points<=amount){
				cash=permanent_points;
				amount_paid=amount-cash;
			}else{
				cash=amount;
				amount_paid=0d;
			}
		}else if(optionFlag.equals("3")){
			if(amount*percentage<=limit_integral){
				expenses=amount*percentage;
			}else{
				expenses=limit_integral;
			}
			if((amount-expenses)>=permanent_points){
				cash=permanent_points;
				amount_paid=amount-expenses-cash;
			}else{
				cash=amount-expenses;
				amount_paid=0d;
			}
		}else if(optionFlag.equals("0")){
			amount_paid=amount;
		}
		
		System.out.println("生活费积分折扣为："+percentage);
		System.out.println("可提取的现金积分为："+permanent_points);
		System.out.println("可提取的生活费积分为："+limit_integral);
		System.out.println("实际扣的生活费积分："+expenses);
		System.out.println("实际扣的现金积分："+cash);
		System.out.println("原订单的金额："+amount);
		System.out.println("最后实际的金额："+amount_paid);
		
		
		//更新订单
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("use_limit_integral",expenses);
		param.put("use_permanent_points",cash);
		param.put("amount_paid",amount_paid);
		param.put("o_id",o_id);
		
		Integer value=orderInformationService.updateOrderInfo(param);
		if(value<1){
			result.put("ResponseStatus","1");
			result.put("msg","订单更新出错!");
			return result;
		}
		//amount_paid<=0 表示不需要微信支付宝支付
		if(amount_paid<=0){
			
			Map<String,Object> stateParam=new HashMap<String,Object>();
			stateParam.put("pay_state","1");
			stateParam.put("o_id",o_id);
			stateParam.put("payment_time",Constants.getSystemTime());
			Integer retValue=orderInformationService.updateOrderInfo(stateParam);
			if(retValue<1){
				result.put("ResponseStatus","1");
				result.put("msg","订单更新出错!");
				return result;
			}		
			//添加生活费积分记录
			if(expenses!=0){
				LimInteStatisties lis = new LimInteStatisties();
				lis.setAuId(user.getAuId());
				lis.setS_name(store.getS_name());
				lis.setC_name("无");
				lis.setLis_consumption(0d);
				lis.setLis_get(expenses);
				lis.setLis_time(Constants.getSystemTime());
				lis.setLis_category("-");
				//期限 这个没有用上
				lis.setLis_term("0");
				lis.setLis_state("1");
				limInteStatistiesService.insertLI(lis);
			}
			//添加现金积分记录
			if(cash!=0){
				PerInteStatistics pis = new PerInteStatistics();
				pis.setAuId(user.getAuId());
				pis.setS_name(store.getS_name());
				pis.setC_name("无");
				pis.setPis_consumption(0d);
				pis.setPis_get(cash);
				pis.setPis_time(Constants.getSystemTime());
				pis.setPis_category("-");
				perInteStatisticsService.insertPI(pis);
			}
			//更新用户积分表
			Double xPermanent = user.getPermanent_points() - cash;
			Double xLimit = user.getLimit_integral() - expenses;
			Map<String,Object> valueParam=new HashMap<String,Object>();
			valueParam.put("limit_integral", xLimit);
			valueParam.put("permanent_points", xPermanent);
			valueParam.put("auId",user.getAuId());
			averageUserService.updateLisPis(valueParam);
			
			result.put("ResponseStatus","0");
			result.put("msg","订单支付完成");
			return result;
		}
		
		
		switch(Integer.parseInt(payType)){
		case 1:
			AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do", DirectPayConfig.ALPayAppId, DirectPayConfig.ALAppPrivateKey, "json", "UTF-8", DirectPayConfig.ALPublicKey, "RSA2");
			AlipayTradeAppPayRequest req = new AlipayTradeAppPayRequest();
			AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
			model.setBody("弥勒缘订单");
			model.setSubject("订单支付");
			model.setOutTradeNo(String.valueOf(o_id));
			model.setTimeoutExpress("30m");
			model.setTotalAmount(String.valueOf(amount_paid));
			//model.setTotalAmount("0.01");
			model.setProductCode("QUICK_MSECURITY_PAY");
			req.setBizModel(model);
			req.setNotifyUrl(DirectPayConfig.ALNotifyUrl); 
			try {
		        AlipayTradeAppPayResponse resp = alipayClient.sdkExecute(req);
		        data.put("ResponseStatus", ResponseStatus.ALRePayFail);
		        data.put("msg", ResponseStatus.ALRePaySuccess_CN_MSG);
		        Map m = new HashMap<String,Object>();
		        m.put("alSign", resp.getBody());
		        data.put("data", m);
		    } catch (AlipayApiException e) {
		        System.out.println(e.getMessage());
		        data.put("ResponseStatus", ResponseStatus.ALRePaySuccess);
		        data.put("msg", ResponseStatus.ALRePayFail_CN_MSG);
		        data.put("oId", "");
		        data.put("data", "");
		    }
			break;
		case 2:
			HashMap<String, String> payParam = new HashMap<String, String>();
			payParam.put("body", "弥勒缘-订单支付");
			payParam.put("out_trade_no",String.valueOf(o_id));
			payParam.put("total_fee", Integer.toString((int)((double)amount_paid * 100)));
			//payParam.put("total_fee", "2000");
			payParam.put("spbill_create_ip", WXPayUtil.getIpAddress(request));
			payParam.put("notify_url", Constants.WXNotifyUrl);
			payParam.put("trade_type", "APP");
			Map<String, String> r;
			try {
				WXPayConfig config = WXPayConfigImpl.getInstance();
				WXPay wxPay = new WXPay(config);
				r = wxPay.unifiedOrder(payParam);
				Map<String, String> appResult = new HashMap<String,String>();
				appResult.put("appid", Constants.WXAppid);
				appResult.put("partnerid", Constants.WXMchid);
				appResult.put("prepayid", r.get("prepay_id"));
				appResult.put("package", "Sign=WXPay");
				appResult.put("noncestr", WXPayUtil.generateNonceStr());
				appResult.put("timestamp", System.currentTimeMillis()/1000+"");
				String sign = WXPayUtil.generateSignature(appResult, Constants.WXApiKey);
				appResult.put("sign", sign);
				data.put("data", appResult);
				data.put("ResponseStatus", ResponseStatus.WXRePayFail);
				data.put("msg",ResponseStatus.WXRePaySuccess_CN_MSG );
			} catch (Exception e) {
				System.out.println(e.getMessage());
				data.put("ResponseStatus", ResponseStatus.WXRePaySuccess);
				data.put("msg",ResponseStatus.WXRePayFail_CN_MSG );
			}
			break;
		}
		return data;
		
	}
	
	
	*/
	
	@RequestMapping("WXPaySuccess")
	@ResponseBody
	public WXPayResult WXPaySuccess(HttpServletRequest request, HttpServletResponse response, @RequestBody WXNotifyParam wxp){
		   log.debug("微信支付回调");
	        Map<String, String> xmlDate = null;
	        try {
	            xmlDate = WXPayUtil.convertBean(wxp);
	            log.debug("请求参数转义成功: " + xmlDate);
	        } catch (Exception e) {
	            log.debug("请求参数转异常:" + e.getMessage());
	            e.printStackTrace();
	        }
	        WXPayResult wp = new WXPayResult();
	        try {
	            log.debug("微信支付通知结果：" + wxp);
	            boolean b = false;
	            b = WXPayUtil.isSignatureValid( xmlDate, Constants.WXApiKey);
	            log.debug("验签结果"+ b);

	            if (wxp.getReturn_code().equals("SUCCESS") && b) {
	                if (wxp.getResult_code().equals("SUCCESS")) {
	                    log.debug("支付成功, 查看或修改订单状态 ");
	                    payService.WXPaySuccess(wxp);
	                    wp.setReturn_code("SUCCESS");
	                    wp.setReturn_msg("OK");
	                }
	            }
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	            log.debug("通知失败"+ e.getMessage());
	            wp.setReturn_code("fail");
	            wp.setReturn_msg("fail");
	        }
	        return wp;
	}
	
	
	@RequestMapping("WXPaySuccessChange")
	@ResponseBody
	public WXPayResult WXPaySuccessChange(HttpServletRequest request, HttpServletResponse response, @RequestBody WXNotifyParam wxp){
		   log.debug("微信支付回调");
	        Map<String, String> xmlDate = null;
	        try {
	            xmlDate = WXPayUtil.convertBean(wxp);
	            log.debug("请求参数转义成功: " + xmlDate);
	        } catch (Exception e) {
	            log.debug("请求参数转异常:" + e.getMessage());
	            e.printStackTrace();
	        }
	        WXPayResult wp = new WXPayResult();
	        try {
	            log.debug("微信支付通知结果：" + wxp);
	            boolean b = false;
	            b = WXPayUtil.isSignatureValid( xmlDate, Constants.WXApiKey);
	            log.debug("验签结果"+ b);

	            if (wxp.getReturn_code().equals("SUCCESS") && b) {
	                if (wxp.getResult_code().equals("SUCCESS")) {
	                    log.debug("支付成功, 查看或修改订单状态 ");
	                    wp.setReturn_code("SUCCESS");
	                    wp.setReturn_msg("OK");
	                    String oId = wxp.getOut_trade_no();
	            		OrderInformation  orderInformation= orderInformationService.selectByOid(Integer.valueOf(oId));
	            		paySuccessChange(orderInformation);
	                }
	            }
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	            log.debug("通知失败"+ e.getMessage());
	            wp.setReturn_code("fail");
	            wp.setReturn_msg("fail");
	        }
	        return wp;
	}
	
	
	
	
	@RequestMapping("ALPaySuccessChange")
	public void ALPaySuccessChange(HttpServletRequest request){
		log.debug("支付宝支付异步通知成功----充值:");
		Map<String,String> params = new HashMap<String,String>();
		Map<String, String[]>  requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
		    String name = (String) iter.next();
		    String[] values = (String[]) requestParams.get(name);
		    String valueStr = "";
		    for (int i = 0; i < values.length; i++) {
		        valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
		  }
		  params.put(name, valueStr);
		 }
		try {
			boolean flag = AlipaySignature.rsaCheckV1(params, DirectPayConfig.ALPublicKey, "UTF-8", "RSA2");
			if(flag){
				String out_trade_no = ((String[]) requestParams.get("out_trade_no"))[0];
        		OrderInformation  orderInformation= orderInformationService.selectByOid(Integer.valueOf(out_trade_no));
        		paySuccessChange(orderInformation);
				log.debug("支付宝支付异步通知验签成功: 订单Id:"+out_trade_no);
			} else {
				log.debug("支付宝支付异步通知验签失败:");
			}
		} catch (AlipayApiException e) {
			//System.out.println(e.getMessage());
			log.debug("支付宝异步通知异常:"+e.getMessage());
		}
		
	}
	
	public void paySuccessChange(OrderInformation orderInformation){
		Double expenses=orderInformation.getUse_limit_integral();
		Double cash=orderInformation.getUse_permanent_points();
		Integer auId=orderInformation.getAuId();
		Integer s_id=orderInformation.getS_id();
		Double amount_paid_dbl=orderInformation.getAmount_paid()*0.01;
		Integer amount_paid_int=Integer.valueOf(String.valueOf(amount_paid_dbl));
		AverageUser user=averageUserService.selectByauId(auId);
		Store store=storeService.selectBysId(s_id);
		//添加生活费积分记录
		if(expenses!=0){
			LimInteStatisties lis = new LimInteStatisties();
			lis.setAuId(user.getAuId());
			lis.setS_name(store.getS_name());
			lis.setC_name("无");
			lis.setLis_consumption(0d);
			lis.setLis_get(expenses);
			lis.setLis_time(Constants.getSystemTime());
			lis.setLis_category("-");
			//期限 这个没有用上
			lis.setLis_term("0");
			lis.setLis_state("1");
			limInteStatistiesService.insertLI(lis);
		}
		//添加现金积分记录
		if(cash!=0){
			PerInteStatistics pis = new PerInteStatistics();
			pis.setAuId(user.getAuId());
			pis.setS_name(store.getS_name());
			pis.setC_name("无");
			pis.setPis_consumption(0d);
			pis.setPis_get(cash);
			pis.setPis_time(Constants.getSystemTime());
			pis.setPis_category("-");
			perInteStatisticsService.insertPI(pis);
		}
		//更新用户积分表
		Double xPermanent = user.getPermanent_points() - cash+amount_paid_int;
		Double xLimit = user.getLimit_integral() - expenses;
		Map<String,Object> valueParam=new HashMap<String,Object>();
		valueParam.put("limit_integral", xLimit);
		valueParam.put("permanent_points", xPermanent);
		valueParam.put("auId",user.getAuId());
		averageUserService.updateLisPis(valueParam);
		
		Map<String,Object> stateParam=new HashMap<String,Object>();
		stateParam.put("o_state","77");
		stateParam.put("pay_state","1");
		stateParam.put("payment_time",Constants.getSystemTime());
		stateParam.put("o_id",orderInformation.getO_id());
		orderInformationService.updateOrderInfo(stateParam);
	}
	
	
	
	@RequestMapping("ALPaySuccess")
	public void ALPaySuccess(HttpServletRequest request){
		log.debug("支付宝支付异步通知成功----充值:");
		Map<String,String> params = new HashMap<String,String>();
		Map<String, String[]>  requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
		    String name = (String) iter.next();
		    String[] values = (String[]) requestParams.get(name);
		    String valueStr = "";
		    for (int i = 0; i < values.length; i++) {
		        valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
		  }
		  params.put(name, valueStr);
		 }
		try {
			boolean flag = AlipaySignature.rsaCheckV1(params, DirectPayConfig.ALPublicKey, "UTF-8", "RSA2");
			if(flag){
				String out_trade_no = ((String[]) requestParams.get("out_trade_no"))[0];
				payService.aLiPaySuccess(out_trade_no);
				log.debug("支付宝支付异步通知验签成功: 订单Id:"+out_trade_no);
			} else {
				log.debug("支付宝支付异步通知验签失败:");
			}
		} catch (AlipayApiException e) {
			//System.out.println(e.getMessage());
			log.debug("支付宝异步通知异常:"+e.getMessage());
		}
		
	}
	
	@ResponseBody
	@RequestMapping("insertOIOld")
	public Map<String, Object> insertOIOld(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("auId") String auId,
			@RequestParam("s_id") String s_id,
			@RequestParam("c_id") String c_id,
			@RequestParam("ocl_num") String ocl_num) throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		OrderInformation oi = new OrderInformation();// 订单类
		OrderCommodityList oc = new OrderCommodityList();// 订单商品类
		AverageUser au = averageUserService.selectByauId(Integer.parseInt(auId));
		if (au != null) {
			String number = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			String up_time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			Store st = storeService.selectBysId(Integer.parseInt(s_id));
			Commodity co = commodityService.selectByCid(Integer.parseInt(c_id));
			if (st != null && co != null) {
				oi.setO_number(number);
				oi.setAuId(au.getAuId());
				oi.setAuBuyerNick(au.getAuBuyerNick());
				oi.setAuMobile(au.getAuMobile());
				oi.setAuAddress(au.getAuAddress());
				oi.setUse_limit_integral(au.getLimit_integral());
				oi.setUse_permanent_points(au.getPermanent_points());
				oi.setO_state("0");
				oi.setStore_consume_state("0");
				oi.setPay_state(0);
				oi.setS_name(st.getS_name());
				oi.setS_id(st.getS_id());
				oi.setS_mobile(st.getS_mobile());
				oi.setO_time(up_time);
				oi.setPayment_time("0");
				Double amount = co.getC_unit_price() * Integer.parseInt(ocl_num);
				oi.setO_amount(amount);
				oi.setAmount_paid(0.0);
				oi.setErmanent_integral_bonus(0.0);
				oi.setTime_limited_integration(0.0);
				oi.setFull_integral_purchase(amount / st.getIntegral_setting());
				Integer insertOrdeInfor = orderInformationService.insertOrdeInfor(oi);
				if (insertOrdeInfor > 0) {
					OrderInformation selectByauId = orderInformationService.selectByauId(Integer.parseInt(auId));
					if (selectByauId != null) {
						oc.setO_id(selectByauId.getO_id());
						oc.setC_name(co.getC_name());
						oc.setOcl_num(Integer.parseInt(ocl_num));
						oc.setC_unit_price(co.getC_unit_price());
						oc.setC_introduce(co.getC_introduce());
						oc.setC_first_figure(co.getC_first_figure());
						Integer insertOC = orderCommodityListService.insertOC(oc);
						if (insertOC > 0) {
							List<OrderCommodityList> selectByOid = orderCommodityListService.selectByOid(selectByauId.getO_id());
							if (selectByOid.size() > 0) {
								OrderDetailsMobileVo odm = new OrderDetailsMobileVo();
								odm.setO_id(selectByauId.getO_id());
								odm.setO_number(selectByauId.getO_number());
								odm.setS_name(selectByauId.getS_name());
								odm.setC_name(selectByOid.get(0).getC_name());
								odm.setOcl_num(selectByOid.get(0).getOcl_num());
								odm.setC_unit_price(selectByOid.get(0).getC_unit_price());
								odm.setC_first_figure(selectByOid.get(0).getC_first_figure());
								odm.setO_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new SimpleDateFormat("yyyyMMddHHmmss").parse(selectByauId.getO_time())));
								odm.setUse_limit_integral(selectByauId.getUse_limit_integral());
								odm.setUse_permanent_points(selectByauId.getUse_permanent_points());
								odm.setIntegral_available((int) (selectByOid.get(0).getC_unit_price() / st.getIntegral_setting()) * Integer.parseInt(ocl_num));

//								Integer commodity_integral = (int) (selectByOid.get(0).getC_unit_price() / st.getIntegral_setting()) * Integer.parseInt(ocl_num);
//								Integer con_comm_points = (int) (commodity_integral * st.getDeductible_percentage());
//								if (con_comm_points <= au.getPermanent_points()) {
//									odm.setTime_limited_integration(con_comm_points * st.getOffset_setting());
//									odm.setErmanent_integral_bonus(0.0);
//								} else {
//									odm.setTime_limited_integration(st.getOffset_setting() * selectByauId.getUse_permanent_points());
//									Integer differ = con_comm_points - selectByauId.getUse_permanent_points();
//									if (selectByauId.getUse_limit_integral() >= differ) {
//										odm.setErmanent_integral_bonus(st.getOffset_setting() * differ);
//									} else {
//										odm.setErmanent_integral_bonus(st.getOffset_setting() * selectByauId.getUse_limit_integral());
//									}
//								}
								// 折扣商品逻辑变更简化
								// 计算折扣后价格并取整
								Integer commodity_price = (int) (selectByOid.get(0).getC_unit_price() * Integer.parseInt(ocl_num) * st.getDeductible_percentage());
								// 折扣后价格所需积分
								Integer con_comm_points = (int) (commodity_price / st.getIntegral_setting());
								if (con_comm_points <= au.getPermanent_points()) {
									// 用户积分多余，设置上限
									odm.setTime_limited_integration(commodity_price * st.getOffset_setting());

								} else {
									// 用户积分不足，全部抵扣
									odm.setTime_limited_integration( selectByauId.getUse_permanent_points() * st.getIntegral_setting() * st.getOffset_setting());
								}
								odm.setErmanent_integral_bonus(0.0);

								odm.setCommodity_integral(((int) (selectByOid.get(0).getC_unit_price() / st.getIntegral_setting())) * Integer.parseInt(ocl_num));
								odm.setAuAddress(au.getAuAddress());
								map.put("obj", odm);
								map.put("obj1", st.getIntegral_setting());
								map.put("ResponseStatus", ResponseStatus.QUERYWASSUCCESS);
								map.put("msg", "订单生成成功!!");
							} else {
								map.put("ResponseStatus", ResponseStatus.STORENULL);
								map.put("msg", "订单生成失败.....");
							}
						} else {
							map.put("ResponseStatus", ResponseStatus.STORENULL);
							map.put("msg", "订单中商品存在异常");
						}
					} else {
						map.put("ResponseStatus", ResponseStatus.STORENULL);
						map.put("msg", "查无此订单信息");
					}
				} else {
					map.put("ResponseStatus", ResponseStatus.STORENULL);
					map.put("msg", "订单异常");
				}
			} else {
				map.put("ResponseStatus", ResponseStatus.STORENULL);
				map.put("msg", "该店铺中商品存在异常");
			}
		} else {
			map.put("ResponseStatus", ResponseStatus.STORENULL);
			map.put("msg", "该用户存在异常");
		}
		return map;
	}
	
	
	
	@ResponseBody
	@RequestMapping("insertOI")
	public Map<String, Object> insertOI(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("auId") String auId,
			@RequestParam("s_id") String s_id,
			@RequestParam("c_id") String c_id,
			@RequestParam("ocl_num") String ocl_num) throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		OrderInformation oi = new OrderInformation();// 订单类
		OrderCommodityList oc = new OrderCommodityList();// 订单商品类
		AverageUser au = averageUserService.selectByauId(Integer.parseInt(auId));
		if (au != null) {
			String number = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			String up_time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			Store st = storeService.selectBysId(Integer.parseInt(s_id));
			Commodity co = commodityService.selectByCid(Integer.parseInt(c_id));
			if (st != null && co != null) {
				oi.setO_number(number);
				oi.setAuId(au.getAuId());
				oi.setAuBuyerNick(au.getAuBuyerNick());
				oi.setAuMobile(au.getAuMobile());
				oi.setAuAddress(au.getAuAddress());
				oi.setUse_limit_integral(0d);
				oi.setUse_permanent_points(0d);
				oi.setO_state("0");
				oi.setStore_consume_state("0");
				oi.setPay_state(0);
				oi.setS_name(st.getS_name());
				oi.setS_id(st.getS_id());
				oi.setS_mobile(st.getS_mobile());
				oi.setO_time(up_time);
				oi.setPayment_time("0");
				Double amount = co.getC_unit_price() * Integer.parseInt(ocl_num);
				oi.setO_amount(amount);
				oi.setAmount_paid(0.0);
				oi.setErmanent_integral_bonus(0.0);
				oi.setTime_limited_integration(0.0);
				oi.setFull_integral_purchase(0d);
				Integer insertOrdeInfor = orderInformationService.insertOrdeInfor(oi);
				if (insertOrdeInfor > 0) {
					OrderInformation selectByauId = orderInformationService.selectByauId(Integer.parseInt(auId));
					if (selectByauId != null) {
						oc.setO_id(selectByauId.getO_id());
						oc.setC_name(co.getC_name());
						oc.setOcl_num(Integer.parseInt(ocl_num));
						oc.setC_unit_price(co.getC_unit_price());
						oc.setC_introduce(co.getC_introduce());
						oc.setC_first_figure(co.getC_first_figure());
						Integer insertOC = orderCommodityListService.insertOC(oc);
						if (insertOC > 0) {
							List<OrderCommodityList> selectByOid = orderCommodityListService.selectByOid(selectByauId.getO_id());
							if (selectByOid.size() > 0) {
								OrderDetailsMobileVo odm = new OrderDetailsMobileVo();
								odm.setO_id(selectByauId.getO_id());
								odm.setO_number(selectByauId.getO_number());
								odm.setS_name(selectByauId.getS_name());
								odm.setC_name(selectByOid.get(0).getC_name());
								odm.setOcl_num(selectByOid.get(0).getOcl_num());
								odm.setC_unit_price(selectByOid.get(0).getC_unit_price());
								odm.setC_first_figure(selectByOid.get(0).getC_first_figure());
								odm.setO_time(up_time);
								odm.setAuAddress(au.getAuAddress());
								map.put("obj", odm);
								map.put("obj1", st.getDeductible_percentage());
								//记录可用的生活费与可用的积分
								map.put("limit_integral", au.getLimit_integral());
								map.put("permanent_points",au.getPermanent_points());
								map.put("ResponseStatus", ResponseStatus.QUERYWASSUCCESS);
								map.put("msg", "订单生成成功!!");
							} else {
								map.put("ResponseStatus", ResponseStatus.STORENULL);
								map.put("msg", "订单生成失败.....");
							}
						} else {
							map.put("ResponseStatus", ResponseStatus.STORENULL);
							map.put("msg", "订单中商品存在异常");
						}
					} else {
						map.put("ResponseStatus", ResponseStatus.STORENULL);
						map.put("msg", "查无此订单信息");
					}
				} else {
					map.put("ResponseStatus", ResponseStatus.STORENULL);
					map.put("msg", "订单异常");
				}
			} else {
				map.put("ResponseStatus", ResponseStatus.STORENULL);
				map.put("msg", "该店铺中商品存在异常");
			}
		} else {
			map.put("ResponseStatus", ResponseStatus.STORENULL);
			map.put("msg", "该用户存在异常");
		}
		return map;
	}
	
	
	

	@ResponseBody
	@RequestMapping("updateOstate")
	public Map<String, Object> updateOstate(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("o_id") String o_id,
			@RequestParam("o_state") String o_state) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		if (o_state.equals("1")) {
			Integer updateOstate = orderInformationService.updateOstate(Integer.parseInt(o_id));
			if (updateOstate > 0) {
				map.put("ResponseStatus", ResponseStatus.QUERYWASSUCCESS);
				map.put("msg", "订单性质修改成功!");
			} else {
				map.put("ResponseStatus", ResponseStatus.STORENULL);
				map.put("msg", "订单性质修改失败......");
			}
		} else {
			map.put("ResponseStatus", ResponseStatus.QUERYWASSUCCESS);
			map.put("msg", "订单性质为等待发货");
		}
		return map;
	}

	@ResponseBody
	@RequestMapping("selectSid")
	public Map<String, Object> selectSid(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("s_id") String s_id,
			@RequestParam("psize") String psize,
			@RequestParam("pstart") String pstart) throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Store store=storeService.selectBysId(Integer.valueOf(s_id));
		Integer auId=store.getAuId();
		AverageUser averageUser=averageUserService.selectByauId(auId);
		Double money=averageUser.getMoney();
		Map<String, Object> m = new LinkedHashMap<String, Object>();
		Integer pstarts = Integer.parseInt(pstart);
		Integer psizes = Integer.parseInt(psize);
		map.put("pstart", pstarts * psizes);
		map.put("psize", psizes);
		map.put("s_id", Integer.parseInt(s_id));
		List<OrderInformation> selectAllOrdInforFen = orderInformationService.selectAllOrdInforFen(map);
		if (selectAllOrdInforFen.size() > 0) {
			List<OrderInformation> oil = new ArrayList<OrderInformation>();
			for (int i = 0; i < selectAllOrdInforFen.size(); i++) {
				OrderInformation oi = new OrderInformation();
				oi.setO_id(selectAllOrdInforFen.get(i).getO_id());
				oi.setS_id(selectAllOrdInforFen.get(i).getS_id());
				oi.setAuId(selectAllOrdInforFen.get(i).getAuId());
				oi.setO_number(selectAllOrdInforFen.get(i).getO_number());
				oi.setS_name(selectAllOrdInforFen.get(i).getS_name());
				oi.setS_mobile(selectAllOrdInforFen.get(i).getS_mobile());
				oi.setAuBuyerNick(selectAllOrdInforFen.get(i).getAuBuyerNick());
				oi.setAuMobile(selectAllOrdInforFen.get(i).getAuMobile());
				oi.setAuAddress(selectAllOrdInforFen.get(i).getAuAddress());
				oi.setO_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new SimpleDateFormat("yyyyMMddHHmmss").parse(selectAllOrdInforFen.get(i).getO_time())));
				oi.setPayment_time(selectAllOrdInforFen.get(i).getPayment_time());
				oi.setO_amount(selectAllOrdInforFen.get(i).getO_amount());
				oi.setAmount_paid(selectAllOrdInforFen.get(i).getAmount_paid());
				oi.setUse_limit_integral(selectAllOrdInforFen.get(i).getUse_limit_integral());
				oi.setUse_permanent_points(selectAllOrdInforFen.get(i).getUse_permanent_points());
				oi.setErmanent_integral_bonus(selectAllOrdInforFen.get(i).getErmanent_integral_bonus());
				oi.setTime_limited_integration(selectAllOrdInforFen.get(i).getTime_limited_integration());
				oi.setFull_integral_purchase(selectAllOrdInforFen.get(i).getFull_integral_purchase());
				oi.setO_state(selectAllOrdInforFen.get(i).getO_state());
				oi.setStore_consume_state(selectAllOrdInforFen.get(i).getStore_consume_state());
				oi.setPay_state(selectAllOrdInforFen.get(i).getPay_state());
				oi.setStore_unconsume_state(selectAllOrdInforFen.get(i).getStore_unconsume_state());
				oil.add(oi);
			}
			m.put("ResponseStatus", ResponseStatus.QUERYWASSUCCESS);
			m.put("msg", ResponseStatus.QUERYWASSUCCESS_CN_MSG);
			m.put("money",money);
			m.put("obj", oil);
		} else {
			m.put("ResponseStatus", ResponseStatus.STORENULL);
			m.put("msg", ResponseStatus.STORENULL_CN_MSG);
		}
		return m;
	}

	@ResponseBody
	@RequestMapping("updateConsume")
	public Map<String, Object> updateConsume(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("o_id") String o_id) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Integer updateConsume = orderInformationService.updateConsume(Integer.parseInt(o_id));
		if (updateConsume > 0) {
			map.put("ResponseStatus", ResponseStatus.QUERYWASSUCCESS);
			map.put("msg", "进店订单已消费修改成功!!");
		} else {
			map.put("ResponseStatus", ResponseStatus.STORENULL);
			map.put("msg", "进店订单已消费修改失败......");
		}
		return map;
	}

	@ResponseBody
	@RequestMapping("selByTime")
	public Map<String, Object> selByTime(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("s_id") String s_id,
			@RequestParam("first_time") String first_time,
			@RequestParam("tail_time") String tail_time) throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<OrderInformation> selectAllOrdeInfor = orderInformationService.selectAllOrdeInfor(Integer.parseInt(s_id));
		List<OrderTimeVo> oiTime = new ArrayList<OrderTimeVo>();
		if (selectAllOrdeInfor.size() > 0) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat dateFormats = new SimpleDateFormat("yyyyMMddHHmmss");
			long fTime = dateFormat.parse(first_time).getTime();
			long tTime = dateFormat.parse(tail_time).getTime();
			for (int i = 0; i < selectAllOrdeInfor.size(); i++) {
				long oTime = dateFormats.parse(selectAllOrdeInfor.get(i).getO_time()).getTime();
				if (fTime <= oTime && oTime <= tTime) {
					OrderTimeVo ot = new OrderTimeVo();
					ot.setO_id(selectAllOrdeInfor.get(i).getO_id());
					ot.setO_number(selectAllOrdeInfor.get(i).getO_number());
					ot.setAuBuyerNick(selectAllOrdeInfor.get(i).getAuBuyerNick());
					ot.setAuMobile(selectAllOrdeInfor.get(i).getAuMobile());
					ot.setUse_limit_integral(selectAllOrdeInfor.get(i).getUse_limit_integral());
					ot.setUse_permanent_points(selectAllOrdeInfor.get(i).getUse_permanent_points());
					ot.setErmanent_integral_bonus(selectAllOrdeInfor.get(i).getErmanent_integral_bonus());
					ot.setTime_limited_integration(selectAllOrdeInfor.get(i).getTime_limited_integration());
					ot.setO_amount(selectAllOrdeInfor.get(i).getO_amount());
					ot.setAmount_paid(selectAllOrdeInfor.get(i).getAmount_paid());
					ot.setFull_integral_purchase(selectAllOrdeInfor.get(i).getFull_integral_purchase());
					ot.setPayment_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new SimpleDateFormat("yyyyMMddHHmmss").parse(selectAllOrdeInfor.get(i).getO_time())));
					ot.setO_state(selectAllOrdeInfor.get(i).getO_state());
					ot.setStore_consume_state(selectAllOrdeInfor.get(i).getStore_consume_state());
					oiTime.add(ot);
				}
			}
			if (oiTime.size() > 0) {
				map.put("ResponseStatus", ResponseStatus.QUERYWASSUCCESS);
				map.put("msg", ResponseStatus.QUERYWASSUCCESS_CN_MSG);
				map.put("obj", oiTime);
			} else {
				map.put("ResponseStatus", ResponseStatus.STORENULL);
				map.put("msg", "该时间段暂无订单信息.....");
			}
		} else {
			map.put("ResponseStatus", ResponseStatus.STORENULL);
			map.put("msg", "该时间段暂无订单信息.....");
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping("viewOrderInformation")
	public Map<String, Object> viewOrderInformation(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("o_id") String o_id) throws Exception{
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		OrderInformation byOid = orderInformationService.selectByOid(Integer.parseInt(o_id));
		if(byOid!=null){
			OrderInformation oi = new OrderInformation();
			oi.setO_id(byOid.getO_id());
			oi.setS_id(byOid.getS_id());
			oi.setAuId(byOid.getAuId());
			oi.setO_number(byOid.getO_number());
			oi.setS_name(byOid.getS_name());
			oi.setS_mobile(byOid.getS_mobile());
			oi.setAuBuyerNick(byOid.getAuBuyerNick());
			oi.setAuMobile(byOid.getAuMobile());
			oi.setAuAddress(byOid.getAuAddress());
			oi.setO_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new SimpleDateFormat("yyyyMMddHHmmss").parse(byOid.getO_time())));
			try{
				oi.setPayment_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new SimpleDateFormat("yyyyMMddHHmmss").parse(byOid.getPayment_time())));
			}catch(Exception e){
				e.printStackTrace();
			}
			oi.setO_amount(byOid.getO_amount());
			oi.setAmount_paid(byOid.getAmount_paid());
			oi.setUse_limit_integral(byOid.getUse_limit_integral());
			oi.setUse_permanent_points(byOid.getUse_permanent_points());
			oi.setErmanent_integral_bonus(byOid.getErmanent_integral_bonus());
			oi.setTime_limited_integration(byOid.getTime_limited_integration());
			oi.setFull_integral_purchase(byOid.getFull_integral_purchase());
			oi.setO_state(byOid.getO_state());
			oi.setStore_consume_state(byOid.getStore_consume_state());
			List<OrderCommodityList> list = orderCommodityListService.selectByOid(byOid.getO_id());
			List<UserShopEvaluate> evaluatelist = userShopEvaluateService.selectByOrderID(Long.valueOf(byOid.getO_id()));
			if(list.size()>0){
				map.put("obj", oi);
				map.put("obj1", list);
				map.put("ResponseStatus", ResponseStatus.QUERYWASSUCCESS);
				map.put("msg", "查询成功");
				if(evaluatelist!=null && evaluatelist.size()>0){
					map.put("evaluateNumber",evaluatelist.size());
					map.put("evaluatelist",evaluatelist);
				}else{
					map.put("evaluateNumber", "0");
				}
			}
		}else{
			map.put("ResponseStatus", ResponseStatus.STORENULL);
			map.put("msg", "查无此订单信息");
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping("queryOrderViaUserID")
	public Map<String, Object> queryOrderViaUserID(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("auId") String auId) throws Exception{
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<OrderUserMobileVo> oumList = new ArrayList<OrderUserMobileVo>();
		List<OrderInformation> userID = orderInformationService.queryOrderViaUserID(Integer.parseInt(auId));
		if(userID.size()>0){
			for (int i = 0; i < userID.size(); i++) {
				OrderUserMobileVo oum = new OrderUserMobileVo();
				List<OrderCommodityList> selectByOid = orderCommodityListService.selectByOid(userID.get(i).getO_id());
				oum.setO_id(userID.get(i).getO_id());
				oum.setO_number(userID.get(i).getO_number());
				oum.setS_name(userID.get(i).getS_name());
				oum.setC_name(selectByOid.get(0).getC_name());
				oum.setOcl_num(selectByOid.get(0).getOcl_num());
				oum.setC_unit_price(selectByOid.get(0).getC_unit_price());
				oum.setC_first_figure(selectByOid.get(0).getC_first_figure());
				oum.setAmount_paid(userID.get(i).getAmount_paid());
				oum.setO_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new SimpleDateFormat("yyyyMMddHHmmss").parse(userID.get(i).getO_time())));
				oumList.add(oum);
			}
			map.put("obj", oumList);
			map.put("ResponseStatus", ResponseStatus.QUERYWASSUCCESS);
			map.put("msg", "查询成功");
		}else{
			map.put("ResponseStatus", ResponseStatus.STORENULL);
			map.put("msg", "该用户暂无订单信息");
		}
		return map;
	}

	@ResponseBody
	@RequestMapping("userDeleteOrder")
	public Map<String, Object> userDeleteOrder(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("o_id") String o_id){
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Integer delectBtoId = orderInformationService.delectBtoId(Integer.parseInt(o_id));
		if(delectBtoId>0){
			map.put("ResponseStatus", ResponseStatus.QUERYWASSUCCESS);
			map.put("msg", "订单删除成功");
		}else{
			map.put("ResponseStatus", ResponseStatus.STORENULL);
			map.put("msg", "订单删除失败......");
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping("scanCodeToGenerateQrder")
	public Map<String, Object> scanCodeToGenerateQrder(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("s_id") String s_id,
			@RequestParam("auId") String auId,@RequestParam("priceTotal") String priceTotal) throws Exception{
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Store bysId = storeService.selectBysId(Integer.parseInt(s_id));
		AverageUser au = averageUserService.selectByauId(Integer.parseInt(auId));
		if(bysId!=null){
			String number = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			String up_time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			OrderInformation oi = new OrderInformation();
			OrderCommodityList oc = new OrderCommodityList();
			oi.setO_number(number);
			oi.setAuId(au.getAuId());
			oi.setAuBuyerNick(au.getAuBuyerNick());
			oi.setAuMobile(au.getAuMobile());
			oi.setAuAddress(au.getAuAddress());
			oi.setUse_limit_integral(au.getLimit_integral());
			oi.setUse_permanent_points(au.getPermanent_points());
			oi.setO_state("2");
			oi.setStore_consume_state("0");
			oi.setPay_state(0);
			oi.setS_name(bysId.getS_name());
			oi.setS_id(bysId.getS_id());
			oi.setS_mobile(bysId.getS_mobile());
			oi.setO_time(up_time);
			oi.setPayment_time("0");
			oi.setO_amount(Double.parseDouble(priceTotal));
			oi.setAmount_paid(0.0);
			oi.setErmanent_integral_bonus(0.0);
			oi.setTime_limited_integration(0.0);
			oi.setFull_integral_purchase(Double.parseDouble(priceTotal) / bysId.getIntegral_setting());
			Integer insertOrdeInfor = orderInformationService.insertOrdeInfor(oi);
			if(insertOrdeInfor>0){
				OrderInformation selectByauId = orderInformationService.selectByauId(Integer.parseInt(auId));
				if(selectByauId!=null){
					oc.setO_id(selectByauId.getO_id());
					oc.setC_name("虚拟商品");
					oc.setOcl_num(1);
					oc.setC_unit_price(1.0);
					oc.setC_introduce("此商品为在店铺中直接购买");
					oc.setC_first_figure(" ");
					orderCommodityListService.insertOC(oc);
					List<OrderCommodityList> selectByOid = orderCommodityListService.selectByOid(selectByauId.getO_id());
					if (selectByOid.size() > 0) {
						OrderDetailsMobileVo odm = new OrderDetailsMobileVo();
						odm.setO_id(selectByauId.getO_id());
						odm.setO_number(selectByauId.getO_number());
						odm.setS_name(selectByauId.getS_name());
						odm.setC_name(selectByOid.get(0).getC_name());
						odm.setOcl_num(selectByOid.get(0).getOcl_num());
						odm.setC_unit_price(selectByOid.get(0).getC_unit_price());
						odm.setC_first_figure(selectByOid.get(0).getC_first_figure());
						odm.setO_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new SimpleDateFormat("yyyyMMddHHmmss").parse(selectByauId.getO_time())));
						odm.setUse_limit_integral(selectByauId.getUse_limit_integral());
						odm.setUse_permanent_points(selectByauId.getUse_permanent_points());
						odm.setIntegral_available((int) (Double.parseDouble(priceTotal) / bysId.getIntegral_setting()));
//						Integer commodity_integral = (int) (selectByOid.get(0).getC_unit_price() / bysId.getIntegral_setting() * Double.parseDouble(priceTotal));
//						Integer con_comm_points = (int) (commodity_integral * bysId.getDeductible_percentage());
//						if (con_comm_points <= au.getPermanent_points()) {
//							odm.setTime_limited_integration(con_comm_points * bysId.getOffset_setting());
//							odm.setErmanent_integral_bonus(0.0);
//						} else {
//							odm.setTime_limited_integration(bysId.getOffset_setting() * selectByauId.getUse_permanent_points());
//							Integer differ = con_comm_points - selectByauId.getUse_permanent_points();
//							if (selectByauId.getUse_limit_integral() >= differ) {
//								odm.setErmanent_integral_bonus(bysId.getOffset_setting() * differ);
//							} else {
//								odm.setErmanent_integral_bonus(bysId.getOffset_setting() * selectByauId.getUse_limit_integral());
//							}
//						}
						// 折扣商品逻辑变更简化
						// 计算折扣后价格并取整
						Integer commodity_price = (int) (Double.parseDouble(priceTotal) * bysId.getDeductible_percentage());
						// 折扣后价格所需积分
						Integer con_comm_points = (int) (commodity_price / bysId.getIntegral_setting());
						if (con_comm_points <= au.getPermanent_points()) {
							// 用户积分多余，设置上限
							odm.setTime_limited_integration(commodity_price * bysId.getOffset_setting());

						} else {
							// 用户积分不足，全部抵扣
							odm.setTime_limited_integration( selectByauId.getUse_permanent_points() * bysId.getIntegral_setting() * bysId.getOffset_setting());
						}
						odm.setErmanent_integral_bonus(0.0);

						odm.setCommodity_integral((int) (Double.parseDouble(priceTotal) / bysId.getIntegral_setting()));
						odm.setAuAddress(au.getAuAddress());
						map.put("obj", odm);
						map.put("obj1", priceTotal);
						map.put("obj2", bysId.getOffset_setting());
						map.put("ResponseStatus", ResponseStatus.QUERYWASSUCCESS);
						map.put("msg", "订单生成成功!!");
					}else{
						map.put("ResponseStatus", ResponseStatus.STORENULL);
						map.put("msg", "订单生成失败......");
					}
				}else{
					map.put("ResponseStatus", ResponseStatus.STORENULL);
					map.put("msg", "订单商品添加失败......");
				}
			}else{
				map.put("ResponseStatus", ResponseStatus.STORENULL);
				map.put("msg", "初始订单生成失败......");
			}
		}else{
			map.put("ResponseStatus", ResponseStatus.STORENULL);
			map.put("msg", "暂无该店铺信息......");
		}
		return map;
	}

	@ResponseBody
	@RequestMapping("modifyOrderAddress")
	public Map<String, Object> modifyOrderAddress(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("o_id") String o_id,
			@RequestParam("auAddress") String auAddress) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Map<String, Object> m = new LinkedHashMap<String, Object>();
		m.put("o_id", Integer.parseInt(o_id));
		m.put("auAddress", auAddress);
		Integer address = orderInformationService.modifyOrderAddress(m);
		if (address > 0) {
			map.put("ResponseStatus", ResponseStatus.QUERYWASSUCCESS);
			map.put("msg", "收货地址添加成功......");
		} else {
			map.put("ResponseStatus", ResponseStatus.STORENULL);
			map.put("msg", "收货地址添加失败......");
		}
		return map;
	}
	
	/***
	 * 修改未进店的订单状态
	 */
	@ResponseBody
	@RequestMapping("modifyStoreUnconsumeState")
	public Map<String, Object> modifyStoreUnconsumeState(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("o_id") Integer o_id) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Map<String, Object> param = new LinkedHashMap<String, Object>();
		param.put("o_id",o_id);
		param.put("store_unconsume_state","1");
		Integer value = orderInformationService.updateOrderInfo(param);
		if (value > 0) {
			map.put("ResponseStatus", ResponseStatus.QUERYWASSUCCESS);
			map.put("msg", "操作成功");
		} else {
			map.put("ResponseStatus", ResponseStatus.STORENULL);
			map.put("msg", "操作失败");
		}
		return map;
	}

}
