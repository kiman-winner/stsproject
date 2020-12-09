package com.cdm.web.dto;

public class PageMaker {
	private int totalCount;	//��ü �Խñ� ����
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	
	private Criteria criteria;

	private int displayPageNum = 5;	//�ϴ� ������ ��ȣ ����

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

	private void calcData() {
		//ȭ�鿡 ������ ������ ������  
		endPage = (int) (Math.ceil(criteria.getPage() / (double) displayPageNum) * displayPageNum);

		startPage = (endPage - displayPageNum) + 1;	 

		//���� ������ ������
		int tempEndPage = (int) (Math.ceil(totalCount / (double) criteria.getPerPageNum()));

		if (endPage > tempEndPage) {
			endPage = tempEndPage;
		}

		prev = startPage == 1 ? false : true;	//���� �������� 1�� �ƴϸ� ���������� ����

		next = endPage * criteria.getPerPageNum() >= totalCount ? false : true;

	}
}