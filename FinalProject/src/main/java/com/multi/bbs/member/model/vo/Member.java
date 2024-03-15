package com.multi.bbs.member.model.vo;

import java.util.Date;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity // 객체를 Table로 생성 시켜주는 어노테이션
@Transactional
@DynamicInsert
@DynamicUpdate
public class Member {
	@Id // PK 지정
	@GeneratedValue(strategy = GenerationType.IDENTITY) // id 자동 생성
	private int mno;
	
	@Column(length = 20, nullable = false, unique = true) // 텍스트 길이를 20으로 제한, nullable=false, NOT NULL, 중복 없이
	private String memberId;
	@Column(length = 100, nullable = false)
	private String password;
	
	@ColumnDefault("'ROLE_USER'") // 사실은 못쓰는 기능, 사유 = 객체에서 null이면 null로 채워준다. 
	private String role;  		  // @ColumnDefault를 사용하기 위해선 반드시 @DynamicInsert, @DynamicUpdate 활용
	private String name;
	private String phone;
	private String email;
	private String address;
	private String hobby;
	@ColumnDefault("'Y'")
	private String status;
	
	@Temporal(TemporalType.TIMESTAMP) // 시간을 현재시간으로 자동으로 생성하는 기능
	@CreationTimestamp
	private Date enrollDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date modifyDate;
}








