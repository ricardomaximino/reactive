package com.brasajava.reativeone.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import com.brasajava.reativeone.domain.entity.Event;
import com.brasajava.reativeone.service.LeadEventSender;

@Component
public class LeadDomainEventsListener {
	private static Logger Log = LoggerFactory.getLogger(LeadDomainEventsListener.class);	
	private LeadEventSender sender;

	public LeadDomainEventsListener(LeadEventSender sender) {
		Log.debug("LEADDOMAINEVENTSLISTENER => LISTEN SPRING DOMAIN EVENTS");
		this.sender = sender;
	}

	@EventListener
	public void notifyDomainEvent(Message<Event> message) {

		switch (message.getHeaders().get(Event.HEADER_TYPE).toString()) {
		case Event.CREATE_OPERATION:
			Log.debug("LEADDOMAINEVENTSLISTENER => RECEIVED SPRING LEAD CREATED EVENT --> " + message.getPayload());
			sender.sendLeadCreatedEvent(message.getPayload());
			break;
		case Event.UPDATE_OPERATION:
			Log.debug("LEADDOMAINEVENTSLISTENER => RECEIVED SPRING LEAD UPDATED EVENT --> " + message.getPayload());
			sender.sendLeadUpdatedEvent(message.getPayload());
			break;

		default:
			Log.error("LEADDOMAINEVENTSLISTENER => RECEIVED AN UNKNOWN SPRING EVENT --> " + message);;
			break;
		}
	}
}
