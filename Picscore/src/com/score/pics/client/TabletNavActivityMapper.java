package com.score.pics.client;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.score.pics.client.login.LoginPlace;
import com.score.pics.client.register.RegisterPlace;
import com.score.pics.client.tabletNavBlank.TabletNavActivity;

public class TabletNavActivityMapper implements ActivityMapper {


	private final ClientFactory clientFactory;
	private TabletNavActivity tna;
	
	
	public TabletNavActivityMapper(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}
	
	

	@Override
	public Activity getActivity(Place place) {
		if(place instanceof LoginPlace || place instanceof RegisterPlace){
			return getTabletNavActivity();
		}
		return null;
	}
	
	public Activity getTabletNavActivity(){
		if(tna==null){
			tna = new TabletNavActivity(clientFactory);
		}
		return tna;
	}
	
	

}
