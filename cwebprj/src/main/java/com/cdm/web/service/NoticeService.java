package com.cdm.web.service;

import java.sql.SQLException;
import java.util.List;

import com.cdm.web.vo.NoticeVO;

public interface NoticeService {

	List<NoticeVO> getList(int page, String field, String query) throws ClassNotFoundException, SQLException;

	int getCount() throws ClassNotFoundException, SQLException;

	int insert(NoticeVO notice) throws SQLException, ClassNotFoundException;

	int update(NoticeVO notice) throws SQLException, ClassNotFoundException;

	int delete(int id) throws ClassNotFoundException, SQLException;

}
