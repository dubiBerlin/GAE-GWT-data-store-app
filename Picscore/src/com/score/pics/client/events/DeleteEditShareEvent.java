package com.score.pics.client.events;

import com.google.gwt.event.shared.GwtEvent;

	/*
	 * Wird vom SettingsWidget abgefeuert und gibt durch die zwei boolean Werte an
	 * ob bei einem Klick auf die CellList, editiert oder gelöscht werden soll.
	 * Wird von der CategoryActivity abgefangen.
	 * 
	 * */

public class DeleteEditShareEvent extends GwtEvent<DeleteEditShareHandler> {

	
	public static final Type<DeleteEditShareHandler> TYPE = new Type<DeleteEditShareHandler>();
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<DeleteEditShareHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(DeleteEditShareHandler handler) {
		handler.deleteOrEditCategory(this);
		
	}

	private boolean delete;
	private boolean edit;
	private boolean share;
	
	public DeleteEditShareEvent(boolean delete, boolean edit, boolean share){
		this.delete = delete;
		this.edit = edit;
		this.share = share;
	}

	
	public boolean isDelete() {
		return delete;
	}

	public boolean isEdit() {
		return edit;
	}

	public boolean isShare() {
		return share;
	}

	
}
