package com.score.pics.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.score.pics.shared.TitleContentSourceProperty;

	/*
	 * Wird vom SaveNamePassword-Widget abgefeuert und von der SaveActivity entgegen genommen.
	 * Also wenn man ein neues Password speichert.
	 * 
	 * */


public class AddTopicSide2to5Event extends GwtEvent<AddTopicSide2to5EventHandler> {
	
	public static final Type<AddTopicSide2to5EventHandler> TYPE = new Type<AddTopicSide2to5EventHandler>();

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<AddTopicSide2to5EventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(AddTopicSide2to5EventHandler handler) {
		handler.speichern(this);
	}
	
	private TitleContentSourceProperty tce;
	
	public AddTopicSide2to5Event(TitleContentSourceProperty tce){
		this.tce= tce;
	}

	public TitleContentSourceProperty getTce() {
		return tce;
	}

	public void setTce(TitleContentSourceProperty tce) {
		this.tce = tce;
	}

	

	
}
