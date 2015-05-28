package com.score.pics.client.side4;

import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.event.shared.EventBus;
import com.score.pics.client.ClientFactory;
import com.score.pics.client.DetailActivity;
import com.score.pics.client.side5.SidePlace5;
import com.score.pics.shared.StringResources;

public class SideActivity4 extends DetailActivity {

	private SideView4 view;
	private final ClientFactory clientFactory;
	private String token;
	
	public SideActivity4(ClientFactory clientFactory) {
		super(clientFactory, clientFactory.getSide4ViewImpl());
		this.clientFactory = clientFactory;
	}


	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		super.start(panel, eventBus);
		
		view=clientFactory.getSide4ViewImpl();
		
		
		SidePlace4 place = (SidePlace4)clientFactory.getPlaceController().getWhere();

		token = place.getToken();
		
		clientFactory.pushHistoryTracker(token);
		
		this.placeToken  = place.getToken();
		this.side = StringResources.side4Identifier();
		
		
		setHandler();
		
		
		view.setHeaderTitle(place.getToken());
		

		
		panel.setWidget(view);
	}
	

	@Override
	public void goToNextPlace(String place) {
		clientFactory.setIDOFPreviousSide(token);
		clientFactory.getPlaceController().goTo(new SidePlace5(place));
	}
	
	
}
