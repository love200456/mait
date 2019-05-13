package com.wingfac.MaitreyaRim.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.wingfac.MaitreyaRim.mapper.OrderInformationMapper;
import com.wingfac.MaitreyaRim.po.OrderInformation;

public class OrderInformationService {

	@Autowired
	private OrderInformationMapper orderInformationMapper;

	public Integer insertOrdeInfor(OrderInformation orderInformation) {
		return orderInformationMapper.insertOrdeInfor(orderInformation);
	}

	public List<OrderInformation> selectAllOrdeInfor(Integer sId) {
		return orderInformationMapper.selectAllOrdeInfor(sId);
	}

	public List<OrderInformation> selectAllOrdInforFen(Map<String, Object> map) {
		return orderInformationMapper.selectAllOrdInforFen(map);
	}

	public List<OrderInformation> selectLke(Map<String, Object> map) {
		return orderInformationMapper.selectLke(map);
	}

	public List<OrderInformation> selectLkeFen(Map<String, Object> map) {
		return orderInformationMapper.selectLkeFen(map);
	}

	public OrderInformation selectByauId(Integer auId) {
		return orderInformationMapper.selectByauId(auId);
	}

	public OrderInformation selectByOid(Integer o_id) {
		return orderInformationMapper.selectByOid(o_id);
	}

	public Integer updateOrdrInfer(Map<String, Object> map) {
		return orderInformationMapper.updateOrdrInfer(map);
	}

	public Integer updateOstate(Integer o_id) {
		return orderInformationMapper.updateOstate(o_id);
	}

	public Integer updateConsume(Integer o_id) {
		return orderInformationMapper.updateConsume(o_id);
	}

	public Integer delectOrdeInfor(String id) {
		return orderInformationMapper.delectOrdeInfor(id.split(","));
	}

	public Integer delectBysId(Integer s_id) {
		return orderInformationMapper.delectBysId(s_id);
	}

	public List<OrderInformation> queryOrderViaUserID(Integer auId) {
		return orderInformationMapper.queryOrderViaUserID(auId);
	}

	public Integer delectBtoId(Integer o_id) {
		return orderInformationMapper.delectBtoId(o_id);
	}

	public Integer updateOrderTime(Map<String, Object> map) {
		return orderInformationMapper.updateOrderTime(map);
	}

	public Integer modifyOrderAddress(Map<String, Object> map) {
		return orderInformationMapper.modifyOrderAddress(map);
	}

	public Integer modifyOrderAllPurchase(Map<String, Object> map) {
		return orderInformationMapper.modifyOrderAllPurchase(map);
	}
	
	public List<OrderInformation> viewAllorder() {
		return orderInformationMapper.viewAllorder();
	}

	public Integer updateSaoma(Integer o_id) {
		return orderInformationMapper.updateSaoma(o_id);
	}
	
	/**
	 * 更新订单信息
	 * @param map
	 * @return
	 */
	public Integer updateOrderInfo(Map<String,Object> map){
		return orderInformationMapper.updateOrderInfo(map);
	}
	
}
