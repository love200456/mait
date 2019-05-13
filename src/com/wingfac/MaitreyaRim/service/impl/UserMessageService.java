package com.wingfac.MaitreyaRim.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.wingfac.MaitreyaRim.mapper.UserMessageMapper;
import com.wingfac.MaitreyaRim.po.UserMessage;

public class UserMessageService {

	@Autowired
	private UserMessageMapper userMessageMapper;

	public Integer insertUM(UserMessage userMessage) {
		return userMessageMapper.insertUM(userMessage);
	}

	public List<UserMessage> selectByauId(Integer auId) {
		return userMessageMapper.selectByauId(auId);
	}

	public Integer delectByauId(Integer auId) {
		return userMessageMapper.delectByauId(auId);
	}

	public Integer deleteUserAnnouncement(Integer um_id) {
		return userMessageMapper.deleteUserAnnouncement(um_id);
	}
}
