package com.score.pics.client.tabletNavBlank;

import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import com.score.pics.client.ClientFactory;

public class TabletNavActivity extends MGWTAbstractActivity {

	private ClientFactory clientFactory;
	
	public TabletNavActivity(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}
	
	
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		
		
		TabletNavViewImpl view = clientFactory.getTabletNavBlankImpl();
		
		panel.setWidget(view);
		
		
	}

}
