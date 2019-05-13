package com.wingfac.MaitreyaRim.mapper;

import java.util.List;
import java.util.Map;

import com.wingfac.MaitreyaRim.po.UserStoreFollow;

public interface UserStoreFollowMapper {

	public Integer insertUSF(UserStoreFollow userStoreFollow);

	public Integer delectUSF(Map<String, Object> map);

	public List<UserStoreFollow> selectByauId(Map<String, Object> map);

	public UserStoreFollow selectByAuidSid(Map<String, Object> map);

	public Integer delectByauId(Integer auId);

	public List<UserStoreFollow> selByauId(Integer auId);

}
