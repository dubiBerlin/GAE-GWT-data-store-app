package com.score.pics.client.side3;

import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.event.shared.EventBus;
import com.score.pics.client.ClientFactory;
import com.score.pics.client.DetailActivity;
import com.score.pics.client.side4.SidePlace4;
import com.score.pics.shared.AppResources;

public class SideActivity3 extends DetailActivity {

	private SideView3 view;
	private final ClientFactory clientFactory;
	
	public SideActivity3(ClientFactory clientFactory) {
		super(clientFactory, clientFactory.getSide3ViewImpl());
		this.clientFactory = clientFactory;
	}
	

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		super.start(panel, eventBus);
		
		view=clientFactory.getSide3ViewImpl();
		
		
		SidePlace3 place = (SidePlace3)clientFactory.getPlaceController().getWhere();
		
		String token = place.getToken();
		
		
		getStartList(token, AppResources.side3Identifier());
		
		String sessionID = Cookies.getCookie("sid");
		
		if(sessionID!=null){
			setHandler();
		}
		
		
		view.setHeaderTitle(token);
		
		panel.setWidget(view);
	}
	
	
	@Override
	public void goToNextPlace(String place) {
		clientFactory.getPlaceController().goTo(new SidePlace4(place));
	}
}
