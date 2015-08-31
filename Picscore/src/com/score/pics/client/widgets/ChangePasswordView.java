package com.score.pics.client.widgets;

import com.google.gwt.user.client.ui.IsWidget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;

public interface ChangePasswordView extends IsWidget {

	public void show();
	public void hide();
	public HasTapHandlers getSaveButton();
	public HasTapHandlers getCloseButton();
	public String getOldPassword();
	public String getNewPassword();
	public String getNewPasswordRepeat();
	public void setResultMessageColor(String color);
	public void setResultMessage(String text);
	
	
}
