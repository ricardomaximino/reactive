package com.brasajava.reativeone.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface LeadReceiverChannelBinding {
	static final String CREATED_CHANNEL = "lead-receiver-created";
	static final String UPDATED_CHANNEL = "lead-receiver-updated";
	static final String DELETED_CHANNEL = "lead-receiver-deleted";
	
	@Input(CREATED_CHANNEL)
	SubscribableChannel listenCreatedEvent();
	@Input(UPDATED_CHANNEL)
	SubscribableChannel listenUpdatedEvent();
	@Input(DELETED_CHANNEL)
	SubscribableChannel listenDeletedEvent();
}
