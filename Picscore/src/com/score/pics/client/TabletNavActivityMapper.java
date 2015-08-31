package com.score.pics.client;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.score.pics.client.login.LoginPlace;
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
		if(place instanceof StartPlace){
			return new StartActivity(clientFactory);
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
		return null;
	}
	
	public Activity getTabletNavActivity(){
		if(tna==null){
			tna = new TabletNavActivity(clientFactory);
		}
		return tna;
	}
	

}
