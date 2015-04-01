package com.score.pics.client.widgets;

import com.google.gwt.user.client.ui.IsWidget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;

public interface AddTopicSide2to5View extends IsWidget {
	
	public HasTapHandlers getCancelImageButton();
	public HasTapHandlers getSaveButton();
	public String getTitle();
	public String getContent();
	public String getSource();
	public void setMessage(String text);
	public void setHeaderTitle(String text);
	
	// Für den Edit Einsatz
	public void setTitle(String text);
	public void setContent(String text);
	public void setSource(String text);
}
