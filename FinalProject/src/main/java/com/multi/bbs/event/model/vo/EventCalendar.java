package com.multi.bbs.event.model.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class EventCalendar {
	@Id
	public String seqNo;		// 고유 ID
	@Column
	public String subTitle;		// 행사 제목
	@Column
	public String sDate;        // 시작일
	@Column
	public String eDate;        // 종료일
	@Column
	public String subPath;      // 관련 링크 URL
	@Column
	public String subDate;      // 상세 일정
	@Column
	public String subContent;   // 행사 내용
	@Column
	public String siteCode;     // 행사 종료 구분
	@Column
	public String groupName;    // 주최자
	@Column
	public String contact;      // 전화번호
	@Column
	public String subDesc;      // 장소
	@Column
	public String subDesc2;     // 참가대상
	@Column
	public String subDesc3;     // 참가바
	@Column
	public String sido;         // 주소(시도)
	@Column
	public String gugun;        // 주소(구군)
}
