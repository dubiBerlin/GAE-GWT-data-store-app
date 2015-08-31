package com.score.pics.client.widgets;

import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.widget.button.Button;
import com.googlecode.mgwt.ui.client.widget.button.image.CancelImageButton;
import com.googlecode.mgwt.ui.client.widget.header.HeaderPanel;
import com.googlecode.mgwt.ui.client.widget.input.MTextBox;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPanel;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPropertyHelper.Alignment;
import com.score.pics.client.helper.GUIHelper;
import com.score.pics.client.resources.AppBundle;

public class AddTopicViewImpl implements AddTopicView{

	private MTextBox textBox;
	private Button saveBttn;
	private Label distance1, distance2, distance3, resultMessage;
	private CancelImageButton close;
	private FlexPanel mainPnl;

	private GUIHelper guiHelper = new GUIHelper();
	
	
	public AddTopicViewImpl() {
		
		AppBundle.INSTANCE.getCss().ensureInjected();
		
		
		mainPnl = new FlexPanel();
		mainPnl.getElement().getStyle().setBackgroundColor("white");
		mainPnl.setSize("100%", "100%");
		mainPnl.setAlignment(Alignment.CENTER);
		
		close = new CancelImageButton();
		close.getElement().getStyle().setBackgroundColor("#3498DB");
		HeaderPanel headerPnl = guiHelper.getHeaderPanel("Create Topic", close, false);
		
		
		FlexPanel body = new FlexPanel();
		body.setAlignment(Alignment.CENTER);
		body.setWidth("100%");
		
		
		distance1 = guiHelper.distance10PX();
		textBox = new MTextBox();
		textBox.getElement().getStyle().setBorderColor("#3498DB");
		textBox.getElement().getStyle().setBorderStyle(BorderStyle.SOLID);
		textBox.getElement().getStyle().setBorderWidth(1, Unit.PX);
		textBox.setWidth("90%");
		distance2 = guiHelper.distance10PX();
		saveBttn = new Button("Speichern");
		saveBttn.setImportant(true);
		saveBttn.setWidth("90%");
				
		saveBttn.getElement().getStyle().setBorderColor("white");
		distance3 = guiHelper.distance10PX();
		
		resultMessage = new Label();
		resultMessage.setStyleName(AppBundle.INSTANCE.getCss().resultLabel());
		
		body.add(distance1);
		body.add(textBox);
		body.add(distance2);
		body.add(saveBttn);
		body.add(distance3);
		body.add(resultMessage);
		
		mainPnl.add(headerPnl);
		mainPnl.add(body);
		
		
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
	public HasTapHandlers getSaveButton() {
		return saveBttn;
	}


	@Override
	public String getTopic() {
		return textBox.getText();
	}


	@Override
	public void setMessage(String text) {
		this.resultMessage.setText(text);
	}



}
