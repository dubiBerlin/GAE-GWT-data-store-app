package com.score.pics.client.side2;

import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.event.shared.EventBus;
import com.score.pics.client.ClientFactory;
import com.score.pics.client.DetailActivity;
import com.score.pics.client.side3.SidePlace3;
import com.score.pics.shared.StringResources;

public class SideActivity2 extends DetailActivity {

	
	
	/*
	 * Activity of Side2
	 * 
	 * 
	 * */

	private SideView2 view;
	private ClientFactory clientFactory;
	private String token; // title = id der aktuellen Entity
	
	
	public SideActivity2(ClientFactory clientFactory){
		super(clientFactory, clientFactory.getSide2ViewImpl());
		this.clientFactory = clientFactory;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		super.start(panel, eventBus);
		view = clientFactory.getSide2ViewImpl();
		final SidePlace2 place = (SidePlace2)clientFactory.getPlaceController().getWhere();
		
		
		
		token = place.getToken();
		this.placeToken  = place.getToken();
		this.side = StringResources.side2Identifier();
		
		clientFactory.pushHistoryTracker(token);
		
		String sessionID = Cookies.getCookie("sid");
		
		if(sessionID!=null){
			setHandler();
		}
		
		view.setHeaderTitle(token);
		
		panel.setWidget(view);
	}
	
	@Override
	public void goToNextPlace(String place) {
		clientFactory.setIDOFPreviousSide(token);
		clientFactory.getPlaceController().goTo(new SidePlace3(place));
	}
	
	
	
	
}
