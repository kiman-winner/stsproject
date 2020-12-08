package com.cdm.web.service;

import java.sql.SQLException;
import java.util.List;

import com.cdm.web.dto.NoticeDTO;

public interface NoticeService {

	List<NoticeDTO> getList(int page, String field, String query) throws ClassNotFoundException, SQLException;

	int getCount() throws ClassNotFoundException, SQLException;

	int insert(NoticeDTO notice) throws SQLException, ClassNotFoundException;

	int update(NoticeDTO notice) throws SQLException, ClassNotFoundException;

	int delete(int id) throws ClassNotFoundException, SQLException;

}
