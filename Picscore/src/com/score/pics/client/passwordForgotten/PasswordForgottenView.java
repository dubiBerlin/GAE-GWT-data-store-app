package com.score.pics.client.passwordForgotten;

import com.google.gwt.user.client.ui.IsWidget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;

public interface PasswordForgottenView extends IsWidget {

	public HasTapHandlers getBackButton();
	public HasTapHandlers getSendButton();
	public String getEmailAdress();
	public void setMessageColor(String color);
	public void setMessage(String text);
	
}
