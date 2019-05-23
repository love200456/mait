package com.wingfac.MaitreyaRim.controller;

import java.util.HashMap;
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

import com.wingfac.MaitreyaRim.po.AverageUser;
import com.wingfac.MaitreyaRim.po.OrderInformation;
import com.wingfac.MaitreyaRim.po.UserShopEvaluate;
import com.wingfac.MaitreyaRim.service.impl.AverageUserService;
import com.wingfac.MaitreyaRim.service.impl.OrderInformationService;
import com.wingfac.MaitreyaRim.service.impl.StoreService;
import com.wingfac.MaitreyaRim.service.impl.UserShopEvaluateService;
import com.wingfac.MaitreyaRim.util.FileUpload;
import com.wingfac.MaitreyaRim.util.ResponseStatus;

@Controller
@RequestMapping("UserShopEvaluate")
public class UserShopEvaluateController {

	@Autowired
	private UserShopEvaluateService userShopEvaluateService;
	@Autowired
	private StoreService storeService;
	@Autowired
	private OrderInformationService orderInformationService;
	
	@Autowired
	private AverageUserService averageUserService;

	@ResponseBody
	@RequestMapping("newGradingInformation")
	public Map<String, Object> newGradingInformation(
			HttpServletRequest request, HttpServletResponse response,
			@RequestParam("o_id") String o_id,
			@RequestParam("user_mark") String user_mark,
			@RequestParam(value = "content", required = false) String content,
			@RequestParam(value = "picA", required = false) MultipartFile picA,
			@RequestParam(value = "picB", required = false) MultipartFile picB,
			@RequestParam(value = "picC", required = false) MultipartFile picC,
			@RequestParam(value = "picD", required = false) MultipartFile picD,
			@RequestParam(value = "picNum", required = false) Integer picNum) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		UserShopEvaluate use = new UserShopEvaluate();
		use.setPicA("");
		use.setPicB("");
		use.setPicC("");
		use.setPicD("");
		use.setPicNum(picNum);
		OrderInformation selectByOid = orderInformationService.selectByOid(Integer.parseInt(o_id));
		if (selectByOid != null) {
			try{
				String s = request.getSession().getServletContext().getRealPath("/");
				if (picA != null) {// 添加店铺图片
					String str = FileUpload.shopPictureUpload(picA, s.substring(0, s.length() - 12));
					if (str != "fail") {
						use.setPicA(str);
					}
				}
				if (picB != null) {// 添加店铺图片
					String str = FileUpload.shopPictureUpload(picB, s.substring(0, s.length() - 12));
					if (str != "fail") {
						use.setPicB(str);
					}
				}
				if (picC != null) {// 添加店铺图片
					String str = FileUpload.shopPictureUpload(picC, s.substring(0, s.length() - 12));
					if (str != "fail") {
						use.setPicC(str);
					}
				}
				if (picD != null) {// 添加店铺图片
					String str = FileUpload.shopPictureUpload(picD, s.substring(0, s.length() - 12));
					if (str != "fail") {
						use.setPicD(str);
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			use.setContent(content);
			use.setAuId(selectByOid.getAuId());
			use.setS_id(selectByOid.getS_id());
			use.setUser_mark(Integer.parseInt(user_mark));
			use.setOrder_id(Long.valueOf(o_id));
			Integer newScore = userShopEvaluateService.newScore(use);
			if (newScore > 0) {
				List<UserShopEvaluate> list = userShopEvaluateService.viewScoreInformation(selectByOid.getS_id());
				Integer total = 0;
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getUser_mark() != 0) {
						total += list.get(i).getUser_mark();
					}
				}
				Double average = (double) (total / list.size());
				Map<String, Object> m = new LinkedHashMap<String, Object>();
				m.put("s_id", selectByOid.getS_id());
				m.put("user_mark", average);
				Integer updateuserMark = storeService.updateuserMark(m);
				if (updateuserMark > 0) {
					map.put("ResponseStatus", ResponseStatus.QUERYWASSUCCESS);
					map.put("msg", "用户打分成功");
				} else {
					map.put("ResponseStatus", ResponseStatus.STORENULL);
					map.put("msg", "店铺打分失败,请重试......");
				}
			} else {
				map.put("ResponseStatus", ResponseStatus.STORENULL);
				map.put("msg", "用户打分失败,请重试......");
			}
		} else {
			map.put("ResponseStatus", ResponseStatus.STORENULL);
			map.put("msg", "查无此订单信息");
		}
		return map;
	}

