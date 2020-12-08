package com.cdm.web.service;

import java.sql.SQLException;
import java.util.List;

import com.cdm.web.dto.CommunityDTO;
import com.cdm.web.dto.NoticeDTO;

public interface CommunityService {

	public void write(CommunityDTO vo) throws Exception;

	List<CommunityDTO> read() throws Exception;

	CommunityDTO detail(int community_num) throws Exception;
}
