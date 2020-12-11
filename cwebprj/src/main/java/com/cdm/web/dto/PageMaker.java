package com.cdm.web.dto;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class PageMaker {
	private int totalCount;	//전체 게시글 갯수
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	
	private Criteria criteria;

	private int displayPageNum = 5;	//하단 페이지 번호 갯수

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

	
	public String makeSearch(int page) {
		
		 UriComponents uriComponents = UriComponentsBuilder.newInstance()
		            .queryParam("page", page)
		            .queryParam("searchType", ((SearchCriteria) criteria).getSearchType())
		            .queryParam("keyword", encoding(((SearchCriteria) criteria).getKeyword()))
		            .build();

		    return uriComponents.toUriString();
		
	}
	private String encoding(String keyword) {	//인코딩 처리 
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
		//화면에 보여질 마지막 페이지  
		endPage = (int) (Math.ceil(criteria.getPage() / (double) displayPageNum) * displayPageNum);

		startPage = (endPage - displayPageNum) + 1;	 

		//실제 마지막 페이지
		int tempEndPage = (int) (Math.ceil(totalCount / (double) criteria.getPerPageNum()));

		if (endPage > tempEndPage) {
			endPage = tempEndPage;
		}

		prev = startPage == 1 ? false : true;	//시작 페이지가 1이 아니면 이전페이지 존재

		next = endPage * criteria.getPerPageNum() >= totalCount ? false : true;

	}
	
	
}
