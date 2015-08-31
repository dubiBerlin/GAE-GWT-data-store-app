package com.score.pics.client;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.score.pics.client.login.LoginActivity;
import com.score.pics.client.login.LoginPlace;
import com.score.pics.client.passwordForgotten.PasswordForgottenActivity;
import com.score.pics.client.passwordForgotten.PasswordForgottenPlace;
import com.score.pics.client.register.RegisterMobileActivity;
import com.score.pics.client.register.RegisterPlace;
import com.score.pics.client.side2.SideActivity2;
import com.score.pics.client.side2.SidePlace2;
import com.score.pics.client.side3.SideActivity3;
import com.score.pics.client.side3.SidePlace3;
import com.score.pics.client.side4.SideActivity4;
import com.score.pics.client.side4.SidePlace4;
import com.score.pics.client.side5.SideActivity5;
import com.score.pics.client.side5.SidePlace5;
import com.score.pics.client.start.StartActivity;
import com.score.pics.client.start.StartPlace;

public class PhoneActivityMapper implements ActivityMapper {

	private RegisterMobileActivity rma;
	private LoginActivity la;
	private StartActivity sa;
	private ClientFactory clientFactory;
	
	
	public PhoneActivityMapper(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}
	
	@Override
	public Activity getActivity(Place place) {
		if(place instanceof RegisterPlace){
			return getRegisterMobileActivity();
		}
		if(place instanceof LoginPlace){
			return getLoginActivity();
		}
		if(place instanceof StartPlace){
			return getStartActivity();
		}
		if(place instanceof SidePlace2){
			return new SideActivity2(clientFactory);
		}
		if(place instanceof SidePlace3){
			return new SideActivity3(clientFactory);
		}
		if(place instanceof SidePlace4){
			return new SideActivity4(clientFactory);
		}
		if(place instanceof SidePlace5){
			return new SideActivity5(clientFactory);
		}
		if(place instanceof PasswordForgottenPlace){
			return new  PasswordForgottenActivity(clientFactory);
		}
		return null;
	}
	
	
	public Activity getRegisterMobileActivity(){
		if(rma == null){
			rma = new RegisterMobileActivity(clientFactory);
		}
		return rma;
	}
	
	public Activity getLoginActivity(){
		if(la == null){
			la = new LoginActivity(clientFactory);
		}
		return la;
	}
	
	public Activity getStartActivity(){
		if(sa == null){
			sa = new StartActivity(clientFactory);
		}
		return sa;
	}

}
