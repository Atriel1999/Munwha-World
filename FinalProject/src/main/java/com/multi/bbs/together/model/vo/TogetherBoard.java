//package com.multi.bbs.together.model.vo;
//
//import java.util.Date;
//import java.util.List;
//
//import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.DynamicInsert;
//import org.hibernate.annotations.DynamicUpdate;
//
//import com.multi.bbs.member.model.vo.Member;
//
//import jakarta.persistence.CascadeType;
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.OneToMany;
//import jakarta.persistence.Temporal;
//import jakarta.persistence.TemporalType;
//import jakarta.transaction.Transactional;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity // 객체를 Table로 생성 시켜주는 어노테이션
//@Transactional
//@DynamicInsert
//@DynamicUpdate
//public class TogetherBoard {
//	// https://bsnippet.tistory.com/38
//	// https://velog.io/@dhk22/JPA-%EC%96%91%EB%B0%A9%ED%96%A5-%EC%97%B0%EA%B4%80%EA%B4%80%EA%B3%84
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private int bno;
//
//	@Column(name = "cno")
//	private String cno;
//	
//	@Column(length = 100)
//	private String title;
//	
//	//@Column(columnDefinition = "TEXT") // 컬럼의 속성을 TEXT 바꿔주는 어노테이션, 주의 : DB 호환성 생각 필요 
//	//private String content;
//	@Column(length = 500) // 컬럼의 속성을 TEXT 바꿔주는 어노테이션, 주의 : DB 호환성 생각 필요 
//	private String content;
//	
//	private int readCount;//조회수
//
//	@Temporal(TemporalType.TIMESTAMP)
//	@CreationTimestamp
//	private Date createDate;
//	
//	@Temporal(TemporalType.TIMESTAMP)
//	@CreationTimestamp
//	private Date modifyDate;
//	
//	@Column(name = "bestcount")
//	private int bestcount;
//	
//	@Column(name = "mno")
//	private int mno;
//	
//	@OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE) // cascade 제약 가능하다.
////	@JoinColumn(name = "r_no") // 리플의 외래키의 네이밍을 설정할때, 대부분 PK로 자동셋팅 됨으로 굳이 필요 없음
//	private List<TogetherBoardReply> replyList;
//	
//	
//	@ManyToOne
//	private Member member;
//	
//	@ManyToOne
//	private TogetherBoardCategory boardCategory;
//	
//	@OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE) // cascade 제약 가능하다.
////	@JoinColumn(name = "r_no") // 리플의 외래키의 네이밍을 설정할때, 대부분 PK로 자동셋팅 됨으로 굳이 필요 없음
//	private List<TogetherBoardAttachFile> boardAttachFileList;
//
//	@Override
//	public String toString() {
//		return "Board [bno=" + bno + ", title=" + title + ", content=" + content + ", readCount=" + readCount
//				+ ", createDate=" + createDate + ", modifyDate=" + modifyDate + ", replyList=" + replyList + ", member="
//				+ member + ", boardCategory=" + boardCategory + ", boardAttachFileList=" + boardAttachFileList + "]";
//	}
//	
//	
//	
//}
//
//
//
//
//
