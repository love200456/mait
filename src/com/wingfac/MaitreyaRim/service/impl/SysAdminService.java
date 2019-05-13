package com.wingfac.MaitreyaRim.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.wingfac.MaitreyaRim.mapper.SysAdminMapper;
import com.wingfac.MaitreyaRim.po.SysAdmin;

public class SysAdminService {

	@Autowired
	private SysAdminMapper sysAdminMapper;

	public SysAdmin selectSys(Map<String, Object> map) {
		return sysAdminMapper.selectSys(map);
	}

}
