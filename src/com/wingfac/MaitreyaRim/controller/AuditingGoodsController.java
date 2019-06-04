package com.wingfac.MaitreyaRim.controller;

import java.lang.reflect.Method;
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

import com.wingfac.MaitreyaRim.po.AuditingGoods;
import com.wingfac.MaitreyaRim.po.Commodity;
import com.wingfac.MaitreyaRim.po.Store;
import com.wingfac.MaitreyaRim.service.impl.AuditingGoodsService;
import com.wingfac.MaitreyaRim.service.impl.CommodityService;
import com.wingfac.MaitreyaRim.service.impl.StoreService;
import com.wingfac.MaitreyaRim.util.Constants;
import com.wingfac.MaitreyaRim.util.FileUpload;
import com.wingfac.MaitreyaRim.util.ResponseStatus;

@Controller
@RequestMapping("AuditingGoods")
public class AuditingGoodsController {

	@Autowired
	private AuditingGoodsService auditingGoodsService;
	@Autowired
	private StoreService storeService;
	@Autowired
	private CommodityService commodityService;

	@ResponseBody
	@RequestMapping("insAG")
	public Map<String, Object> insAG(HttpServletRequest request,
			HttpServletResponse response, AuditingGoods guditingGoods,
			@RequestParam("auId") String auId,
			@RequestParam("unit_price") String unit_price,
			@RequestParam("ag_name") String ag_name,
			@RequestParam("ag_introduce") String ag_introduce,
			@RequestParam("discount_type") String discount_type,
			@RequestParam(value = "file1", required = false) MultipartFile file1,
			@RequestParam(value = "files", required = false) MultipartFile[] files)
					throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Store byauId = storeService.selectByauId(Integer.parseInt(auId));
		if (byauId != null) {
			if (!"".equals(unit_price) && !"".equals(ag_name)&& !"".equals(ag_introduce)) {
				guditingGoods.setS_id(byauId.getS_id());
				guditingGoods.setS_name(byauId.getS_name());
				guditingGoods.setS_mobile(byauId.getS_mobile());
				guditingGoods.setUnit_price(Double.parseDouble(unit_price));
				guditingGoods.setAg_name(ag_name);
				guditingGoods.setAg_introduce(ag_introduce);
				guditingGoods.setDiscount_type(discount_type);
				String s = request.getSession().getServletContext()
						.getRealPath("/");
				String uploadPicture1 = "";
				if (file1 != null) {
					uploadPicture1 = FileUpload.commodityPictureUpload(file1,
							s.substring(0, s.length() - 12));
					if (uploadPicture1 != "fail") {
						guditingGoods.setFirst_figure(uploadPicture1);
					} else {
						map.put("ResponseStatus",
								ResponseStatus.AUDITINGGOODSFAIL1);
						map.put("msg",
								ResponseStatus.AUDITINGGOODSFAIL_CN_MSG1);
					}
				} else {
					guditingGoods.setFirst_figure(" ");
				}
				if (files != null && files.length > 0) {
					String[] sun = { "one", "two", "three", "four", "five","six", "seven", "eight" };
					int i = 0;
					for (MultipartFile file : files) {
						String path = FileUpload.commodityPictureUpload(file, s.substring(0, s.length() - 12));
						Class<? extends AuditingGoods> clazz = guditingGoods.getClass();
						Method m = clazz.getMethod("setPicture_" + sun[i],String.class);
						m.invoke(guditingGoods, path);
						i++;
						if(i>=sun.length){
							break;
						}
					}
				}
				
				Integer insertAg = auditingGoodsService.insertAg(guditingGoods);
				if (insertAg > 0) {
					map.put("ResponseStatus",ResponseStatus.AUDITINGGOODSSUCCESS);
					map.put("msg",ResponseStatus.AUDITINGGOODSSUCCESS_CN_MSG);
				} else {
					map.put("ResponseStatus",ResponseStatus.AUDITINGGOODSFAIL);
					map.put("msg", ResponseStatus.AUDITINGGOODSFAIL_CN_MSG);
				}
			} else {
				map.put("ResponseStatus", ResponseStatus.AUDITINGGOODSFAIL2);
				map.put("msg", ResponseStatus.AUDITINGGOODSFAIL_CN_MSG2);
			}
		} else {
			map.put("ResponseStatus", ResponseStatus.AUDITINGGOODSFAIL3);
			map.put("msg", ResponseStatus.AUDITINGGOODSFAIL_CN_MSG3);
		}
		return map;
	}

	@RequestMapping("selectAuditGoods")
	public String selectAuditGoods(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap,
			@RequestParam(value = "curPage", defaultValue = "0", required = false) Integer curPage) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Integer pstart = curPage * Constants.sun;
		map.put("psize", Constants.sun);
		map.put("pstart", pstart);
		List<AuditingGoods> selectAllPageTotal = auditingGoodsService
				.selectAllPageTotal();
		if (selectAllPageTotal.size() > 0) {
			List<AuditingGoods> selectAllPage = auditingGoodsService
					.selectAllPage(map);
			if (selectAllPage.size() > 0) {
				Integer pages = selectAllPageTotal.size() / Constants.sun;
				if (selectAllPageTotal.size() % Constants.sun != 0) {
					pages = pages + 1;
				}
				modelMap.put("pages", pages);
				modelMap.put("list", selectAllPage);
				modelMap.put("curPage", curPage);
				modelMap.put("tiaojian", true);
			}
		}
		return "auditing/auditing";
	}

	@RequestMapping("SelectLike")
	public String SelectLike(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap,
			@RequestParam("searchVal") String searchVal,
			@RequestParam(value = "curPage", defaultValue = "0", required = false) Integer curPage) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Integer pstart = curPage * Constants.sun;
		map.put("ag_name", searchVal);
		map.put("psize", Constants.sun);
		map.put("pstart", pstart);
		List<AuditingGoods> selectLike = auditingGoodsService
				.selectLike(searchVal);
		if (selectLike.size() > 0) {
			List<AuditingGoods> selectByLisk = auditingGoodsService
					.selectByLisk(map);
			if (selectByLisk.size() > 0) {
				Integer pages = selectLike.size() / Constants.sun;
				if (selectLike.size() % Constants.sun != 0) {
					pages = pages + 1;
				}
				modelMap.put("pages", pages);
				modelMap.put("list", selectByLisk);
				modelMap.put("curPage", curPage);
				modelMap.put("tiaojian", false);
				modelMap.put("searchVal", searchVal);
			}
		}
		return "auditing/auditing";
	}

	@ResponseBody
	@RequestMapping("delectAuditGoods")
	public Map<String, Object> delectAuditGoods(String checkedId) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		String[] agIds = checkedId.split(",");
		Integer agId[] = new Integer[agIds.length];
		for (int i = 0; i < agIds.length; i++) {
			agId[i] = Integer.parseInt(agIds[i]);
		}
		// 以下为审核通过后向店铺商品表中添加数据
		Commodity c = new Commodity();
		for (int i = 0; i < agId.length; i++) {
			AuditingGoods selectByagId = auditingGoodsService
					.selectByagId(agId[i]);
			if (selectByagId != null) {
				c.setS_id(selectByagId.getS_id());
				c.setC_first_figure(selectByagId.getFirst_figure());
				c.setC_unit_price(selectByagId.getUnit_price());
				c.setC_name(selectByagId.getAg_name());
				c.setC_introduce(selectByagId.getAg_introduce());
				c.setDiscount_type(selectByagId.getDiscount_type());

				if (selectByagId.getPicture_one() != null) {
					c.setC_picture_one(selectByagId.getPicture_one());
				} else {
					c.setC_picture_one(" ");
				}
				if (null != selectByagId.getPicture_two()) {
					c.setC_picture_two(selectByagId.getPicture_two());
				} else {
					c.setC_picture_two(" ");
				}
				if (selectByagId.getPicture_three() != null) {
					c.setC_picture_three(selectByagId.getPicture_three());
				} else {
					c.setC_picture_three(" ");
				}
				if (selectByagId.getPicture_four() != null) {
					c.setC_picture_four(selectByagId.getPicture_four());
				} else {
					c.setC_picture_four(" ");
				}
				if (selectByagId.getPicture_five() != null) {
					c.setC_picture_five(selectByagId.getPicture_five());
				} else {
					c.setC_picture_five(" ");
				}
				if (selectByagId.getPicture_six() != null) {
					c.setC_picture_six(selectByagId.getPicture_six());
				} else {
					c.setC_picture_six(" ");
				}
				if (selectByagId.getPicture_seven() != null) {
					c.setC_picture_seven(selectByagId.getPicture_seven());
				} else {
					c.setC_picture_seven(" ");
				}
				if (selectByagId.getPicture_eight() != null) {
					c.setC_picture_eight(selectByagId.getPicture_eight());
				} else {
					c.setC_picture_eight(" ");
				}

				// 五折商品只有一个限制
				if (selectByagId.getDiscount_type().equals("1")) {
					commodityService.updateCommodityDiscount(c);
				}
				// 插入商品信息
				commodityService.insertCommodity(c);
			} else {
				map.put("msg", "2");
			}
		}
		Integer delectAg = auditingGoodsService.delectAg(checkedId);
		if (delectAg > 0) {
			map.put("msg", "1");
		} else {
			map.put("msg", "2");
		}
		return map;
	}

	@ResponseBody
	@RequestMapping("deleteAg")
	public Map<String, Object> deleteAg(String checkedId) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Integer delectAg = auditingGoodsService.delectAg(checkedId);
		if (delectAg > 0) {
			map.put("msg", "1");
		} else {
			map.put("msg", "2");
		}
		return map;
	}

}
