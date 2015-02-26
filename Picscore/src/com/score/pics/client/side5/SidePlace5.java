package com.score.pics.client.side5;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

/*
 * Place: 
 * */

public class SidePlace5  extends Place{

	private final String token;
	
	public SidePlace5(String id) {
		this.token = id;
	}
	
	public String getToken(){
		return token;
	}
	
	public static class SidePlaceTokenizer5 implements PlaceTokenizer<SidePlace5>{

		@Override
		public SidePlace5 getPlace(String id) {
			return new SidePlace5(id);
		}

		@Override
		public String getToken(SidePlace5 place) {
			return place.getToken();
		}
	}
}
