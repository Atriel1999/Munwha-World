package com.multi.bbs.museum.model.vo;



public class MuseumParam {
	// html - form의 name과 일치하는 파라메터
	private String searchAddress;  
	private String searchName;
	
	// 페이징인자
	private int page;        // 현재페이지
	private int limit;       // 한페이지 최대항목수 
	private int offset; 
	
	public MuseumParam() {
		super();
		page = 1; 
		searchAddress = "";
	}
	
	public MuseumParam(String searchAddress, String searchName, int page, int limit, int offset) {
		super();
		this.searchAddress = searchAddress;
		this.searchName = searchName;
		
		this.page = page;
		this.limit = limit;
		this.offset = offset;
	}
	
	@Override
	public String toString() {
		return "MuseumParam [searchAddress=" + searchAddress + ", searchName=" + searchName + 
				", page=" + page + ", limit=" + limit + ", offset=" + offset + "]";
	}
	
	public String getsearchAddress() {
		if(searchAddress == null) {
			return "";
		}
		return searchAddress;
	}
	
	public void setsearchAddress(String searchAddress) {
		this.searchAddress = searchAddress;
	}
	
	public String getsearchName() {
		return searchName;
	}

	public void setsearchName(String searchName) {
		this.searchName = searchName;
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

	
	
	


}
