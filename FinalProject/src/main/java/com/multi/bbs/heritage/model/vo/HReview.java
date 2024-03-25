package com.multi.bbs.heritage.model.vo;

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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Transactional
@DynamicInsert
@DynamicUpdate
public class HReview {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int rno;
	
	@Column(length = 1000)
	private String rcontent;
	
	@ManyToOne
	private Member member; // 회원 연결
	
	@ManyToOne
	private Heritage heritage; // 문화재 연결
	
	@Column
	private int rate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date modDate;
}
