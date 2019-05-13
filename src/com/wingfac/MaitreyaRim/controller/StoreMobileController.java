package com.wingfac.MaitreyaRim.controller;

import java.util.ArrayList;
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
import org.springframework.web.multipart.MultipartFile;

import com.wingfac.MaitreyaRim.mapper.CommodityMapper;
import com.wingfac.MaitreyaRim.po.Commodity;
import com.wingfac.MaitreyaRim.po.Store;
import com.wingfac.MaitreyaRim.service.impl.StoreService;
import com.wingfac.MaitreyaRim.util.Constants;
import com.wingfac.MaitreyaRim.util.FileUpload;
import com.wingfac.MaitreyaRim.util.ResponseStatus;

@Controller
@RequestMapping("StoreMobile")
public class StoreMobileController {

	@Autowired
	private StoreService storeService;
	@Autowired
	private CommodityMapper commodityMapper;

	@ResponseBody
	@RequestMapping("insertStore")
	public Map<String, Object> insertStore(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("auId") String auId,
			@RequestParam("s_name") String s_name,
			@RequestParam("open_time") String open_time,
			@RequestParam("s_mobile") String s_mobile,
			@RequestParam("s_address") String s_address,
			@RequestParam("describe") String describe,
			@RequestParam("s_physical") String s_physical,
			@RequestParam("s_longitude") String s_longitude,
			@RequestParam("s_latitude") String s_latitude,
			@RequestParam("is_open") String is_open,
			@RequestParam("working_hours") String working_hours,
			@RequestParam("deductible_percentage") Double deductible_percentage,
			@RequestParam(value = "file1", required = false) MultipartFile file1,
			@RequestParam(value = "file2", required = false) MultipartFile file2)
			throws Exception {
		Map<String, Object> m = new LinkedHashMap<String, Object>();
		if (!"".equals(s_name) && !"".equals(open_time) && !"".equals(s_mobile)
				&& !"".equals(s_address) && !"".equals(describe)
				&& !"".equals(s_physical) && !"".equals(s_longitude)
				&& !"".equals(s_latitude)) {
			Store selectByauId = storeService.selectByauId(Integer.parseInt(auId));
			if (selectByauId != null) {
				m.put("ResponseStatus", ResponseStatus.STOREFAIL);// '1'
				m.put("msg", "已存在店铺无法新建.....");
			} else {
				Map<String, Object> map = new LinkedHashMap<String, Object>();
				map.put("auId", Integer.parseInt(auId));
				map.put("toc_id", "0");
				map.put("ttc_id", "0");
				map.put("s_name", s_name);
				map.put("first_picture", " ");
				map.put("open_time", open_time);
				map.put("s_mobile", s_mobile);
				map.put("s_address", s_address);
				map.put("describe", describe);
				map.put("s_physical", s_physical);
				map.put("s_longitude", s_longitude);
				map.put("s_latitude", s_latitude);
				map.put("is_open", is_open);
				map.put("user_mark", "0");
				map.put("integral_setting", "0.1");
				map.put("offset_setting", "1.0");
				map.put("payment_code", Constants.orUrl);
				map.put("deductible_percentage",deductible_percentage);
				map.put("working_hours",working_hours);
				map.put("shop_note", " ");
				String s = request.getSession().getServletContext().getRealPath("/");
				String uploadPicture1 = "";
				String uploadPicture2 = "";
				if (file1 != null) {// 添加店铺图片
					uploadPicture1 = FileUpload.shopPictureUpload(file1, s.substring(0, s.length() - 12));
					if (uploadPicture1 != "fail") {
						map.put("picture", uploadPicture1);
					} else {
						m.put("ResponseStatus", ResponseStatus.STOREFAIL1);
						m.put("msg", ResponseStatus.STOREFAIL_CN_MSG1);
					}
				} else {
					map.put("picture", " ");
				}
				if (file2 != null) {// 添加店铺LOGO
					uploadPicture2 = FileUpload.shopPictureUpload(file2, s.substring(0, s.length() - 12));
					if (uploadPicture2 != "fail") {
						map.put("s_logo", uploadPicture2);
					} else {
						m.put("ResponseStatus", ResponseStatus.STOREFAIL2);
						m.put("msg", ResponseStatus.STOREFAIL_CN_MSG2);
					}
				} else {
					map.put("s_logo", " ");
				}
				Integer newStore = storeService.insertStore(map);
				if (newStore > 0) {
					Store store = storeService.selectByauId(Integer.parseInt(auId));
					String code = Constants.orUrl + "text=" + store.getS_id();
					Map<String, Object> sco = new LinkedHashMap<String, Object>();
					sco.put("s_id", store.getS_id());
					sco.put("payment_code", code);
					storeService.updateCode(sco);
					m.put("ResponseStatus", ResponseStatus.STORESUCCESS);
					m.put("msg", ResponseStatus.STORESUCCESS_CN_MSG);
				} else {
					m.put("ResponseStatus", ResponseStatus.STOREFAIL);
					m.put("msg", ResponseStatus.STOREFAIL_CN_MSG);
				}
			}
		} else {
			m.put("ResponseStatus", ResponseStatus.STOREFAIL3);
			m.put("msg", ResponseStatus.STOREFAIL_CN_MSG3);
		}
		return m;
	}

