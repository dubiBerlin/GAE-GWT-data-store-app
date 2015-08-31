package com.score.pics.client.widgets;

import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.widget.button.Button;
import com.googlecode.mgwt.ui.client.widget.button.image.CancelImageButton;
import com.googlecode.mgwt.ui.client.widget.header.HeaderPanel;
import com.googlecode.mgwt.ui.client.widget.input.MTextArea;
import com.googlecode.mgwt.ui.client.widget.input.MTextBox;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPanel;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPropertyHelper.Alignment;
import com.score.pics.client.helper.GUIHelper;
import com.score.pics.client.resources.AppBundle;


public class AddTopicSide2to5ViewImpl implements AddTopicSide2to5View{

	private MTextBox  titleTxtBox, sourceBox;
	private MTextArea textBox;
	private Button saveBttn;
	private Label distance1, distance2, distance3, distance4,distance5, resultMessage;
	private Label titleHeader, contentHeader, sourceHeader;
	private CancelImageButton close;
	private FlexPanel mainPnl, body;
	private HeaderPanel headerPnl;
	private GUIHelper guiHelper = new GUIHelper();
	
	public AddTopicSide2to5ViewImpl() {
		
		AppBundle.INSTANCE.getCss().ensureInjected();
		
		mainPnl = new FlexPanel();
		mainPnl.getElement().getStyle().setBackgroundColor("white");
		mainPnl.setSize("100%", "100%");
		mainPnl.setAlignment(Alignment.CENTER);
		
		//headerPnl = new HeaderPanel();
		
		close = new CancelImageButton();
		close.getElement().getStyle().setBackgroundColor("#3498DB");
		
		
		body = new FlexPanel();
		body.setAlignment(Alignment.CENTER);
		body.setWidth("100%");
		
		
		distance1 = guiHelper.distance10PX();
		titleHeader = guiHelper.ueberschrift("Title");
		titleTxtBox = new MTextBox();
		titleTxtBox.getElement().getStyle().setBorderColor("#3498DB");
		titleTxtBox.getElement().getStyle().setBorderStyle(BorderStyle.SOLID);
		titleTxtBox.getElement().getStyle().setBorderWidth(1, Unit.PX);
		titleTxtBox.setWidth("90%");
		
		distance2 = guiHelper.distance10PX();
		contentHeader = guiHelper.ueberschrift("Content");
		textBox = new MTextArea();
		textBox.getElement().getStyle().setBorderColor("#3498DB");
		textBox.getElement().getStyle().setBorderStyle(BorderStyle.SOLID);
		textBox.getElement().getStyle().setBorderWidth(1, Unit.PX);
		textBox.setWidth("90%");
		
		distance5 = guiHelper.distance10PX();
		sourceHeader = guiHelper.ueberschrift("Source");
		sourceBox = new MTextBox();
		sourceBox.getElement().getStyle().setBorderColor("#3498DB");
		sourceBox.getElement().getStyle().setBorderStyle(BorderStyle.SOLID);
		sourceBox.getElement().getStyle().setBorderWidth(1, Unit.PX);
		sourceBox.setWidth("90%");
		
		distance2 = guiHelper.distance10PX();
		saveBttn = new Button("Speichern");
		saveBttn.setImportant(true);
		saveBttn.setWidth("90%");
		

		
		distance3 = guiHelper.distance10PX();
		saveBttn.getElement().getStyle().setBorderColor("white");
		
		distance4 = guiHelper.distance10PX();
		
		resultMessage = new Label();
		resultMessage.setStyleName(AppBundle.INSTANCE.getCss().resultLabel());
		
		body.add(distance1);
		body.add(titleHeader);
		body.add(titleTxtBox);
		body.add(distance2);
		body.add(contentHeader);
		body.add(textBox);
		body.add(distance5);
		body.add(sourceHeader);
		body.add(sourceBox);
		body.add(distance3);
		body.add(saveBttn);
		body.add(distance4);
		body.add(resultMessage);
//		mainPnl.add(headerPnl);
		
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
	public String getTitle() {
		return this.titleTxtBox.getText();
	}

	@Override
	public String getContent() {
		return textBox.getText();
	}

	@Override
	public String getSource() {
		return sourceBox.getText();
	}




	@Override
	public void setMessage(String text) {
		resultMessage.setText(text);
	}

	@Override
	public void setHeaderTitle(String text) {
		headerPnl = guiHelper.getHeaderPanel("new entry for "+text, close, false);
		mainPnl.add(headerPnl);
		mainPnl.add(body);
	}


	@Override
	public void setTitle(String text) {
		this.titleTxtBox.setText(text);
	}


	@Override
	public void setContent(String text) {
		this.textBox.setText(text);
	}


	@Override
	public void setSource(String text) {
		this.sourceBox.setText(text);
	}

}
