package com.wingfac.MaitreyaRim.controller;

import java.lang.reflect.Method;
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
import org.springframework.web.multipart.MultipartFile;

import com.wingfac.MaitreyaRim.po.Commodity;
import com.wingfac.MaitreyaRim.po.Store;
import com.wingfac.MaitreyaRim.service.impl.ComRecommendationService;
import com.wingfac.MaitreyaRim.service.impl.CommodityService;
import com.wingfac.MaitreyaRim.service.impl.StoreService;
import com.wingfac.MaitreyaRim.util.Constants;
import com.wingfac.MaitreyaRim.util.FileUpload;
import com.wingfac.MaitreyaRim.util.ResponseStatus;

@Controller
@RequestMapping("Commodity")
public class CommodityController {

	@Autowired
	private CommodityService commodityService;
	@Autowired
	private StoreService storeService;
	@Autowired
	private ComRecommendationService comRecommendationService;

	@RequestMapping("selectBysIdFen")
	public String selectBysIdFen(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap,
			@RequestParam("s_id") String s_id,
			@RequestParam(value = "curPage", defaultValue = "0", required = false) Integer curPage) {
		if (!"".equals(s_id)) {
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			Integer pstart = curPage * Constants.sun12;
			map.put("psize", Constants.sun12);
			map.put("pstart", pstart);
			map.put("s_id", Integer.parseInt(s_id));
			List<Commodity> selectBySiD = commodityService
					.selectBySiD(Integer.parseInt(s_id));
			if (selectBySiD.size() > 0) {
				List<Commodity> selectAll = commodityService.selectAll(map);
				if (selectAll.size() > 0) {
					Integer pages = selectBySiD.size() / Constants.sun12;
					if (selectBySiD.size() % Constants.sun12 != 0) {
						pages = pages + 1;
					}
					modelMap.put("pages", pages);
					modelMap.put("list", selectAll);
					modelMap.put("curPage", curPage);
					modelMap.put("tiaojian", true);
				}
			}
		}
		return "shop/shopgood";
	}

