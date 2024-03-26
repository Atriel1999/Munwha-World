package com.multi.bbs.heritage.model.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "himage")
@Transactional
public class Himage {
	@Id
	@Column(name = "himageno")
	public int hImageNo;
	@Column(name = "ccbakdcd")
	public String ccbaKdcd;
	@Column(name = "ccbaasno")
	public String ccbaAsno;
	@Column(name = "ccbactcd")
	public String ccbaCtcd;
	@Column(name = "ccbamnm1")
	public String ccbaMnm1;
	@Column(name = "ccbamnm2")
	public String ccbaMnm2;
	@Column(name = "sn1")
	public int sn1;
	@Column(name = "imagenuri1")
	public String imageNuri1;
	@Column(name = "imageurl1")
	public String imageUrl1;
	@Column(name = "ccimdesc1")
	public String ccimDesc1;
	@Column(name = "sn2")
	public int sn2;
	@Column(name = "imagenuri2")
	public String imageNuri2;
	@Column(name = "imageurl2")
	public String imageUrl2;
	@Column(name = "ccimdesc2")
	public String ccimDesc2;
	@Column(name = "sn3")
	public int sn3;
	@Column(name = "imagenuri3")
	public String imageNuri3;
	@Column(name = "imageurl3")
	public String imageUrl3;
	@Column(name = "ccimdesc3")
	public String ccimDesc3;
	@Column(name = "sn4")
	public int sn4;
	@Column(name = "imagenuri4")
	public String imageNuri4;
	@Column(name = "imageurl4")
	public String imageUrl4;
	@Column(name = "ccimdesc4")
	public String ccimDesc4;
	@Column(name = "sn5")
	public int sn5;
	@Column(name = "imagenuri5")
	public String imageNuri5;
	@Column(name = "imageurl5")
	public String imageUrl5;
	@Column(name = "ccimdesc5")
	public String ccimDesc5;
}
