package com.multi.bbs.event.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multi.bbs.event.model.vo.EventCalendar;

public interface EventCalendarRepository extends JpaRepository<EventCalendar, String> {
	List<EventCalendar> findAll();
}
