package com.multi.bbs.heritage.model.vo;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.multi.bbs.member.model.vo.Member;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
public class EventBookmark {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int fno;
	
	@ManyToOne
	private Heritage heritage; // 문화재연결
	
	@ManyToOne
	private Member member; // 회원연결
	
	
}
