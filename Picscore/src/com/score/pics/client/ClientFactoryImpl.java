package com.score.pics.client;

import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.score.pics.client.login.LoginView;
import com.score.pics.client.login.LoginViewImpl;
import com.score.pics.client.register.RegisterMobileView;
import com.score.pics.client.register.RegisterMobileViewImpl;
import com.score.pics.client.side2.SideView2;
import com.score.pics.client.side2.SideViewImpl2;
import com.score.pics.client.side3.SideView3;
import com.score.pics.client.side3.SideViewImpl3;
import com.score.pics.client.start.StartView;
import com.score.pics.client.start.StartViewImpl;

public class ClientFactoryImpl implements ClientFactory {
	
	private PlaceController placeController;
	private EventBus eventBus;
	private RegisterMobileView registerMV;
	private LoginView lv;
	private StartView sv;
	private SideView2 sv2;
	private SideView3 sv3;
	private String username;
	private String actualSide;
	
	public ClientFactoryImpl() {
		eventBus = new SimpleEventBus();
		placeController = new PlaceController(eventBus);
	}


	@Override
	public EventBus getEventBus() {
		return eventBus;
	}

	@Override
	public PlaceController getPlaceController() {
		return placeController;
	}
	
	@Override
	public RegisterMobileView getRegisterMobileView() {
		if(registerMV==null){
			registerMV = new RegisterMobileViewImpl();
		}
		return registerMV;
	}


	@Override
	public LoginView getLoginViewImpl() {
		if(lv==null){
			lv = new LoginViewImpl();
		}
		return lv;
	}


	@Override
	public StartView getStartViewImpl() {
		if(sv==null){
			sv = new StartViewImpl();
		}
		return sv;
	}


	@Override
	public String getUserName() {
		return username;
	}


	@Override
	public void setUserName(String text) {
		this.username= text;
	}


	@Override
	public SideView2 getSide2ViewImpl() {
		if(sv2==null){
			sv2 = new SideViewImpl2();
		}
		return sv2;
	}
	
	@Override
	public SideView3 getSide3ViewImpl() {
		if(sv3==null){
			sv3 = new SideViewImpl3();
		}
		return sv3;
	}


	@Override
	public String getSideLocation() {
		return actualSide;
	}


	@Override
	public void setSideLocation(String text) {
		actualSide = text;
	}
	
}
