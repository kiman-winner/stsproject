package com.cdm.web.page;

public class SearchCriteria extends Criteria {	//ǥ�� ���, �˻� ��� �߰�

	private String searchType;// �˻� Ÿ��
	private String keyword;// �˻� ��

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
