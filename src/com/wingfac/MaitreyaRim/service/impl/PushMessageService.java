package com.wingfac.MaitreyaRim.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.wingfac.MaitreyaRim.mapper.PushMessageMapper;
import com.wingfac.MaitreyaRim.po.PushMessage;

public class PushMessageService {

	@Autowired
	private PushMessageMapper pushMessageMapper;

	public Integer insertPM(Map<String, Object> map) {
		return pushMessageMapper.insertPM(map);
	}
	
	public PushMessage selectId(Integer pm_id){
		return pushMessageMapper.selectId(pm_id);
	}

	public List<PushMessage> selectAll() {
		return pushMessageMapper.selectAll();
	}

	public List<PushMessage> selectAllPage(Map<String, Object> map) {
		return pushMessageMapper.selectAllPage(map);
	}

	public Integer updatePM(Map<String, Object> map) {
		return pushMessageMapper.updatePM(map);
	}
	
	public Integer delectPM(String id){
		return pushMessageMapper.delectPM(id.split(","));
	}

}
