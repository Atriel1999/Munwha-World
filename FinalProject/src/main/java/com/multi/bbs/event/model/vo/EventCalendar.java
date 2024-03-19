package com.multi.bbs.event.model.vo;

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
	public String seqNo;		// 고유 ID
	@Column(name = "SUBTITLE")
	public String subTitle;		// 행사 제목
	@Column(name = "SDATE")
	public String sDate;        // 시작일
	@Column(name = "EDATE")
	public String eDate;        // 종료일
	@Column(name = "SUBPATH")
	public String subPath;      // 관련 링크 URL
	@Column(name = "SUBDATE")
	public String subDate;      // 상세 일정
	@Column(name = "SUBCONTENT")
	public String subContent;   // 행사 내용
	@Column(name = "SITECODE")
	public String siteCode;     // 행사 종료 구분
	@Column(name = "GROUPNAME")
	public String groupName;    // 주최자
	@Column(name = "CONTACT")
	public String contact;      // 전화번호
	@Column(name = "SUBDESC")
	public String subDesc;      // 장소
	@Column(name = "SUBDESC2")
	public String subDesc2;     // 참가대상
	@Column(name = "SUBDESC3")
	public String subDesc3;     // 참가바
	@Column(name = "SIDO")
	public String sido;         // 주소(시도)
	@Column(name = "GUGUN")
	public String gugun;        // 주소(구군)
}
