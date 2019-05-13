package com.wingfac.MaitreyaRim.mapper;

import java.util.List;
import java.util.Map;

import com.wingfac.MaitreyaRim.po.OrderInformation;

public interface OrderInformationMapper {

	public Integer insertOrdeInfor(OrderInformation orderInformation);

	public List<OrderInformation> selectAllOrdeInfor(Integer sId);

	public List<OrderInformation> selectAllOrdInforFen(Map<String, Object> map);

	public List<OrderInformation> selectLke(Map<String, Object> map);

	public List<OrderInformation> selectLkeFen(Map<String, Object> map);

	public OrderInformation selectByauId(Integer auId);

	public OrderInformation selectByOid(Integer o_id);

	public Integer updateOrdrInfer(Map<String, Object> map);

	public Integer updateOstate(Integer o_id);

	public Integer updateConsume(Integer o_id);

	public Integer delectOrdeInfor(String[] id);

	public Integer delectBysId(Integer s_id);

	public List<OrderInformation> queryOrderViaUserID(Integer auId);

	public Integer delectBtoId(Integer o_id);

	public Integer updateOrderTime(Map<String, Object> map);

	public Integer modifyOrderAddress(Map<String, Object> map);

	public Integer modifyOrderAllPurchase(Map<String, Object> map);
	
	public List<OrderInformation> viewAllorder();
	
	public Integer updateSaoma(Integer o_id);

	/**
	 * 更新订单信息
	 * @param map
	 * @return
	 */
	public Integer updateOrderInfo(Map<String,Object> map);
	
}
