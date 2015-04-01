package com.score.pics.client.side3;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.event.shared.EventBus;
import com.score.pics.client.ClientFactory;
import com.score.pics.client.DetailActivity;
import com.score.pics.client.side4.SidePlace4;
import com.score.pics.shared.StringResources;

public class SideActivity3 extends DetailActivity {

	private String token;
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
		
		token = place.getToken();
		
		clientFactory.pushHistoryTracker(token);
		
		getStartList(token, StringResources.side3Identifier());
		
		String sessionID = Cookies.getCookie("sid");
		
		if(sessionID!=null){
			setHandler();
		}
		
		
		view.setHeaderTitle(token);
		
		panel.setWidget(view);
	}
	
	@Override
	public void printValue() {
		Window.alert("Side3: "+token);
	}
	
	@Override
	public void goToNextPlace(String place) {
		clientFactory.setIDOFPreviousSide(token);
		clientFactory.getPlaceController().goTo(new SidePlace4(place));
	}
}
