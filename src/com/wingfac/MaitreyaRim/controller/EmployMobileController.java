package com.wingfac.MaitreyaRim.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.wingfac.MaitreyaRim.po.Employ;
import com.wingfac.MaitreyaRim.po.Store;
import com.wingfac.MaitreyaRim.service.impl.EmployService;
import com.wingfac.MaitreyaRim.service.impl.StoreService;
import com.wingfac.MaitreyaRim.util.ResponseStatus;

@Controller
@RequestMapping("EmployMobile")
public class EmployMobileController {

	@Autowired
	private EmployService employService;

	@Autowired
	private StoreService storeService;

	@ResponseBody
	@RequestMapping("insertEmpInfo")
	public Map<String, Object> insertEmpInfo(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("s_id") String s_id,
			@RequestParam("auId") String auId,
			@RequestParam("position") String position,
			@RequestParam("position_describe") String position_describe,
			@RequestParam("salary") String salary,
			@RequestParam("work_nature") String work_nature,
			@RequestParam("emp_num") String emp_num,
			@RequestParam("contacts") String contacts,
			@RequestParam("contacts_tel") String contacts_tel,
			@RequestParam("employType") String employType)
					throws Exception {
		Map<String, Object> m = new LinkedHashMap<String, Object>();
		if (!"".equals(auId) && !"".equals(position)
				&& !"".equals(position_describe) && !"".equals(salary)
				&& !"".equals(work_nature) && !"".equals(emp_num)
				&& !"".equals(contacts) && !"".equals(contacts_tel)) {
			if (s_id == null || s_id.equals("")) {
				m.put("ResponseStatus", ResponseStatus.EMPINFOFAIL);// '1'
				m.put("msg", "未创建店铺不能发布招聘信息.....");
			} else {
				Map<String, Object> map = new LinkedHashMap<String, Object>();
				map.put("auId", auId);
				map.put("position", position);
				map.put("position_describe", position_describe);
				map.put("salary", salary);
				map.put("work_nature", work_nature);
				map.put("emp_num", emp_num);
				map.put("contacts", contacts);
				map.put("contacts_tel", contacts_tel);
				map.put("employType",employType);
				String nowDate = new SimpleDateFormat("yyyy-MM-dd")
						.format(new Date());
				map.put("release_date", nowDate);

				String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.format(new Date());
				map.put("update_time", nowTime);
				map.put("create_time", nowTime);

				Integer insertResult = employService.insertEmpInfo(map);
				if (insertResult > 0) {
					m.put("ResponseStatus", ResponseStatus.EMPINFOSUCCESS);
					m.put("msg", ResponseStatus.EMPINFOSUCCESS_CN_MSG);
				} else {
					m.put("ResponseStatus", ResponseStatus.EMPINFOFAIL);
					m.put("msg", ResponseStatus.EMPINFOFAIL_CN_MSG);
				}
			}
		} else {
			m.put("ResponseStatus", ResponseStatus.EMPINFOERROR);
			m.put("msg", ResponseStatus.EMPINFOERROR_CN_MSG);
		}
		return m;
	}

	// 通过ID显示招聘信息
	@ResponseBody
	@RequestMapping("selectById")
	public Map<String, Object> selectById(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("empId") String empId) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Employ empInfo = employService.selectById(Integer.parseInt(empId));

		if (empInfo != null) {
			map.put("ResponseStatus", ResponseStatus.QUERYWASSUCCESS);// '0'
			map.put("msg", ResponseStatus.QUERYWASSUCCESS_CN_MSG);// '查询成功'
			map.put("empInfo", empInfo);
		} else {
			map.put("ResponseStatus", ResponseStatus.EMPINFONULL);// '1'
			map.put("msg", ResponseStatus.EMPINFONULL_CN_MSG);// '无招聘信息'
		}

		return map;
	}

	// 显示全部招聘列表
	@ResponseBody
	@RequestMapping("selectAll")
	public Map<String, Object> selectAll(HttpServletRequest request,
			@RequestParam("pstart") String pstart,
			@RequestParam("psize") String psize) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Map<String, Object> m = new LinkedHashMap<String, Object>();
		Integer pstarts = Integer.parseInt(pstart);
		Integer psizes = Integer.parseInt(psize);
		m.put("pstart", pstarts * psizes);
		m.put("psize", psizes);

		List<Employ> allPassList = employService.selectAllPass(m);
		if (allPassList.size() > 0) {
			map.put("ResponseStatus", ResponseStatus.QUERYWASSUCCESS);// '0'
			map.put("msg", ResponseStatus.QUERYWASSUCCESS_CN_MSG);// '查询成功'
			map.put("obj", allPassList);
		} else {
			map.put("ResponseStatus", ResponseStatus.EMPINFONULL);// '1'
			map.put("msg", ResponseStatus.EMPINFONULL_CN_MSG);// '无招聘信息'
		}

		return map;
	}

	// 通过用户ID显示招聘列表
	@ResponseBody
	@RequestMapping("selectAllByauId")
	public Map<String, Object> selectAllByauId(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("auId") String auId,
			@RequestParam("pstart") String pstart,
			@RequestParam("psize") String psize) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Map<String, Object> m = new LinkedHashMap<String, Object>();
		Integer pstarts = Integer.parseInt(pstart);
		Integer psizes = Integer.parseInt(psize);
		m.put("auId", auId);
		m.put("pstart", pstarts * psizes);
		m.put("psize", psizes);

		Store storeInfo = storeService.selectByauId(Integer.parseInt(auId));
		if (storeInfo == null) {
			map.put("ResponseStatus", ResponseStatus.EMPINFONULL);// '1'
			map.put("msg", ResponseStatus.EMPINFONULL_CN_MSG);// '无招聘信息'
		} else {

			List<Employ> selectAllByauId = employService.selectAllByauId(m);
			if (selectAllByauId.size() > 0) {
				map.put("ResponseStatus", ResponseStatus.QUERYWASSUCCESS);// '0'
				map.put("msg", ResponseStatus.QUERYWASSUCCESS_CN_MSG);// '查询成功'
				map.put("obj", selectAllByauId);
			} else {
				map.put("ResponseStatus", ResponseStatus.EMPINFONULL);// '1'
				map.put("msg", ResponseStatus.EMPINFONULL_CN_MSG);// '无招聘信息'
			}
		}
		return map;
	}

	// 通过用户ID显示招聘数量
	@ResponseBody
	@RequestMapping("selectNumByauId")
	public Map<String, Object> selectNumByauId(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("auId") String auId) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Integer empNum = employService.selectNumByauId(Integer.valueOf(auId));

		map.put("ResponseStatus", ResponseStatus.QUERYWASSUCCESS);// '0'
		map.put("msg", ResponseStatus.QUERYWASSUCCESS_CN_MSG);// '查询成功'
		map.put("empNum", empNum);

		return map;
	}
}