	/**
	 * 查询评论列表
	 * @param s_id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("evaluateList")
	public Map<String, Object> evaluateList(@RequestParam(value="s_id",required=true) Integer s_id){
		Map<String,Object> result=new HashMap<String,Object>();
		List<UserShopEvaluate> list = userShopEvaluateService.viewScoreInformation(s_id);
		for(UserShopEvaluate use:list){
			Integer auid=use.getAuId();
			AverageUser averageUser=averageUserService.selectByauId(auid);
			String auAvatar=averageUser.getAuAvatar();
			String auBuyerNick=averageUser.getAuBuyerNick();
			use.setAuAvatar(auAvatar);
			use.setAuBuyerNick(auBuyerNick);
		}
		result.put("ResponseStatus","0");
		result.put("msg","操作完成!");
		result.put("list",list);
		return result;
	}
	
	/**
	 * 根据评论ID 查询一条评论信息
	 * @param use_id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("selectByID")
	public Map<String, Object> selectByID(@RequestParam(value="use_id",required=true) Long use_id){
		Map<String,Object> result=new HashMap<String,Object>();
		UserShopEvaluate obj = userShopEvaluateService.selectByID(use_id);
		Integer auid=obj.getAuId();
		AverageUser averageUser=averageUserService.selectByauId(auid);
		String auAvatar=averageUser.getAuAvatar();
		String auBuyerNick=averageUser.getAuBuyerNick();
		obj.setAuAvatar(auAvatar);
		obj.setAuBuyerNick(auBuyerNick);
		result.put("ResponseStatus","0");
		result.put("msg","操作完成!");
		result.put("data",obj);
		return result;
	}
	
	
	/**
	 * 更新用户评论信息
	 * @param use_id
	 * @param content
	 * @param resetContent
	 * @return
	 */
	@ResponseBody
	@RequestMapping("updateShopEvaluate")
	public Map<String, Object> updateShopEvaluate(
			@RequestParam(value="use_id",required=true) Long use_id,
			@RequestParam(value="content",required=false) String content,
			@RequestParam(value="resetContent",required=false) String resetContent,
			@RequestParam(value = "picA", required = false) MultipartFile picA,
			@RequestParam(value = "picB", required = false) MultipartFile picB,
			@RequestParam(value = "picC", required = false) MultipartFile picC,
			@RequestParam(value = "picD", required = false) MultipartFile picD,
			@RequestParam(value = "picNum", required = false) Integer picNum,
			HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> result=new HashMap<String,Object>();
		Map<String,Object> param=new HashMap<String,Object>();
		String s = request.getSession().getServletContext().getRealPath("/");
		param.put("picA","");
		param.put("picB","");
		param.put("picC","");
		param.put("picD","");
		param.put("picNum",picNum);
		try{
			if (picA != null) {// 添加店铺图片
				String str = FileUpload.shopPictureUpload(picA, s.substring(0, s.length() - 12));
				if (str != "fail") {
					param.put("picA",str);
				}
			}
			if (picB != null) {// 添加店铺图片
				String str = FileUpload.shopPictureUpload(picB, s.substring(0, s.length() - 12));
				if (str != "fail") {
					param.put("picB",str);
				}
			}
			if (picC != null) {// 添加店铺图片
				String str = FileUpload.shopPictureUpload(picC, s.substring(0, s.length() - 12));
				if (str != "fail") {
					param.put("picC",str);
				}
			}
			if (picD != null) {// 添加店铺图片
				String str = FileUpload.shopPictureUpload(picD, s.substring(0, s.length() - 12));
				if (str != "fail") {
					param.put("picD",str);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}

		param.put("use_id",use_id);
		param.put("content",content);
		param.put("resetContent",resetContent);
		userShopEvaluateService.updateShopEvaluate(param);
		result.put("ResponseStatus","0");
		result.put("msg","操作完成!");
		return result;
	}
	
}
