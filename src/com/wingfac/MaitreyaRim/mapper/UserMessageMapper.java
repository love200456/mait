package com.wingfac.MaitreyaRim.mapper;

import java.util.List;

import com.wingfac.MaitreyaRim.po.UserMessage;

public interface UserMessageMapper {

	public Integer insertUM(UserMessage userMessage);

	public List<UserMessage> selectByauId(Integer auId);

	public Integer delectByauId(Integer auId);

	public Integer deleteUserAnnouncement(Integer um_id);

}
