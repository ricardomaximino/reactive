package com.brasajava.reativeone.domain.entity;

import java.time.LocalDateTime;

public class Event {
	public static final String HEADER_TYPE = "type";
	public static final String CREATE_OPERATION = "created";
	public static final String UPDATE_OPERATION = "updated";
	public static final String DELETE_OPERATION = "deleted";

	private String id;
	private String type;
	private String operator;
	private LocalDateTime dateTime;

	public Event() {
	}

	public Event(String id, String type, String operator) {
		this.id = id;
		this.type = type;
		this.operator = operator;
		this.dateTime = LocalDateTime.now();
	}

	public Event(String id, String type, String operator, LocalDateTime dateTime) {
		this.id = id;
		this.type = type;
		this.operator = operator;
		this.dateTime = dateTime;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", type=" + type + ", operator=" + operator + ", dateTime=" + dateTime + "]";
	}

}
