package com.cdm.web.dao;

import java.util.List;

import com.cdm.web.dto.CommunityDTO;

public interface CommunityDAO {
	
	public void write(CommunityDTO vo) throws Exception ;
	public List<CommunityDTO> read() throws Exception ;
	public CommunityDTO detail(int community_num)throws Exception ;
	public void delete(int community_num)throws Exception ;
	void modify(CommunityDTO vo) throws Exception;
}
