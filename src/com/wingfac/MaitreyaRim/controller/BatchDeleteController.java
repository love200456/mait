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

import com.wingfac.MaitreyaRim.po.AverageUser;
import com.wingfac.MaitreyaRim.po.Commodity;
import com.wingfac.MaitreyaRim.po.LimInteStatisties;
import com.wingfac.MaitreyaRim.po.OrderInformation;
import com.wingfac.MaitreyaRim.po.PerInteStatistics;
import com.wingfac.MaitreyaRim.po.ShoppingCartVo;
import com.wingfac.MaitreyaRim.po.Store;
import com.wingfac.MaitreyaRim.po.UserMessage;
import com.wingfac.MaitreyaRim.po.UserStoreFollow;
import com.wingfac.MaitreyaRim.service.impl.AdvertisingCategoryService;
import com.wingfac.MaitreyaRim.service.impl.AverageUserService;
import com.wingfac.MaitreyaRim.service.impl.ComRecommendationService;
import com.wingfac.MaitreyaRim.service.impl.CommodityService;
import com.wingfac.MaitreyaRim.service.impl.LimInteStatistiesService;
import com.wingfac.MaitreyaRim.service.impl.OrderCommodityListService;
import com.wingfac.MaitreyaRim.service.impl.OrderInformationService;
import com.wingfac.MaitreyaRim.service.impl.PerInteStatisticsService;
import com.wingfac.MaitreyaRim.service.impl.ShoppingCartVoService;
import com.wingfac.MaitreyaRim.service.impl.StoreService;
import com.wingfac.MaitreyaRim.service.impl.UserMessageService;
import com.wingfac.MaitreyaRim.service.impl.UserStoreFollowService;

@Controller
@RequestMapping("BatchDelete")
public class BatchDeleteController {

	@Autowired
	private AverageUserService averageUserService;
	@Autowired
	private StoreService storeService;
	@Autowired
	private CommodityService commodityService;
	@Autowired
	private PerInteStatisticsService perInteStatisticsService;
	@Autowired
	private LimInteStatistiesService limInteStatistiesService;
	@Autowired
	private UserMessageService userMessageService;
	@Autowired
	private UserStoreFollowService userStoreFollowService;
	@Autowired
	private ShoppingCartVoService shoppingCartVoService;
	@Autowired
	private OrderInformationService orderInformationService;
	@Autowired
	private OrderCommodityListService orderCommodityListService;
	@Autowired
	private AdvertisingCategoryService advertisingCategoryService;
	@Autowired
	private ComRecommendationService comRecommendationService;

