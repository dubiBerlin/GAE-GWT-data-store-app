package com.score.pics.client.widgets;

import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.user.client.ui.IsWidget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;

public interface SettingsView extends IsWidget {
	
	public HasTapHandlers getCancelImageButton();
	public HasTapHandlers getLogoutButton();
	public HasTapHandlers getChangePasswordButton();
	public HasTapHandlers getEditHandler();
	public HasTapHandlers getDeleteHandler();
	public HasTapHandlers getShareHandler();
	
//	public HasValueChangeHandlers<Boolean> getEditChangeHandler();
	public HasValueChangeHandlers<Boolean> getDeleteChangeHandler();
//	public HasValueChangeHandlers<Boolean> getShareChangeHandler();
	
	public void setEditValue(boolean value);
	public void setDeleteValue(boolean value);
	public void setShareValue(boolean value);
	public boolean getEditValue();
	public boolean getDeleteValue();
	public boolean getShareValue();
	
	public void hide();
	public void show();
	
}
