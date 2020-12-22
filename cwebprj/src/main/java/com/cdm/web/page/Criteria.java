package com.cdm.web.page;

public class Criteria { //페이지 메이커 사용 표준

	private int page;	//요청 페이지
	private int perPageNum;//한페이지당 게시물 
	private int endPage;//출력 수 

	public Criteria() {
		this.page = 1;
		this.perPageNum = 10;
		this.endPage = 10;
	}

	public int getEndPage() { // 시작 페이지 +9
		return (this.page - 1) * perPageNum + perPageNum;
	}

	public void setPage(int page) {

		if (page <= 0) {
			this.page = 1;
			return;
		}

		this.page = page;
	}

	public int getPage() {
		return page;
	}

	public void setPerPageNum(int perPageNum) {

		if (perPageNum <= 0 || perPageNum > 100) {
			this.perPageNum = 10;
			return;
		}

		this.perPageNum = perPageNum;
	}

	public int getPerPageNum() {

		return this.perPageNum;
	}

	public int getPageStart() {
		return (this.page - 1) * perPageNum + 1;
	}

}