	@RequestMapping("selectLikeFen")
	public String selectLikeFen(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap,
			@RequestParam("s_id") String s_id,
			@RequestParam("searchVal") String searchVal,
			@RequestParam(value = "curPage", defaultValue = "0", required = false) Integer curPage) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Map<String, Object> m = new LinkedHashMap<String, Object>();
		Integer pstart = curPage * Constants.sun12;
		map.put("psize", Constants.sun12);
		map.put("pstart", pstart);
		map.put("s_id", Integer.parseInt(s_id));
		map.put("c_name", searchVal);
		m.put("s_id", Integer.parseInt(s_id));
		m.put("c_name", searchVal);
		List<Commodity> selectBySearchVal = commodityService
				.selectBySearchVal(m);
		if (selectBySearchVal.size() > 0) {
			List<Commodity> selectBySearchValAll = commodityService
					.selectBySearchValAll(map);
			if (selectBySearchValAll.size() > 0) {
				Integer pages = selectBySearchVal.size() / Constants.sun12;
				if (selectBySearchVal.size() % Constants.sun12 != 0) {
					pages = pages + 1;
				}
				modelMap.put("pages", pages);
				modelMap.put("list", selectBySearchValAll);
				modelMap.put("curPage", curPage);
				modelMap.put("tiaojian", false);
				modelMap.put("searchVal", searchVal);
			}
		}
		return "shop/shopgood";
	}

	@ResponseBody
	@RequestMapping("deleteCommodity")
	public Map<String, Object> deleteCommodity(String checkedId) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Integer delectCommodity = commodityService.delectCommodity(checkedId);
		String[] cId = checkedId.split(",");
		for (int i = 0; i < cId.length; i++) {
			comRecommendationService.deleCid(Integer.parseInt(cId[i]));
		}
		if (delectCommodity > 0) {
			map.put("msg", "1");
		} else {
			map.put("msg", "2");
		}
		return map;
	}

	@ResponseBody
	@RequestMapping("selectBysId")
	public Map<String, Object> selectBysId(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("s_id") String s_id) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Store selectBysId = storeService.selectBysId(Integer.parseInt(s_id));
		if (selectBysId != null) {
			List<Commodity> selectBySiD = commodityService
					.selectBySiD(Integer.parseInt(s_id));
			// 五折商品区分WLK 20190507
/*			Commodity selectDisBySiD = commodityService
					.selectDisBySiD(Integer.parseInt(s_id));*/
			
			List<Commodity> selectDisBySiD = commodityService
					.selectDisBySiDChange(Integer.parseInt(s_id));
			// 为IOS端组合列表
			List<Commodity> selectAllBySiD = new ArrayList<>();
			if (selectDisBySiD != null) {
				selectAllBySiD.addAll(selectDisBySiD);
			}
			for (int i = 0; i < selectBySiD.size(); i++) {
				selectAllBySiD.add(selectBySiD.get(i));
			}
		
			if (selectAllBySiD.size() > 0) {
				// 赋值返回
				map.put("selectBysId", selectBysId);
				map.put("selectBySiD", selectBySiD);
				map.put("selectDisBySiD", selectDisBySiD);
				map.put("selectAllBySiD", selectAllBySiD);
				map.put("ResponseStatus", ResponseStatus.QUERYWASSUCCESS);
				map.put("msg", ResponseStatus.QUERYWASSUCCESS_CN_MSG);
			} else {
				map.put("obj1", selectBysId.getFirst_picture());
				map.put("ResponseStatus", ResponseStatus.COMMODITYFAIL);
				map.put("msg", ResponseStatus.COMMODITYFAIL_CN_MSG);
			}

		} else {
			map.put("ResponseStatus", ResponseStatus.AUDITINGGOODSFAIL4);
			map.put("msg", ResponseStatus.AUDITINGGOODSFAIL_CN_MSG4);
		}
		return map;
	}

	@ResponseBody
	@RequestMapping("selectByauId")
	public Map<String, Object> selectByauId(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("auId") String auId,
			@RequestParam("pstart") String pstart,
			@RequestParam("psize") String psize) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Map<String, Object> m = new LinkedHashMap<String, Object>();
		Integer pstarts = Integer.parseInt(pstart);
		Integer psizes = Integer.parseInt(psize);
		m.put("pstart", pstarts * psizes);
		m.put("psize", psizes);
		Store selectByauId = storeService.selectByauId(Integer.parseInt(auId));
		if (selectByauId != null) {
			List<Commodity> selectBySiD = commodityService
					.selectBySiD(selectByauId.getS_id());
			if (selectBySiD.size() > 0) {
				m.put("s_id", selectByauId.getS_id());
				List<Commodity> selectAll = commodityService.selectAll(m);
				if (selectAll.size() > 0) {
					map.put("ResponseStatus", ResponseStatus.QUERYWASSUCCESS);
					map.put("msg", ResponseStatus.QUERYWASSUCCESS_CN_MSG);
					map.put("selectAll", selectAll);
					map.put("obj1", selectByauId.getFirst_picture());
				} else {
					map.put("ResponseStatus", ResponseStatus.COMMODITYFAIL1);
					map.put("msg", ResponseStatus.COMMODITYFAIL_CN_MSG1);
				}
			} else {
				if (selectByauId.getFirst_picture() == " ") {
					map.put("obj1", " ");
				} else {
					map.put("obj1", selectByauId.getFirst_picture());
				}
				map.put("ResponseStatus", ResponseStatus.COMMODITYFAIL);
				map.put("msg", ResponseStatus.COMMODITYFAIL_CN_MSG);
			}
		} else {
			map.put("ResponseStatus", ResponseStatus.AUDITINGGOODSFAIL3);
			map.put("msg", ResponseStatus.AUDITINGGOODSFAIL_CN_MSG3);
		}
		return map;
	}

	@ResponseBody
	@RequestMapping("selectBycId")
	public Map<String, Object> selectBycId(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("c_id") String c_id) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Commodity selectByCid = commodityService
				.selectByCid(Integer.parseInt(c_id));
		if (selectByCid != null) {
			map.put("ResponseStatus", ResponseStatus.QUERYWASSUCCESS);
			map.put("msg", ResponseStatus.QUERYWASSUCCESS_CN_MSG);
			map.put("selectByCid", selectByCid);
		} else {
			map.put("ResponseStatus", ResponseStatus.COMMODITYFAIL2);
			map.put("msg", ResponseStatus.COMMODITYFAIL_CN_MSG2);
		}
		return map;
	}

	@ResponseBody
	@RequestMapping("updateComm")
	public Map<String, Object> updateComm(HttpServletRequest request,
			HttpServletResponse response, Commodity commodity,
			@RequestParam("c_id") String c_id,
			@RequestParam("c_unit_price") String c_unit_price,
			@RequestParam("c_name") String c_name,
			@RequestParam("c_introduce") String c_introduce,
			@RequestParam("discount_type") String discount_type,
			@RequestParam(value = "file1", required = false) MultipartFile file1,
			@RequestParam(value = "files", required = false) MultipartFile[] files)
					throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		String s = request.getSession().getServletContext().getRealPath("/");
		String uploadPicture1 = "";

		Commodity selectByCid = commodityService
				.selectByCid(Integer.parseInt(c_id));
		commodity.setS_id(selectByCid.getS_id());
		commodity.setC_id(Integer.parseInt(c_id));
		commodity.setC_unit_price(Double.parseDouble(c_unit_price));
		commodity.setC_name(c_name);
		commodity.setC_introduce(c_introduce);
		commodity.setDiscount_type(discount_type);

		if (file1 != null) {
			uploadPicture1 = FileUpload.commodityPictureUpload(file1,
					s.substring(0, s.length() - 12));
			if (uploadPicture1 != "fail") {
				commodity.setC_first_figure(uploadPicture1);
			} else {
				map.put("ResponseStatus", ResponseStatus.AUDITINGGOODSFAIL1);
				map.put("msg", ResponseStatus.AUDITINGGOODSFAIL_CN_MSG1);
				return map;
			}
		} else {
			commodity.setC_first_figure(selectByCid.getC_first_figure());
		}
		if (files != null) {
			String[] sun = { "one", "two", "three", "four", "five", "six",
					"seven", "eight" };
			int i = 0;
			for (MultipartFile file : files) {
				String path = FileUpload.commodityPictureUpload(file,
						s.substring(0, s.length() - 12));
				Class<? extends Commodity> clazz = commodity.getClass();
				Method m = clazz.getMethod("setC_picture_" + sun[i],
						String.class);
				m.invoke(commodity, path);
				i++;
			}

		}
		// 五折商品只有一个限制
		if (discount_type.equals("1")) {
			commodityService.updateCommodityDiscount(commodity);
		}
		// 更新商品信息
		Integer updateCommodity = commodityService
				.updateCommodity(commodity);
		if (updateCommodity > 0) {
			map.put("ResponseStatus", ResponseStatus.COMMODITYSUCCESS);
			map.put("msg", ResponseStatus.COMMODITYSUCCESS_CN_MSG);
		} else {
			map.put("ResponseStatus", ResponseStatus.COMMODITYFAIL3);
			map.put("msg", ResponseStatus.COMMODITYFAIL_CN_MSG3);
		}
		return map;
	}

	@ResponseBody
	@RequestMapping("selectLikeCom")
	public Map<String, Object> selectLikeCom(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("c_name") String c_name,
			@RequestParam("pstart") String pstart,
			@RequestParam("psize") String psize) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Map<String, Object> m = new LinkedHashMap<String, Object>();
		Integer pstarts = Integer.parseInt(pstart);
		Integer psizes = Integer.parseInt(psize);
		m.put("pstart", pstarts * psizes);
		m.put("psize", psizes);
		m.put("c_name", c_name);
		List<Commodity> selectBySearchMobile = commodityService
				.selectBySearchMobile(m);
		if (selectBySearchMobile.size() > 0) {
			map.put("obj", selectBySearchMobile);
			map.put("ResponseStatus", ResponseStatus.QUERYWASSUCCESS);
			map.put("msg", ResponseStatus.QUERYWASSUCCESS_CN_MSG);
		} else {
			map.put("ResponseStatus", ResponseStatus.STORENULL);
			map.put("msg", ResponseStatus.STORENULL_CN_MSG);
		}
		return map;
	}

}
