package com.score.pics.client.side3;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

/*
 * Place: 
 * */

public class SidePlace3  extends Place{

	private final String token;
	
	public SidePlace3(String id) {
		this.token = id;
	}
	
	public String getToken(){
		return token;
	}
	
	public static class SidePlaceTokenizer3 implements PlaceTokenizer<SidePlace3>{

		@Override
		public SidePlace3 getPlace(String id) {
			return new SidePlace3(id);
		}

		@Override
		public String getToken(SidePlace3 place) {
			return place.getToken();
		}
	}
}
