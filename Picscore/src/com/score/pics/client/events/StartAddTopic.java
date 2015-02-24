package com.score.pics.client.events;

import com.google.gwt.event.shared.GwtEvent;

	/*
	 * Wird vom SaveNamePassword-Widget abgefeuert und von der SaveActivity entgegen genommen.
	 * Also wenn man ein neues Password speichert.
	 * 
	 * */


public class StartAddTopic extends GwtEvent<StartaddTopicEventHandler> {
	
	public static final Type<StartaddTopicEventHandler> TYPE = new Type<StartaddTopicEventHandler>();

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<StartaddTopicEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(StartaddTopicEventHandler handler) {
		handler.speichern(this);
	}
	
	private String topic;
	
	public StartAddTopic(String topic){
		this.topic = topic;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	
}
