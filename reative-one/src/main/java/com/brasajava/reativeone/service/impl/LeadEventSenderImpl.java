package com.brasajava.reativeone.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.reactive.FluxSender;
import org.springframework.cloud.stream.reactive.StreamEmitter;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.brasajava.reativeone.channel.LeadSenderChannelBinding;
import com.brasajava.reativeone.domain.entity.Event;
import com.brasajava.reativeone.service.LeadEventSender;

import reactor.core.publisher.Flux;

//@EnableAutoConfiguration
//@EnableBinding(LeadSenderChannelBinding.class)
@Component
public class LeadEventSenderImpl implements LeadEventSender {
	private static Logger Log = LoggerFactory.getLogger(LeadEventSenderImpl.class);
	private FluxSender createdFluxSender;
	private FluxSender updatedFluxSender;
	private FluxSender deletedFluxSender;

	@StreamEmitter
	public void setCreatedFluxSender(@Output(LeadSenderChannelBinding.CREATED_CHANNEL) FluxSender createdFluxSender) {
		this.createdFluxSender = createdFluxSender;
	}

	public void setUpdatedFluxSender(@Output(LeadSenderChannelBinding.UPDATED_CHANNEL) FluxSender updatedFluxSender) {
		this.updatedFluxSender = updatedFluxSender;
	}

	public void setDeletedFluxSender(@Output(LeadSenderChannelBinding.DELETED_CHANNEL) FluxSender deletedFluxSender) {
		this.deletedFluxSender = deletedFluxSender;
	}

	@Override
	public void sendLeadCreatedEvent(Event event) {
		createdFluxSender.send(buildMessage(event)).map(message -> {
			Log.debug("LEADEVENTSENDERIMPL => SENDING LEAD CREATED EVENT TO THE QUEUE => " + event);
			return message;
		}).doOnError(error -> {
			Log.error("LEADEVENTSENDERIMPL => ERROR SENDING LEAD CREATED EVENT TO THE QUEUE => " + event);
		}).subscribe();

	}

	@Override
	public void sendLeadUpdatedEvent(Event event) {
		updatedFluxSender.send(buildMessage(event)).map(message -> {
			Log.debug("LEADEVENTSENDERIMPL => SENDING LEAD UPDATED EVENT TO THE QUEUE => " + event);
			return message;
		}).doOnError(error -> {
			Log.error("LEADEVENTSENDERIMPL => ERROR SENDING LEAD UPDATED EVENT TO THE QUEUE => " + event);
		}).subscribe();
	}

	@Override
	public void sendLeadDeletedEvent(Event event) {
		deletedFluxSender.send(buildMessage(event)).map(message -> {
			Log.debug("LEADEVENTSENDERIMPL => SENDING LEAD DELETED EVENT TO THE QUEUE => " + event);
			return message;
		}).doOnError(error -> {
			Log.error("LEADEVENTSENDERIMPL => ERROR SENDING LEAD DELETED EVENT TO THE QUEUE => " + event);
		}).subscribe();
	}
	
	private Flux<Message<Event>> buildMessage(Event payload){
		return Flux.just(MessageBuilder.withPayload(payload).build());
	}

}
