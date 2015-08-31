package com.score.pics.client.tablet;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import com.score.pics.client.ClientFactory;

public class TabletMainContentPresentationActivity extends MGWTAbstractActivity {

	private ClientFactory clientFactory;
	private TabletMainContentPresentationView view;
	
	
	public TabletMainContentPresentationActivity(ClientFactory clientFactory){
		this.clientFactory = clientFactory;
	
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		
		
		view = clientFactory.getTabletMainBlankImpl();
		
		panel.setWidget(view);
		
		
	}
	
}
