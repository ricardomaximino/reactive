package com.brasajava.reativeone.common.util;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.brasajava.reativeone.domain.entity.Event;

@Component
public class LeadEventBuilder {

	public Event createEvent(String id, String type, String operator) {
		return new Event(id, type, operator);
	}
	
	public Event createEvent(String id, String type, String operator, LocalDateTime dateTime) {
		return new Event(id, type, operator, dateTime);
	}
}