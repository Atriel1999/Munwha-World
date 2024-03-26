package com.multi.bbs.museum.model.vo;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.multi.bbs.member.model.vo.Member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity // 객체를 Table로 생성 시켜주는 어노테이션
@Transactional
@DynamicInsert
@DynamicUpdate
@Table(name = "museumreply")
public class MuseumReply {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int rno;
	
	@ManyToOne
	private Member member;
	
	@ManyToOne
    private Museum museum;
	
	
	@Column(length = 2000)
	private String content;	
	
	@Column
    private int rating; // 평점
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createdate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date modifydate;
	
	private String status;
	
	
	
	@Override
	public String toString() {
		return "Reply [rno=" + rno + ", content=" + content +   ", rating=" + rating + ", createdate=" + createdate + ", modifydate="
				+ modifydate + "]";
	}

}

