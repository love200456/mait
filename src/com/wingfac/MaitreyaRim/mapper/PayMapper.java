package com.wingfac.MaitreyaRim.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface PayMapper {

	Map<String, Object> getOrderById(String oId);

	Map<String, Object> getStoreById(String sId);

	void updateOrderConsumeState(@Param("oId")String oId, @Param("store_consume_state")int store_consume_state, @Param("pay_state")int pay_state, 
			@Param("ermanent_integral_bonus")double ermanent_integral_bonus,
			@Param("time_limited_integration")double time_limited_integration, @Param("amount_paid")double amount_paid);

	void updateUserIntegration(@Param("auId")String auId, @Param("permanent_points")double permanent_points, @Param("limit_integral")double limit_integral);

	Map<String, Object> getUserById(String auId);

	void addTransactionRecord(Map<String, Object> param);

	void updateOrderPayAndState(@Param("oId")String oId, @Param("store_consume_state")int store_consume_state,@Param("payState") int payState);


}
