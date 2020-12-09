package com.cdm.web.dto;

public class Criteria {

    private int page;
    private int perPageNum;
    private int endPage;

    public Criteria() {
        this.page = 1;
        this.perPageNum = 10;
        this.endPage=10;
    }

    public int getEndPage() {	//시작 페이지 +9
		return (this.page - 1) * perPageNum +  perPageNum-1;
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
        return (this.page - 1) * perPageNum;
    }

   
}