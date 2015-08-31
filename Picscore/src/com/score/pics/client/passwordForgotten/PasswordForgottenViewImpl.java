package com.score.pics.client.passwordForgotten;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.widget.button.Button;
import com.googlecode.mgwt.ui.client.widget.button.image.PreviousitemImageButton;
import com.googlecode.mgwt.ui.client.widget.header.HeaderPanel;
import com.googlecode.mgwt.ui.client.widget.input.MEmailTextBox;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPanel;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPropertyHelper.Alignment;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPropertyHelper.Justification;
import com.score.pics.client.helper.GUIHelper;
import com.score.pics.client.resources.AppBundle;
import com.score.pics.shared.StringResources;

public class PasswordForgottenViewImpl implements PasswordForgottenView {
	
	private FlexPanel mainPnl;
	private Label  messageLabel;
	private MEmailTextBox emailTextBox;
	private Button sendBttn;
	private PreviousitemImageButton backBttn;
	private GUIHelper guiHelper;
	
	public PasswordForgottenViewImpl() {
		
		AppBundle.INSTANCE.getCss().ensureInjected();
		
		guiHelper = new GUIHelper();
		
		mainPnl = new FlexPanel();
		mainPnl.getElement().getStyle().setBackgroundColor("white");
		mainPnl.setSize("100%", "100%");
		
		mainPnl.add(getHeaderPanel());
		mainPnl.add(getBody());
		
	}
	
	private FlexPanel getBody(){

		FlexPanel body = new FlexPanel();
		body.setAlignment(Alignment.CENTER);
		body.setJustification(Justification.SPACE_BETWEEN);
		body.getElement().getStyle().setBackgroundColor("white");
		body.setWidth("100%");
		
		Label distance = guiHelper.distance10PX();
		Label titel = guiHelper.ueberschrift(StringResources.getStringSendPasswordToEmailLabel());
		body.add(distance);
		body.add(titel);
		
		distance = guiHelper.distance5PX();
		emailTextBox = new MEmailTextBox();
		emailTextBox.setWidth("90%");
		emailTextBox.setPlaceHolder("Email");
		body.add(distance);
		body.add(emailTextBox);
		
		distance = guiHelper.distance10PX();
		sendBttn = new Button(StringResources.getStringSend());
		sendBttn.getElement().getStyle().setBorderColor("white");
		sendBttn.setWidth("90%");
		sendBttn.setConfirm(true);
		body.add(distance);
		body.add(sendBttn);
		
		distance = guiHelper.distance10PX();
		messageLabel = new Label();
		messageLabel.setStyleName(AppBundle.INSTANCE.getCss().resultLabel());
		messageLabel.getElement().setId(StringResources.getIdOfResultLabel());
		body.add(distance);
		body.add(messageLabel);
		
		
		return body;
	}
	
	private HeaderPanel getHeaderPanel(){
		HeaderPanel headerPanel = new HeaderPanel();
		headerPanel.getElement().getStyle().setBackgroundColor("#3498DB");
		headerPanel.setWidth("100%");
		
		Label titleLabel = new Label(StringResources.getStringPasswordForgottenHeaderLabel());
		titleLabel.setStyleName(AppBundle.INSTANCE.getCss().headerLabel());
		
		backBttn = new PreviousitemImageButton();
		
		headerPanel.add(backBttn);
		headerPanel.add(titleLabel);
		
		return headerPanel;
	}
	
	@Override
	public Widget asWidget() {
		return mainPnl;
	}

	@Override
	public HasTapHandlers getBackButton() {
		return backBttn;
	}

	@Override
	public HasTapHandlers getSendButton() {
		return sendBttn;
	}
	
	@Override
	public String getEmailAdress() {
		return emailTextBox.getText();
	}

	@Override
	public void setMessageColor(String color) {
		messageLabel.getElement().getStyle().setColor(color);
	}

	@Override
	public void setMessage(String text) {
		messageLabel.setText(text);
	}

}
