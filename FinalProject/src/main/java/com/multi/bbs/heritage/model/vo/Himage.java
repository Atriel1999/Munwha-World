package com.multi.bbs.heritage.model.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
	@Column(name = "hImageNo")
	public int hImageNo;
	@Column(name = "ccbaKdcd")
	public String ccbaKdcd;
	@Column(name = "ccbaAsno")
	public String ccbaAsno;
	@Column(name = "ccbaCtcd")
	public String ccbaCtcd;
	@Column(name = "ccbaMnm1")
	public String ccbaMnm1;
	@Column(name = "ccbaMnm2")
	public String ccbaMnm2;
	@Column(name = "sn1")
	public int sn1;
	@Column(name = "imageNuri1")
	public String imageNuri1;
	@Column(name = "imageUrl1")
	public String imageUrl1;
	@Column(name = "ccimDesc1")
	public String ccimDesc1;
	@Column(name = "sn2")
	public int sn2;
	@Column(name = "imageNuri2")
	public String imageNuri2;
	@Column(name = "imageUrl2")
	public String imageUrl2;
	@Column(name = "ccimDesc2")
	public String ccimDesc2;
	@Column(name = "sn3")
	public int sn3;
	@Column(name = "imageNuri3")
	public String imageNuri3;
	@Column(name = "imageUrl3")
	public String imageUrl3;
	@Column(name = "ccimDesc3")
	public String ccimDesc3;
	@Column(name = "sn4")
	public int sn4;
	@Column(name = "imageNuri4")
	public String imageNuri4;
	@Column(name = "imageUrl4")
	public String imageUrl4;
	@Column(name = "ccimDesc4")
	public String ccimDesc4;
	@Column(name = "sn5")
	public int sn5;
	@Column(name = "imageNuri5")
	public String imageNuri5;
	@Column(name = "imageUrl5")
	public String imageUrl5;
	@Column(name = "ccimDesc5")
	public String ccimDesc5;
}
