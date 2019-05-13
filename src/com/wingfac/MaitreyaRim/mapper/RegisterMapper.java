package com.wingfac.MaitreyaRim.mapper;

import java.util.Map;

import com.wingfac.MaitreyaRim.po.AverageUser;
import com.wingfac.MaitreyaRim.po.AverageUserVo;

public interface RegisterMapper {

	public Integer insertAverUser(AverageUser averageUser);

	public AverageUser selectByMobile(String auMobile);

	public Integer updateauAvatar(Map<String, Object> map);

	public AverageUserVo selectByauId(Integer auId);

	public Integer updateauPasswordByauMoblie(Map<String, Object> map);

	public Integer updateauPasswordByauId(Map<String, Object> map);

	public Integer updateauAddress(Map<String, Object> map);
	
	public Integer updateauNick(Map<String, Object> map);

}
