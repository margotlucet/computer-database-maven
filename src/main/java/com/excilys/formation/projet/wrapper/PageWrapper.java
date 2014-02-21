package com.excilys.formation.projet.wrapper;

import java.util.List;

import com.excilys.formation.projet.util.Constant;

public class PageWrapper<T> {
	private List<T> elementList;
	private int currPage;
	private int pageCount;
	private int resultCount;
	private int resultsPerPage;
	private int currentResultCount;
	private String orderBy = Constant.NAME;
	private String orderDirection = Constant.ASC;



	public List<T> getElementList() {
		return elementList;
	}

	public void setElementList(List<T> elementList) {
		this.elementList = elementList;
	}

	public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getResultCount() {
		return resultCount;
	}

	public void setResultCount(int resultCount) {
		this.resultCount = resultCount;
	}

	public int getResultsPerPage() {
		return resultsPerPage;
	}

	public void setResultsPerPage(int resultsPerPage) {
		this.resultsPerPage = resultsPerPage;
	}

	public int getCurrentResultCount() {
		return currentResultCount;
	}

	public void setCurrentResultCount(int currentResultCount) {
		this.currentResultCount = currentResultCount;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getOrderDirection() {
		return orderDirection;
	}

	public void setOrderDirection(String orderDirection) {
		this.orderDirection = orderDirection;
	}
	
	@Override
	public String toString() {
		return "PageWrapper [currPage="
				+ currPage + ", pageCount=" + pageCount + ", resultCount="
				+ resultCount + ", resultsPerPage=" + resultsPerPage
				+ ", currentResultCount=" + currentResultCount + "]";
	}

}
