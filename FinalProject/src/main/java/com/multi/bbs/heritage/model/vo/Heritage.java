package com.multi.bbs.heritage.model.vo;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "heritage")
@Transactional
public class Heritage {
	@Id
	@Column(name = "hno")
	public int hno;
	@Column(name = "sn")
	public int sn;
	@Column(name = "ccbaMnm1")
	public String ccbaMnm1;
	@Column(name = "ccbaMnm2")
	public String ccbaMnm2;
	@Column(name = "ccmaName")
	public String ccmaName;
	@Column(name = "ccbaKdcd")
	public String ccbaKdcd;
	@Column(name = "ccbaCtcd")
	public String ccbaCtcd;
	@Column(name = "ccbaAsno")
	public String ccbaAsno;
	@Column(name = "longitude")
	public double longitude;
	@Column(name = "latitude")
	public double latitude;
	@Column(name = "gcodeName")
	public String gcodeName;
	@Column(name = "bcodeName")
	public String bcodeName;
	@Column(name = "mcodeName")
	public String mcodeName;
	@Column(name = "scodeName")
	public String scodeName;
	@Column(name = "date")
	public String date;
	@Column(name = "ccbaLcad")
	public String ccbaLcad;
	@Column(name = "ccceName")
	public String ccceName;
	@Column(name = "imageUrl")
	public String imageUrl;
	@Column(name = "content")
	public String content;
	@Column(name = "rate")
	public double rate;
	@Column(name = "reviewCount")
	@ColumnDefault("0")
	public int reviewCount;
	@Column(name = "viewCount")
	@ColumnDefault("0")
	public int viewCount;
	@Column(name = "ccbaCtcdNm")
	public String ccbaCtcdNm;
	@Column(name = "ccsiName")
	public String ccsiName;
	@Column(name = "ccbaQuan")
	public String ccbaQuan;
	@Column(name = "ccbaPoss")
	public String ccbaPoss;
	@Column(name = "videoUrl")
	public String videoUrl;
}
