package com.multi.bbs.heritage.model.vo;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "heritage")
@Transactional
@DynamicUpdate
@DynamicInsert
public class Heritage {
	@Id
	@Column(name = "hno")
	public int hno;
	@Column(name = "sn")
	public int sn;
	@Column(name = "ccbamnm1")
	public String ccbaMnm1; // 문화재 이름
	@Column(name = "ccbamnm2")
	public String ccbaMnm2;
	@Column(name = "ccmaname")
	public String ccmaName; // 유형
	@Column(name = "ccbakdcd")
	public String ccbaKdcd;
	@Column(name = "ccbactcd")
	public String ccbaCtcd;
	@Column(name = "ccbaasno")
	public String ccbaAsno;
	@Column(name = "longitude")
	public double longitude;
	@Column(name = "latitude")
	public double latitude;
	@Column(name = "gcodename")
	public String gcodeName;
	@Column(name = "bcodename")
	public String bcodeName;
	@Column(name = "mcodename")
	public String mcodeName;
	@Column(name = "scodename")
	public String scodeName;
	@Column(name = "date")
	public String date;
	@Column(name = "ccbalcad")
	public String ccbaLcad;
	@Column(name = "cccename")
	public String ccceName;
	@Column(name = "imageurl")
	public String imageUrl;
	@Column(name = "content")
	public String content;
	@Column(name = "rate")
	public double rate;
	@Column(name = "reviewcount")
	public int reviewCount;
	@Column(name = "viewcount")
	public int viewCount;
	@Column(name = "ccbactcdnm") // 지역
	public String ccbaCtcdNm;
	@Column(name = "ccsiname")
	public String ccsiName;
	@Column(name = "ccbaquan")
	public String ccbaQuan;
	@Column(name = "ccbaposs")
	public String ccbaPoss;
	@Column(name = "videourl")
	public String videoUrl;
}
