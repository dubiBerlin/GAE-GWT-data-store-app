package com.score.pics.client.widgets;

import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.widget.button.image.CancelImageButton;
import com.googlecode.mgwt.ui.client.widget.header.HeaderPanel;
import com.googlecode.mgwt.ui.client.widget.input.radio.MRadioButton;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPanel;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPropertyHelper.Alignment;
import com.score.pics.client.GUIHelper;
import com.score.pics.client.resources.AppBundle;

public class SettingsViewImpl implements SettingsView {
	
	private MRadioButton delete;
	private MRadioButton edit;
	private MRadioButton share;
	private CancelImageButton close;
	private FlexPanel mainPnl;
	private Label distance1;
	

	public SettingsViewImpl() {
		
		AppBundle.INSTANCE.getCss().ensureInjected();
		
		mainPnl = new FlexPanel();
		mainPnl.getElement().getStyle().setBackgroundColor("white");
		mainPnl.setSize("100%", "100%");
		mainPnl.setAlignment(Alignment.CENTER);
		
		close = new CancelImageButton();
		HeaderPanel hdrPnl = GUIHelper.getHeaderPanel("Settings", close, false);
		distance1 = GUIHelper.distance10PX();
		
		mainPnl.add(hdrPnl);
		mainPnl.add(distance1);
		
		mainPnl.add(getBody());
		
		
	}
	
	private FlexPanel getBody(){

		FlexPanel body = new FlexPanel();
		body.setAlignment(Alignment.CENTER);
		body.getElement().getStyle().setBackgroundColor("white");
		body.setWidth("100%");
		
		this.edit = new MRadioButton("Edit");
		edit.setText("Edit");
		edit.setWidth("90%");
//		edit.setValue(false);
		edit.getElement().getStyle().setColor("#3498DB");
		
		
		Label de1 = GUIHelper.distance10PX();
		
		this.delete = new MRadioButton("Delete");
		delete.getElement().getStyle().setColor("#3498DB");
		delete.setWidth("90%");
//		delete.setValue(true);
		delete.setText("delete");
		
		Label de2 = GUIHelper.distance10PX();

		this.share = new MRadioButton("share");
		share.getElement().getStyle().setColor("#3498DB");
		share.setWidth("90%");
		share.setText("share");
//		share.setValue(false);
		
		body.add(edit);
		body.add(de1);
		body.add(delete);
		body.add(de2);
		body.add(share);
		
		return body;
	}

	@Override
	public Widget asWidget() {
		return mainPnl;
	}

	@Override
	public HasTapHandlers getCancelImageButton() {
		return close;
	}

	@Override
	public HasTapHandlers getEditHandler() {
		return edit;
	}

	@Override
	public HasTapHandlers getDeleteHandler() {
		return delete;
	}

	@Override
	public HasTapHandlers getShareHandler() {
		return share;
	}

	@Override
	public void setEditValue(boolean value) {
		edit.setValue(value);
	}

	@Override
	public void setDeleteValue(boolean value) {
		delete.setValue(value);
	}

	@Override
	public void setShareValue(boolean value) {
		share.setValue(value);
	}

	@Override
	public boolean getEditValue() {
		return edit.isEnabled();
	}

	@Override
	public boolean getDeleteValue() {
		return delete.getValue();
	}

	@Override
	public boolean getShareValue() {
		return share.isEnabled();
	}

	@Override
	public HasValueChangeHandlers<Boolean> getDeleteChangeHandler() {
		return delete;
	}

}
