package com.score.pics.client;

import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;
import com.score.pics.client.login.LoginView;
import com.score.pics.client.register.RegisterMobileView;
import com.score.pics.client.side2.SideView2;
import com.score.pics.client.side3.SideView3;
import com.score.pics.client.start.StartView;

public interface ClientFactory {

	public EventBus getEventBus();

	public PlaceController getPlaceController();

	public RegisterMobileView getRegisterMobileView();
	
	public LoginView getLoginViewImpl();

	public StartView getStartViewImpl();
	
	public SideView2 getSide2ViewImpl();
	
	public SideView3 getSide3ViewImpl();
	
	public String getUserName();
	
	public void setUserName(String text);
	
	
	
	public String getSideLocation();
	
	public void setSideLocation(String text);
	
	
}