	@ResponseBody
	@RequestMapping("selectAllOneStore")
	public Map<String, Object> selectAllOneStore(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("toc_id") String toc_id,
			@RequestParam("pstart") String pstart,
			@RequestParam("psize") String psize) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Map<String, Object> m = new LinkedHashMap<String, Object>();
		Integer pstarts = Integer.parseInt(pstart);
		Integer psizes = Integer.parseInt(psize);
		map.put("pstart", pstarts * psizes);
		map.put("psize", psizes);
		map.put("toc_id", Integer.parseInt(toc_id));
		List<Store> selectAll = storeService.selectAllBytocId(map);
		if (selectAll.size() > 0) {
			m.put("ResponseStatus", ResponseStatus.QUERYWASSUCCESS);
			m.put("msg", ResponseStatus.QUERYWASSUCCESS_CN_MSG);
			m.put("selectAll", selectAll);
		} else {
			m.put("ResponseStatus", ResponseStatus.STORENULL);
			m.put("msg", ResponseStatus.STORENULL_CN_MSG);
		}
		return m;
	}

	// 分页通过二级标题ID显示该标题下所有店铺信息 ("ttc_id" -二级标题编号/"pstart -页数/"psize -个数'每页'")
	@ResponseBody
	@RequestMapping("selectAllTwoStore")
	public Map<String, Object> selectAllTwoStore(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("ttc_id") String ttc_id,
			@RequestParam("pstart") String pstart,
			@RequestParam("psize") String psize) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Map<String, Object> m = new LinkedHashMap<String, Object>();
		Integer pstarts = Integer.parseInt(pstart);
		Integer psizes = Integer.parseInt(psize);
		map.put("pstart", pstarts * psizes);
		map.put("psize", psizes);
		map.put("ttc_id", Integer.parseInt(ttc_id));
		List<Store> selectAll = storeService.selectAllByttcId(map);
		if (selectAll.size() > 0) {
			m.put("ResponseStatus", ResponseStatus.QUERYWASSUCCESS);// '0'
			m.put("msg", ResponseStatus.QUERYWASSUCCESS_CN_MSG);// '查询成功'
			m.put("selectAll", selectAll);
		} else {
			m.put("ResponseStatus", ResponseStatus.STORENULL);// '1'
			m.put("msg", ResponseStatus.STORENULL_CN_MSG);// '已无内容'
		}
		return m;
	}

	/**
	 * 根据二级类别查询对应的商品
	 * @param request
	 * @param response
	 * @param ttc_id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getCommodityByTTC")
	public Map<String, Object> getCommodityByTTC(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("ttc_id") Integer ttc_id) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Map<String, Object> m = new LinkedHashMap<String, Object>();
		map.put("ttc_id",ttc_id);
		List<Commodity> listAll=new ArrayList<Commodity>();
		List<Store> selectAll = storeService.selectAllStoreByTTC(ttc_id);
		for(Store store:selectAll){
			Integer s_id=store.getS_id();
			List<Commodity> list=commodityMapper.selectByStoreID(s_id);
			listAll.addAll(list);
		}
		if (listAll.size() > 0) {
			m.put("ResponseStatus", ResponseStatus.QUERYWASSUCCESS);// '0'
			m.put("msg", ResponseStatus.QUERYWASSUCCESS_CN_MSG);// '查询成功'
			m.put("listAll", listAll);
		} else {
			m.put("ResponseStatus", ResponseStatus.STORENULL);// '1'
			m.put("msg", ResponseStatus.STORENULL_CN_MSG);// '已无内容'
		}
		return m;
	}
	
	
	// 通过用户ID显示店铺信息
	@ResponseBody
	@RequestMapping("selectByauId")
	public Map<String, Object> selectByauId(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("auId") String auId) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Store byauId = storeService.selectByauId(Integer.parseInt(auId));
		if (byauId != null) {
			map.put("ResponseStatus", ResponseStatus.QUERYWASSUCCESS);// '0'
			map.put("msg", ResponseStatus.QUERYWASSUCCESS_CN_MSG);// '查询成功'
			map.put("byauId", byauId);
		} else {
			map.put("ResponseStatus", ResponseStatus.STORENULL);// '1'
			map.put("msg", ResponseStatus.STORENULL_CN_MSG);// '已无内容'
		}
		return map;
	}

	// 通过店铺ID修改店铺信息
	@ResponseBody
	@RequestMapping("updateBysId")
	public Map<String, Object> updateBysId(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("s_id") String s_id,
			@RequestParam("s_name") String s_name,
			@RequestParam("open_time") String open_time,
			@RequestParam("s_mobile") String s_mobile,
			@RequestParam("s_address") String s_address,
			@RequestParam("describe") String describe,
			@RequestParam("s_physical") String s_physical,
			@RequestParam("s_longitude") String s_longitude,
			@RequestParam("s_latitude") String s_latitude,
			@RequestParam("deductible_percentage") Double deductible_percentage,
			@RequestParam("is_open") String is_open,
			@RequestParam(value = "working_hours", required = false) String working_hours,
			@RequestParam(value = "file1", required = false) MultipartFile file1,
			@RequestParam(value = "file2", required = false) MultipartFile file2)
			throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Map<String, Object> m = new LinkedHashMap<String, Object>();
		if (!"".equals(s_name) && !"".equals(open_time) && !"".equals(s_mobile)
				&& !"".equals(s_address) && !"".equals(describe)
				&& !"".equals(s_physical) && !"".equals(s_longitude)
				&& !"".equals(s_latitude)) {
			Store store = storeService.selectBysId(Integer.parseInt(s_id));
			map.put("s_id", Integer.parseInt(s_id));
			map.put("s_name", s_name);
			map.put("open_time", open_time);
			map.put("s_mobile", s_mobile);
			map.put("s_address", s_address);
			map.put("describe", describe);
			map.put("s_physical", s_physical);
			map.put("s_longitude", Double.parseDouble(s_longitude));
			map.put("s_latitude", Double.parseDouble(s_latitude));
			map.put("is_open", is_open);
			map.put("payment_code", store.getPayment_code());
			map.put("deductible_percentage",deductible_percentage);
			map.put("working_hours",working_hours);
			String s = request.getSession().getServletContext().getRealPath("/");
			String uploadPicture1 = "";
			String uploadPicture2 = "";
			if (file1 != null) {// 添加店铺图片
				uploadPicture1 = FileUpload.shopPictureUpload(file1, s.substring(0, s.length() - 12));
				if (uploadPicture1 != "fail") {
					map.put("picture", uploadPicture1);
				} else {
					m.put("ResponseStatus", ResponseStatus.STOREFAIL1);// '1'
					m.put("msg", ResponseStatus.STOREFAIL_CN_MSG1);// '店铺图片添加失败'
				}
			} else {
				Store selectBysId = storeService.selectBysId(Integer.parseInt(s_id));
				map.put("picture", selectBysId.getPicture());
			}
			if (file2 != null) {// 添加店铺LOGO
				uploadPicture2 = FileUpload.shopPictureUpload(file2, s.substring(0, s.length() - 12));
				if (uploadPicture2 != "fail") {
					map.put("s_logo", uploadPicture2);
				} else {
					m.put("ResponseStatus", ResponseStatus.STOREFAIL2);// '1'
					m.put("msg", ResponseStatus.STOREFAIL_CN_MSG2);// 'LOGO图片添加失败'
				}
			} else {
				Store selectBysId = storeService.selectBysId(Integer.parseInt(s_id));
				map.put("s_logo", selectBysId.getS_logo());
			}
			Integer bysId = storeService.updateBysId(map);
			if (bysId > 0) {
				m.put("ResponseStatus", ResponseStatus.STOREUPDATESUCCESS);// '0'
				m.put("msg", ResponseStatus.STOREUPDATESUCCESS_CN_MSG);// '修改成功'
			} else {
				m.put("ResponseStatus", ResponseStatus.STOREUPDATEFAIL);// '1'
				m.put("msg", ResponseStatus.STOREUPDATEFAIL_CN_MSG);// '修改失败'
			}
		} else {
			m.put("ResponseStatus", ResponseStatus.STOREFAIL3);// '1'
			m.put("msg", ResponseStatus.STOREFAIL_CN_MSG3);// '店铺添加信息有误'
		}
		return m;
	}

	// 通过店铺ID修改店铺地址
	@ResponseBody
	@RequestMapping("updateAddress")
	public Map<String, Object> updateAddress(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("s_id") String s_id,
			@RequestParam("s_address") String s_address,
			@RequestParam("s_longitude") String s_longitude,
			@RequestParam("s_latitude") String s_latitude) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Map<String, Object> m = new LinkedHashMap<String, Object>();
		map.put("s_id", Integer.parseInt(s_id));
		map.put("s_address", s_address);
		map.put("s_longitude", Double.parseDouble(s_longitude));
		map.put("s_latitude", Double.parseDouble(s_latitude));
		Integer updateSaddress = storeService.updateSaddress(map);
		if (updateSaddress > 0) {
			m.put("ResponseStatus", ResponseStatus.STOREADDRESSSUCCESS);// '0'
			m.put("msg", ResponseStatus.STOREADDRESSSUCCESS_CN_MSG);// '店铺地址修改成功!!'
		} else {
			m.put("ResponseStatus", ResponseStatus.STOREADDRESSFAIL);// '1'
			m.put("msg", ResponseStatus.STOREADDRESSFAIL_CN_MSG);// '店铺修改失败'
		}
		return m;
	}

	// 通过店铺ID查看该店铺信息
	@ResponseBody
	@RequestMapping("selectBysId")
	public Map<String, Object> selectBysId(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("s_id") String s_id) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Store selectBysId = storeService.selectBysId(Integer.parseInt(s_id));
		if (selectBysId != null) {
			map.put("ResponseStatus", ResponseStatus.QUERYWASSUCCESS);// '0'
			map.put("msg", ResponseStatus.QUERYWASSUCCESS_CN_MSG);// '查询成功'
			map.put("storeDetails", selectBysId);
		} else {
			map.put("ResponseStatus", ResponseStatus.AUDITINGGOODSFAIL);// '1'
			map.put("msg", ResponseStatus.AUDITINGGOODSFAIL_CN_MSG4);// '该店铺未存在'
		}
		return map;
	}

	// 模糊搜索店铺(分页)
	@ResponseBody
	@RequestMapping("selectLike")
	public Map<String, Object> selectLike(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("s_name") String s_name,
			@RequestParam("pstart") String pstart,
			@RequestParam("psize") String psize) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Map<String, Object> m = new LinkedHashMap<String, Object>();
		Integer pstarts = Integer.parseInt(pstart);
		Integer psizes = Integer.parseInt(psize);
		m.put("pstart", pstarts * psizes);
		m.put("psize", psizes);
		m.put("s_name", s_name);
		List<Store> selectAllLike = storeService.selectAllLike(m);
		if (selectAllLike.size() > 0) {
			map.put("obj", selectAllLike);
			map.put("ResponseStatus", ResponseStatus.QUERYWASSUCCESS);// '0'
			map.put("msg", ResponseStatus.QUERYWASSUCCESS_CN_MSG);// '查询成功'
		} else {
			map.put("ResponseStatus", ResponseStatus.STORENULL);// '1'
			map.put("msg", ResponseStatus.STORENULL_CN_MSG);// '已无内容'
		}
		return map;
	}

	// 通过店铺ID修改店铺首图
	@ResponseBody
	@RequestMapping("updStoreFire")
	public Map<String, Object> updStoreFire(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("s_id") String s_id,
			@RequestParam(value = "file", required = false) MultipartFile file)
			throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Map<String, Object> m = new LinkedHashMap<String, Object>();
		map.put("s_id", s_id);
		String s = request.getSession().getServletContext().getRealPath("/");
		String uploadPicture = "";
		if (file != null) {// 添加店铺首图
			uploadPicture = FileUpload.shopPictureUpload(file, s.substring(0, s.length() - 12));
			if (uploadPicture != "fail") {
				map.put("first_picture", uploadPicture);
			} else {
				m.put("ResponseStatus", ResponseStatus.STOREFAIL1);// '1'
				m.put("msg", "店铺首图添加失败");
			}
		} else {
			map.put("first_picture", " ");
		}
		Integer updStoreFire = storeService.updStoreFire(map);
		if (updStoreFire > 0) {
			Store selectBysId = storeService.selectBysId(Integer.parseInt(s_id));
			List<Commodity> selectBySiD = commodityMapper.selectBySiD(Integer.parseInt(s_id));
			m.put("selectAll", selectBySiD);
			m.put("obj1", selectBysId.getFirst_picture());
			m.put("ResponseStatus", ResponseStatus.QUERYWASSUCCESS);// '0'
			m.put("msg", "店铺首图添加成功");
		} else {
			m.put("ResponseStatus", ResponseStatus.STORENULL);// '1'
			m.put("msg", "店铺首图添加失败");
		}
		return m;
	}

}
