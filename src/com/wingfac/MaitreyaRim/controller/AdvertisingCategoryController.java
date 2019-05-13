package com.wingfac.MaitreyaRim.controller;

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

import com.wingfac.MaitreyaRim.po.AdvertisingCategory;
import com.wingfac.MaitreyaRim.po.Store;
import com.wingfac.MaitreyaRim.service.impl.AdvertisingCategoryService;
import com.wingfac.MaitreyaRim.service.impl.StoreService;
import com.wingfac.MaitreyaRim.util.FileUpload;
import com.wingfac.MaitreyaRim.util.ResponseStatus;

@Controller
@RequestMapping("AdvertisingCategory")
public class AdvertisingCategoryController {

	@Autowired
	private AdvertisingCategoryService advertisingCategoryService;
	@Autowired
	private StoreService storeService;

	@RequestMapping("jumpClassify")
	public String jumpClassify(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("rank") String rank,
			ModelMap modelMap) {
		List<Store> slesctAllPage = storeService.slesctAllPage();
		if (rank.equals("1")) {
			modelMap.put("slesctAllPage", slesctAllPage);
			return "classify/classify";
		} else {
			modelMap.put("slesctAllPage", slesctAllPage);
			return "classify/classify2";
		}
	}

	@RequestMapping("selectAC")
	public String selectAC(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap,
			@RequestParam("level") String level) {
		if (level.equals("1")) {
			List<AdvertisingCategory> selectOne = advertisingCategoryService.selectOne();
			if (selectOne.size() > 0) {
				modelMap.put("selectAC", selectOne);
				modelMap.put("judge", "2");
			} else {
				modelMap.put("judge", "1");
			}
			return "classify/firstadv";
		} else {
			List<AdvertisingCategory> selectTwo = advertisingCategoryService.selectTwo();
			if (selectTwo.size() > 0) {
				modelMap.put("selectAC", selectTwo);
				modelMap.put("judge", "2");
			} else {
				modelMap.put("judge", "1");
			}
			return "classify/secondadv";
		}
	}

