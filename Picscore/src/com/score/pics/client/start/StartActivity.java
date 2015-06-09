package com.score.pics.client.start;

import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.event.shared.EventBus;
import com.score.pics.client.ClientFactory;
import com.score.pics.client.DetailActivity;
import com.score.pics.client.login.LoginPlace;
import com.score.pics.client.side2.SidePlace2;
import com.score.pics.shared.StringResources;


public class StartActivity extends DetailActivity {

	private ClientFactory clientFactory;
	private StartView view;
	private String token; // title = id der aktuellen Entity
	
	
	public StartActivity(ClientFactory clientFactory){
		super(clientFactory, clientFactory.getStartViewImpl() );
		this.clientFactory = clientFactory;
	}
	
	
	@Override
	public void start(final AcceptsOneWidget panel, final EventBus eventBus) {
		super.start(panel, eventBus);
		System.out.println("StartActivity()");
		view = clientFactory.getStartViewImpl();
		
		StartPlace place = (StartPlace)clientFactory.getPlaceController().getWhere();
		
		token = place.getToken();
		placeToken = place.getToken();
		this.side = StringResources.startSideIdentifier();
		
		clientFactory.pushHistoryTracker(clientFactory.getUserName());
			
		 
		
		String sessionID = Cookies.getCookie("sid");
		
		if(sessionID!=null){
			setHandler();

			
		}
		else{
			clientFactory.getPlaceController().goTo(new LoginPlace());
		}
		
		view.setHeaderTitle(token);
		panel.setWidget(view);
	}
	

	@Override
	public void goToNextPlace(String place) {
		clientFactory.setIDOFPreviousSide(token);
		clientFactory.getPlaceController().goTo(new SidePlace2(place));
	}
	


}
