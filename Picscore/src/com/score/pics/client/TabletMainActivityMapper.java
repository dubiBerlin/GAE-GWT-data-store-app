package com.score.pics.client;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.Window;
import com.score.pics.client.login.LoginActivity;
import com.score.pics.client.login.LoginPlace;
import com.score.pics.client.register.RegisterMobileActivity;
import com.score.pics.client.register.RegisterPlace;
import com.score.pics.client.side2.SidePlace2;
import com.score.pics.client.side3.SidePlace3;
import com.score.pics.client.side4.SidePlace4;
import com.score.pics.client.side5.SidePlace5;
import com.score.pics.client.start.StartPlace;
import com.score.pics.client.tablet.TabletMainContentPresentationActivity;

public class TabletMainActivityMapper implements ActivityMapper {

	private final ClientFactory clientFactory;

	private RegisterMobileActivity rma;
	private Place lastPlace;
	private LoginActivity la;
	
	public TabletMainActivityMapper(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;

	}


	@Override
	public Activity getActivity(Place place) {
		
		if(place instanceof LoginPlace){
			return getLoginActivity();
		}
		if(place instanceof RegisterPlace){
			return getRegisterMobileActivity();
		}
		if(place instanceof StartPlace || place instanceof SidePlace2 || place instanceof SidePlace3 || place instanceof SidePlace4 || place instanceof SidePlace5   ){
			return new TabletMainContentPresentationActivity(clientFactory);
		}
		return null;
	}
	

	public Activity getLoginActivity(){
		if(la == null){
			la = new LoginActivity(clientFactory);
		}
		return la;
	}

	
	public Activity getRegisterMobileActivity(){
		if(rma == null){
			rma = new RegisterMobileActivity(clientFactory);
		}
		return rma;
	}
}
