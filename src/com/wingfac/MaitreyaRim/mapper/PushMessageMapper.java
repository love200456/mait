package com.wingfac.MaitreyaRim.mapper;

import java.util.List;
import java.util.Map;

import com.wingfac.MaitreyaRim.po.PushMessage;

public interface PushMessageMapper {

	public Integer insertPM(Map<String, Object> map);

	public PushMessage selectId(Integer pm_id);

	public List<PushMessage> selectAll();

	public List<PushMessage> selectAllPage(Map<String, Object> map);

	public Integer updatePM(Map<String, Object> map);

	public Integer delectPM(String[] id);

}
