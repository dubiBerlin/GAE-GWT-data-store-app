package com.score.pics.client.side2;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

/*
 * Place: 
 * */

public class SidePlace2  extends Place{

	private final String token;
	
	public SidePlace2(String id) {
		this.token = id;
	}
	
	public String getToken(){
		return token;
	}
	
	public static class SidePlaceTokenizer2 implements PlaceTokenizer<SidePlace2>{

		@Override
		public SidePlace2 getPlace(String id) {
			return new SidePlace2(id);
		}

		@Override
		public String getToken(SidePlace2 place) {
			return place.getToken();
		}
	}
}
