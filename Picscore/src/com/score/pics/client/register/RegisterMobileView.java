package com.score.pics.client.register;

import com.google.gwt.user.client.ui.IsWidget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;

public interface RegisterMobileView extends IsWidget {

	public HasTapHandlers getButton();
	public void setMessage(String text);
	public String getUsername();
	public String getEmail();
	public String getPassword();
	public String getPasswordRepeat();
	
}