	@RequestMapping("insertAC")
	public String insertAC(
			HttpServletRequest request,
			HttpServletResponse response,
			ModelMap modelMap,
			@RequestParam("levels") String levels,
			@RequestParam("sId1") String sId1,
			@RequestParam("sId2") String sId2,
			@RequestParam("sId3") String sId3,
			@RequestParam("sId4") String sId4,
			@RequestParam(value = "file1", required = false) MultipartFile file1,
			@RequestParam(value = "file2", required = false) MultipartFile file2,
			@RequestParam(value = "file3", required = false) MultipartFile file3,
			@RequestParam(value = "file4", required = false) MultipartFile file4)
			throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		String s = request.getSession().getServletContext().getRealPath("/");
		String uploadPicture1 = "";
		String uploadPicture2 = "";
		String uploadPicture3 = "";
		String uploadPicture4 = "";
		Integer insertAC = 0;
		if (!"".equals(sId1)) {
			if (file1 != null) {
				uploadPicture1 = FileUpload.acPictureUpload(file1, s.substring(0, s.length() - 12));
				if (uploadPicture1 != "fail") {
					map.put("ac_picture", uploadPicture1);
					map.put("s_id", Integer.parseInt(sId1));
					map.put("ac_level", levels);
					insertAC = advertisingCategoryService.insertAC(map);
					if (insertAC > 0) {
						insertAC = 0;
						if (file2 != null) {
							uploadPicture2 = FileUpload.acPictureUpload(file2, s.substring(0, s.length() - 12));
							if (uploadPicture2 != "fail") {
								map.put("ac_picture", uploadPicture2);
								map.put("s_id", Integer.parseInt(sId2));
								map.put("ac_level", levels);
								insertAC = advertisingCategoryService.insertAC(map);
								if (insertAC > 0) {
									insertAC = 0;
									if (file3 != null) {
										uploadPicture3 = FileUpload.acPictureUpload(file3, s.substring(0,s.length() - 12));
										if (uploadPicture3 != "fail") {
											map.put("ac_picture", uploadPicture3);
											map.put("s_id", Integer.parseInt(sId3));
											map.put("ac_level", levels);
											insertAC = advertisingCategoryService.insertAC(map);
											if (insertAC > 0) {
												insertAC = 0;
												if (file4 != null) {
													uploadPicture4 = FileUpload.acPictureUpload(file4, s.substring(0,s.length() - 12));
													if (uploadPicture4 != "fail") {
														map.put("ac_picture", uploadPicture4);
														map.put("s_id", Integer.parseInt(sId4));
														map.put("ac_level", levels);
														insertAC = advertisingCategoryService.insertAC(map);
														if (insertAC > 0) {
															return this.selectAC(request,response,modelMap,levels);
														} else {
															request.setAttribute("msg", "广告添加失败....");
														}
													} else {
														request.setAttribute("msg", "图片添加失败....");
													}
												} else {
													request.setAttribute("msg", "图片不能为空....");
												}
											} else {
												request.setAttribute("msg", "广告添加失败....");
											}
										} else {
											request.setAttribute("msg", "图片添加失败....");
										}
									} else {
										request.setAttribute("msg", "图片不能为空....");
									}
								} else {
									request.setAttribute("msg", "广告添加失败....");
								}
							} else {
								request.setAttribute("msg", "图片添加失败....");
							}
						} else {
							request.setAttribute("msg", "图片不能为空....");
						}
					} else {
						request.setAttribute("msg", "广告添加失败....");
					}
				} else {
					request.setAttribute("msg", "图片添加失败....");
				}
			} else {
				request.setAttribute("msg", "图片不能为空....");
			}
		} else {
			request.setAttribute("msg", "店铺编号不能为空....");
		}
		return this.selectAC(request, response, modelMap, levels);
	}

	@RequestMapping("updateAC")
	public String updateAC(
			HttpServletRequest request,
			HttpServletResponse response,
			ModelMap modelMap,
			@RequestParam("levels") String levels,
			@RequestParam("sIds1") String sId1,
			@RequestParam("sIds2") String sId2,
			@RequestParam("sIds3") String sId3,
			@RequestParam("sIds4") String sId4,
			@RequestParam("acId1") String acId1,
			@RequestParam("acId2") String acId2,
			@RequestParam("acId3") String acId3,
			@RequestParam("acId4") String acId4,
			@RequestParam(value = "file5", required = false) MultipartFile file5,
			@RequestParam(value = "file6", required = false) MultipartFile file6,
			@RequestParam(value = "file7", required = false) MultipartFile file7,
			@RequestParam(value = "file8", required = false) MultipartFile file8)
			throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		String s = request.getSession().getServletContext().getRealPath("/");
		String uploadPicture1 = "";
		String uploadPicture2 = "";
		String uploadPicture3 = "";
		String uploadPicture4 = "";
		Integer resultAC = 0;
		if(file5 != null && !sId1.isEmpty()){
			map.put("s_id", Integer.parseInt(sId1));
			map.put("ac_id", acId1);
			map.put("ac_level", levels);
			AdvertisingCategory byId = null;
			if (!acId1.equals("")) {
				byId = advertisingCategoryService.selectById(Integer.parseInt(acId1));
			}

			uploadPicture1 = FileUpload.acPictureUpload(file5, s.substring(0, s.length() - 12));
			if(uploadPicture1 != "fail"){
				map.put("ac_picture", uploadPicture1);
			} else if (byId != null){
				map.put("ac_picture", byId.getAc_picture());
			}

			if (byId != null) {
				resultAC = advertisingCategoryService.updateAC(map);
			} else {
				resultAC = advertisingCategoryService.insertAC(map);
			}

		}
		if(file6 != null && !sId2.isEmpty()){
			map.put("s_id", Integer.parseInt(sId2));
			map.put("ac_id", acId2);
			map.put("ac_level", levels);
			AdvertisingCategory byId = null;
			if (!acId2.equals("")) {
				byId = advertisingCategoryService.selectById(Integer.parseInt(acId2));
			}

			uploadPicture2 = FileUpload.acPictureUpload(file6, s.substring(0, s.length() - 12));
			if(uploadPicture2 != "fail"){
				map.put("ac_picture", uploadPicture2);
			}else if (byId != null){
				map.put("ac_picture", byId.getAc_picture());
			}

			if (byId != null) {
				resultAC = advertisingCategoryService.updateAC(map);
			} else {
				resultAC = advertisingCategoryService.insertAC(map);
			}

		}
		if(file7 != null && !sId3.isEmpty()){
			map.put("s_id", Integer.parseInt(sId3));
			map.put("ac_id", acId3);
			map.put("ac_level", levels);
			AdvertisingCategory byId = null;
			if (!acId3.equals("")) {
				byId = advertisingCategoryService.selectById(Integer.parseInt(acId3));
			}

			uploadPicture3 = FileUpload.acPictureUpload(file7, s.substring(0, s.length() - 12));
			if(uploadPicture3 != "fail"){
				map.put("ac_picture", uploadPicture3);
			}else if (byId != null){
				map.put("ac_picture", byId.getAc_picture());
			}

			if (byId != null) {
				resultAC = advertisingCategoryService.updateAC(map);
			} else {
				resultAC = advertisingCategoryService.insertAC(map);
			}
		}
		if(file8 != null && !sId4.isEmpty()){
			map.put("s_id", Integer.parseInt(sId4));
			map.put("ac_id", acId4);
			map.put("ac_level", levels);
			AdvertisingCategory byId = null;
			if (!acId4.equals("")) {
				byId = advertisingCategoryService.selectById(Integer.parseInt(acId4));
			}

			uploadPicture4 = FileUpload.acPictureUpload(file8, s.substring(0, s.length() - 12));
			if(uploadPicture4 != "fail"){
				map.put("ac_picture", uploadPicture4);
			}else if (byId != null){
				map.put("ac_picture", byId.getAc_picture());
			}

			if (byId != null) {
				resultAC = advertisingCategoryService.updateAC(map);
			} else {
				resultAC = advertisingCategoryService.insertAC(map);
			}
		}
		if(resultAC > 0){
			request.setAttribute("msg", "图片修改成功");
		}else{
			request.setAttribute("msg", "图片修改失败......");
		}
		return this.selectAC(request, response, modelMap, levels);
	}

	@ResponseBody
	@RequestMapping("selectOne")
	public Map<String, Object> selectOne(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<AdvertisingCategory> selectOne = advertisingCategoryService
				.selectOne();
		if (selectOne.size() > 0) {
			map.put("obj", selectOne);
			map.put("ResponseStatus", ResponseStatus.QUERYWASSUCCESS);
			map.put("msg", ResponseStatus.QUERYWASSUCCESS_CN_MSG);
		} else {
			map.put("ResponseStatus", ResponseStatus.STORENULL);
			map.put("msg", ResponseStatus.STORENULL_CN_MSG);
		}
		return map;
	}

	@ResponseBody
	@RequestMapping("selectTwo")
	public Map<String, Object> selectTwo(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<AdvertisingCategory> selectTwo = advertisingCategoryService
				.selectTwo();
		if (selectTwo.size() > 0) {
			map.put("obj", selectTwo);
			map.put("ResponseStatus", ResponseStatus.QUERYWASSUCCESS);
			map.put("msg", ResponseStatus.QUERYWASSUCCESS_CN_MSG);
		} else {
			map.put("ResponseStatus", ResponseStatus.STORENULL);
			map.put("msg", ResponseStatus.STORENULL_CN_MSG);
		}
		return map;
	}

}
