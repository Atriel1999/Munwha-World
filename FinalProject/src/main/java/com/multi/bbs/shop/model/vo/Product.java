package com.multi.bbs.shop.model.vo;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Product {
	private int pno;
	private String title;
	private String description;
	private String price;
	private String image; // main 이미지
	private List<String> bigImageList; // 썸네일 + 제품 상세보기
	private List<String> contentImageList; // 설명글 
}
