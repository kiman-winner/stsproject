package com.cdm.web.dao;

import java.util.List;

import com.cdm.web.dto.CommunityDTO;
import com.cdm.web.dto.Criteria;

public interface CommunityDAO {
	
	public void write(CommunityDTO vo) throws Exception ;
	public List<CommunityDTO> read(Criteria criteria) throws Exception ;
	public CommunityDTO detail(int community_num)throws Exception ;
	public void delete(int community_num)throws Exception ;
	void modify(CommunityDTO vo) throws Exception;
	public void updateViewCount(int community_num)throws Exception;
	public int listCount() throws Exception;	//게시물 총 개수 
}
