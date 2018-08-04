package com.brasajava.reativeone.channel;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface LeadSenderChannelBinding {
	static final String CREATED_CHANNEL = "lead-sender-created";
	static final String UPDATED_CHANNEL = "lead-sender-updated";
	static final String DELETED_CHANNEL = "lead-sender-deleted";
	
	@Output(CREATED_CHANNEL)
	MessageChannel sendCreatedEvent();
	@Output(UPDATED_CHANNEL)
	MessageChannel sendUpdatedEvent();
	@Output(DELETED_CHANNEL)
	MessageChannel sendDeletedEvent();

}
