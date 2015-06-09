package com.score.pics.client;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.score.pics.client.login.LoginActivity;
import com.score.pics.client.login.LoginPlace;
import com.score.pics.client.register.RegisterMobileActivity;
import com.score.pics.client.register.RegisterPlace;

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
