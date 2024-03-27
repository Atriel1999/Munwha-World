package com.multi.bbs.shop.model.vo;

import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@Entity(name = "Product")
@Transactional
public class Product {
	@Id
	@Column(name = "pno")
	private int pno;
	@Column(name = "TITLE")
	private String title;
	@Column(name = "DESCRIPTION")
	private String description;
	@Column(name = "LINK")
	private String link;
	@Column(name = "PRICE")
	private int price;
	@Column(name = "MALLNAME")
	private String mallname;
	@Column(name = "BRAND")
	private String brand;
	@Column(name = "imgurl1")
	private String imgurl1;
	@Column(name = "imgurl2")
	private String imgurl2;
	@Column(name = "imgurl3")
	private String imgurl3;
	@Column(name = "imgurl4")
	private String imgurl4;
}
