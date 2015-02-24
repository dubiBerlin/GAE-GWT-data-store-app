package com.score.pics.client.login;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.widget.button.Button;
import com.googlecode.mgwt.ui.client.widget.header.HeaderPanel;
import com.googlecode.mgwt.ui.client.widget.input.MPasswordTextBox;
import com.googlecode.mgwt.ui.client.widget.input.MTextBox;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPanel;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPropertyHelper.Alignment;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPropertyHelper.Justification;
import com.score.pics.client.GUIHelper;
import com.score.pics.client.resources.AppBundle;


public class LoginViewImpl implements LoginView {

	private FlexPanel mainPnl;
	private HeaderPanel headerPanel;
	private Label  nameLabel, passwordLabel, errorLabel, distance;
	private MTextBox username;
	private MPasswordTextBox password;
	private Button loginButton, registerButton;
	
	public LoginViewImpl() {
		
		AppBundle.INSTANCE.getCss().ensureInjected();
		
		mainPnl = new FlexPanel();
		mainPnl.setSize("100%", "100%");

		
		headerPanel = GUIHelper.getHeaderPanel("Debate App");
		
		mainPnl.add(headerPanel);
		
		mainPnl.add(getBody());
		
		
	}
	
	
	private FlexPanel getBody(){
		FlexPanel body = new FlexPanel();
		body.setWidth("100%");
		body.setAlignment(Alignment.CENTER);
		body.setJustification(Justification.SPACE_BETWEEN);
		
		distance = GUIHelper.distance10PX();
		nameLabel = GUIHelper.ueberschrift("Username");
		body.add(distance);
		body.add(nameLabel);
		
		// nochmal ein 
		// kleiner Abstand zwischen dem Label Username und der TextBox
		distance = GUIHelper.distance5PX();
		username = new MTextBox();
		username.setWidth("90%");
		body.add(distance);
		body.add(username);
		
		distance = GUIHelper.distance10PX();
		passwordLabel = GUIHelper.ueberschrift("Passwort");
		body.add(distance);
		body.add(passwordLabel);
		
		// nochmal ein 
		// kleiner Abstand zwischen dem Label Username und der TextBox
		distance = GUIHelper.distance5PX();
		password = new MPasswordTextBox();
		password.setWidth("90%");
		body.add(distance);
		body.add(password);
		
		distance = GUIHelper.distance10PX();
		loginButton = new Button("Login");
		loginButton.setImportant(true);
		loginButton.setWidth("90%");
		body.add(distance);
		body.add(loginButton);
		
		distance = GUIHelper.distance10PX();
		registerButton= new Button("Register");
		registerButton.setWidth("90%");
		registerButton.setSmall(true);
		registerButton.getElement().getStyle().setBackgroundColor("white");
		registerButton.getElement().getStyle().setBorderColor("white");
		body.add(distance);
		body.add(registerButton);
		
		distance = GUIHelper.distance10PX();
		errorLabel = GUIHelper.errorLabel();
		
		body.add(distance);
		body.add(errorLabel);
		
		return body;
	}

	@Override
	public Widget asWidget() {
		return mainPnl;
	}


	@Override
	public String getUsername() {
		return username.getText();
	}


	@Override
	public String getPassword() {
		return password.getText();
	}


	@Override
	public void setResult(String text) {
		errorLabel.setText(text);
	}


	@Override
	public HasTapHandlers getLoginButton() {
		return loginButton;
	}


	@Override
	public HasTapHandlers getRegisterButton() {
		return registerButton;
	}
	
	
	

}
