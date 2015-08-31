package com.score.pics.client.register;

import com.google.gwt.dom.client.Style.TextAlign;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.widget.button.Button;
import com.googlecode.mgwt.ui.client.widget.header.HeaderPanel;
import com.googlecode.mgwt.ui.client.widget.input.MEmailTextBox;
import com.googlecode.mgwt.ui.client.widget.input.MPasswordTextBox;
import com.googlecode.mgwt.ui.client.widget.input.MTextBox;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPanel;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPropertyHelper.Alignment;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPropertyHelper.Justification;
import com.score.pics.client.helper.GUIHelper;


public class RegisterMobileViewImpl implements RegisterMobileView {

	public RegisterMobileViewImpl() {
			createMobileGUI();
	}
	
	@Override
	public Widget asWidget() {
		return newMainPnl;
		
	}
	
	/*Mobile Version*/
	private FlexPanel mainPanelMobile = new FlexPanel();
	private MTextBox userName;
	private MEmailTextBox email;
	private MPasswordTextBox password;
	private MPasswordTextBox passwordRepeat;
	private Button submit;
	private HeaderPanel headerPnl;
	private Label messageLabel;
	private Label distance1, distance2;
	private FlexPanel newMainPnl;
	private GUIHelper guiHelper = new GUIHelper();
	
	
	private void createMobileGUI(){
		
		newMainPnl = new FlexPanel();
		newMainPnl.setSize("100%", "100%");
		newMainPnl.getElement().getStyle().setBackgroundColor("white");
//		mainPanelMobile.setSize("100%", "100%");
		mainPanelMobile.setWidth("100%");
		mainPanelMobile.getElement().getStyle().setBackgroundColor("white");
		mainPanelMobile.setAlignment(Alignment.CENTER);
		mainPanelMobile.setJustification(Justification.SPACE_BETWEEN);
		
		headerPnl = guiHelper.getHeaderPanel("Registrierung");
//		headerPnl = new HeaderPanel();
//		headerPnl.getElement().getStyle().setBackgroundColor("#3498DB");
//		headerPnl.setWidth("100%");
		
		mainPanelMobile.add(headerPnl);
		
		distance1 = guiHelper.distance10PX();
		mainPanelMobile.add(distance1);
		
		Label label = guiHelper.ueberschrift("Username:");
		label.setWidth("90%");
		distance2 = guiHelper.distance5PX();
		userName = new MTextBox();
		userName.setWidth("90%");
		distance1 = guiHelper.distance10PX();
		mainPanelMobile.add(label);
		mainPanelMobile.add(distance2);
		mainPanelMobile.add(userName);
		mainPanelMobile.add(distance1);
		
		label = guiHelper.ueberschrift("Email:");
		label.setWidth("90%");
		distance2 = guiHelper.distance5PX();
		email = new MEmailTextBox();
		email.setWidth("90%");
		distance1 = guiHelper.distance10PX();
		mainPanelMobile.add(label);
		mainPanelMobile.add(distance2);
		mainPanelMobile.add(email);
		mainPanelMobile.add(distance1);
		
		label = guiHelper.ueberschrift("Passwort:");
		label.setWidth("90%");
		distance2 = guiHelper.distance5PX();
		password = new MPasswordTextBox();
		password.setWidth("90%");
		distance1 = guiHelper.distance10PX();
		mainPanelMobile.add(label);
		mainPanelMobile.add(distance2);
		mainPanelMobile.add(password);
		mainPanelMobile.add(distance1);
		
		
		label = guiHelper.ueberschrift("Passwort wiederholen:");
		label.setWidth("90%");
		distance2 = guiHelper.distance5PX();
		passwordRepeat = new MPasswordTextBox();
		passwordRepeat.setWidth("90%");
		distance1 = guiHelper.distance10PX();
		mainPanelMobile.add(label);
		mainPanelMobile.add(distance2);
		mainPanelMobile.add(passwordRepeat);
		mainPanelMobile.add(distance1);
		
		
		submit = new Button();
		submit.setWidth("90%");
		submit.setImportant(true);
		submit.getElement().getStyle().setBorderColor("white");
		submit.setText("Senden");
		distance1 = guiHelper.distance10PX();
		mainPanelMobile.add(submit);
		mainPanelMobile.add(distance1);
		
		messageLabel = new Label();
		messageLabel.setHeight("4em");
		messageLabel.getElement().setId("messageID");
		messageLabel.setWidth("90%");
//		messageLabel.getElement().getStyle().setBackgroundColor("red");
//		DOM.getElementById("messageID").getStyle().setVisibility(Visibility.HIDDEN);
		messageLabel.getElement().getStyle().setTextAlign(TextAlign.CENTER);
		mainPanelMobile.add(messageLabel);
		
		newMainPnl.add(mainPanelMobile);
	}


	
	/* DesktopShit */
	@Override
	public HasTapHandlers getButton() {
		return submit;
	}

	@Override
	public void setMessage(String text) {
		messageLabel.setText(text);
	}

	@Override
	public String getUsername() {
		return userName.getText();
	}

	@Override
	public String getEmail() {
		return email.getText();
	}

	@Override
	public String getPassword() {
		return password.getText();
	}

	@Override
	public String getPasswordRepeat() {
		return passwordRepeat.getText();
	}

	
}
