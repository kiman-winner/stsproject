package com.cdm.web.page;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class PageMaker {// 페이지 관리
	private int totalCount; // 전체 게시글 갯수
	private int startPage;// 시작 페이지
	private int endPage;// 페이지 양
	private boolean prev;// 다음 페이지
	private boolean next;// 이전 페이지

	private Criteria criteria;//페이지 표준 

	private int displayPageNum = 5; // 하단 페이지 번호 갯수

	public boolean isPrev() {
		return prev;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public boolean isNext() {
		return next;
	}

	public Criteria getCriteria() {
		return criteria;
	}

	public void setCriteria(Criteria criteria) {
		this.criteria = criteria;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calcData();
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public String makeSearch(int page) {	//url 제작

		UriComponents uriComponents = UriComponentsBuilder.newInstance().queryParam("page", page)
				.queryParam("searchType", ((SearchCriteria) criteria).getSearchType())
				.queryParam("keyword", encoding(((SearchCriteria) criteria).getKeyword())).build();

		return uriComponents.toUriString();
	}

	private String encoding(String keyword) { // 인코딩 처리
		if (keyword == null || keyword.trim().length() == 0) {
			return "";
		}

		try {
			return URLEncoder.encode(keyword, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

	private void calcData() {
		// 화면에 보여질 마지막 페이지, 버림(현재페이지/화면에 보여질 페이지개수) * 화면에 보여질 페이지 개수
		endPage = (int) (Math.ceil(criteria.getPage() / (double) displayPageNum) * displayPageNum);

		startPage = (endPage - displayPageNum) + 1;

		// 실제 마지막 페이지
		int tempEndPage = (int) (Math.ceil(totalCount / (double) criteria.getPerPageNum()));

		if (endPage > tempEndPage) {
			endPage = tempEndPage;
		}

		prev = startPage == 1 ? false : true; // 시작 페이지가 1이 아니면 이전페이지 존재

		next = endPage * criteria.getPerPageNum() >= totalCount ? false : true; //홤녀상 마지막 페이지가  전체 마지막 페이지보다 적다면 다음 페이지 존재

	}

}
