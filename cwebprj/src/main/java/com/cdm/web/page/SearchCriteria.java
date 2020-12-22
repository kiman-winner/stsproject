package com.cdm.web.page;

public class SearchCriteria extends Criteria {	//표준 상속, 검색 기능 추가

	private String searchType;// 검색 타입
	private String keyword;// 검색 명

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
}
