package com.multi.bbs.event.model.vo;

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
@Entity(name = "EVENTCALENDAR")
@Transactional
public class EventCalendar {
	@Id
	@Column(name = "SEQNO")
	public String seqno;		// 고유 ID
	@Column(name = "SUBTITLE")
	public String subtitle;		// 행사 제목
	@Column(name = "SDATE")
	public int sdate;        // 시작일
	@Column(name = "EDATE")
	public int edate;        // 종료일
	@Column(name = "SUBPATH")
	public String subpath;      // 관련 링크 URL
	@Column(name = "SUBDATE")
	public String subdate;      // 상세 일정
	@Column(name = "SUBCONTENT")
	public String subcontent;   // 행사 내용
	@Column(name = "SITECODE")
	public String sitecode;     // 행사 종료 구분
	@Column(name = "GROUPNAME")
	public String groupname;    // 주최자
	@Column(name = "CONTACT")
	public String contact;      // 전화번호
	@Column(name = "SUBDESC")
	public String subdesc;      // 장소
	@Column(name = "SUBDESC2")
	public String subdesc2;     // 참가대상
	@Column(name = "SUBDESC3")
	public String subdesc3;     // 참가바
	@Column(name = "SIDO")
	public String sido;         // 주소(시도)
	@Column(name = "GUGUN")
	public String gugun;        // 주소(구군)
	@Column(name = "read_count")
	private int readCount;
}
