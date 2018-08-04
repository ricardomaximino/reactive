package com.brasajava.reativeone.service;

import com.brasajava.reativeone.domain.entity.Event;

public interface LeadEventSender {

	void sendLeadCreatedEvent(Event event);
	void sendLeadUpdatedEvent(Event event);
	void sendLeadDeletedEvent(Event event);
}
