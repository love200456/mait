package com.wingfac.MaitreyaRim.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.wingfac.MaitreyaRim.po.AverageUser;
import com.wingfac.MaitreyaRim.po.AverageUserVo;
import com.wingfac.MaitreyaRim.service.impl.RegisterService;
import com.wingfac.MaitreyaRim.util.FileUpload;
import com.wingfac.MaitreyaRim.util.ResponseStatus;

@Controller
@RequestMapping("register")
public class RegisterController {

	@Autowired
	private RegisterService registerService;

	@ResponseBody
	@RequestMapping("registered")
	public Map<String, Object> registered(HttpServletRequest request,
			@RequestParam("auMobile") String auMobile,
			@RequestParam("auPasswordOne") String auPasswordOne,
			@RequestParam("auPasswordTwo") String auPasswordTwo,
			@RequestParam("auBuyerNick") String auBuyerNick,
			@RequestParam(value = "file", required = false) MultipartFile file)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (!"".equals(auMobile) && !"".equals(auPasswordOne)
				&& !"".equals(auPasswordTwo)) {
			AverageUser byMobile = registerService.selectByMobile(auMobile);
			if (byMobile != null) {
				map.put("ResponseStatus", ResponseStatus.REGISTFAIL2);
				map.put("msg", ResponseStatus.REGISTFAIL_CN_MSG2);
			} else {
				if (auPasswordOne.equals(auPasswordTwo)) {
					AverageUser au = new AverageUser();
					au.setAuMobile(auMobile);
					au.setAuPassword(auPasswordTwo);
					au.setAuIdentity("1");
					au.setLimit_integral(0);
					au.setPermanent_points(0);
					if (!"".equals(auBuyerNick)) {
						au.setAuBuyerNick(auBuyerNick);
					} else {
						au.setAuBuyerNick("未设置");
					}
					String s = request.getSession().getServletContext().getRealPath("/");
					String uploadPicture = "";
					if (file != null) {
						uploadPicture = FileUpload.userPictureUpload(file, s.substring(0, s.length() - 12));
						if (uploadPicture != "fail") {
							au.setAuAvatar(uploadPicture);
						} else {
							au.setAuAvatar("");
						}
					} else {
						au.setAuAvatar("");
					}
					au.setAuIdentity("1");
					au.setAuAddress("未填写");
					au.setRemarks(" ");
					au.setIntegral_remark(" ");
					Integer averUser = registerService.insertAverUser(au);
					if (averUser > 0) {
						map.put("ResponseStatus", ResponseStatus.REGISTSUCCESS);
						map.put("msg", ResponseStatus.REGISTSUCCESS_CN_MSG);
					} else {
						map.put("ResponseStatus", ResponseStatus.REGISTFAIL3);
						map.put("msg", ResponseStatus.REGISTFAIL_CN_MSG3);
					}
				} else {
					map.put("ResponseStatus", ResponseStatus.REGISTFAIL1);
					map.put("msg", ResponseStatus.REGISTFAIL_CN_MSG1);
				}
			}
		} else {
			map.put("ResponseStatus", ResponseStatus.REGISTFAIL);
			map.put("msg", ResponseStatus.REGISTFAIL_CN_MSG);
		}
		return map;
	}

	@ResponseBody
	@RequestMapping("updateauAvatars")
	public Map<String, Object> updateauAvatars(HttpServletRequest request,
			@RequestParam("auId") String auId,
			@RequestParam("file") MultipartFile file) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> m = new HashMap<String, Object>();
		Integer auIds = Integer.parseInt(auId);
		AverageUserVo byauId = registerService.selectByauId(auIds);
		String oldAvatar = byauId.getAuAvatar();
		if (oldAvatar != " ") {
			FileUpload.deleteFileName(oldAvatar);
		}
		String s = request.getSession().getServletContext().getRealPath("/");
		String uploadPicture = "";
		if (file != null) {
			String url = s.substring(0, s.length() - 12);
			uploadPicture = FileUpload.userPictureUpload(file, url);
			map.put("auId", auIds);
			map.put("auAvatar", uploadPicture);
			Integer avatar = registerService.updateauAvatar(map);
			if (avatar > 0) {
				AverageUserVo selectByauId = registerService
						.selectByauId(auIds);
				m.put("ResponseStatus", ResponseStatus.MODIFYSUCCESS);
				m.put("msg", ResponseStatus.MODIFYSUCCESS_CN_MSG);
				m.put("selectByauId", selectByauId.getAuAvatar());
			} else {
				m.put("ResponseStatus", ResponseStatus.MODIFYFAIL);
				m.put("msg", ResponseStatus.MODIFYFAIL_CN_MSG);
			}
		}
		return m;
	}

	@ResponseBody
	@RequestMapping("updateauPassword")
	public Map<String, Object> updateauPassword(HttpServletRequest request,
			@RequestParam("auId") String auId,
			@RequestParam("rawPass") String rawPass,
			@RequestParam("newPass") String newPass,
			@RequestParam("newPassTwo") String newPassTwo) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> m = new HashMap<String, Object>();
		AverageUserVo byauId = registerService.selectByauId(Integer
				.parseInt(auId));
		if (byauId != null) {
			if (rawPass.equals(byauId.getAuPassword())) {
				if (newPass.equals(newPassTwo)) {
					map.put("auId", Integer.parseInt(auId));
					map.put("auPassword", newPassTwo);
					Integer passwordByauId = registerService
							.updateauPasswordByauId(map);
					if (passwordByauId > 0) {
						m.put("ResponseStatus", ResponseStatus.MODIFYSUCCESS);
						m.put("msg", ResponseStatus.MODIFYSUCCESS_CN_MSG);
					} else {
						m.put("ResponseStatus", ResponseStatus.MODIFYFAIL);
						m.put("msg", ResponseStatus.MODIFYFAIL_CN_MSG);
					}
				} else {
					m.put("ResponseStatus", ResponseStatus.PASSWORDFAIL);
					m.put("msg", ResponseStatus.PASSWORDFAIL_CN_MSG);
				}
			} else {
				m.put("ResponseStatus", ResponseStatus.PASSWORDFAIL1);
				m.put("msg", ResponseStatus.PASSWORDFAIL_CN_MSG1);
			}
		} else {
			m.put("ResponseStatus", ResponseStatus.PASSWORDFAIL2);
			m.put("msg", ResponseStatus.PASSWORDFAIL_CN_MSG2);
		}
		return m;
	}

	@ResponseBody
	@RequestMapping("forgetPassword")
	public Map<String, Object> forgetPassword(HttpServletRequest request,
			@RequestParam("mobile") String mobile,
			@RequestParam("nPassword") String nPassword,
			@RequestParam("tPassword") String tPassword) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> m = new HashMap<String, Object>();
		AverageUser byMobile = registerService.selectByMobile(mobile);
		if (byMobile != null) {
			if (nPassword.equals(tPassword)) {
				map.put("auMobile", mobile);
				map.put("auPassword", tPassword);
				Integer byauMoblie = registerService
						.updateauPasswordByauMoblie(map);
				if (byauMoblie > 0) {
					m.put("ResponseStatus", ResponseStatus.MODIFYSUCCESS);
					m.put("msg", ResponseStatus.MODIFYSUCCESS_CN_MSG);
				} else {
					m.put("ResponseStatus", ResponseStatus.MODIFYFAIL);
					m.put("msg", ResponseStatus.MODIFYFAIL_CN_MSG);
				}
			} else {
				m.put("ResponseStatus", ResponseStatus.FORGETFAIL1);
				m.put("msg", ResponseStatus.FORGETFAIL_CN_MSG1);
			}
		} else {
			m.put("ResponseStatus", ResponseStatus.FORGETFAIL);
			m.put("msg", ResponseStatus.FORGETFAIL_CN_MSG);
		}
		return m;
	}
	
	@ResponseBody
	@RequestMapping("updateauAddress")
	public Map<String, Object> updateauAddress(HttpServletRequest request,
			@RequestParam("auId") String auId,
			@RequestParam("auAddress") String auAddress) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> m = new HashMap<String, Object>();
		AverageUserVo selectByauId = registerService.selectByauId(Integer
				.parseInt(auId));
		if (selectByauId != null) {
			m.put("auId", Integer.parseInt(auId));
			m.put("auAddress", auAddress);
			Integer updateauAddress = registerService.updateauAddress(m);
			if (updateauAddress > 0) {
				map.put("ResponseStatus", ResponseStatus.USERADDRESSSUCCESS);
				map.put("msg", ResponseStatus.USERADDRESSSUCCESS_CN_MSG);
			} else {
				map.put("ResponseStatus", ResponseStatus.USERADDRESSFAIL);
				map.put("msg", ResponseStatus.USERADDRESSFAIL_CN_MSG);
			}
		} else {
			map.put("ResponseStatus", ResponseStatus.PASSWORDFAIL2);
			map.put("msg", ResponseStatus.PASSWORDFAIL_CN_MSG2);
		}
		return map;
	}

	@ResponseBody
	@RequestMapping("updateauNick")
	public Map<String, Object> updateauNick(HttpServletRequest request,
			@RequestParam("auId") String auId,
			@RequestParam("auBuyerNick") String auBuyerNick) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Map<String, Object> m = new LinkedHashMap<String, Object>();
		m.put("auId", Integer.parseInt(auId));
		m.put("auBuyerNick", auBuyerNick);
		Integer updateauNick = registerService.updateauNick(m);
		if (updateauNick > 0) {
			map.put("ResponseStatus", ResponseStatus.BUYERNICKSUCCESS);
			map.put("msg", ResponseStatus.BUYERNICKSUCCESS_CN_MSG);
		} else {
			map.put("ResponseStatus", ResponseStatus.BUYERNICKFAIL);
			map.put("msg", ResponseStatus.BUYERNICKFAIL_CN_MSG);
		}
		return map;
	}

}
