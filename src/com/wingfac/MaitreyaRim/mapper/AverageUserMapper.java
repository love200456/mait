package com.wingfac.MaitreyaRim.mapper;

import java.util.List;
import java.util.Map;

import com.wingfac.MaitreyaRim.po.AverageUser;

public interface AverageUserMapper {

	public List<AverageUser> selectAll();

	public List<AverageUser> selectAllFen(Map<String, Object> map);

	public List<AverageUser> selectLike(String auMobile);

	public List<AverageUser> selectLikeFen(Map<String, Object> map);

	public AverageUser selectByauId(Integer auId);

	public Integer updateauIdentity(Integer auId);

	public Integer updateauIntegral(Map<String, Object> map);

	public Integer updateLisstate(Map<String, Object> map);

	public Integer updateLisPis(Map<String, Object> map);

	public Integer deleteUaer(Integer auId);

	public Integer modifyRemarks(Map<String, Object> map);
	
	public Integer modifyIntegralRemark(Map<String, Object> map);
	
	public Integer updateMoney(Map<String,Object> map);

}