	@ResponseBody
	@RequestMapping("delectauId")
	public Map<String, Object> delectauId(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("checkedId") String checkedId) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		String[] auIds = checkedId.split(",");
		Integer auId[] = new Integer[auIds.length];
		for (int i = 0; i < auIds.length; i++) {
			auId[i] = Integer.parseInt(auIds[i]);
		}
		AverageUser selectByauId = null;
		Store st = null;
		List<OrderInformation> selectAllOrdeInfor = null;
		Integer deleteUaer = 0;
		for (int i = 0; i < auId.length; i++) {
			if (auId[i] != 0) {
				selectByauId = averageUserService.selectByauId(auId[i]);
				if (selectByauId.getAuIdentity().equals("1")) {
					List<PerInteStatistics> selectByauId2 = perInteStatisticsService.selectByauId(auId[i]);
					if (selectByauId2.size() > 0) {
						perInteStatisticsService.delectInte(auId[i]);
					}
					List<LimInteStatisties> selectByauId3 = limInteStatistiesService.selectByauId(auId[i]);
					if (selectByauId3.size() > 0) {
						limInteStatistiesService.delectByauId(auId[i]);
					}
					List<UserMessage> selectByauId4 = userMessageService.selectByauId(auId[i]);
					if (selectByauId4.size() > 0) {
						userMessageService.delectByauId(auId[i]);
					}
					List<UserStoreFollow> selByauId = userStoreFollowService.selByauId(auId[i]);
					if (selByauId.size() > 0) {
						userStoreFollowService.delectByauId(auId[i]);
					}
					List<ShoppingCartVo> selByauId2 = shoppingCartVoService.selByauId(auId[i]);
					if (selByauId2.size() > 0) {
						shoppingCartVoService.delevtByauId(auId[i]);
					}
					deleteUaer += averageUserService.deleteUaer(auId[i]);
				} else {
					st = storeService.selectByauId(auId[i]);
					if (st != null) {
						List<PerInteStatistics> selectByauId2 = perInteStatisticsService.selectByauId(auId[i]);
						if (selectByauId2.size() > 0) {
							perInteStatisticsService.delectInte(auId[i]);
						}
						List<LimInteStatisties> selectByauId3 = limInteStatistiesService.selectByauId(auId[i]);
						if (selectByauId3.size() > 0) {
							limInteStatistiesService.delectByauId(auId[i]);
						}
						List<UserMessage> selectByauId4 = userMessageService.selectByauId(auId[i]);
						if (selectByauId4.size() > 0) {
							userMessageService.delectByauId(auId[i]);
						}
						List<UserStoreFollow> selByauId = userStoreFollowService.selByauId(auId[i]);
						if (selByauId.size() > 0) {
							userStoreFollowService.delectByauId(auId[i]);
						}
						List<ShoppingCartVo> selByauId2 = shoppingCartVoService.selByauId(auId[i]);
						if (selByauId2.size() > 0) {
							shoppingCartVoService.delevtByauId(auId[i]);
						}
						List<Commodity> selectBySiD = commodityService.selectBySiD(st.getS_id());
						if (selectBySiD.size() > 0) {
							commodityService.delectBysId(st.getS_id());
						}
						selectAllOrdeInfor = orderInformationService.selectAllOrdeInfor(st.getS_id());
						if (selectAllOrdeInfor.size() > 0) {
							orderInformationService.delectBysId(st.getS_id());
							for (int j = 0; j < selectAllOrdeInfor.size(); j++) {
								orderCommodityListService.delectOC(selectAllOrdeInfor.get(j).getO_id());
							}
						}
						advertisingCategoryService.batchDelete(st.getS_id());
						comRecommendationService.batchDelete(st.getS_id());
						storeService.delectBysId(st.getS_id());
						deleteUaer += averageUserService.deleteUaer(auId[i]);
					}
				}
			}
			if (deleteUaer > 0) {
				map.put("msg", "1");
			} else {
				map.put("msg", "2");
			}
		}
		return map;
	}

	@ResponseBody
	@RequestMapping("delectSto")
	public Map<String, Object> delectSto(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("checkedId") String checkedId) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		String[] sIds = checkedId.split(",");
		Integer sId[] = new Integer[sIds.length];
		for (int i = 0; i < sIds.length; i++) {
			sId[i] = Integer.parseInt(sIds[i]);
		}
		List<OrderInformation> selectAllOrdeInfor = null;
		Integer deleteUaer = 0;
		for (int i = 0; i < sId.length; i++) {
			if (sId[i] != 0) {
				Store selectBysId = storeService.selectBysId(sId[i]);
				if (selectBysId != null) {
					List<PerInteStatistics> selectByauId2 = perInteStatisticsService.selectByauId(selectBysId.getAuId());
					if (selectByauId2.size() > 0) {
						perInteStatisticsService.delectInte(selectBysId.getAuId());
					}
					List<LimInteStatisties> selectByauId3 = limInteStatistiesService.selectByauId(selectBysId.getAuId());
					if (selectByauId3.size() > 0) {
						limInteStatistiesService.delectByauId(selectBysId.getAuId());
					}
					List<UserMessage> selectByauId4 = userMessageService.selectByauId(selectBysId.getAuId());
					if (selectByauId4.size() > 0) {
						userMessageService.delectByauId(selectBysId.getAuId());
					}
					List<UserStoreFollow> selByauId = userStoreFollowService.selByauId(selectBysId.getAuId());
					if (selByauId.size() > 0) {
						userStoreFollowService.delectByauId(selectBysId.getAuId());
					}
					List<ShoppingCartVo> selByauId2 = shoppingCartVoService.selByauId(selectBysId.getAuId());
					if (selByauId2.size() > 0) {
						shoppingCartVoService.delevtByauId(selectBysId.getAuId());
					}
					List<Commodity> selectBySiD = commodityService.selectBySiD(selectBysId.getS_id());
					if (selectBySiD.size() > 0) {
						commodityService.delectBysId(selectBysId.getS_id());
					}
					selectAllOrdeInfor = orderInformationService.selectAllOrdeInfor(selectBysId.getS_id());
					if (selectAllOrdeInfor.size() > 0) {
						orderInformationService.delectBysId(selectBysId.getS_id());
						for (int j = 0; j < selectAllOrdeInfor.size(); j++) {
							orderCommodityListService.delectOC(selectAllOrdeInfor.get(j).getO_id());
						}
					}
					advertisingCategoryService.batchDelete(selectBysId.getS_id());
					comRecommendationService.batchDelete(selectBysId.getS_id());
					storeService.delectBysId(selectBysId.getS_id());
					deleteUaer += averageUserService.deleteUaer(selectBysId.getAuId());
				}
			}
			if (deleteUaer > 0) {
				map.put("msg", "1");
			} else {
				map.put("msg", "2");
			}
		}
		return map;
	}

}
