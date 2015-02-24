package com.score.pics.client.register;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.widget.button.Button;
import com.googlecode.mgwt.ui.client.widget.header.HeaderPanel;
import com.googlecode.mgwt.ui.client.widget.input.MEmailTextBox;
import com.googlecode.mgwt.ui.client.widget.input.MNumberTextBox;
import com.googlecode.mgwt.ui.client.widget.input.MPasswordTextBox;
import com.googlecode.mgwt.ui.client.widget.input.MTextBox;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPanel;

public class RegisterMobileViewImpl implements RegisterMobileView {

	public RegisterMobileViewImpl() {
			createMobileGUI();
	}
	
	@Override
	public Widget asWidget() {
		return mainPanelMobile;
		
	}
	
	/*Mobile Version*/
	private FlexPanel mainPanelMobile = new FlexPanel();
	private MTextBox firstName;
	private MTextBox lastName;
	private MTextBox userName;
	private MEmailTextBox email;
	private MPasswordTextBox password;
	private MPasswordTextBox passwordRepeat;
	private Button submit;
	private HeaderPanel headerPnl;
	private Label messageLabel;
	
	private void createMobileGUI(){
		
		mainPanelMobile.setSize("100%", "100%");
		
		headerPnl = new HeaderPanel();
		headerPnl.getElement().getStyle().setBackgroundColor("#3498DB");
		headerPnl.setWidth("100%");
		mainPanelMobile.add(headerPnl);
		
		Label label = new Label("Firstname:");
		firstName = new MTextBox();
		mainPanelMobile.add(label);
		mainPanelMobile.add(firstName);
		
		label = new Label("Lastname:");
		lastName = new MTextBox();
		mainPanelMobile.add(label);
		mainPanelMobile.add(lastName);		
		
		label = new Label("Username:");
		userName = new MTextBox();
		mainPanelMobile.add(label);
		mainPanelMobile.add(userName);
		
		label = new Label("Email:");
		email = new MEmailTextBox();
		mainPanelMobile.add(label);
		mainPanelMobile.add(email);
		
		label = new Label("Password:");
		password = new MPasswordTextBox();
		passwordRepeat = new MPasswordTextBox();
		
		submit = new Button();
		submit.setImportant(true);
		submit.setText("Senden");
		
		mainPanelMobile.add(label);
		mainPanelMobile.add(password);
		
		//mainPanelMobile.add(passwordRepeat);
		mainPanelMobile.add(submit);
		
		messageLabel = new Label();
		messageLabel.setText("message");
		mainPanelMobile.add(messageLabel);
		
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
	public String getFirstname() {
		return firstName.getText();
	}

	@Override
	public String getLastname() {
		return lastName.getText();
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

	
}
