package com.multi.bbs.shop.model.vo;

public class ProductParam {
	private String searchValue;

	private int page;
	private int limit;
	private int offset;

	public ProductParam() {
		super();
		page = 1;
		searchValue = "";
	}

	public ProductParam(String searchValue, int page, int limit, int offset) {
		super();
		this.searchValue = searchValue;
		this.page = page;
		this.limit = limit;
		this.offset = offset;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	@Override
	public String toString() {
		return "ProductParam [searchValue=" + searchValue + ", page=" + page + ", limit=" + limit + ", offset=" + offset
				+ "]";
	}

}
