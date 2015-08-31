package com.score.pics.client.login;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasTouchStartHandlers;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.ui.client.widget.button.Button;
import com.googlecode.mgwt.ui.client.widget.header.HeaderPanel;
import com.googlecode.mgwt.ui.client.widget.input.MPasswordTextBox;
import com.googlecode.mgwt.ui.client.widget.input.MTextBox;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPanel;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPropertyHelper.Alignment;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPropertyHelper.Justification;
import com.score.pics.client.helper.GUIHelper;
import com.score.pics.client.resources.AppBundle;
import com.score.pics.shared.StringResources;


public class LoginViewImpl implements LoginView {

	private FlexPanel mainPnl;
	private HeaderPanel headerPanel;
	private Label  nameLabel, passwordLabel, errorLabel, distance, passwordForgottenLabel;
	private MTextBox username;
	private MPasswordTextBox password;
	private Button loginButton, registerButton;
	private GUIHelper guiHelper;
	
	
	public LoginViewImpl() {
		
		AppBundle.INSTANCE.getCss().ensureInjected();
		AppBundle.INSTANCE.font().ensureInjected();
		
		guiHelper = new GUIHelper();
		
		mainPnl = new FlexPanel();
		mainPnl.getElement().getStyle().setBackgroundColor("white");
		mainPnl.setSize("100%", "100%");

		
		headerPanel = guiHelper.getHeaderPanel("Information collector");
		
		mainPnl.add(headerPanel);
		
		mainPnl.add(getBody());
		
		
	}
	
	
	private FlexPanel getBody(){
		FlexPanel body = new FlexPanel();
		body.setWidth("100%");
		body.setAlignment(Alignment.CENTER);
		body.setJustification(Justification.SPACE_BETWEEN);
		
		distance = guiHelper.distance10PX();
		nameLabel = guiHelper.ueberschrift("Username");
		body.add(distance);
		body.add(nameLabel);
		
		// nochmal ein 
		// kleiner Abstand zwischen dem Label Username und der TextBox
		distance = guiHelper.distance5PX();
		username = new MTextBox();
		username.setWidth("90%");
		body.add(distance);
		body.add(username);
		
		distance = guiHelper.distance10PX();
		passwordLabel = guiHelper.ueberschrift("Passwort");
		body.add(distance);
		body.add(passwordLabel);
		
		// nochmal ein 
		// kleiner Abstand zwischen dem Label Username und der TextBox
		distance = guiHelper.distance5PX();
		password = new MPasswordTextBox();
		password.setWidth("90%");
		body.add(distance);
		body.add(password);
		
		distance = guiHelper.distance10PX();
		loginButton = new Button("Login");
		loginButton.getElement().getStyle().setBorderColor("white");
		loginButton.setImportant(true);
		loginButton.setWidth("90%");
		body.add(distance);
		body.add(loginButton);
		
		distance = guiHelper.distance10PX();
		registerButton= new Button("Register");
		registerButton.setWidth("90%");
		registerButton.setSmall(true);
		registerButton.getElement().getStyle().setBackgroundColor("#eb8c98");//("#8BDE7C");
		registerButton.getElement().getStyle().setBorderColor("white");
		registerButton.getElement().getStyle().setColor("white");
		body.add(distance);
		body.add(registerButton);
		
		distance = guiHelper.distance10PX();
		passwordForgottenLabel = new Label("Password forgotten?");
		passwordForgottenLabel.addStyleName(AppBundle.INSTANCE.getCss().resultLabel());
		//passwordForgottenLabel.getElement().getStyle().setColor(StringResources.getErrorColor());// The message can be only a error message because the password or username is wrong
		body.add(distance);
		body.add(passwordForgottenLabel);
		
		
		distance = guiHelper.distance10PX();
		errorLabel = guiHelper.errorLabel();
		
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


	@Override
	public HasTouchStartHandlers getNewPasswordLabel() {
		return passwordForgottenLabel;
	}


	@Override
	public HasClickHandlers getNewPasswordClickLabel() {
		// TODO Auto-generated method stub
		return passwordForgottenLabel;
	}
	
	
	

}
