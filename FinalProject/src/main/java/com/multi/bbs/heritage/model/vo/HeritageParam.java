package com.multi.bbs.heritage.model.vo;

public class HeritageParam {
	private String keyword;
	private String region;
	private String category;
	private String generation;

	// 페이징 인자
	private int page;
	private int limit;
	private int offset;

	public HeritageParam() {
		super();
		page = 1;
	}

	public HeritageParam(String keyword, String region, String category, String generation, int page, int limit,
			int offset) {
		super();
		this.keyword = keyword;
		this.region = region;
		this.category = category;
		this.generation = generation;
		this.page = page;
		this.limit = limit;
		this.offset = offset;
	}

	@Override
	public String toString() {
		return "HeritageParam [keyword=" + keyword + ", region=" + region + ", category=" + category + ", generation="
				+ generation + ", page=" + page + ", limit=" + limit + ", offset=" + offset + "]";
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getGeneration() {
		return generation;
	}

	public void setGeneration(String generation) {
		this.generation = generation;
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
