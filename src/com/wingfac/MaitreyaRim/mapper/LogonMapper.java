package com.wingfac.MaitreyaRim.mapper;

import java.util.Map;

import com.wingfac.MaitreyaRim.po.AverageUserVo;

public interface LogonMapper {

	public AverageUserVo logon(Map<String, Object> map);

	public AverageUserVo jude(String auMobile);

}
