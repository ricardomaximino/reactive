package com.brasajava.reativeone.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import com.brasajava.reativeone.channel.LeadReceiverChannelBinding;
import com.brasajava.reativeone.domain.entity.Event;

import reactor.core.publisher.Flux;

//@EnableBinding(LeadReceiverChannelBinding.class)
//@EnableAutoConfiguration
@Component
public class LeadQueueListener {

	private static Logger Log = LoggerFactory.getLogger(LeadQueueListener.class); 
	
	@StreamListener
	public void receiveCreatedEvent(@Input(LeadReceiverChannelBinding.CREATED_CHANNEL) Flux<Message<Event>> fluxMessage) {
		Log.debug("LEADQUEUELISTENER => LISTEN TO LEAD CREATED EVENT QUEUE");
		fluxMessage.map(message -> {
			Log.debug("LEADQUEUELISTENER => RECEIVED CREATED EVENT MESSAGE FROM THE QUEUE --> " + message);
			return message;
		}).doOnError(error -> {
			Log.error("LEADQUEUELISTENER => RECEIVED AN ERROR FROM THE QUEUE OF CREATED EVENT --> " + error);
		}).subscribe();
	}

	@StreamListener
	public void receiveUpdatedEvent(@Input(LeadReceiverChannelBinding.UPDATED_CHANNEL) Flux<Message<Event>> fluxMessage) {
		Log.debug("LEADQUEUELISTENER => LISTEN TO LEAD UPDATED EVENT QUEUE");
		fluxMessage.map(message -> {
			Log.debug("LEADQUEUELISTENER => RECEIVED UPDATED EVENT MESSAGE FROM THE QUEUE --> " + message);
			return message;
		}).doOnError(error -> {
			Log.error("LEADQUEUELISTENER => RECEIVED AN ERROR FROM THE QUEUE OF UPDATED EVENT --> " + error);
		}).subscribe();
	}

	@StreamListener
	public void receiveDeletedEvent(@Input(LeadReceiverChannelBinding.DELETED_CHANNEL) Flux<Message<Event>> fluxMessage) {
		Log.debug("LEADQUEUELISTENER => LISTEN TO LEAD DELETED EVENT QUEUE");
		fluxMessage.map(message -> {
			Log.debug("LEADQUEUELISTENER => RECEIVED DELETED EVENT MESSAGE FROM THE QUEUE --> " + message);
			return message;
		}).doOnError(error -> {
			Log.error("LEADQUEUELISTENER => RECEIVED AN ERROR FROM THE QUEUE OF DELETED EVENT --> " + error);
		}).subscribe();
	}

}
