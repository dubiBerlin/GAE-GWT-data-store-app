package com.score.pics.client.tabletNavBlank;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.ui.client.widget.header.HeaderPanel;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPanel;
import com.score.pics.client.resources.AppBundle;

public class TabletNavViewImpl implements IsWidget{

	private FlexPanel mainPnl;
	private HeaderPanel headerPanel;
	
	public TabletNavViewImpl() {
		

		AppBundle.INSTANCE.getCss().ensureInjected();
		

		mainPnl = new FlexPanel();
		mainPnl.getElement().getStyle().setBackgroundColor("white");
		mainPnl.setSize("100%", "100%");
		
		headerPanel = new HeaderPanel();
		headerPanel.getElement().getStyle().setBackgroundColor("#3498DB");
		headerPanel.setWidth("100%");
		
		
		mainPnl.add(headerPanel);
		
	}

	@Override
	public Widget asWidget() {
		// TODO Auto-generated method stub
		return mainPnl;
	}



}
