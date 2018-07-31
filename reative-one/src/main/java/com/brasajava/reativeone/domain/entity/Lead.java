package com.brasajava.reativeone.domain.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Lead implements Serializable{

	private static final long serialVersionUID = -7609437790563785736L;
	@Id
	private String id;
	private String name;
	private String lastname;
	private LocalDateTime createDateTime;
	private LocalDateTime updateDateTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public LocalDateTime getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(LocalDateTime createDateTime) {
		this.createDateTime = createDateTime;
	}
	public LocalDateTime getUpdateDateTime() {
		return updateDateTime;
	}
	public void setUpdateDateTime(LocalDateTime updateDateTime) {
		this.updateDateTime = updateDateTime;
	}
	@Override
	public String toString() {
		return "Lead [id=" + id + ", name=" + name + ", lastname=" + lastname + ", createDateTime=" + createDateTime
				+ ", updateDateTime=" + updateDateTime + "]";
	}
	
}
