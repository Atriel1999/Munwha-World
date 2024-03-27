package com.multi.bbs.museum.model.vo;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "museum")
public class Museum {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int msno;
	
	@Column(length = 100)
	private String msname;         // 박물관 이름
	
	@Column(length = 10)
	private String mstype;         // 박물관 타입
	
	@Column(length = 100)           
	private String msweekopen;     // 평일 오픈시간
	@Column(length = 100)
	private String msweekclose;
	@Column(length = 100)
	private String msholidayopen;
	@Column(length = 100)
	private String msholidayclose;
	
	@Column(length = 1000)
	private String msclosureinfo;       // 휴관정보
	
	@Column(length = 100)
	private String msadultcharge;      // 어른 요금 
	@Column(length = 100)
	private String msyngcharge;
	@Column(length = 100)
	private String mschildcharge;
	
	
	@Column(length = 100)
	private String mstel;            // 전화번호
	@Column(length = 1000)
	private String msaddress;        // 주소 
	
	@Column(length = 30)
	private String mslati;           // 라티 
	@Column(length = 30)
	private String mslong;           // 롱 
	
	@Column(length = 100)  
	private String msopername;      //  운영기관명 
	@Column(length = 30)
	private String msdate;          // 데이터 일자
	@Column(length = 3000)  
	private String mshomepage;   
	
	@Column // 컬럼의 속성을 TEXT 바꿔주는 어노테이션, 주의 : DB 호환성 생각 필요 
	private int reviewcount;
	private int favcount;
	private int rating;
	private int readcount;               // 조회수 
	
	@Transient
	private String imageUrl;
	
	
	@OneToMany(mappedBy = "museum", cascade = CascadeType.REMOVE) // cascade 제약 가능하다.
//	@JoinColumn(name = "r_no") // 리플의 외래키의 네이밍을 설정할때, 대부분 PK로 자동셋팅 됨으로 굳이 필요 없음
	private List<MuseumReply> replyList;      // 리플 
	
	
	@Override
	public String toString() {
		return "Museum [msno=" + msno + ", msname=" + msname + ", mstype=" + mstype + ", msweekopen=" + msweekopen 
				+ ", msweekclose=" + msweekclose + ", msholidayopen=" + msholidayopen + ", msholidayclose=" + msholidayclose
				+ ", msclosureinfo=" + msclosureinfo + ", msadultcharge=" + msadultcharge + ", msyngcharge=" + msyngcharge
				+ ", mschildcharge=" + mschildcharge + ", mstel=" + mstel + ", msaddress=" + msaddress
				+ ", mslati=" + mslati + ", mslong=" + mslong + ", msopername=" + msopername + ", msdate=" + msdate
				+ ", mshomepage=" + mshomepage + ", reviewcount=" + reviewcount + ", favcount=" + favcount 
				+ ", rating=" + rating + ",readcount=" + readcount + ", imageUrl=" + imageUrl + "]";
	}


	
	
	
}
